package com.restapi.desafiotriagil.dto;

import com.restapi.desafiotriagil.model.Pokemon;
import com.restapi.desafiotriagil.model.Team;

import java.util.List;

public record TeamDTO(
        Long id,
        String owner,
        List<Pokemon> pokemons
) {
    public TeamDTO(Team team){
        this(team.getId(), team.getOwner().getName(), team.getPokemons());
    }

}
