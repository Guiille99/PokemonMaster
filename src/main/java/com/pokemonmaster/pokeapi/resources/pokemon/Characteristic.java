package com.pokemonmaster.pokeapi.resources.pokemon;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Description;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.stats.Stat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Characteristic {
    private Integer id;
    private Integer geneModulo;
    private List<Integer> possibleValues;
    private NamedApiResource<Stat> highestStat;
    private List<Description> descriptions;
}
