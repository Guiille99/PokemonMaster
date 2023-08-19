package com.pokemonmaster.pokeapi.resources.moves;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveMetaData {
    private NamedApiResource<MoveAilment> ailment;
    private NamedApiResource<MoveCategory> category;
    private Integer minHits;
    private Integer maxHits;
    private Integer minTurns;
    private Integer maxTurns;
    private Integer drain;
    private Integer healing;
    private Integer critRate;
    private Integer ailmentChance;
    private Integer flinchChance;
    private Integer statChance;
}
