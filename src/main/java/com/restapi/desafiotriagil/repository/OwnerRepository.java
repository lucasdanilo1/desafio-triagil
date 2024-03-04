package com.restapi.desafiotriagil.repository;

import com.restapi.desafiotriagil.model.Owner;
import com.restapi.desafiotriagil.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByName(String user);
}
