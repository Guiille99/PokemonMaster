package com.pokemonmaster.pokeapi.resources.pokemon.pokemon;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonSprites {
    private String frontDefault, frontShiny, frontFemale, frontShinyFemale,
                    backDefault, backShiny, backFemale, backShinyFemale;
    private OtherSprites other;
}

