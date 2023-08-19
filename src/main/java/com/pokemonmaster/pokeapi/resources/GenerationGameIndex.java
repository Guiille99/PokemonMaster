package com.pokemonmaster.pokeapi.resources;

import com.pokemonmaster.pokeapi.resources.generations.Generation;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenerationGameIndex {
    private Integer gameIndex;
    private NamedApiResource<Generation> generation;
}
