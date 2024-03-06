package com.restapi.desafiotriagil.infra.exception;

import lombok.Getter;

@Getter
public class PokemonNotFoundException extends RuntimeException {

    private final String pokemonName;

    public PokemonNotFoundException(String pokemonName){
        this.pokemonName = pokemonName;
    }

}
