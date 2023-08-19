package com.pokemonmaster.pokeapi.client.interfaces;

import com.pokemonmaster.pokeapi.resources.PokeApiResource;

public interface IPokeApiEndPointRegistry {
    <T extends PokeApiResource> String getEndpoint(Class<T> resource);
}
