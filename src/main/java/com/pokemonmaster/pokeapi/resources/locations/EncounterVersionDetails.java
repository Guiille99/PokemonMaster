package com.pokemonmaster.pokeapi.resources.locations;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.version.Version;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EncounterVersionDetails {
    private Integer rate;
    private NamedApiResource<Version> verison;
}
