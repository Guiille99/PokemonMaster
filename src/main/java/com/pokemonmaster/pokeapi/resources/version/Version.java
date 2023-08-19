package com.pokemonmaster.pokeapi.resources.version;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Version implements PokeApiResource {
    private Integer id;
    private String name;
    private List<Name> names;
    private NamedApiResource<VersionGroup> versionGroup;
}
