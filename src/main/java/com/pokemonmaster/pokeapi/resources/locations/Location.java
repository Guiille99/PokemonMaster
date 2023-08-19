package com.pokemonmaster.pokeapi.resources.locations;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.GenerationGameIndex;
import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Location implements PokeApiResource {
    private Integer id;
    private String name;
    private NamedApiResource<Region> region;
    private List<Name> names;
    private List<GenerationGameIndex> gameIndices;
    private List<NamedApiResource<LocationArea>> areas;
}
