package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.stats.Stat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonStat {
    private NamedApiResource<Stat> stat;
    private Integer effort;
    private Integer baseStat;
}
