package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.item.Item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonHeldItem {
    private NamedApiResource<Item> item;
    private List<PokemonHeldItemVersion> versionDetails;
}
