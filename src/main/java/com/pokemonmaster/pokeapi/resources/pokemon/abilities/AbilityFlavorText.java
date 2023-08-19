package com.pokemonmaster.pokeapi.resources.pokemon.abilities;

import com.pokemonmaster.pokeapi.resources.Language;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AbilityFlavorText {
    private String flavorText;
    private NamedApiResource<Language> language;
    private NamedApiResource<VersionGroup> versionGroup;
}
