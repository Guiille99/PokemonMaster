package com.pokemonmaster.pokeapi.resources.pokedex;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Description;
import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.locations.Region;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pokedex implements PokeApiResource {
    private Integer id;
    private String name;
    private boolean isMainSeries;
    private List<Description> descriptions;
    private List<Name> names;
    private List<PokemonEntry> pokemonEntries;
    private NamedApiResource<Region> region;
    private List<NamedApiResource<VersionGroup>> versionGroups;
}
