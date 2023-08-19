package com.pokemonmaster.pokeapi.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerboseEffect {
    private String effect;
    private String shortEffect;
    private NamedApiResource<Language> language;
}
