package com.pokemonmaster.pokeapi.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Effect {
    private String effect;
    private NamedApiResource<Language> language;
}
