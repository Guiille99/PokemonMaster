package com.pokemonmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiClient;

@Controller
public class PokeApiController {
    @Autowired
    private IPokeApiClient pokeApiService;

}
