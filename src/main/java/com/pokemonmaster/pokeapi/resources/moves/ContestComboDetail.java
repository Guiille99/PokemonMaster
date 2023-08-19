package com.pokemonmaster.pokeapi.resources.moves;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContestComboDetail {
    private List<NamedApiResource<Move>> userBefore;
    private List<NamedApiResource<Move>> userAfter;
}
