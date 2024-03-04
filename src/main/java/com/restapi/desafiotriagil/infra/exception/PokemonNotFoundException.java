package com.restapi.desafiotriagil.infra.exception;

import lombok.Getter;

@Getter
public class PokemonNotFoundException extends RuntimeException {

    private String pokemonName;

    public PokemonNotFoundException(String pokemonName){
        this.pokemonName = pokemonName;
    }

}
