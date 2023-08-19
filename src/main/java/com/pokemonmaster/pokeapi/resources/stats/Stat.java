package com.pokemonmaster.pokeapi.resources.stats;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.ApiResource;
import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.moves.MoveDamageClass;
import com.pokemonmaster.pokeapi.resources.pokemon.Characteristic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Stat implements PokeApiResource{
    private Integer id;
    private String name;
    private Integer gameIndex;
    private boolean isBattleOnly;
    private MoveStatAffectSets affectingMoves;
    private NatureStatAffectSets affectingNatures;
    private List<ApiResource<Characteristic>> characteristics;
    private NamedApiResource<MoveDamageClass> moveDamageClass;
    private List<Name> names;
}
