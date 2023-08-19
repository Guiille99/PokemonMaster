package com.pokemonmaster.pokeapi.resources.stats;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.moves.Move;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveStatAffect {
    private Integer change;
    private NamedApiResource<Move> move;
}
