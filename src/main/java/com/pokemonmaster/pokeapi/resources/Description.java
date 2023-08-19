package com.pokemonmaster.pokeapi.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Description {
    private String description;
    private NamedApiResource<Language> language;
}
