package com.pokemonmaster.pokeapi.resources.pokemonShapes;

import com.pokemonmaster.pokeapi.resources.Language;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AwesomeName {
    private String awesomeName;
    private NamedApiResource<Language> language;
}
