package com.pokemonmaster.pokeapi.resources.berry;

import java.util.List;

import com.fasterxml.jackson.core.sym.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BerryFirmness implements PokeApiResource {
    private Integer id;
    private String name;
    private List<NamedApiResource<Berry>> berries;
    private List<Name> names; // The name of this resource listed in different languages.
}
