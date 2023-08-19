package com.pokemonmaster.pokeapi.resources.pokemon.abilities;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Effect;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AbilityEffectChange {
    private List<Effect> effectEntries;
    private List<NamedApiResource<VersionGroup>> versionGroup;
}
