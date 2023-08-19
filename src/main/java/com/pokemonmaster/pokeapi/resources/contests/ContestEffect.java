package com.pokemonmaster.pokeapi.resources.contests;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Effect;
import com.pokemonmaster.pokeapi.resources.FlavorText;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContestEffect {
    private Integer id;
    private Integer appeal;
    private Integer jam;
    private List<Effect> effectEntries;
    private List<FlavorText> flavorTextEntries;
}
