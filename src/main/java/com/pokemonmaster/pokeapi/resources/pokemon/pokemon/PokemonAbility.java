package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonAbility {
    private boolean isHidden;
    private Integer slot;
    private NamedApiResource<Ability> ability;
}
