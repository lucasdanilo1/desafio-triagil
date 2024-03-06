package com.restapi.desafiotriagil.services;

import com.restapi.desafiotriagil.dto.TeamCreationDTO;
import com.restapi.desafiotriagil.dto.TeamDTO;
import com.restapi.desafiotriagil.model.Owner;
import com.restapi.desafiotriagil.model.Pokemon;
import com.restapi.desafiotriagil.model.Team;

import java.util.List;

public interface TeamService {

    void createAndSaveTeam(TeamCreationDTO teamCreationDTO);

    List<TeamDTO> getAllTeams();

    List<TeamDTO> getTeamsByOwner(String ownerName);

    List<String> removePokemonDuplicatesFromTeam(List<String> team);

    Owner getOrCreateNewOwner(String userName);

    Team saveEachPokemonInTeam(List<String> pokemonNames, Team team);

    Pokemon obtainPokemonInfoAndSave(String pokemonName);

}
