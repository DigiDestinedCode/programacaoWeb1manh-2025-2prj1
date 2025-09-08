package com.senac.games.controller;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.InscricaoDTOUpdateResponse;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.InscricaoDTOUpdateResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.entity.Inscricao;
import com.senac.games.service.InscricaoService;
import com.senac.games.service.InscricaoService;
import com.senac.games.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inscricao")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar inscricaos",
            description = "Endpoint para listar todos os inscricaos"
    )
    public ResponseEntity<List<InscricaoDTOResponse>> listarInscricoes() {
        return ResponseEntity.ok(inscricaoService.listarInscricoes());
    }

    @GetMapping("/listarPorInscricaoId/{inscricaoId}")
    @Operation(
            summary = "Listar inscricao pelo id de inscricao",
            description = "Endpoint para listar inscricao por Id de inscricao"
    )
    public ResponseEntity<InscricaoDTOResponse> listarPorInscricaoId(@PathVariable("inscricaoId") Integer inscricaoId) {
        InscricaoDTOResponse inscricao = inscricaoService.listarPorInscricaoId(inscricaoId);
        if (inscricao == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(inscricao);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo inscricao",
            description = "Endpoint para criar um novo registro de inscricao"
    )
    public ResponseEntity<InscricaoDTOResponse> criarInscricao(
            @Valid @RequestBody InscricaoDTORequest inscricao
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.criarInscricao(inscricao));
    }

    @PutMapping("/atualizar/{inscricaoId}")
    @Operation(
            summary = "Atualizar todos os dados do inscricao",
            description = "Endpoint para atualizar o registro de inscricao"
    )
    public ResponseEntity<InscricaoDTOResponse> atualizarInscricao(
            @PathVariable("inscricaoId") Integer inscricaoId,
            @Valid @RequestBody InscricaoDTORequest inscricaoDTORequest
    ) {
        return ResponseEntity.ok(inscricaoService.atualizarInscricao(inscricaoId, inscricaoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{inscricaoId}")
    @Operation(
            summary = "Atualizar campo status do inscricao",
            description = "Endpoint para atualizar apenas o status do inscricao"
    )
    public ResponseEntity<InscricaoDTOUpdateResponse> atualizarStatusInscricao(
            @PathVariable("inscricaoId") Integer inscricaoId,
            @Valid @RequestBody InscricaoDTORequest inscricaoDTOUpdateRequest
    ) {
        return ResponseEntity.ok(inscricaoService.atualizarStatusInscricao(inscricaoId, inscricaoDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{inscricaoId}")
    @Operation(
            summary = "Apagar registro do inscricao",
            description = "Endpoint para apagar registro do inscricao"
    )
    public ResponseEntity<Void> apagarInscricao(@PathVariable("inscricaoId") Integer inscricaoId) {
        inscricaoService.apagarInscricao(inscricaoId);
        return ResponseEntity.noContent().build();
    }
}
