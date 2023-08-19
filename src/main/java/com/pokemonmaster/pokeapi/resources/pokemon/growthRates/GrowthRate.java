package com.pokemonmaster.pokeapi.resources.pokemon.growthRates;

import java.util.List;

import org.springframework.context.annotation.Description;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GrowthRate implements PokeApiResource {
    private Integer id;
    private String name;
    private String formula;
    private List<Description> descriptions;
    private List<GrowthRateExperienceLevel> levels;
    private NamedApiResource<PokemonSpecies> pokemonSpecies;
}
