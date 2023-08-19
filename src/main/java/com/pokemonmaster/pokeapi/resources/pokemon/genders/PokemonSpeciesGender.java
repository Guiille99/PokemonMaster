package com.pokemonmaster.pokeapi.resources.pokemon.genders;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonSpeciesGender {
    private Integer rate;
    private NamedApiResource<PokemonSpecies> pokemonSpecies;
}
