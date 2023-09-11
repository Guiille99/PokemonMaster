package com.pokemonmaster.pokeapi.resources;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class NamedApiResource <T extends PokeApiResource> {
    private String name;
    private String url;
    
    public int compareToByName(NamedApiResource<T> obj) {
        return this.name.compareTo(obj.getName());
    }
}
