package com.pokemonmaster.pokeapi.resources.types;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.generations.Generation;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TypeRelationsPast<T> {
    private NamedApiResource<Generation> generation;
    private TypeRelations damageRelations;
}
