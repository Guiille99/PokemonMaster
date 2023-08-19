package com.pokemonmaster.pokeapi.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class PageQuery {
    private Integer limit, offset;
}
