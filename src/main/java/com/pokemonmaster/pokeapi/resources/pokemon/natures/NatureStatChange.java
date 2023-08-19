package com.pokemonmaster.pokeapi.resources.pokemon.natures;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.pokeathlonStats.PokeathlonStat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NatureStatChange {
    private Integer maxChange;
    private NamedApiResource<PokeathlonStat> pokeathlonStat;
}
