package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.moves.MoveLearnMethod;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonMoveVersion {
    private NamedApiResource<MoveLearnMethod> moveLearnMethod;
    private NamedApiResource<VersionGroup> versionGroup;
}
