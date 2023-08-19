package com.pokemonmaster.pokeapi.resources.pokedex;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonEntry {
    private Integer entryNumber;
    private NamedApiResource<PokemonSpecies> pokemonSpecies;
}
