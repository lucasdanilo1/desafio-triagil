package com.restapi.desafiotriagil.controller;

import com.restapi.desafiotriagil.dto.TeamCreationDTO;
import com.restapi.desafiotriagil.dto.TeamDTO;
import com.restapi.desafiotriagil.services.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/teams/", produces = {"application/json"})
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping
    @Transactional
    @Operation(summary = "Deve criar um novo time para determinado usuario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Pokemon não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"status\": 404, \"message\": \"Pokemon ' ' não encontrado\"}"))
            }),
            @ApiResponse(responseCode = "422", description = "Erro de validação dos campos na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"status\": 422, " +
                            "\"message\": \"Erro de validação\"," +
                            " \"errors\": [{\"field\": \"user\", " +
                            "\"message\": \"não deve estar em branco\"}]}"))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Um erro desconhecido ocorreu\"}"))
            })

    })
    public ResponseEntity<?> createTeam(@RequestBody @Valid TeamCreationDTO teamCreationDTO){
        teamService.createAndSaveTeam(teamCreationDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Deve retornar todos os times disponíveis e seus respectivos usuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Um erro desconhecido ocorreu\"}"))
            })
    })
    public ResponseEntity<List<TeamDTO>> getTeams(){
        return ResponseEntity.ok().body(teamService.getAllTeams());
    }

    @GetMapping("/{ownerName}")
    @Operation(summary = "Deve retornar todos os times de determinado usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"status\": 404, \"message\": \"Usuário ' ' não encontrado\"}"))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Um erro desconhecido ocorreu\"}"))
            })
    })
    public ResponseEntity<List<TeamDTO>> getTeamsByOwner(@PathVariable String ownerName){
        return ResponseEntity.ok().body(teamService.getTeamsByOwner(ownerName));
    }


}
