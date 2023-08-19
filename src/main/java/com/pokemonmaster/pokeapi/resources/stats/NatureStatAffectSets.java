package com.pokemonmaster.pokeapi.resources.stats;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.natures.Nature;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NatureStatAffectSets {
    private List<NamedApiResource<Nature>> increase, decrease;
}
