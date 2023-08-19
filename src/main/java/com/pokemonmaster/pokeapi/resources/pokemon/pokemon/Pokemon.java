package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.VersionGameIndex;
import com.pokemonmaster.pokeapi.resources.pokemonForms.PokemonForm;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pokemon implements PokeApiResource {
    private Integer id;
    private String name;
    private Integer baseExperience;
    private Integer height; // en decimetros
    private boolean isDefault;
    private Integer order;
    private Integer weight; // en hectogramos
    private List<PokemonAbility> abilities;
    private List<NamedApiResource<PokemonForm>> forms;
    private List<VersionGameIndex> gameIndices;
    private List<PokemonHeldItem> heldItmes;
    private String locationAreaEncounters;
    private List<PokemonMove> moves;
    private List<PokemonTypePast> pastTypes;
    private PokemonSprites sprites;
    private NamedApiResource<PokemonSpecies> species;
    private List<PokemonStat> stats;
    private List<PokemonType> types;
}
