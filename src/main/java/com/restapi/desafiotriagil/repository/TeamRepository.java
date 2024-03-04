package com.restapi.desafiotriagil.repository;

import com.restapi.desafiotriagil.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByOwnerName(String ownerName);

}
