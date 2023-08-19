package com.pokemonmaster.pokeapi.resources;

import com.pokemonmaster.pokeapi.resources.machines.Machine;
import com.pokemonmaster.pokeapi.resources.version.VersionGroup;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MachineVersionDetail {
    private ApiResource<Machine> machine;
    private NamedApiResource<VersionGroup> versionGroup;
}
