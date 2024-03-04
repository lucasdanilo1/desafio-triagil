package com.restapi.desafiotriagil.model;

import com.restapi.desafiotriagil.dto.PokemonResultDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {

    @Id
    private Long id;

    private String name;

    private BigDecimal height = BigDecimal.ZERO;

    private BigDecimal weight = BigDecimal.ZERO;

    public Pokemon(PokemonResultDTO pokemonResult){
        this.id = pokemonResult.id();
        this.name = pokemonResult.name();
        this.height = pokemonResult.height();
        this.weight = pokemonResult.weight();
    }

}
