package com.pokemonmaster.pokeapi;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokeApiConfigurationProperties {
    @NotNull
	@Value("${pokeapi.base-uri}")
	private URI baseUri;
	@Value("${pokeapi.max-buffer-size}")
	private int maxBytesToBuffer;
}
