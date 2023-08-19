package com.pokemonmaster.pokeapi.resources.evolutions;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChainLink {
    private boolean isBaby;
    private NamedApiResource<PokemonSpecies> species;
    private List<EvolutionDetail> evolutionDetails;
    private List<ChainLink> evolvesTo;
}
