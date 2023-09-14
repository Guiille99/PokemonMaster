package com.pokemonmaster.pokeapi.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class NamedApiResource <T extends PokeApiResource> {
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    
    public int compareToByName(NamedApiResource<T> obj) {
        return this.name.compareTo(obj.getName());
    }
}
