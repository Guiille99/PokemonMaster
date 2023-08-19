package com.pokemonmaster.pokeapi.resources.moves;

import java.util.List;

import org.springframework.context.annotation.Description;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveTarget implements PokeApiResource {
    private Integer id;
    private String name;
    private List<Description> descriptions;
    private List<Name> names;
    private List<NamedApiResource<Move>> moves;
}
