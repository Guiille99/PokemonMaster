package com.pokemonmaster.pokeapi.resources;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Language implements PokeApiResource {
    private Integer id;
    private String name;
    private boolean official;
    private String iso639;
    private String iso3166;
    private List<Name> names;
}
