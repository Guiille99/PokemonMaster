package com.pokemonmaster.pokeapi.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NamedApiResource <T extends PokeApiResource> {
    private String name;
    private String url;
}
