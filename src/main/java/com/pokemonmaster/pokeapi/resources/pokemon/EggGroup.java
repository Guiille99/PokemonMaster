package com.pokemonmaster.pokeapi.resources.pokemon;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EggGroup implements PokeApiResource {
    private Integer id;
    private String name;
    private List<Name> names;
    private List<NamedApiResource<PokemonSpecies>> pokemonSpecies;
}
