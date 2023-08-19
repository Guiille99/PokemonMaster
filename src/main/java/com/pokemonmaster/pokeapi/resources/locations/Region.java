package com.pokemonmaster.pokeapi.resources.locations;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.pokedex.Pokedex;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Region implements PokeApiResource{
    private Integer id;
    private String name;
    private List<NamedApiResource<Location>> locations;
    private List<Name> names;
    private NamedApiResource<Generation> generation;
    private List<NamedApiResource<Pokedex>> pokedexes;
    private List<NamedApiResource<VersionGroup>> versionGroups;
}
