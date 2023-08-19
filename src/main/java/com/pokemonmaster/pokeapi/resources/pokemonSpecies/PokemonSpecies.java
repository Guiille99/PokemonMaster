package com.pokemonmaster.pokeapi.resources.pokemonSpecies;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.ApiResource;
import com.pokemonmaster.pokeapi.resources.Description;
import com.pokemonmaster.pokeapi.resources.FlavorText;
import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.evolutions.EvolutionChain;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.pokemon.EggGroup;
import com.pokemonmaster.pokeapi.resources.pokemon.PokemonHabitat;
import com.pokemonmaster.pokeapi.resources.pokemon.growthRates.GrowthRate;
import com.pokemonmaster.pokeapi.resources.pokemonShapes.PokemonShape;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonSpecies implements PokeApiResource {
    private Integer id;
    private String name;
    private Integer order;
    private Integer genderRate;
    private Integer captureRate;
    private Integer baseHapiness;
    private boolean isBaby;
    private boolean isLegendary;
    private boolean isMythical;
    private Integer hatchCounter;
    private boolean hasGenderDifferences;
    private boolean formsSwitchable;
    private NamedApiResource<GrowthRate> growthRate;
    private List<PokemonSpeciesDexEntry> pokedexNumbers;
    private List<NamedApiResource<EggGroup>> eggGroups;
    private NamedApiResource<PokemonShape> shape;
    private NamedApiResource<PokemonSpecies> evolvesFromSpecies;
    private ApiResource<EvolutionChain> evolutionChain;
    private NamedApiResource<PokemonHabitat> habitat;
    private NamedApiResource<Generation> generation;
    private List<Name> names;
    private List<PalParkEncounterArea> palParkEncounters;
    private List<FlavorText> flavorText;
    private List<Description> formDescriptions;
    private List<Genus> genera;
    private List<PokemonSpeciesVariety> varieties;


}
