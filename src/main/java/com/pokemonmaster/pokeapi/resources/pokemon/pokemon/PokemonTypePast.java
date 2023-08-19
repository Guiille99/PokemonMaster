package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.generations.Generation;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonTypePast {
    private NamedApiResource<Generation> generation;
    private List<PokemonType> types;
}
