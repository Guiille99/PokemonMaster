package com.pokemonmaster.pokeapi.resources.generations;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.moves.Move;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.Ability;
import com.pokemonmaster.pokeapi.resources.pokemonSpecies.PokemonSpecies;
import com.pokemonmaster.pokeapi.resources.types.Type;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Generation implements PokeApiResource  {
  private Integer id;
	private String name;
	private List<NamedApiResource<Ability>> abilities;
	private List<Name> names;
	private List<NamedApiResource<Move>> moves;
	private List<NamedApiResource<PokemonSpecies>> pokemonSpecies;
	private List<NamedApiResource<Type>> types;
	private List<NamedApiResource<VersionGroup>> versionGroups;
}
