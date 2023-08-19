package com.pokemonmaster.pokeapi.resources.types;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TypePokemon {
    private Integer slot;
    private NamedApiResource<Pokemon> pokemon;
}
