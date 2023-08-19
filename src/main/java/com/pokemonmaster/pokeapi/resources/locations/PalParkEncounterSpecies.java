package com.pokemonmaster.pokeapi.resources.locations;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PalParkEncounterSpecies {
    private Integer baseScore;
    private Integer rate;
    private NamedApiResource<PokemonSpecies> pokemonSpecies;
}
