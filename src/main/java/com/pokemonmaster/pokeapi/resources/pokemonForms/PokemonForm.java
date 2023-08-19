package com.pokemonmaster.pokeapi.resources.pokemonForms;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.PokemonFormType;
import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.pokemon.pokemon.Pokemon;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonForm implements PokeApiResource {
    private Integer id;
    private String name;
    private Integer order;
    private Integer formOrder;
    private boolean isDefault;
    private boolean isBattleOnly;
    private boolean isMega;
    private String formName;
    private NamedApiResource<Pokemon> pokemon;
    private List<PokemonFormType> types;
    private PokemonFormSprites sprites;
    private NamedApiResource<VersionGroup> versionGroup;
    private List<Name> names;
    private List<Name> formNames;
    
}
