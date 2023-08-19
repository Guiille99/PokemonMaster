package com.pokemonmaster.pokeapi.resources;

import com.pokemonmaster.pokeapi.resources.version.Version;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VersionGameIndex {
    private Integer gameIndex;
    private NamedApiResource<Version> version;
}
