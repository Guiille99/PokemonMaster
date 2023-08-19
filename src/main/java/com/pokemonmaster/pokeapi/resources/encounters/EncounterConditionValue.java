package com.pokemonmaster.pokeapi.resources.encounters;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EncounterConditionValue implements PokeApiResource {
    private Integer id;
    private String name;
    private NamedApiResource<EncounterCondition> condition;
    private List<Name> names;
}
