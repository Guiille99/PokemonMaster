package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.types.Type;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonType {
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("type")
    private NamedApiResource<Type> type;
}
