package com.restapi.desafiotriagil.infra.exception;

import lombok.Getter;

@Getter
public class OwnerNotFoundException extends RuntimeException {

    private final String ownerName;

    public OwnerNotFoundException(String ownerName){
        this.ownerName = ownerName;
    }

}
