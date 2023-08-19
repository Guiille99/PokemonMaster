package com.pokemonmaster.pokeapi.resources.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSprites {
	@JsonProperty("default")
    private String imageUrl;
	
}
