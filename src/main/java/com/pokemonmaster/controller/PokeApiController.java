package com.pokemonmaster.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiClient;
import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.AbilityPokemon;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonType;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;
import com.pokemonmaster.pokeapi.resources.types.Type;
import com.pokemonmaster.pokeapi.resources.types.TypePokemon;

import jakarta.websocket.server.PathParam;

@Controller
public class PokeApiController {
    @Autowired
    private IPokeApiClient pokeApiService;
    private HashMap<Integer, PageQuery> generationRange = new HashMap<>();
    private final Integer PAGE_SIZE = 12;
    private List<NamedApiResource<Pokemon>> pokemonList = new ArrayList<>();
    private boolean isFinish = false;

    @GetMapping("/")
    public String home(Model model){
        ArrayList<Pokemon> pokemonDestacados = new ArrayList<>();
        for (int index = 0; index < 8; index++) {
            pokemonDestacados.add(pokeApiService.getRandomPokemon());
        }
        for (Pokemon pokemon : pokemonDestacados) {
			System.out.println(pokemon.getSprites().getOther().getOfficialArtwork().getFrontDefault());
		}
        model.addAttribute("title", "PokeMaster");
        model.addAttribute("nGeneraciones", pokeApiService.getNGeneraciones());
        model.addAttribute("pokemonDestacados", pokemonDestacados);
        return "index";
    }

    @PostMapping("/search")
    public String filterByNameOrId(@PathParam(value = "idOrName") String idOrName, Model model){
        return "redirect:/pokemon/"+idOrName;
    }

    @GetMapping("/generacion/{generacion}")
    public String showGeneraciones(@PathVariable(value = "generacion")Integer generacion, @RequestParam(defaultValue = "0") int page, Model model){
        
        if (this.generationRange.isEmpty()) {
            initGenerationRange();
        }
        int totalPaginas = (int) Math.ceil((double)generationRange.get(generacion).getLimit() / PAGE_SIZE);
        model.addAttribute("generacion", generacion);
        model.addAttribute("nGeneraciones", pokeApiService.getNGeneraciones());
        model.addAttribute("totalPaginas", totalPaginas);
        
        System.out.println("Generación: "+ generacion);
        return "generaciones/showGeneracion";
    }

    @GetMapping("/pokedex")
    public String showPokedex(Model model){
        model.addAttribute("nGeneraciones", pokeApiService.getNGeneraciones());
        return "pokedex/showPokedex";
    }

    @GetMapping("/pokemon/{pokemon}")
    public String showPokemon(@PathVariable(value = "pokemon") String idOrName, Model model){
        List<String> debilidades = new ArrayList<>();
        Pokemon pokemon = new Pokemon();
        PokemonSpecies pokemonSpecies = new PokemonSpecies();
        try {
            pokemon = pokeApiService.getPokemonDto(idOrName);
            pokemonSpecies = pokeApiService.getResource(PokemonSpecies.class, idOrName).block();
        } catch (Exception e) {
            pokemon = null;
        }

        if (pokemon != null) {
            for (PokemonType type : pokemon.getTypes()) {
                debilidades.addAll(pokeApiService.getDebilidadesByTipo(type.getType()));
                debilidades = debilidades.stream()
                .filter(debilidad -> !debilidad.equalsIgnoreCase(type.getType().getName())) //Elimina las debilidades que coincidad con su tipo
                .distinct() //Elimina repetidos
                .collect(Collectors.toList());
            }
        }

        model.addAttribute("pokemon", pokemon);
        model.addAttribute("pokemonSpecies", pokemonSpecies);
        model.addAttribute("nGeneraciones", pokeApiService.getNGeneraciones());
        model.addAttribute("debilidades", debilidades);
        return "pokemon/showPokemon";
    }

    @PostMapping ("/api/pokedex/filter")
    public ResponseEntity<Map<String, Object>> getPokemonFilter(@PathParam(value = "tipos") String tipos, 
    @PathParam(value = "debilidades") String debilidades, @PathParam(value = "nombre") String nombre, @PathParam(value = "habilidad") String habilidad, @RequestParam(defaultValue = "0") Integer page){
        if (CollectionUtils.isNotEmpty(pokemonList)) {
            pokemonList.clear();
        }
        if (StringUtils.isEmpty(tipos) && StringUtils.isEmpty(debilidades) && StringUtils.isEmpty(habilidad) && StringUtils.isEmpty(nombre)) {
            return getPokemonByGeneration(1);
        }
        Set<NamedApiResource<Pokemon>> pokemonSet = new HashSet<>();
        List<NamedApiResource<Pokemon>> pokemonType = null; // Lista de Pokemon de los tipos filtrados
        List<NamedApiResource<Pokemon>> pokemonWeaknessList = null; // Lista con los Pokemon débiles a los tipos filtrados
        List<NamedApiResource<Pokemon>> pokemonAbilitiesList = null; // Lista con los Pokemon con las habilidades filtradas
        // Almacenamos todos los pokemons de los tipos filtrados
        if (StringUtils.isNotEmpty(tipos)) {
            pokemonType = new ArrayList<>();
            String[] types = tipos.split(",");
            for (String typeName : types) {
                // List<NamedApiResource<Pokemon>> pkAux = new ArrayList<>();
                Type tipo = pokeApiService.getResource(Type.class, typeName).block();
                for (TypePokemon pk : tipo.getPokemon()) {
                    pokemonType.add(pk.getPokemon());
                }
            }
            if (types.length > 1) {
                pokemonType = retainRepeatedPokemon(pokemonType, types.length);
            }
        } 

        if (StringUtils.isNotEmpty(debilidades)) {
            pokemonWeaknessList = new ArrayList<>();
            String[] types = debilidades.split(",");
            List<String> weaknesess = new ArrayList<>();
            // Almacenamos tipos que sean débiles a los tipos filtrados
            for (String typeName : types) {
                Type tipo = pokeApiService.getResource(Type.class, typeName).block();
                for (NamedApiResource<Type> type : tipo.getDamageRelations().getDoubleDamageTo()) {
                    weaknesess.add(type.getName());
                }
            }
            // Almacenamos los pokemon
            for (String weakness : weaknesess) {
                Type tipo = pokeApiService.getResource(Type.class, weakness).block();
                for (TypePokemon pk : tipo.getPokemon()) {
                    pokemonWeaknessList.add(pk.getPokemon());
                }
            }
            if (types.length > 1) {
                pokemonWeaknessList = retainRepeatedPokemon(pokemonWeaknessList, types.length);
            }
        }

        // Se filtra por habilidad
        if (StringUtils.isNotEmpty(habilidad)) {
            pokemonAbilitiesList = new ArrayList<>();
            Ability ability = pokeApiService.getResource(Ability.class, habilidad.toLowerCase().replaceAll(" ", "-")).block();
            for (AbilityPokemon pk : ability.getPokemon()) {
                pokemonAbilitiesList.add(pk.getPokemon());
            }
        }

        pokemonList = encontrarElementosComunes(new ArrayList<>(Arrays.asList(pokemonType, pokemonWeaknessList, pokemonAbilitiesList)));

        pokemonSet = new HashSet<>(pokemonList);
        if (CollectionUtils.isNotEmpty(pokemonList)) {
            // Si se filtra por nombre habiendo filtrado también por tipo y/o debilidad
            if (StringUtils.isNotEmpty(nombre)) {
                pokemonSet = pokemonSet.stream()
                .filter(pokemon -> pokemon.getName().contains(nombre))
                .collect(Collectors.toSet());
            }
        } else{
            NamedApiResourceList<Pokemon> pokemonsResource = pokeApiService.getResource(Pokemon.class, new PageQuery(100000, 0)).block(); 
            if (StringUtils.isNotEmpty(nombre)) {
                pokemonSet = pokemonsResource.getResults().stream()
                .filter(pokemon -> pokemon.getName().contains(nombre))
                .collect(Collectors.toSet());
            }
        }
        pokemonList = new ArrayList<>(pokemonSet);
        Comparator<NamedApiResource<Pokemon>> comparator = Comparator.comparingInt(pokemon -> {
            return getIdFromURL(pokemon.getUrl());
        });
        Collections.sort(pokemonList, comparator);
        eliminaPokemonVariantes();
        // Almacenamos los pokemon
        return ResponseEntity.ok(loadMorePokemon(page));
    }

    @GetMapping ("/api/pokedex/abilities")
    public ResponseEntity<List<NamedApiResource<Ability>>> getAbilities(){
        Comparator<NamedApiResource<Ability>> comparator = Comparator.comparing(ability -> ability.getName());
        List<NamedApiResource<Ability>> abilities = pokeApiService.getResource(Ability.class, new PageQuery(358, 0)).block().getResults();
        Collections.sort(abilities, comparator);
        return ResponseEntity.ok(abilities);
        
    } 

    @GetMapping("/api/generacion-paginate/{generacion}")
    public ResponseEntity<List<NamedApiResource<Pokemon>>> getPokemonByGenerationPaginate(@PathVariable(value = "generacion")Integer generacion, @RequestParam(defaultValue = "0") int page){
        if (this.generationRange.isEmpty()) {
            initGenerationRange();
        }
        PageQuery pageQuery;
        int totalPaginas = (int) Math.ceil((double)generationRange.get(generacion).getLimit() / PAGE_SIZE);
        Integer limit = generationRange.get(generacion).getLimit();
        Integer offset = generationRange.get(generacion).getOffset();

        if (page > 0) {
            page--;
        }
        if (page + 1 == 1) {
            pageQuery = new PageQuery(PAGE_SIZE, offset);
        }
        else if (page + 1 == totalPaginas) {
            Integer diff = (limit + offset) - (offset + PAGE_SIZE * page);
            pageQuery = new PageQuery(diff, offset + PAGE_SIZE * page);
        } else{
            pageQuery = new PageQuery(PAGE_SIZE, offset + PAGE_SIZE * page);
        }
        // NamedApiResourceList<Pokemon> pk = pokeApiService.getResource(Pokemon.class, pageQuery).block();
        this.pokemonList = pokeApiService.getResource(Pokemon.class, pageQuery).block().getResults();
        return ResponseEntity.ok(this.pokemonList);
    }
    
    
    @PostMapping("/api/generacion")
    public ResponseEntity<Map<String, Object>> getPokemonByGeneration(@PathParam(value = "generacion") Integer generacion){
        if (this.generationRange.isEmpty()) {
            initGenerationRange();
        }
        Integer limit = generationRange.get(generacion).getLimit();
        Integer offset = generationRange.get(generacion).getOffset();
        PageQuery pageQuery = new PageQuery(limit, offset);
        pokemonList = pokeApiService.getResource(Pokemon.class, pageQuery).block().getResults();
        
        return ResponseEntity.ok(loadMorePokemon(0));
    }
    
    @PostMapping("/my-team/generate")
    public ResponseEntity<List<Pokemon>> generateMyRandomTeam(@RequestBody(required = false) List<PokemonType> pokemonTypes){
        List<Pokemon> myRandomTeam = new ArrayList<>();
        if (CollectionUtils.isEmpty(pokemonTypes)) {
            for (int i = 0; i < 6; i++) {
                myRandomTeam.add(pokeApiService.getRandomPokemon());
            }
        } else{
            List<String> debilidades = new ArrayList<>();
            for (PokemonType type : pokemonTypes) {
                debilidades.addAll(pokeApiService.getDebilidadesByTipo(type.getType()));
            }
            for (int i = 0; i < 6; i++) {
                Pokemon pokemon = new Pokemon();
                boolean isEffective = false;
                do {
                    pokemon = pokeApiService.getRandomPokemon();
                    for (PokemonType type : pokemon.getTypes()) {
                        if (debilidades.contains(type.getType().getName())) {
                            isEffective = true;
                            break;
                        }
                    }
                } while (!isEffective);
                myRandomTeam.add(pokemon);
            }
        }
        return ResponseEntity.ok(myRandomTeam);
    }

    // @PostMapping("/api/my-team-against/generate")
    // public void generateMyRandomAgainstTeam(@RequestBody List<PokemonType> pokemonTypes){
    //     List<Pokemon> myRandomTeam = new ArrayList<>();
    //     for (int i = 0; i < 6; i++) {
            
    //     }
    //     Type tipo = pokeApiService.getTypeByUrl(pokemonTypes.get(0).getType().getUrl());
    //     // return ResponseEntity.ok();
    // }

    @Async
    private void initGenerationRange(){
        int[] nPokemonsByGeneration = {151, 100, 135, 107, 156, 72, 88, 96, 115};
        int offset = 0;
        for (int i = 0; i < nPokemonsByGeneration.length; i++) {
            this.generationRange.put(i+1, new PageQuery(nPokemonsByGeneration[i], offset));
            offset += nPokemonsByGeneration[i];
        }
    }

    // @GetMapping("/api/pokedex/loadMore")
    private Map<String, Object> loadMorePokemon(Integer page){
        Integer start = page * PAGE_SIZE;
        Integer end = start + PAGE_SIZE;
        isFinish = false;
        if (PAGE_SIZE > pokemonList.size() || end > pokemonList.size()) {
            end = pokemonList.size();
            isFinish = true;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("isFinish", isFinish);
        response.put("pokemons", pokemonList.subList(start, end)); 
        return response;
    }

    @GetMapping("/api/pokedex/loadMore")
    private ResponseEntity<Map<String, Object>> loadMorePokemonEndPoint(@PathParam(value = "page") Integer page){
        return ResponseEntity.ok(loadMorePokemon(page));
    }

    private Integer getIdFromURL(String url){
        String[] parts = url.split("/");
		return Integer.parseInt(parts[parts.length - 1]);
	}
    /**
     * Método que deja solo los pokemons repetidos de la lista.
     * Útil para obtener los Pokemon de más de un tipo distinto
     * 
     */
    private List<NamedApiResource<Pokemon>> retainRepeatedPokemon(List<NamedApiResource<Pokemon>> pkList, Integer frecuencia){
        Map<NamedApiResource<Pokemon>, Integer> frequencyMap = pkList.stream()
        .collect(Collectors.groupingBy(pokemon -> pokemon, Collectors.summingInt(e -> 1)));

        return frequencyMap.entrySet()
        .stream()
        .filter(entry -> entry.getValue() == frecuencia)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
    }

    /**
     * Elimina las variantes especiales de los Pokemon
     */
    private void eliminaPokemonVariantes(){
        pokemonList = pokemonList.stream()
        .filter(pokemon ->{
            String name = pokemon.getName();
            boolean valid = true;
            if (name.contains("-") && !name.endsWith("-f") && !name.endsWith("-m")
            && !name.endsWith("origin") && !name.endsWith("altered")) {
                return false;
            }
            return valid;
        })
        .collect(Collectors.toList());
    }

    /**
     * Fusiona todas las listas de los filtros generando una única lista con los Pokemon que coinciden en todas ellas
     */
    private <T> List<T> encontrarElementosComunes(List<List<T>> lists){
        if (CollectionUtils.isEmpty(lists)) {
            return new ArrayList<>();
        }
        List<T> pkComunesList = new ArrayList<>();
        for (List<T> list : lists) {
            if (list != null) {
                pkComunesList = new ArrayList<>(list);
                break;
            }
        }
        // Iterar sobre las listas restantes
        for (int i = 1; i < lists.size(); i++) {
            if (CollectionUtils.isNotEmpty(lists.get(i))) {
                List<T> listaActual = lists.get(i);
                pkComunesList = pkComunesList.stream()
                        .filter(listaActual::contains)
                        .collect(Collectors.toList());
            }
        }
        return pkComunesList;
    }
}
