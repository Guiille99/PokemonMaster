package com.pokemonmaster.pokeapi.client;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiEndPointRegistry;
import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiEntityFactory;
import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientEntityFactory implements IPokeApiEntityFactory {
    private WebClient webClient;
	private IPokeApiEndPointRegistry endpointRegistry;

	public WebClientEntityFactory(WebClient webClient, IPokeApiEndPointRegistry endpointRegistry) {
		this.webClient = webClient;
		this.endpointRegistry = endpointRegistry;
	}

	@Override
	public <T extends PokeApiResource> Mono<T> getResource(Class<T> resourceClass, String nameOrId) {
		String endpoint = endpointRegistry.getEndpoint(resourceClass);
		return webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path(endpoint)
						.path("/{id}")
						.build(nameOrId))
				.retrieve()
				.bodyToMono(resourceClass);
	}
	
	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getBaseResource(Class<T> resourceClass) {
		String endpoint = endpointRegistry.getEndpoint(resourceClass);
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path(endpoint).build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<NamedApiResourceList<T>>(){});
	}
	
	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getBaseResource(Class<T> resourceClass, PageQuery query) {
		String endpoint = endpointRegistry.getEndpoint(resourceClass);
		return webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path(endpoint)
						.queryParam("limit", query.getLimit())
						.queryParam("offset", query.getOffset())
						.build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<NamedApiResourceList<T>>(){});
	}
	
	@Override
	public <T extends PokeApiResource> Mono<T> getNamedResource(NamedApiResource<T> resource, Class<T> resourceClass) {
		return webClient.get()
				.uri(URI.create(resource.getUrl()))
				.retrieve()
				.bodyToMono(resourceClass);
	}

	@Override
	public <T extends PokeApiResource> Flux<T> getNamedResources(List<NamedApiResource<T>> resources, Class<T> resourceClass) {
		List<Mono<T>> resourceMonos = resources.stream()
				.map(resource -> getNamedResource(resource, resourceClass))
				.collect(Collectors.toList());
		
		return Flux.merge(resourceMonos);
	}
}
