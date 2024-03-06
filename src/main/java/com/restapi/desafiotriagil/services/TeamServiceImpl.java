package com.restapi.desafiotriagil.services;

import com.restapi.desafiotriagil.dto.PokemonResultDTO;
import com.restapi.desafiotriagil.dto.TeamCreationDTO;
import com.restapi.desafiotriagil.dto.TeamDTO;
import com.restapi.desafiotriagil.infra.exception.OwnerNotFoundException;
import com.restapi.desafiotriagil.infra.exception.PokemonNotFoundException;
import com.restapi.desafiotriagil.model.Owner;
import com.restapi.desafiotriagil.model.Pokemon;
import com.restapi.desafiotriagil.model.Team;
import com.restapi.desafiotriagil.repository.OwnerRepository;
import com.restapi.desafiotriagil.repository.PokemonRepositoy;
import com.restapi.desafiotriagil.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    PokemonRepositoy pokemonRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    @Transactional
    public void createAndSaveTeam(TeamCreationDTO teamCreationDTO) {

        List<String> pokemonTeam = teamCreationDTO.team();

        boolean hasPokemonDuplicates = pokemonTeam.stream().distinct().count() != teamCreationDTO.team().size();

        if(hasPokemonDuplicates){
            pokemonTeam = this.removePokemonDuplicatesFromTeam(teamCreationDTO.team());
        }

        var owner = this.getOrCreateNewOwner(teamCreationDTO.user());
        var team = this.saveEachPokemonInTeam(pokemonTeam, new Team());

        team.setOwner(owner);

        teamRepository.save(team);

        owner.getTeams().add(team);

        ownerRepository.save(owner);

    }

    @Override
    public List<TeamDTO> getAllTeams(){
        return teamRepository.findAll().stream().map(TeamDTO::new).toList();
    }

    @Override
    public List<TeamDTO> getTeamsByOwner(String ownerName) {
        if(!teamRepository.existsByOwnerName(ownerName)){
            throw new OwnerNotFoundException(ownerName);
        }
        return teamRepository.findAllByOwnerName(ownerName).stream().map(TeamDTO::new).toList();
    }

    @Override
    public List<String> removePokemonDuplicatesFromTeam(List<String> team){
        Set<String> set = new HashSet<>();
        List<String> teamWithoutDuplicates = new ArrayList<>();

        for (String pokemon : team) {
            if (!set.contains(pokemon)) {
                set.add(pokemon);
                teamWithoutDuplicates.add(pokemon);
            }
        }
        return teamWithoutDuplicates;
    }

    @Override
    public Owner getOrCreateNewOwner(String userName) {

        Owner owner = ownerRepository.findByName(userName);
        if (owner == null) {
            owner = new Owner();
            owner.setName(userName);
        }
        return owner;

    }

    @Override
    public Team saveEachPokemonInTeam(List<String> pokemonNames, Team team){

        for (String pokemonName : pokemonNames) {

            Pokemon pokemon;

            if(!pokemonRepository.existsByName(pokemonName)){
                pokemon = this.obtainPokemonInfoAndSave(pokemonName);
            } else pokemon = pokemonRepository.findByName(pokemonName);

            team.getPokemons().add(pokemon);
        }

        return team;

    }

    @Override
    public Pokemon obtainPokemonInfoAndSave(String pokemonName){

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PokemonResultDTO> resp;

        try{
            resp = restTemplate.getForEntity(
                    "https://pokeapi.co/api/v2/pokemon/" + pokemonName, PokemonResultDTO.class);
        } catch (Exception ex) {
            throw new PokemonNotFoundException(pokemonName);
        }

        if (resp.getStatusCode() == HttpStatus.OK) {

            var pokemonResult = resp.getBody();

            assert pokemonResult != null;

            Pokemon pokemon = new Pokemon(pokemonResult);
            pokemonRepository.save(pokemon);

            return pokemon;

        } else throw new RuntimeException("Failed to fetch Pokemon: " + pokemonName);

    }

}
