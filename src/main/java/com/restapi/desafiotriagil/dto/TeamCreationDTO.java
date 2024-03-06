package com.restapi.desafiotriagil.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record TeamCreationDTO(

        @NotBlank
        String user,

        @NotEmpty
        @Valid
        List<@NotBlank String> team

) {

}
