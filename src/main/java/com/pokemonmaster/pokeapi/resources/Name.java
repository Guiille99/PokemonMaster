package com.pokemonmaster.pokeapi.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Name {
    private String name; // The localized name for an API resource in a specific language.
    private Language language;
}
