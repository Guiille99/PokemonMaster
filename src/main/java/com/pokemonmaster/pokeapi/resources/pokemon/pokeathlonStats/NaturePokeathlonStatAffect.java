package com.pokemonmaster.pokeapi.resources.pokemon.pokeathlonStats;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.natures.Nature;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NaturePokeathlonStatAffect {
    private Integer maxChange;
    private NamedApiResource<Nature> nature;
}
