package com.restapi.desafiotriagil.controller;

import com.restapi.desafiotriagil.dto.CreationTeamDTO;
import com.restapi.desafiotriagil.dto.TeamDTO;
import com.restapi.desafiotriagil.services.TeamService;
import com.restapi.desafiotriagil.services.TeamServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams/")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createTeam(@RequestBody @Valid CreationTeamDTO creationTeamDTO){
        teamService.createAndSaveTeam(creationTeamDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getTeams(){
        return ResponseEntity.ok().body(teamService.getAllTeams());
    }

    @GetMapping("{ownerName}")
    public ResponseEntity<List<TeamDTO>> getTeamsByOwner(@PathVariable String ownerName){
        return ResponseEntity.ok().body(teamService.getTeamsByOwner(ownerName));
    }

}
