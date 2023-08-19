package com.pokemonmaster.pokeapi.client.interfaces;

import java.util.List;
import java.util.function.Supplier;

import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPokeApiClient {
    <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls);
	<T extends PokeApiResource> Mono<T> getResource(Class<T> cls, String idOrName);
	<T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls, PageQuery query);
	<T extends PokeApiResource> Mono<T> followResource(Supplier<NamedApiResource<T>> resourceSupplier, Class<T> cls);
	<T extends PokeApiResource> Flux<T> followResources(Supplier<List<NamedApiResource<T>>> resourcesSupplier, Class<T> cls);
}
