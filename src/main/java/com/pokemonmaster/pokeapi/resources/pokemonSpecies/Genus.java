package com.pokemonmaster.pokeapi.resources.pokemonSpecies;

import com.pokemonmaster.pokeapi.resources.Language;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Genus {
    private String genus;
    private NamedApiResource<Language> language;
}
