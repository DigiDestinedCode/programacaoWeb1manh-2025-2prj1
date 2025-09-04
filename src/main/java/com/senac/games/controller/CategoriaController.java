package com.senac.games.controller;

import com.senac.games.dto.request.CategoriaDTORequest;
import com.senac.games.dto.response.CategoriaDTOResponse;
import com.senac.games.dto.response.CategoriaDTOUpdateResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    public  CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar categorias",
            description = "Endpoint para listar todos os categorias")

    public ResponseEntity<List<Categoria>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/listarPorCategoriaId/{categoriaId}")
    @Operation(
            summary = "Listar categoria pelo id de categoria",
            description = "Endpoint para categorias por Id de categoria")

    public ResponseEntity<Categoria> listarPorCategoriaId(@PathVariable("categoriaId") Integer categoriaId){
        Categoria categoria = categoriaService.listarPorCategoriaId(categoriaId);
        if(categoria == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(categoria);
        }
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo categoria",
            description = "Endpoint para criar um novo registro de categoria")
    public ResponseEntity<CategoriaDTOResponse> criarCategoria(
            @Valid @RequestBody CategoriaDTORequest categoria)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoria));
    }

    @PutMapping("/atualizar/{categoriaId}")
    @Operation(
            summary = "Atualizar todos os dados do categoria ",
            description = "Endpoint para atualizar o registro de categoria")
    public ResponseEntity<CategoriaDTOResponse> atualizarCategoria(
            @PathVariable("categoriaId") Integer categoriaId,
            @RequestBody CategoriaDTORequest categoriaDTORequest) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria(categoriaId, categoriaDTORequest));
    }

    @PatchMapping("/atualizarStatus/{categoriaId}")
    @Operation(summary = "Atualizar campo status do categoria", description = "Endpoint para atualizar o status do categoria")
    public ResponseEntity<CategoriaDTOUpdateResponse> atualizarStatusCategoria(
            @Valid
            @PathVariable("categoriaId") Integer categoriaId,
            @RequestBody CategoriaDTORequest categoriaDTOUpdateRequest
    ){return ResponseEntity.ok(categoriaService.atualizarStatusCategoria(categoriaId, categoriaDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{categoriaId}")
    @Operation(summary = "Apagar registro do categoria", description = "Endpoint para apagar registro do categoria")
    public ResponseEntity apagarCategoria(@PathVariable("categoriaId") Integer categoriaId) {
        categoriaService.apagarCategoria(categoriaId);
        return ResponseEntity.noContent().build();
    }
}
