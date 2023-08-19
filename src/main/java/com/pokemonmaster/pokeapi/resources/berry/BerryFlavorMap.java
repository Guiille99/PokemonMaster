package com.pokemonmaster.pokeapi.resources.berry;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BerryFlavorMap {
    private Integer potency;
    private NamedApiResource<BerryFlavor> flavor;
}
