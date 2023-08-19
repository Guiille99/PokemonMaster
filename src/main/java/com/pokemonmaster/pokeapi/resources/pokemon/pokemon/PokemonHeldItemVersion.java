package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.version.Version;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonHeldItemVersion {
    private NamedApiResource<Version> version;
    private Integer rarity;
}
