package com.pokemonmaster.pokeapi.resources;

import com.pokemonmaster.pokeapi.resources.version.Version;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VersionGroupFlavorText {
    private String text;
    private NamedApiResource<Version> verison;
    private NamedApiResource<VersionGroup> versionGroup;
}
