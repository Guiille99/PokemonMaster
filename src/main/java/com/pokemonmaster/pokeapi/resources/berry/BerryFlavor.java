package com.pokemonmaster.pokeapi.resources.berry;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.contests.ContestType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BerryFlavor implements PokeApiResource {
    private Integer id;
    private String name;
    private List<FlavorBerryMap> berries;
    private NamedApiResource<ContestType> contestType;
    private List<Name> names;
}
