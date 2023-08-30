package com.pokemonmaster.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiClient;
import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;

@Controller
public class PokeApiController {
    @Autowired
    private IPokeApiClient pokeApiService;
    private HashMap<Integer, PageQuery> generationRange = new HashMap<>();
    private final Integer PAGE_SIZE = 12;

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

    @GetMapping("/api/generacion/{generacion}")
    public ResponseEntity<NamedApiResourceList<Pokemon>> getPokemonByGenerationData(@PathVariable(value = "generacion")Integer generacion, @RequestParam(defaultValue = "0") int page){
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
        NamedApiResourceList<Pokemon> pk = pokeApiService.getResource(Pokemon.class, pageQuery).block();
        return ResponseEntity.ok(pk);
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
}
