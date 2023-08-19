package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.moves.Move;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonMove {
    private NamedApiResource<Move> move;
    private List<PokemonMoveVersion> versionGropupDetails;
}
