package com.pokemonmaster.pokeapi.client.interfaces;

import java.util.List;

import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPokeApiEntityFactory {
    <T extends PokeApiResource> Mono<T> getResource(Class<T> resourceClass, String nameOrId);
	<T extends PokeApiResource> Mono<NamedApiResourceList<T>> getBaseResource(Class<T> resourceClass);
	<T extends PokeApiResource> Mono<NamedApiResourceList<T>> getBaseResource(Class<T> resourceClass, PageQuery query);
	<T extends PokeApiResource> Mono<T> getNamedResource(NamedApiResource<T> resource, Class<T> resourceClass);
	<T extends PokeApiResource> Flux<T> getNamedResources(List<NamedApiResource<T>> resources, Class<T> resourceClass);
}
