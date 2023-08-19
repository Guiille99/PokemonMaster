package com.pokemonmaster.pokeapi.resources.locations;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.VersionEncounterDetail;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class PokemonEncounter {
    private NamedApiResource<Pokemon> pokemon;
    private List<VersionEncounterDetail> versionDetails;
}
