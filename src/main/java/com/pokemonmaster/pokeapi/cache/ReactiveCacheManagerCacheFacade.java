package com.pokemonmaster.pokeapi.cache;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache.ValueWrapper;
// import org.springframework.boot.autoconfigure.cache.CacheProperties.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

public class ReactiveCacheManagerCacheFacade implements ICacheFacade {
    private Logger LOG = LogManager.getLogger(ReactiveCacheManagerCacheFacade.class);
	
	private CacheManager cacheManager;
	
	public ReactiveCacheManagerCacheFacade(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public <T extends PokeApiResource> Mono<T> get(CacheSpec<T> cacheSpec) {
		return getOrCache(cacheSpec.getType(), cacheSpec.getKey(), cacheSpec.getMonoSupplier());
	}

	@Override
	public <T extends PokeApiResource> Flux<T> getMany(List<CacheSpec<T>> cacheSpecs) {
		List<Mono<T>> resourceMonos = cacheSpecs.stream()
				.map(this::get)
				.collect(Collectors.toList());
		
		return Flux.merge(resourceMonos);
	}
	
	private <T extends PokeApiResource> Mono<T> getOrCache(Class<T> cls, String resourceName, Supplier<Mono<T>> onCacheMiss) {
		com.github.benmanes.caffeine.cache.Cache<String, Mono<T>> cache = Caffeine.newBuilder().build(); // Create a Caffeine cache

        return Mono.defer(() -> {
            Mono<T> cachedValue = cache.getIfPresent(resourceName); // Check if the value is already cached
            if (cachedValue != null) {
                return cachedValue; // Return the cached value
            } else {
                return onCacheMiss.get() // Execute the cache miss supplier
                        .doOnNext(value -> cache.put(resourceName, Mono.just(value))); // Cache the value
            }
        });
	}
	
	private <T extends PokeApiResource> Mono<Signal<? extends T>> checkCache(Class<T> cls, String key) {
		String cacheName = getCacheNameForClassResource(cls);
		Optional<Signal<? extends T>> resourceFromCache = Optional.ofNullable(cacheManager.getCache(cacheName))
				.map(cache -> cache.get(key))
				.map(ValueWrapper::get)
				.map(Signal.class::cast);

		return Mono.<Signal<? extends T>>justOrEmpty(resourceFromCache);
	}
	
	private <T extends PokeApiResource> Mono<Void> writeToCache(Class<T> cls, String key, Signal<? extends T> value) {
		String cacheName = getCacheNameForClassResource(cls);
		Consumer<Cache> writeToCache = cache -> cache.put(key, value);
		Runnable logCacheFailure = () -> LOG.warn("Cache '{}' does not exist. Could not cache PokeApi resource. Please ensure cache '{}' exists or allow lazy creation of caches.", 
				cacheName,
				cacheName);

		return Mono.fromRunnable(() -> {
			Optional.ofNullable(cacheManager.getCache(cacheName))
				.ifPresentOrElse(writeToCache, logCacheFailure);
		});
	}

	private <T extends PokeApiResource> String getCacheNameForClassResource(Class<T> resourceClass) {
		return resourceClass.getName();
	}
    
}