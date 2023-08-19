package com.pokemonmaster.pokeapi.resources.contests;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.berry.BerryFlavor;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContestType implements PokeApiResource {
    private Integer id;
    private String name;
    private NamedApiResource<BerryFlavor> berryFlavor;
    private List<ContestName> names; // The name of this contest type listed in different languages.
}
