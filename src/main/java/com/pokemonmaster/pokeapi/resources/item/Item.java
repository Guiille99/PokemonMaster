package com.pokemonmaster.pokeapi.resources.item;

import java.util.List;

import com.fasterxml.jackson.core.sym.Name;
import com.pokemonmaster.pokeapi.resources.ApiResource;
import com.pokemonmaster.pokeapi.resources.GenerationGameIndex;
import com.pokemonmaster.pokeapi.resources.MachineVersionDetail;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.VerboseEffect;
import com.pokemonmaster.pokeapi.resources.VersionGroupFlavorText;
import com.pokemonmaster.pokeapi.resources.evolutions.EvolutionChain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item implements PokeApiResource {
    private Integer id;
    private String name;
    private Integer cost;
    private Integer flingPower;
    private List<NamedApiResource<ItemFlingEffect>> flingEffect;
    private List<NamedApiResource<ItemAttribute>> atributes;
    private NamedApiResource<ItemCategory> category;
    private List<VerboseEffect> effectEntries;
    private List<VersionGroupFlavorText> flavorTextEntries;
    private List<GenerationGameIndex> gameIndices;
    private List<Name> names;
    private ItemSprites sprites;
    private List<ItemHolderPokemon> heldByPokemon;
    private ApiResource<EvolutionChain> babyTriggerFor;
    private List<MachineVersionDetail> machines;
}
