package com.pokemonmaster.pokeapi.resources.moves;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.ApiResource;
import com.pokemonmaster.pokeapi.resources.MachineVersionDetail;
import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.VerboseEffect;
import com.pokemonmaster.pokeapi.resources.contests.ContestEffect;
import com.pokemonmaster.pokeapi.resources.contests.ContestType;
import com.pokemonmaster.pokeapi.resources.contests.SuperContestEffect;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.resources.pokemon.abilities.AbilityEffectChange;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;
import com.pokemonmaster.pokeapi.resources.types.Type;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Move implements PokeApiResource {
    private Integer id;
    private String name;
    private Integer accuracy;
    private Integer effect_chance;
    private Integer pp;
    private Integer priority;
    private Integer power;
    private ContestComboSets contestCombos;
    private NamedApiResource<ContestType> contestType;
    private ApiResource<ContestEffect> contestEffect;
    private NamedApiResource<MoveDamageClass> damageClass;
    private List<VerboseEffect> effectEntries;
    private List<AbilityEffectChange> effectChanges;
    private List<NamedApiResource<Pokemon>> learnedByPokemon;
    private List<MoveFlavorText> flavorTextEntries;
    private NamedApiResource<Generation> generation;
    private List<MachineVersionDetail> machines;
    private MoveMetaData meta;
    private List<Name> names;
    private List<PastMoveStatValues> pastValues;
    private List<MoveStatChange> statChanges;
    private ApiResource<SuperContestEffect> superContestEffect;
    private NamedApiResource<MoveTarget> target;
    private NamedApiResource<Type> type;
}
