package com.pokemonmaster.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiClient;
import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;
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

    @PostMapping ("/api/pokedex/filter")
    public ResponseEntity<Map<String, Object>> getPokemonFilter(@PathParam(value = "tipos") String tipos, 
    @PathParam(value = "debilidades") String debilidades, @PathParam(value = "nombre") String nombre, @PathParam(value = "habilidad") String habilidad, @RequestParam(defaultValue = "0") Integer page){
        if (CollectionUtils.isNotEmpty(pokemonList)) {
            pokemonList.clear();
        }
        Set<NamedApiResource<Pokemon>> pokemonSet = new HashSet<>();
        // Almacenamos todos los pokemons de los tipos filtrados
        if (StringUtils.isNotEmpty(tipos)) {
            String[] types = tipos.split(",");
            for (String typeName : types) {
                Type tipo = pokeApiService.getResource(Type.class, typeName).block();
                for (TypePokemon pk : tipo.getPokemon()) {
                    pokemonList.add(pk.getPokemon());
                }
            }
        } 

        // Almacenamos todos los pokemons débiles a los tipos filtrados
        if (StringUtils.isNotEmpty(debilidades)) {
            String[] weaknesses = debilidades.split(",");
            for (String weakName : weaknesses) {
                Type weakness = pokeApiService.getResource(Type.class, weakName).block();
                for (TypePokemon pk : weakness.getPokemon()) {
                    pokemonList.add(pk.getPokemon());
                }
            }
        }

        if (CollectionUtils.isNotEmpty(pokemonList)) {
            pokemonSet = new HashSet<>(pokemonList);
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
        // Almacenamos los pokemon
        return ResponseEntity.ok(loadMorePokemon(page));
    }

    @GetMapping ("/api/pokedex/abilities")
    public ResponseEntity<NamedApiResourceList<Ability>> getAbilities(){
        return ResponseEntity.ok(pokeApiService.getResource(Ability.class, new PageQuery(358, 0)).block());
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
    
    @GetMapping("/my-team/generate")
    public ResponseEntity<List<Pokemon>> generateMyRandomTeam(){
        List<Pokemon> myRandomTeam = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            myRandomTeam.add(pokeApiService.getRandomPokemon());
        }
        return ResponseEntity.ok(myRandomTeam);
    }

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
        // Integer end = PAGE_SIZE * (page + 1);
        Integer end = start + PAGE_SIZE;
        isFinish = false;
        if (PAGE_SIZE > pokemonList.size() || end > pokemonList.size()) {
            end = pokemonList.size();
            isFinish = true;
        }
        
        // if (start + end > pokemonList.size()) {
        //     end = pokemonList.size();
        // }

        Map<String, Object> response = new HashMap<>();
        response.put("isFinish", isFinish);
        response.put("pokemons", pokemonList.subList(start, end)); 
        // return pokemonList.subList(start, end);
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
}
