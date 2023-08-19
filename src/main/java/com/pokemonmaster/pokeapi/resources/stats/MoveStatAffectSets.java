package com.pokemonmaster.pokeapi.resources.stats;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveStatAffectSets {
    private List<MoveStatAffect> increase, decrease;
}
