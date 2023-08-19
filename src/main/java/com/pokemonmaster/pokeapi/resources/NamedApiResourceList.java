package com.pokemonmaster.pokeapi.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NamedApiResourceList <T extends PokeApiResource> implements PokeApiResource  {
    private Integer count; // The total number of resources available from this API.
    private String next;  // The URL for the next page in the list.
    private String previous; // The URL for the previous page in the list
    private List<NamedApiResource<T>> results; // A list of named API resources.
    
    @Override
    @JsonIgnore
    public Integer getId() {
        return 0;
    }
    @Override
    @JsonIgnore
    public String getName() {
        return "";
    }
}
