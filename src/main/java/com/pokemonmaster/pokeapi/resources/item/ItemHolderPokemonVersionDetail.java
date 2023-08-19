package com.pokemonmaster.pokeapi.resources.item;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.version.Version;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemHolderPokemonVersionDetail {
    private Integer rarity;
    private NamedApiResource<Version> version;
}
