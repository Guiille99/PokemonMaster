package com.pokemonmaster.pokeapi.resources.moves;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.VerboseEffect;
import com.pokemonmaster.pokeapi.resources.types.Type;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PastMoveStatValues {
    private Integer accuracy;
    private Integer effectChance;
    private Integer power;
    private Integer pp;
    private List<VerboseEffect> effectEntries;
    private NamedApiResource<Type> type;
    private NamedApiResource<VersionGroup> versionGroup;
}
