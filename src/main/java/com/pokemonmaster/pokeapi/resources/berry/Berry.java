package com.pokemonmaster.pokeapi.resources.berry;

import java.util.List;

import com.pokemonmaster.pokeapi.resources.NamedApiResource;
import com.pokemonmaster.pokeapi.resources.PokeApiResource;
import com.pokemonmaster.pokeapi.resources.item.Item;
import com.pokemonmaster.pokeapi.resources.types.Type;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Berry implements PokeApiResource {
    private Integer id;
    private String name;
    // Time it takes the tree to grow one stage, in hours. Berry trees go through four of these growth stages before they can be picked.
    private Integer growthTime; 
    private Integer maxHarvest; // The maximum number of these berries that can grow on one tree in Generation IV.
    private Integer naturalGiftPower; // The power of the move "Natural Gift" when used with this Berry.
    private Integer size; // Tamaño de la baya en milimetros
    private Integer smoothness; // The smoothness of this Berry, used in making Pokéblocks or Poffins.
    private Integer soilDryness; // The speed at which this Berry dries out the soil as it grows. A higher rate means the soil dries more quickly.
    private NamedApiResource<BerryFirmness> firmness;
    private List<BerryFlavorMap> flavors;
    private NamedApiResource<Item> item; // Berries are actually items. This is a reference to the item specific data for this berry.
    private NamedApiResource<Type> naturalGiftType;
}
