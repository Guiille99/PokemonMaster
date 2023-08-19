package com.pokemonmaster.pokeapi.resources.pokemon.abilities;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AbilityPokemon {
    private boolean isHidden;
    private Integer slot;
    private NamedApiResource<Pokemon> pokemon;
}
