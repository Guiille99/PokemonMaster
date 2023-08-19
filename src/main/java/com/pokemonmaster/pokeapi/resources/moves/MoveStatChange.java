package com.pokemonmaster.pokeapi.resources.moves;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.stats.Stat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveStatChange {
    private Integer change;
    private NamedApiResource<Stat> stat;
}
