package com.pokemonmaster.pokeapi.resources.contests;

import com.pokemonmaster.pokeapi.resources.Language;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContestName {
    private String name;
    private String color;
    private NamedApiResource<Language> language;
}
