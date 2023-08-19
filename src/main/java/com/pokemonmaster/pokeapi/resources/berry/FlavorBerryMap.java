package com.pokemonmaster.pokeapi.resources.berry;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlavorBerryMap {
    private Integer potency;
    private NamedApiResource<Berry> berry;
}
