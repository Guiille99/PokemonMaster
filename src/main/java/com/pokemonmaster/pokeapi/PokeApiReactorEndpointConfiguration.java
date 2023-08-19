package com.pokemonmaster.pokeapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.pokemonmaster.pokeapi.client.MapEndPointRegistry;
import com.pokemonmaster.pokeapi.client.interfaces.IPokeApiEndPointRegistry;
import com.pokemonmaster.pokeapi.resources.Language;
import com.pokemonmaster.pokeapi.resources.berry.Berry;
import com.pokemonmaster.pokeapi.resources.berry.BerryFirmness;
import com.pokemonmaster.pokeapi.resources.berry.BerryFlavor;
import com.pokemonmaster.pokeapi.resources.contests.ContestEffect;
import com.pokemonmaster.pokeapi.resources.contests.ContestType;
import com.pokemonmaster.pokeapi.resources.contests.SuperContestEffect;
import com.pokemonmaster.pokeapi.resources.encounters.EncounterCondition;
import com.pokemonmaster.pokeapi.resources.encounters.EncounterConditionValue;
import com.pokemonmaster.pokeapi.resources.encounters.EncounterMethod;
import com.pokemonmaster.pokeapi.resources.evolutions.EvolutionChain;
import com.pokemonmaster.pokeapi.resources.evolutions.EvolutionTrigger;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.item.Item;
import com.pokemonmaster.pokeapi.resources.item.ItemAttribute;
import com.pokemonmaster.pokeapi.resources.item.ItemCategory;
import com.pokemonmaster.pokeapi.resources.item.ItemFlingEffect;
import com.pokemonmaster.pokeapi.resources.item.ItemPocket;
import com.pokemonmaster.pokeapi.resources.locations.Location;
import com.pokemonmaster.pokeapi.resources.locations.LocationArea;
import com.pokemonmaster.pokeapi.resources.locations.Region;
import com.pokemonmaster.pokeapi.resources.machines.Machine;
import com.pokemonmaster.pokeapi.resources.moves.Move;
import com.pokemonmaster.pokeapi.resources.moves.MoveAilment;
import com.pokemonmaster.pokeapi.resources.moves.MoveCategory;
import com.pokemonmaster.pokeapi.resources.moves.MoveDamageClass;
import com.pokemonmaster.pokeapi.resources.moves.MoveLearnMethod;
import com.pokemonmaster.pokeapi.resources.moves.MoveTarget;
import com.pokemonmaster.pokeapi.resources.pokedex.Pokedex;
import com.pokemonmaster.pokeapi.resources.pokemon.Characteristic;
import com.pokemonmaster.pokeapi.resources.pokemon.EggGroup;
import com.pokemonmaster.pokeapi.resources.pokemon.PokemonHabitat;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;
import com.pokemonmaster.pokeapi.resources.pokemon.genders.Gender;
import com.pokemonmaster.pokeapi.resources.pokemon.growthRates.GrowthRate;
import com.pokemonmaster.pokeapi.resources.pokemon.natures.Nature;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonColor;
import com.pokemonmaster.pokeapi.resources.pokemonForms.PokemonForm;
import com.pokemonmaster.pokeapi.resources.pokemonShapes.PokemonShape;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;
import com.pokemonmaster.pokeapi.resources.stats.Stat;
import com.pokemonmaster.pokeapi.resources.types.Type;
import com.pokemonmaster.pokeapi.resources.version.Version;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

public class PokeApiReactorEndpointConfiguration {
    @Bean
	public IPokeApiEndPointRegistry endpointRegistry() {
		Map<Class<?>, String> endpoints = new HashMap<>();
		endpoints.put(Ability.class, "ability");
		endpoints.put(Berry.class, "berry");
		endpoints.put(BerryFirmness.class, "berry-firmness");
		endpoints.put(BerryFlavor.class, "berry-flavor");
		endpoints.put(Characteristic.class, "characteristic");
		endpoints.put(ContestEffect.class, "contest-effect");
		endpoints.put(ContestType.class, "contest-type");
		endpoints.put(EggGroup.class, "egg-group");
		endpoints.put(EncounterCondition.class, "encounter-condition");
		endpoints.put(EncounterConditionValue.class, "encounter-condition-value");
		endpoints.put(EncounterMethod.class, "encounter-method");
		endpoints.put(EvolutionChain.class, "evolution-chain");
		endpoints.put(EvolutionTrigger.class, "evolution-trigger");
		endpoints.put(Gender.class, "gender");
		endpoints.put(Generation.class, "generation");
		endpoints.put(GrowthRate.class, "growth-rate");
		endpoints.put(Item.class, "item");
		endpoints.put(ItemAttribute.class, "item-attribute");
		endpoints.put(ItemCategory.class, "item-category");
		endpoints.put(ItemFlingEffect.class, "item-fling-effect");
		endpoints.put(ItemPocket.class, "item-pocket");
		endpoints.put(Language.class, "language");
		endpoints.put(Location.class, "location");
		endpoints.put(LocationArea.class, "location-area");
		endpoints.put(Machine.class, "machine");
		endpoints.put(Move.class, "move");
		endpoints.put(MoveAilment.class, "move-ailment");
		endpoints.put(MoveCategory.class, "move-category");
		endpoints.put(MoveDamageClass.class, "move-damage-class");
		endpoints.put(MoveLearnMethod.class, "move-learn-method");
		endpoints.put(MoveTarget.class, "move-target");
		endpoints.put(Nature.class, "nature");
		endpoints.put(Pokedex.class, "pokedex");
		endpoints.put(Pokemon.class, "pokemon");
		endpoints.put(PokemonColor.class, "pokemon-color");
		endpoints.put(PokemonForm.class, "pokemon-form");
		endpoints.put(PokemonHabitat.class, "pokemon-habitat");
		endpoints.put(PokemonShape.class, "pokemon-shape");
		endpoints.put(PokemonSpecies.class, "pokemon-species");
		endpoints.put(Region.class, "pokemon-region");
		endpoints.put(Stat.class, "stat");
		endpoints.put(SuperContestEffect.class, "super-contest-effect");
		endpoints.put(Type.class, "type");
		endpoints.put(Version.class, "version");
		endpoints.put(VersionGroup.class, "version-group");
		
		return new MapEndPointRegistry(endpoints);
	}
}
