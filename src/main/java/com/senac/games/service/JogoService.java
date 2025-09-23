package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final CategoriaRepository categoriaRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public JogoService(JogoRepository jogoRepository,
                       CategoriaRepository categoriaRepository,
                       ModelMapper modelMapper) {
        this.jogoRepository = jogoRepository;
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    public List<JogoDTOResponse> listarJogos() {
        return jogoRepository.listarJogos()
                .stream()
                .map(jogo -> modelMapper.map(jogo, JogoDTOResponse.class))
                .toList()
                ;
    }

    public JogoDTOResponse listarPorJogoId(Integer jogoId) {
        Jogo jogo = jogoRepository.obterJogoPeloId(jogoId);
        return (jogo != null) ? modelMapper.map(jogo, JogoDTOResponse.class) : null;
    }

    @Transactional
    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {
        Jogo jogo = modelMapper.map(jogoDTORequest, Jogo.class);
        Categoria categoria = categoriaRepository.obterCategoriaPeloId(jogoDTORequest.getCategoriaId());
        if(categoria != null){
            jogo.setCategoria(categoria);
            Jogo JogoSave = jogoRepository.save(jogo);
            return modelMapper.map(JogoSave, JogoDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public JogoDTOResponse atualizarJogo(Integer jogoId, JogoDTORequest jogoDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Jogo jogo = jogoRepository.obterJogoPeloId(jogoId);
        //se encontra o registro a ser atualizado
        if (jogo != null) {
            // atualiza dados do jogo a partir do DTO
            jogo.setNome(jogoDTORequest.getNome());
            jogo.setStatus(jogoDTORequest.getStatus());
            // atualiza a categoria vinculada
            Categoria categoria = categoriaRepository.obterCategoriaPeloId(jogoDTORequest.getCategoriaId());
            if (categoria == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada");
            }
            jogo.setCategoria(categoria);
            Jogo JogoSave = jogoRepository.save(jogo);
            return modelMapper.map(JogoSave, JogoDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jogo não encontrado");
        }
    }

    @Transactional
    public JogoDTOUpdateResponse atualizarStatusJogo(Integer jogoId, JogoDTORequest jogoDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Jogo jogo = jogoRepository.obterJogoPeloId(jogoId);
        //se encontra o registro a ser atualizado
        if (jogo != null) {
            // atualiza o status do Jogo a partir do DTO
            jogo.setStatus(jogoDTOUpdateRequest.getStatus());
            Jogo JogoSave = jogoRepository.save(jogo);
            return modelMapper.map(JogoSave, JogoDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza jogo inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarJogo(Integer jogoId) {
        jogoRepository.apagadoLogicoJogo(jogoId);
    }
}
