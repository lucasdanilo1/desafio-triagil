package com.restapi.desafiotriagil.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreationTeamDTO(

        @NotBlank
        String user,

        @NotNull
        @Valid
        List<@NotBlank String> team

) {

}
