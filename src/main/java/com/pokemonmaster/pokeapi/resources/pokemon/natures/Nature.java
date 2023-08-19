package com.pokemonmaster.pokeapi.resources.pokemon.natures;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.Name;
import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.berry.BerryFlavor;
import com.pokemonmaster.pokeapi.resources.stats.Stat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Nature implements PokeApiResource {
    private Integer id;
    private String name;
    private NamedApiResource<Stat> decreasedStat;
    private NamedApiResource<Stat> increasedStat;
    private NamedApiResource<BerryFlavor> hatesFlavor;
    private NamedApiResource<BerryFlavor> likesFlavor;
    private List<NatureStatChange> pokeathlonStatChages;
    private List<MoveBattleStylePreference> moveBattleStylePreferences;
    List<Name> names;
}
