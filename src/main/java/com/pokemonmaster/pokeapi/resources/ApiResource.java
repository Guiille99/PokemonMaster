package com.pokemonmaster.pokeapi.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResource<T> {
    private String url;
}
