package com.pokemonmaster.pokeapi.resources;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.encounters.EncounterConditionValue;
import com.pokemonmaster.pokeapi.resources.encounters.EncounterMethod;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Encounter {
    private Integer minLevel;
    private Integer maxLevel;
    private List<NamedApiResource<EncounterConditionValue>> conditionValues;
    private Integer chance;
    private NamedApiResource<EncounterMethod> method;
}
