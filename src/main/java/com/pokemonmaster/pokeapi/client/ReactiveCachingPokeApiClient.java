package com.pokemonmaster.pokeapi.client;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.pokemonmaster.pokeapi.cache.CacheSpec;
import com.pokemonmaster.pokeapi.cache.ICacheFacade;
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

public class ReactiveCachingPokeApiClient implements IPokeApiClient {
    private IPokeApiEntityFactory entityFactory;	
	private ICacheFacade cacheFacade;

	public ReactiveCachingPokeApiClient(IPokeApiEntityFactory entityFactory, ICacheFacade cacheFacade) {
		this.entityFactory = entityFactory;
		this.cacheFacade = cacheFacade;
	}

	

	@Override
	public <T extends PokeApiResource> Mono<T> getResource(Class<T> cls, String idOrName) {
		CacheSpec<T> cacheSpec = CacheSpec.get(cls, idOrName)
				.orCache(() -> entityFactory.getResource(cls, idOrName));
		
		return cacheFacade.get(cacheSpec);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls) {
		Class<NamedApiResourceList<T>> collectionResourceCalss = (Class<NamedApiResourceList<T>>)(Class<?>)NamedApiResourceList.class;
		CacheSpec<NamedApiResourceList<T>> cacheSpec = CacheSpec.get(collectionResourceCalss, "collection")
				.orCache(() -> entityFactory.getBaseResource(cls));
		
		return cacheFacade.get(cacheSpec);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls, PageQuery query) {
		Class<NamedApiResourceList<T>> collectionResourceCalss = (Class<NamedApiResourceList<T>>)(Class<?>)NamedApiResourceList.class;
		String key = String.format("collection-offset%d-limit%d", query.getOffset(), query.getLimit());
		CacheSpec<NamedApiResourceList<T>> cacheSpec = CacheSpec.get(collectionResourceCalss, key)
				.orCache(() -> entityFactory.getBaseResource(cls, query));
		
		return cacheFacade.get(cacheSpec);
	}

	@Override
	public <T extends PokeApiResource> Mono<T> followResource(Supplier<NamedApiResource<T>> resourceSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourceSupplier)
			.map(resource -> resourceToCacheSpec(resource, cls))
			.flatMap(cacheFacade::get);
	}

	@Override
	public <T extends PokeApiResource> Flux<T> followResources(Supplier<List<NamedApiResource<T>>> resourcesSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourcesSupplier)
			.map(resources -> resourcesToCacheSpecs(resources, cls))
			.flatMapMany(cacheFacade::getMany);
	}
	
	private <T extends PokeApiResource> CacheSpec<T> resourceToCacheSpec(NamedApiResource<T> resource, Class<T> cls) {
		return CacheSpec.get(cls, resource.getName())
				.orCache(() -> entityFactory.getNamedResource(resource, cls));
	}
	
	private <T extends PokeApiResource> List<CacheSpec<T>> resourcesToCacheSpecs(List<NamedApiResource<T>> resources, Class<T> cls) {
		return resources.stream()
            .map(resource -> resourceToCacheSpec(resource, cls))
            .collect(Collectors.toList());
	}



	@Override
	public Pokemon getPokemonDto(String idOrName) {
		Pokemon pokemon = new Pokemon();
		Pokemon pokemonAux = getResource(Pokemon.class, idOrName).block();
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