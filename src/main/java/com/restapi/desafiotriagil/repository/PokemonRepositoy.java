package com.restapi.desafiotriagil.repository;

import com.restapi.desafiotriagil.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepositoy extends JpaRepository<Pokemon, Long> {

    Pokemon findByName(String pokemonName);

    boolean existsByName(String pokemonName);

}
