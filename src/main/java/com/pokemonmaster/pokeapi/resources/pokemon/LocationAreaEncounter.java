package com.pokemonmaster.pokeapi.resources.pokemon;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.VersionEncounterDetail;
import com.pokemonmaster.pokeapi.resources.locations.LocationArea;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LocationAreaEncounter {
    private NamedApiResource<LocationArea> locationArea;
    private List<VersionEncounterDetail> versionDetails;
}
