package com.pokemonmaster.pokeapi.resources.version;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.utils.Localizable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Version implements PokeApiResource, Localizable {
    private Integer id;
    private String name;
    private List<Name> names;
    private NamedApiResource<VersionGroup> versionGroup;
}
