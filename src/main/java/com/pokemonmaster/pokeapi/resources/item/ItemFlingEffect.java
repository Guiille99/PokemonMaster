package com.pokemonmaster.pokeapi.resources.item;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Effect;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemFlingEffect implements PokeApiResource {
    private Integer id;
    private String name;
    private List<NamedApiResource<Item>> items;
    private List<Effect> effectEntries;
}
