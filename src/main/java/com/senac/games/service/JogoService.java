package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateResponse;
import com.senac.games.entity.Jogo;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import com.senac.games.repository.JogoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> listarJogos(){

        return this.jogoRepository.listarJogos();
    }

    public Jogo listarPorJogoId(Integer jogoId) {
        return this.jogoRepository.obterJogoPeloId(jogoId);
    }

    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {

        Jogo jogo = modelMapper.map(jogoDTORequest, Jogo.class);
        Jogo jogoSave = this.jogoRepository.save(jogo);
        JogoDTOResponse jogoDTOResponse = modelMapper.map(jogoSave, JogoDTOResponse.class);
        return jogoDTOResponse;
    }

    @Transactional
    public JogoDTOResponse atualizarJogo(Integer jogoId, JogoDTORequest jogoDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizar
        Jogo jogo = this.listarPorJogoId(jogoId);
        //se encontra o registro a ser atualizado
        if (jogo != null) {
            modelMapper.map(jogoDTORequest, jogo);
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //compatível com o repository para atualizar

            jogo.setCategoria(categoriaRepository.obterCategoriaPeloId(jogoDTORequest.getCategoriaId())
            );
            //com o objeto no formato correto tipo "jogo" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Jogo tempResponse = jogoRepository.save(jogo);
            return modelMapper.map(tempResponse, JogoDTOResponse.class);
        } else {
            return null;
        }
    }


    public JogoDTOUpdateResponse atualizarStatusJogo(Integer jogoId, JogoDTORequest jogoDTOUpdateRequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Jogo jogo = this.listarPorJogoId(jogoId);
        //se encontra o registro a ser atualizado
        if (jogo != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //compatível com o repository para atualizar
            jogo.setStatus(jogoDTOUpdateRequest.getStatus());
            //modelMapper.map(jogoDTOUpdateRequest,jogo);

            //com o objeto no formato correto tipo "jogo" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Jogo tempResponse = jogoRepository.save(jogo);
            return modelMapper.map(tempResponse, JogoDTOUpdateResponse.class);
        } else{
            return null;
        }
    }
    public void apagarJogo(Integer jogoId){
        jogoRepository.apagadoLogicoJogo(jogoId);
    }
}
