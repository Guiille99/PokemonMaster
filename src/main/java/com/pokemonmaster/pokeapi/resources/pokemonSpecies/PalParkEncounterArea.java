package com.pokemonmaster.pokeapi.resources.pokemonSpecies;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.locations.PalParkArea;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PalParkEncounterArea {
    private Integer baseScore, rate;
    private NamedApiResource<PalParkArea> area;
}
