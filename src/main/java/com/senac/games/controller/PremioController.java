package com.senac.games.controller;

import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.dto.response.PremioDTOUpdateResponse;
import com.senac.games.entity.Premio;
import com.senac.games.entity.Premio;
import com.senac.games.service.PremioService;
import com.senac.games.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/premio")
public class PremioController {

    private PremioService premioService;

    public  PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar premios",
            description = "Endpoint para listar todos os premios")

    public ResponseEntity<List<Premio>> listarPremios(){
        return ResponseEntity.ok(premioService.listarPremios());
    }

    @GetMapping("/listarPorPremioId/{premioId}")
    @Operation(
            summary = "Listar premio pelo id de premio",
            description = "Endpoint para premios por Id de premio")

    public ResponseEntity<Premio> listarPorPremioId(@PathVariable("premioId") Integer premioId){
        Premio premio = premioService.listarPorPremioId(premioId);
        if(premio == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(premio);
        }
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo premio",
            description = "Endpoint para criar um novo registro de premio")
    public ResponseEntity<PremioDTOResponse> criarPremio(
            @Valid @RequestBody PremioDTORequest premio)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.criarPremio(premio));
    }

    @PutMapping("/atualizar/{premioId}")
    @Operation(
            summary = "Atualizar todos os dados do premio ",
            description = "Endpoint para atualizar o registro de premio")
    public ResponseEntity<PremioDTOResponse> atualizarPremio(
            @PathVariable("premioId") Integer premioId,
            @RequestBody PremioDTORequest premioDTORequest) {
        return ResponseEntity.ok(premioService.atualizarPremio(premioId, premioDTORequest));
    }

    @PatchMapping("/atualizarStatus/{premioId}")
    @Operation(summary = "Atualizar campo status do premio", description = "Endpoint para atualizar o status do premio")
    public ResponseEntity<PremioDTOUpdateResponse> atualizarStatusPremio(
            @Valid
            @PathVariable("premioId") Integer premioId,
            @RequestBody PremioDTORequest premioDTOUpdateRequest
    ){return ResponseEntity.ok(premioService.atualizarStatusPremio(premioId, premioDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{premioId}")
    @Operation(summary = "Apagar registro do premio", description = "Endpoint para apagar registro do premio")
    public ResponseEntity apagarPremio(@PathVariable("premioId") Integer premioId) {
        premioService.apagarPremio(premioId);
        return ResponseEntity.noContent().build();
    }
}
