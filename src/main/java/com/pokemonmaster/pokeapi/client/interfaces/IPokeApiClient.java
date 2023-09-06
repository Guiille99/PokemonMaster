package com.pokemonmaster.pokeapi.client.interfaces;

import java.util.List;
import java.util.function.Supplier;

import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonAbility;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPokeApiClient {
    <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls);
	<T extends PokeApiResource> Mono<T> getResource(Class<T> cls, String idOrName);
	<T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls, PageQuery query);
	<T extends PokeApiResource> Mono<T> followResource(Supplier<NamedApiResource<T>> resourceSupplier, Class<T> cls);
	<T extends PokeApiResource> Flux<T> followResources(Supplier<List<NamedApiResource<T>>> resourcesSupplier, Class<T> cls);
	Integer getNGeneraciones();
	Pokemon getRandomPokemon();
	List<PokemonType> typesInSpanish(List<PokemonType> types);
	List<PokemonAbility> abilitiesInSpanish(List<PokemonAbility> abilities);
	Pokemon getPokemonDto(String idOrName);
	NamedApiResource<Ability> getAbilityInSpanish(NamedApiResource<Ability> habilidad);
	int sortByID(String pkId1, String pkId2);
}
