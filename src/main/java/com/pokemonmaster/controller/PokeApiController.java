package com.pokemonmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.util.Named;
import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiClient;
import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;

@Controller
public class PokeApiController {
    @Autowired
    private IPokeApiClient pokeApiService;

    //Ejemplo para obtener los pokemons de la 1a generacion
    @GetMapping("/")
    public String home(Model model){
        // PageQuery page = new PageQuery(151, 0);
        // NamedApiResourceList<Pokemon> pk = pokeApiService.getResource(Pokemon.class, page).block();
        model.addAttribute("title", "PokeMaster");
        model.addAttribute("nGeneraciones", getNGeneraciones());
        return "index";
    }
    
    private Integer getNGeneraciones(){
        NamedApiResourceList<Generation> generaciones = pokeApiService.getResource(Generation.class).block();
        return generaciones.getCount();
    }
}
