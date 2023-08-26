package com.pokemonmaster.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.util.Named;
import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiClient;
import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonAbility;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonType;
import com.pokemonmaster.pokeapi.resources.types.Type;

@Controller
public class PokeApiController {
    @Autowired
    private IPokeApiClient pokeApiService;

    //Ejemplo para obtener los pokemons de la 1a generacion
    @GetMapping("/")
    public String home(Model model){
        // PageQuery page = new PageQuery(151, 0);
        // NamedApiResourceList<Pokemon> pk = pokeApiService.getResource(Pokemon.class, page).block();
        ArrayList<Pokemon> pokemonDestacados = new ArrayList<>();
        for (int index = 0; index < 8; index++) {
            pokemonDestacados.add(getRandomPokemon());
        }
        for (Pokemon pokemon : pokemonDestacados) {
			System.out.println(pokemon.getSprites().getOther().getOfficialArtwork().getFrontDefault());
		}
        model.addAttribute("title", "PokeMaster");
        model.addAttribute("nGeneraciones", getNGeneraciones());
        model.addAttribute("pokemonDestacados", pokemonDestacados);
        return "index";
    }
    
    private Integer getNGeneraciones(){
        NamedApiResourceList<Generation> generaciones = pokeApiService.getResource(Generation.class).block();
        return generaciones.getCount();
    }
    private Pokemon getRandomPokemon(){
        Pokemon pokemon = new Pokemon();
        Pokemon pokemonAux = pokeApiService.getResource(Pokemon.class, String.valueOf((int) (Math.random()*500)+1)).block();
        pokemon.setId(pokemonAux.getId());
        pokemon.setName(pokemonAux.getName());
        pokemon.setAbilities(abilitiesInSpanish(pokemonAux.getAbilities()));
        pokemon.setTypes(typesInSpanish(pokemonAux.getTypes()));
        pokemon.setSprites(pokemonAux.getSprites());

        return pokemon;
    }

    private List<PokemonType> typesInSpanish(List<PokemonType> types){
        for (PokemonType type : types) {
            Type typeResource = pokeApiService.getResource(Type.class, type.getType().getName()).block();
            type.getType().setName(typeResource.getNames().get(5).getName().toLowerCase());
        }
        return types;
    }
    private List<PokemonAbility> abilitiesInSpanish(List<PokemonAbility> abilities){
        for (PokemonAbility ability : abilities) {
            Ability abilityResource = pokeApiService.getResource(Ability.class, ability.getAbility().getName()).block();
            ability.getAbility().setName(abilityResource.getNames().get(5).getName());
        }
        return abilities;
    }
}
