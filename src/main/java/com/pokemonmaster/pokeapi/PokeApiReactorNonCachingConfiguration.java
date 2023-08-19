package com.pokemonmaster.pokeapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PokeApiReactorBaseConfiguration.class)
public class PokeApiReactorNonCachingConfiguration {
    // @Bean
	// public IPokeApiClient pokeApiClient(IPokeApiEntityFactory entityFactory) {
	// 	return new ReactiveNonCachingPokeApiClient(entityFactory);
	// }
}
