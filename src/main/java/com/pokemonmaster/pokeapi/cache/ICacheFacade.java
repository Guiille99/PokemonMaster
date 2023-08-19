package com.pokemonmaster.pokeapi.cache;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICacheFacade {
    <T extends PokeApiResource> Mono<T> get(CacheSpec<T> cacheSpec);
	<T extends PokeApiResource> Flux<T> getMany(List<CacheSpec<T>> cacheSpecs);
}
