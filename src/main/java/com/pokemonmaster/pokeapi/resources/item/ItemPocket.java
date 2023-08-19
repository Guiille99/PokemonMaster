package com.pokemonmaster.pokeapi.resources.item;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemPocket implements PokeApiResource {
    private Integer id;
    private String name;
    private List<NamedApiResource<ItemCategory>> categories;
    private List<Name> names;
}
