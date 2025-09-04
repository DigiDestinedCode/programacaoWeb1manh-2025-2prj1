package com.senac.games.controller;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateResponse;
import com.senac.games.entity.Jogo;
import com.senac.games.entity.Jogo;
import com.senac.games.service.JogoService;
import com.senac.games.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jogo")
public class JogoController {

    private JogoService jogoService;

    public  JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar jogos",
            description = "Endpoint para listar todos os jogos")

    public ResponseEntity<List<Jogo>> listarJogos(){
        return ResponseEntity.ok(jogoService.listarJogos());
    }

    @GetMapping("/listarPorJogoId/{jogoId}")
    @Operation(
            summary = "Listar jogo pelo id de jogo",
            description = "Endpoint para jogos por Id de jogo")

    public ResponseEntity<Jogo> listarPorJogoId(@PathVariable("jogoId") Integer jogoId){
        Jogo jogo = jogoService.listarPorJogoId(jogoId);
        if(jogo == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(jogo);
        }
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo jogo",
            description = "Endpoint para criar um novo registro de jogo")
    public ResponseEntity<JogoDTOResponse> criarJogo(
            @Valid @RequestBody JogoDTORequest jogo)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.criarJogo(jogo));
    }

    @PutMapping("/atualizar/{jogoId}")
    @Operation(
            summary = "Atualizar todos os dados do jogo ",
            description = "Endpoint para atualizar o registro de jogo")
    public ResponseEntity<JogoDTOResponse> atualizarJogo(
            @PathVariable("jogoId") Integer jogoId,
            @RequestBody JogoDTORequest jogoDTORequest) {
        return ResponseEntity.ok(jogoService.atualizarJogo(jogoId, jogoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{jogoId}")
    @Operation(summary = "Atualizar campo status do jogo", description = "Endpoint para atualizar o status do jogo")
    public ResponseEntity<JogoDTOUpdateResponse> atualizarStatusJogo(
            @Valid
            @PathVariable("jogoId") Integer jogoId,
            @RequestBody JogoDTORequest jogoDTOUpdateRequest
    ){return ResponseEntity.ok(jogoService.atualizarStatusJogo(jogoId, jogoDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{jogoId}")
    @Operation(summary = "Apagar registro do jogo", description = "Endpoint para apagar registro do jogo")
    public ResponseEntity apagarJogo(@PathVariable("jogoId") Integer jogoId) {
        jogoService.apagarJogo(jogoId);
        return ResponseEntity.noContent().build();
    }
}
