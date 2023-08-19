package com.pokemonmaster.pokeapi.resources.pokemon.genders;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Gender {
    private Integer id;
    private String name;
    private List<PokemonSpeciesGender> pokemonSpeciesDetails;
    private List<NamedApiResource<PokemonSpecies>> requiredForEvolution;
}
