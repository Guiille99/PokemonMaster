package com.pokemonmaster.pokeapi.resources.pokemon.natures;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.moves.MoveBattleStyle;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveBattleStylePreference {
    private Integer lowHpPreference;
    private Integer highHpPreference;
    private NamedApiResource<MoveBattleStyle> moveBattleStyle;
}
