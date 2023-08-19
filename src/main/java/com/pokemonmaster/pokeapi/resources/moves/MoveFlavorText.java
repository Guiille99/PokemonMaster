package com.pokemonmaster.pokeapi.resources.moves;

import com.pokemonmaster.pokeapi.resources.Language;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveFlavorText {
    private String flavorText;
    private NamedApiResource<Language> languague;
    private NamedApiResource<VersionGroup> versionGroup;
}
