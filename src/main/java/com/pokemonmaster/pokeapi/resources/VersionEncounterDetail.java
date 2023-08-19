package com.pokemonmaster.pokeapi.resources;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.version.Version;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VersionEncounterDetail {
    private NamedApiResource<Version> version;
    private Integer maxChance;
    private List<Encounter> encounters;
}
