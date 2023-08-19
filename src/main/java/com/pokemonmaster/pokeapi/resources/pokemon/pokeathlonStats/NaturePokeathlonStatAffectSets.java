package com.pokemonmaster.pokeapi.resources.pokemon.pokeathlonStats;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NaturePokeathlonStatAffectSets {
    private List<NaturePokeathlonStatAffect> increase, decrease;
}
