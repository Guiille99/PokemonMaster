package com.pokemonmaster.pokeapi.resources.types;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.GenerationGameIndex;
import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.moves.Move;
import com.pokemonmaster.pokeapi.resources.moves.MoveDamageClass;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Type implements PokeApiResource {
    private Integer id;
    private String name;
    private TypeRelations damageRelations;
    private List<TypeRelationsPast<Type>> pastDamageRelations;
    private List<GenerationGameIndex> gameIndices;
    private NamedApiResource<Generation> generation;
    private NamedApiResource<MoveDamageClass> moveDamageClass;
    private List<Name> names;
    private List<TypePokemon> pokemon;
    private List<NamedApiResource<Move>> moves;
}
