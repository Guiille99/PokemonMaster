package com.pokemonmaster.pokeapi.client;

import java.util.Collections;
import java.util.Map;

import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiEndPointRegistry;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

public class MapEndPointRegistry implements IPokeApiEndPointRegistry {
    private Map<Class<?>, String> endpoints;

    public MapEndPointRegistry(Map<Class<?>, String> endpoints) {
        this.endpoints = Collections.unmodifiableMap(endpoints);
	}

	@Override
	public <T extends PokeApiResource> String getEndpoint(Class<T> resource) {
		return endpoints.get(resource);
	}
}
