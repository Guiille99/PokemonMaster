package com.pokemonmaster.pokeapi.resources;

import com.pokemonmaster.pokeapi.resources.version.Version;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlavorText {
    private String flavorText;
    private NamedApiResource<Language> language;
    private NamedApiResource<Version> version;
}
