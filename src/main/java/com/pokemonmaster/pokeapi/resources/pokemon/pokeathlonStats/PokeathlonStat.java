package com.pokemonmaster.pokeapi.resources.pokemon.pokeathlonStats;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokeathlonStat implements PokeApiResource {
    private Integer id;
    private String name;
    private List<Name> names;
    private NaturePokeathlonStatAffectSets affectingNatures;
}
