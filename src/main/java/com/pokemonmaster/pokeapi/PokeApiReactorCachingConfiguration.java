package com.pokemonmaster.pokeapi;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.pokemonmaster.pokeapi.cache.ICacheFacade;
import com.pokemonmaster.pokeapi.cache.ReactiveCacheManagerCacheFacade;
import com.pokemonmaster.pokeapi.client.ReactiveCachingPokeApiClient;
import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiClient;
import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiEntityFactory;

@Configuration
@Import(PokeApiReactorBaseConfiguration.class)
public class PokeApiReactorCachingConfiguration {
    	public static final String CACHE_FACADE_BEAN = "pokeApiReactorCacheFacade";
	
	@Bean(CACHE_FACADE_BEAN)
	public ICacheFacade cacheFacade(CacheManager cacheManager) {
		return new ReactiveCacheManagerCacheFacade(cacheManager);
	}
	
	@Bean
	public IPokeApiClient pokeApiClient(IPokeApiEntityFactory entityFactory, ICacheFacade cacheFacade) {
		return new ReactiveCachingPokeApiClient(entityFactory, cacheFacade);
	}
}
