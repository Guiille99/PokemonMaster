package com.pokemonmaster.pokeapi.resources.evolutions;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.item.Item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EvolutionChain {
    private Integer id;
    private NamedApiResource<Item> item;
    private ChainLink chain;
}
