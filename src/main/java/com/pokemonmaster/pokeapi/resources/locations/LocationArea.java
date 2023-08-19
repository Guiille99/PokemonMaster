package com.pokemonmaster.pokeapi.resources.locations;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LocationArea implements PokeApiResource {
    private Integer id;
    private String name;
    private Integer gameIndex;
    private List<EncounterMethodRate> encounterMethodRates;
    private NamedApiResource<Location> location;
}
