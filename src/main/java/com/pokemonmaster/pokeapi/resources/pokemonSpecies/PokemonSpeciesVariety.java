package com.pokemonmaster.pokeapi.resources.pokemonSpecies;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonSpeciesVariety {
    private boolean isDefault;
    private NamedApiResource<Pokemon> pokemon;
}
