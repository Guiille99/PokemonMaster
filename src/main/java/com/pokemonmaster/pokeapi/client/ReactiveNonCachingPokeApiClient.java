package com.pokemonmaster.pokeapi.client;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.scheduling.annotation.Async;

import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiClient;
import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiEntityFactory;
import com.pokemonmaster.pokeapi.query.PageQuery;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.NamedApiResourceList;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonAbility;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonType;
import com.pokemonmaster.pokeapi.resources.types.Type;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveNonCachingPokeApiClient implements IPokeApiClient {
    private IPokeApiEntityFactory entityFactory;
	
	public ReactiveNonCachingPokeApiClient(IPokeApiEntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls) {
		return entityFactory.getBaseResource(cls);
	}

	@Override
	public <T extends PokeApiResource> Mono<T> getResource(Class<T> cls, String idOrName) {
		return entityFactory.getResource(cls, idOrName);
	}

	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls, PageQuery query) {
		return entityFactory.getBaseResource(cls, query);
	}

	@Override
	public <T extends PokeApiResource> Mono<T> followResource(Supplier<NamedApiResource<T>> resourceSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourceSupplier)
				.flatMap(resource -> entityFactory.getNamedResource(resource, cls));
	}

	@Override
	public <T extends PokeApiResource> Flux<T> followResources(Supplier<List<NamedApiResource<T>>> resourcesSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourcesSupplier)
				.flatMapMany(resources -> entityFactory.getNamedResources(resources, cls));
	}

	@Override
	public Pokemon getPokemonDto(String idOrName) {
		Pokemon pokemon = new Pokemon();
		Pokemon pokemonAux = getResource(Pokemon.class, idOrName).block();
		pokemon.setId(pokemonAux.getId());
		pokemon.setId(pokemonAux.getId());
        pokemon.setName(pokemonAux.getName());
        pokemon.setAbilities(abilitiesInSpanish(pokemonAux.getAbilities()));
        pokemon.setTypes(typesInSpanish(pokemonAux.getTypes()));
        pokemon.setSprites(pokemonAux.getSprites());
		return pokemon;
	}


	@Override
 	public Integer getNGeneraciones(){
        NamedApiResourceList<Generation> generaciones = getResource(Generation.class).block();
        return generaciones.getCount();
    }
	@Override
	@Async
    public Pokemon getRandomPokemon(){
        Pokemon pokemon = new Pokemon();
        Pokemon pokemonAux = getResource(Pokemon.class, String.valueOf((int) (Math.random()*500)+1)).block();
        pokemon.setId(pokemonAux.getId());
        pokemon.setName(pokemonAux.getName());
        pokemon.setAbilities(abilitiesInSpanish(pokemonAux.getAbilities()));
        pokemon.setTypes(typesInSpanish(pokemonAux.getTypes()));
        pokemon.setSprites(pokemonAux.getSprites());

        return pokemon;
    }

	@Override
    public List<PokemonType> typesInSpanish(List<PokemonType> types){
        for (PokemonType type : types) {
            Type typeResource = getResource(Type.class, type.getType().getName()).block();
            type.getType().setName(typeResource.getNames().get(5).getName().toLowerCase());
        }
        return types;
    }
	
	@Override
    public List<PokemonAbility> abilitiesInSpanish(List<PokemonAbility> abilities){
        for (PokemonAbility ability : abilities) {
            Ability abilityResource = getResource(Ability.class, ability.getAbility().getName()).block();
            ability.getAbility().setName(abilityResource.getNames().get(5).getName());
        }
        return abilities;
    }
}
