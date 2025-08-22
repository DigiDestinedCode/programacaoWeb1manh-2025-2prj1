package com.senac.games.controller;

import com.senac.games.entity.Jogo;
import com.senac.games.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/jogo")
public class JogoController {

    private JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar jogos", description = "Endpoint para listar todos os jogos")
    public ResponseEntity<List<Jogo>> listarJogos(){
        return ResponseEntity.ok(jogoService.listarJogos());
    }
}
