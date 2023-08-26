package com.pokemonmaster.pokeapi.resources.pokemon.abilities;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.VerboseEffect;
import com.pokemonmaster.pokeapi.resources.generations.Generation;
import com.pokemonmaster.pokeapi.utils.Localizable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ability implements PokeApiResource, Localizable {
    private Integer id;
    private String name;
    private boolean isMainSeries;
    private NamedApiResource<Generation> generation;
    private List<Name> names;
    private List<VerboseEffect> effectEntries;
    private List<AbilityEffectChange> effectChanges;
    private List<AbilityFlavorText> flavorTextEntries;
    private List<AbilityPokemon> pokemon;
}
