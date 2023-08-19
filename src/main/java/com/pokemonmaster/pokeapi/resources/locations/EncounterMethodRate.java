package com.pokemonmaster.pokeapi.resources.locations;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.encounters.EncounterMethod;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EncounterMethodRate {
    private NamedApiResource<EncounterMethod> encounterMethod;
    private List<EncounterVersionDetails> versionDetails;
}
