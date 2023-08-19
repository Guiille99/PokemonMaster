package com.pokemonmaster.pokeapi.resources.pokemonSpecies;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokedex.Pokedex;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonSpeciesDexEntry {
    private Integer entryNumber;
    private NamedApiResource<Pokedex> pokedex;
}
