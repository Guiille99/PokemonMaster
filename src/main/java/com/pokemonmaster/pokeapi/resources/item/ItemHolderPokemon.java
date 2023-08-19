package com.pokemonmaster.pokeapi.resources.item;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemHolderPokemon {
    private NamedApiResource<Pokemon> pokemon;
    private List<ItemHolderPokemonVersionDetail> versionDetails;
}
