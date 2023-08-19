package com.pokemonmaster.pokeapi.resources.locations;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PalParkArea implements PokeApiResource {
    private Integer id;
    private String name;
    private List<Name> names;
    private List<PalParkEncounterSpecies> pokemonEncounters;
}
