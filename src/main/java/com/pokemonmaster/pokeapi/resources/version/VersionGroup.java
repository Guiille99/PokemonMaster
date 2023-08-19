package com.pokemonmaster.pokeapi.resources.version;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.locations.Region;
import com.pokemonmaster.pokeapi.resources.moves.MoveLearnMethod;
import com.pokemonmaster.pokeapi.resources.pokedex.Pokedex;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VersionGroup implements PokeApiResource {
    private Integer id;
    private String name;
    private Integer order;
    private NamedApiResource<Generation> generation;
    private List<NamedApiResource<MoveLearnMethod>> moveLearnMethods;
    private List<NamedApiResource<Pokedex>> podexes;
    private List<NamedApiResource<Region>> regions;
    private List<NamedApiResource<Version>> versions;
}
