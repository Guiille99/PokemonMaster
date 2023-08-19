package com.pokemonmaster.pokeapi.resources.machines;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.item.Item;
import com.pokemonmaster.pokeapi.resources.moves.Move;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Machine {
    private Integer id;
    private NamedApiResource<Item> item;
    private NamedApiResource<Move> move;
    private NamedApiResource<VersionGroup> versionGroup;
}
