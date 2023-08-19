package com.pokemonmaster.pokeapi.resources.moves;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContestComboSets {
    private ContestComboDetail normal;
    @JsonProperty("super")
    private ContestComboDetail superb;
}
