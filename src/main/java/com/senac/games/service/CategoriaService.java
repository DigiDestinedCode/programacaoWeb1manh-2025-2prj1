package com.senac.games.service;

import com.senac.games.dto.request.CategoriaDTORequest;
import com.senac.games.dto.response.CategoriaDTOResponse;
import com.senac.games.dto.response.CategoriaDTOUpdateResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias(){

        return this.categoriaRepository.listarCategorias();
    }

    public Categoria listarPorCategoriaId(Integer categoriaId) {
        return this.categoriaRepository.obterCategoriaPeloId(categoriaId);
    }

    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest) {

        Categoria categoria = modelMapper.map(categoriaDTORequest, Categoria.class);
        Categoria categoriaSave = this.categoriaRepository.save(categoria);
        CategoriaDTOResponse categoriaDTOResponse = modelMapper.map(categoriaSave, CategoriaDTOResponse.class);
        return categoriaDTOResponse;
    }

    public CategoriaDTOResponse atualizarCategoria(Integer categoriaId, CategoriaDTORequest categoriaDTORequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Categoria categoria = this.listarPorCategoriaId(categoriaId);
        //se encontra o registro a ser atualizado
        if (categoria != null){
            modelMapper.map(categoriaDTORequest,categoria);
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            //com o objeto no formato correto tipo "categoria" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Categoria tempResponse = categoriaRepository.save(categoria);
            return modelMapper.map(tempResponse,CategoriaDTOResponse.class);
        } else{
            return null;
        }

    }

    public CategoriaDTOUpdateResponse atualizarStatusCategoria(Integer categoriaId, CategoriaDTORequest categoriaDTOUpdateRequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Categoria categoria = this.listarPorCategoriaId(categoriaId);
        //se encontra o registro a ser atualizado
        if (categoria != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            categoria.setStatus(categoriaDTOUpdateRequest.getStatus());
            //modelMapper.map(categoriaDTOUpdateRequest,categoria);

            //com o objeto no formato correto tipo "categoria" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Categoria tempResponse = categoriaRepository.save(categoria);
            return modelMapper.map(tempResponse, CategoriaDTOUpdateResponse.class);
        } else{
            return null;
        }
    }
    public void apagarCategoria(Integer categoriaId){
        categoriaRepository.apagadoLogicoCategoria(categoriaId);
    }
}
