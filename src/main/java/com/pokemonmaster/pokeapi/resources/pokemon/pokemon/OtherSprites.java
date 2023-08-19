package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class OtherSprites {
    @JsonProperty("dream_world")
    private PokemonSprites dreamWorld;

    @JsonProperty("home")
    private PokemonSprites home;

    @JsonProperty("official-artwork")
    private PokemonSprites officialArtwork;
}
