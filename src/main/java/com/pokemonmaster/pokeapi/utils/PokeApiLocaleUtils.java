package com.pokemonmaster.pokeapi.utils;

import java.util.Optional;

import com.pokemonmaster.pokeapi.resources.Name;

public class PokeApiLocaleUtils {
	public static Optional<Name> getInLocale(Localizable localizable, String locale) {
		return localizable.getNames().stream()
				.filter(name -> name.getLanguage().getName().equalsIgnoreCase(locale))
				.findFirst();
	}
}
