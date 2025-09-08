package com.senac.games.controller;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateResponse;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.entity.Patrocinador;
import com.senac.games.service.PatrocinadorService;
import com.senac.games.service.PatrocinadorService;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrocinador")
public class PatrocinadorController {

    private final PatrocinadorService patrocinadorService;

    public PatrocinadorController(PatrocinadorService patrocinadorService) {
        this.patrocinadorService = patrocinadorService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar patrocinadors",
            description = "Endpoint para listar todos os patrocinadors"
    )
    public ResponseEntity<List<PatrocinadorDTOResponse>> listarPatrocinadores() {
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadores());
    }

    @GetMapping("/listarPorPatrocinadorId/{patrocinadorId}")
    @Operation(
            summary = "Listar patrocinador pelo id de patrocinador",
            description = "Endpoint para listar patrocinador por Id de patrocinador"
    )
    public ResponseEntity<PatrocinadorDTOResponse> listarPorPatrocinadorId(@PathVariable("patrocinadorId") Integer patrocinadorId) {
        PatrocinadorDTOResponse patrocinador = patrocinadorService.listarPorPatrocinadorId(patrocinadorId);
        if (patrocinador == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(patrocinador);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo patrocinador",
            description = "Endpoint para criar um novo registro de patrocinador"
    )
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinador(
            @Valid @RequestBody PatrocinadorDTORequest patrocinador
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patrocinadorService.criarPatrocinador(patrocinador));
    }

    @PutMapping("/atualizar/{patrocinadorId}")
    @Operation(
            summary = "Atualizar todos os dados do patrocinador",
            description = "Endpoint para atualizar o registro de patrocinador"
    )
    public ResponseEntity<PatrocinadorDTOResponse> atualizarPatrocinador(
            @PathVariable("patrocinadorId") Integer patrocinadorId,
            @Valid @RequestBody PatrocinadorDTORequest patrocinadorDTORequest
    ) {
        return ResponseEntity.ok(patrocinadorService.atualizarPatrocinador(patrocinadorId, patrocinadorDTORequest));
    }

    @PatchMapping("/atualizarStatus/{patrocinadorId}")
    @Operation(
            summary = "Atualizar campo status do patrocinador",
            description = "Endpoint para atualizar apenas o status do patrocinador"
    )
    public ResponseEntity<PatrocinadorDTOUpdateResponse> atualizarStatusPatrocinador(
            @PathVariable("patrocinadorId") Integer patrocinadorId,
            @Valid @RequestBody PatrocinadorDTORequest patrocinadorDTOUpdateRequest
    ) {
        return ResponseEntity.ok(patrocinadorService.atualizarStatusPatrocinador(patrocinadorId, patrocinadorDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{patrocinadorId}")
    @Operation(
            summary = "Apagar registro do patrocinador",
            description = "Endpoint para apagar registro do patrocinador"
    )
    public ResponseEntity<Void> apagarPatrocinador(@PathVariable("patrocinadorId") Integer patrocinadorId) {
        patrocinadorService.apagarPatrocinador(patrocinadorId);
        return ResponseEntity.noContent().build();
    }
}

