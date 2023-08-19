package com.pokemonmaster.pokeapi.resources.contests;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.FlavorText;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.moves.Move;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class SuperContestEffect {
    private Integer id;
    private Integer appeal;
    private List<FlavorText> flavorTextEntries;
    private List<NamedApiResource<Move>> moves;
}
