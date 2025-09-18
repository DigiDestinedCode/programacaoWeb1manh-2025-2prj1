package com.senac.games.controller;

import com.senac.games.dto.request.ParticipanteDTORequest;

import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.dto.response.ParticipanteDTOUpdateResponse;
import com.senac.games.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/participante")
@Tag(name = "participante", description = "API para o gerenciamento de participante")
public class ParticipanteController {

    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar participantes",
            description = "Endpoint para listar todos os participantes"
    )
    public ResponseEntity<List<ParticipanteDTOResponse>> listarParticipantes() {
        return ResponseEntity.ok(participanteService.listarParticipantes());
    }

    @GetMapping("/listarPorParticipanteId/{participanteId}")
    @Operation(
            summary = "Listar participante pelo id de participante",
            description = "Endpoint para listar participante por Id de participante"
    )
    public ResponseEntity<ParticipanteDTOResponse> listarPorParticipanteId(@PathVariable("participanteId") Integer participanteId) {
        ParticipanteDTOResponse participante = participanteService.listarPorParticipanteId(participanteId);
        if (participante == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(participante);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo participante",
            description = "Endpoint para criar um novo registro de participante"
    )
    public ResponseEntity<ParticipanteDTOResponse> criarParticipante(
            @Valid @RequestBody ParticipanteDTORequest participante
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.criarParticipante(participante));
    }

    @PutMapping("/atualizar/{participanteId}")
    @Operation(
            summary = "Atualizar todos os dados do participante",
            description = "Endpoint para atualizar o registro de participante"
    )
    public ResponseEntity<ParticipanteDTOResponse> atualizarParticipante(
            @PathVariable("participanteId") Integer participanteId,
            @Valid @RequestBody ParticipanteDTORequest participanteDTORequest
    ) {
        return ResponseEntity.ok(participanteService.atualizarParticipante(participanteId, participanteDTORequest));
    }

    @PatchMapping("/atualizarStatus/{participanteId}")
    @Operation(
            summary = "Atualizar campo status do participante",
            description = "Endpoint para atualizar apenas o status do participante"
    )
    public ResponseEntity<ParticipanteDTOUpdateResponse> atualizarStatusParticipante(
            @PathVariable("participanteId") Integer participanteId,
            @Valid @RequestBody ParticipanteDTORequest participanteDTOUpdateRequest
    ) {
        return ResponseEntity.ok(participanteService.atualizarStatusParticipante(participanteId, participanteDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{participanteId}")
    @Operation(
            summary = "Apagar registro do participante",
            description = "Endpoint para apagar registro do participante"
    )
    public ResponseEntity<Void> apagarParticipante(@PathVariable("participanteId") Integer participanteId) {
        participanteService.apagarParticipante(participanteId);
        return ResponseEntity.noContent().build();
    }
}
