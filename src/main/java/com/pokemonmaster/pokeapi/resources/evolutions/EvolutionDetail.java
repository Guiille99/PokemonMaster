package com.pokemonmaster.pokeapi.resources.evolutions;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.item.Item;
import com.pokemonmaster.pokeapi.resources.locations.Location;
import com.pokemonmaster.pokeapi.resources.moves.Move;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;
import com.pokemonmaster.pokeapi.resources.types.Type;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EvolutionDetail {
    private NamedApiResource<Item> item;
    private NamedApiResource<EvolutionTrigger> trigger;
    private Integer gender;
    private NamedApiResource<Item> heldItem;
    private NamedApiResource<Move> knowMove;
    private NamedApiResource<Type> knowMoveType;
    private NamedApiResource<Location> location;
    private Integer minLevel;
    private Integer minBeauty;
    private Integer minHapiness;
    private Integer minAffection;
    private boolean needsOverworldRain; // Whether or not it must be raining in the overworld to cause evolution this Pokémon species
    private PokemonSpecies partySpecies;
    private NamedApiResource<Type> partyType;
    private Integer relativePhysicalStats;
    private String timeOfDate;
    private NamedApiResource<PokemonSpecies> tradeSpecies;
    private boolean turnUpsideOwn;
}
