package com.pokemonmaster.pokeapi.resources.types;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TypeRelations {
    private List<NamedApiResource<Type>> noDamageTo, halfDamageTo, doubleDamageTo, 
                                        noDamageFrom, halfDamageFrom, doubleDamageFrom;
}
