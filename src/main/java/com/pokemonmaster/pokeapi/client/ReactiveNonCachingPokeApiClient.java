package com.pokemonmaster.pokeapi.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonStat;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonType;
import com.pokemonmaster.pokeapi.resources.stats.Stat;
import com.pokemonmaster.pokeapi.resources.types.Type;

import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveNonCachingPokeApiClient implements IPokeApiClient {
    private IPokeApiEntityFactory entityFactory;
	@Getter
	public Map<String, String> translateTypes = new HashMap<>();

	
	public ReactiveNonCachingPokeApiClient(IPokeApiEntityFactory entityFactory) {
		this.entityFactory = entityFactory;
		initTranslateTypes();
	}

	private void initTranslateTypes(){
		translateTypes.put("normal", "normal");
		translateTypes.put("fighting", "lucha");
		translateTypes.put("flying", "volador");
		translateTypes.put("steel", "acero");
		translateTypes.put("water", "agua");
		translateTypes.put("bug", "bicho");
		translateTypes.put("dragon", "dragón");
		translateTypes.put("electric", "eléctrico");
		translateTypes.put("ghost", "fantasma");
		translateTypes.put("fire", "fuego");
		translateTypes.put("fairy", "hada");
		translateTypes.put("ice", "hielo");
		translateTypes.put("grass", "planta");
		translateTypes.put("psychic", "psíquico");
		translateTypes.put("rock", "roca");
		translateTypes.put("dark", "siniestro");
		translateTypes.put("ground", "tierra");
		translateTypes.put("poison", "veneno");
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
        pokemon.setName(pokemonAux.getName());
		pokemon.setHeight((double)pokemonAux.getHeight()/(double)10);
		pokemon.setWeight((double)pokemonAux.getWeight()/(double)10);
        pokemon.setAbilities(abilitiesInSpanish(pokemonAux.getAbilities()));
		pokemon.setStats(statsInSpanish(pokemonAux.getStats()));
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

	public List<PokemonStat> statsInSpanish(List<PokemonStat> stats){
		for (PokemonStat stat : stats) {
			Stat statResource = getResource(Stat.class, stat.getStat().getName()).block();
			stat.getStat().setName(statResource.getNames().get(5).getName());
		}
		return stats;
	}

	@Override
	@Async
	public NamedApiResource<Ability> getAbilityInSpanish(NamedApiResource<Ability> habilidad){
		habilidad.setName(getResource(Ability.class, habilidad.getName()).block().getNames().get(5).getName());
		return habilidad;
	}

	@Override
	public int sortByID(String pkId1, String pkId2) {
		return Integer.compare(getIdFromURL(pkId1), getIdFromURL(pkId2));
	}

	private Integer getIdFromURL(String url){
		return Integer.parseInt(url.substring(url.lastIndexOf("/") + 1));
	}

	@Override
	public List<String> getDebilidadesByTipo(NamedApiResource<Type> typeResource) {
		// NamedApiResource<Type> typResourceAux = typeResource;
		// Type tipo = new Type();
		// for (Map.Entry<String, String> typeEntry : translateTypes.entrySet()) {
		// 	if (typeEntry.getValue().equalsIgnoreCase(typResourceAux.getName())) {
		// 		tipo = getResource(Type.class, typeEntry.getKey()).block();
		// 		break;
		// 	}
		// }
		Type tipo = getTypeByUrl(typeResource.getUrl());
		List<String> debilidades = new ArrayList<>();
		for (NamedApiResource<Type> weakness : tipo.getDamageRelations().getDoubleDamageFrom()) {
			debilidades.add(translateTypes.get(weakness.getName()));
		}
		return debilidades;
	}

	@Override
	public Type getTypeByUrl(String url) {
		String[] urlParts = url.split("/");
		String typeID = urlParts[urlParts.length - 1];
		return getResource(Type.class, typeID).block();
	}
}
