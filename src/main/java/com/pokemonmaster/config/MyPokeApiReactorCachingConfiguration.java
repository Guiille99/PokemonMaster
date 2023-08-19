package com.pokemonmaster.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.pokemonmaster.pokeapi.PokeApiReactorCachingConfiguration;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
@Import(PokeApiReactorCachingConfiguration.class)
@EnableCaching
public class MyPokeApiReactorCachingConfiguration {
   	@Bean
	public ConnectionProvider connectionProvider() {
	    return ConnectionProvider.builder("Auto refresh & no connection limit")
		    .maxIdleTime(Duration.ofSeconds(10))
		    .maxConnections(500)
		    .pendingAcquireMaxCount(-1)
		    .build();
	}

	@Bean
	public HttpClient httpClient(ConnectionProvider connectionProvider) {
		return HttpClient.create(connectionProvider)
                  .compress(true)
                  .resolver(DefaultAddressResolverGroup.INSTANCE);
	} 
}
