package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.types.Type;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonFormType {
    private Integer slot;
    private NamedApiResource<Type> type;
}
