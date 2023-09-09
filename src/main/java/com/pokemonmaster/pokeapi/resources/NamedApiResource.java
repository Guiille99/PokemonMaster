package com.pokemonmaster.pokeapi.resources;

import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class NamedApiResource <T extends PokeApiResource> {
    private String name;
    private String url;
    
    // @Override
    // public int compareTo(NamedApiResource<T> obj) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    // }
    
    public int compareToByName(NamedApiResource<T> obj) {
        return this.name.compareTo(obj.getName());
    }
}
