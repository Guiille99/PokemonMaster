package com.pokemonmaster.pokeapi.resources.moves;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Description;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveCategory implements PokeApiResource {
    private Integer id;
    private String name;
    private List<NamedApiResource<Move>> moves;
    private List<Description> descriptions;
}
