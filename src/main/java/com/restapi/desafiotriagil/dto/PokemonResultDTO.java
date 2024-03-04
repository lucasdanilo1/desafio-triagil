package com.restapi.desafiotriagil.dto;

import java.math.BigDecimal;

public record PokemonResultDTO(

        Long id,
        String name,
        BigDecimal weight,
        BigDecimal height

) {
}
