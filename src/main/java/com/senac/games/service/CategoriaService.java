package com.senac.games.service;

import com.senac.games.dto.request.CategoriaDTORequest;
import com.senac.games.dto.response.*;
import com.senac.games.entity.Categoria;
import com.senac.games.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository,
                         ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    public List<CategoriaDTOResponse> listarCategorias() {
        return categoriaRepository.listarCategorias()
                .stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaDTOResponse.class))
                .toList()
                ;
    }

    public CategoriaDTOResponse listarPorCategoriaId(Integer categoriaId) {
        Categoria categoria = categoriaRepository.obterCategoriaPeloId(categoriaId);
        return (categoria != null) ? modelMapper.map(categoria, CategoriaDTOResponse.class) : null;
    }

    @Transactional
    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest) {
        Categoria categoria = modelMapper.map(categoriaDTORequest, Categoria.class);
        Categoria CategoriaSave = this.categoriaRepository.save(categoria);
        return modelMapper.map(CategoriaSave, CategoriaDTOResponse.class);
    }

    @Transactional
    public CategoriaDTOResponse atualizarCategoria(Integer categoriaId, CategoriaDTORequest categoriaDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Categoria categoria = categoriaRepository.obterCategoriaPeloId(categoriaId);
        //se encontra o registro a ser atualizado
        if (categoria != null) {
            // atualiza dados do categoria a partir do DTO
            modelMapper.map(categoriaDTORequest, categoria);
            // atualiza a categoria vinculada
            Categoria tempResponse = categoriaRepository.save(categoria);
            return modelMapper.map(tempResponse, CategoriaDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza categoria inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public CategoriaDTOUpdateResponse atualizarStatusCategoria(Integer categoriaId, CategoriaDTORequest categoriaDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Categoria categoria = categoriaRepository.obterCategoriaPeloId(categoriaId);
        //se encontra o registro a ser atualizado
        if (categoria != null) {
            // atualiza o status do Categoria a partir do DTO
            categoria.setStatus(categoriaDTOUpdateRequest.getStatus());
            Categoria CategoriaSave = categoriaRepository.save(categoria);
            return modelMapper.map(CategoriaSave, CategoriaDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza categoria inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarCategoria(Integer categoriaId) {
        categoriaRepository.apagadoLogicoCategoria(categoriaId);
    }
}