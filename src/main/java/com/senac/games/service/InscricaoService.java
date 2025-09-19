package com.senac.games.service;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.InscricaoDTOUpdateResponse;
import com.senac.games.entity.Jogo;
import com.senac.games.entity.Participante;
import com.senac.games.entity.Inscricao;
import com.senac.games.repository.JogoRepository;
import com.senac.games.repository.ParticipanteRepository;
import com.senac.games.repository.InscricaoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final ParticipanteRepository participanteRepository;
    private final JogoRepository jogoRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public InscricaoService(InscricaoRepository inscricaoRepository,
                            ParticipanteRepository participanteRepository,
                            JogoRepository jogoRepository,
                            ModelMapper modelMapper) {
        this.inscricaoRepository = inscricaoRepository;
        this.participanteRepository = participanteRepository;
        this.jogoRepository = jogoRepository;
        this.modelMapper = modelMapper;
    }

    public List<InscricaoDTOResponse> listarInscricoes() {
        return inscricaoRepository.listarInscricoes()
                .stream()
                .map(inscricao -> modelMapper.map(inscricao, InscricaoDTOResponse.class))
                .toList()
                ;
    }

    public InscricaoDTOResponse listarPorInscricaoId(Integer inscricaoId) {
        Inscricao inscricao = inscricaoRepository.obterInscricaoPeloId(inscricaoId);
        return (inscricao != null) ? modelMapper.map(inscricao, InscricaoDTOResponse.class) : null;
    }

    @Transactional
    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricao = modelMapper.map(inscricaoDTORequest, Inscricao.class);
        Participante participante = participanteRepository.obterParticipantePeloId(inscricaoDTORequest.getParticipanteId());
        Jogo jogo = jogoRepository.obterJogoPeloId(inscricaoDTORequest.getJogoId());
        if(participante != null && jogo != null){
            inscricao.setParticipante(participante);
            inscricao.setJogo(jogo);

            Inscricao InscricaoSave = inscricaoRepository.save(inscricao);
            return modelMapper.map(InscricaoSave, InscricaoDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public InscricaoDTOResponse atualizarInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Inscricao inscricao = inscricaoRepository.obterInscricaoPeloId(inscricaoId);
        //se encontra o registro a ser atualizado
        if (inscricao != null) {
            // atualiza dados do inscricao a partir do DTO
            modelMapper.map(inscricaoDTORequest, inscricao);
            // atualiza a participante vinculada
            inscricao.setParticipante(participanteRepository.obterParticipantePeloId(inscricaoDTORequest.getParticipanteId()));
            inscricao.setJogo(jogoRepository.obterJogoPeloId(inscricaoDTORequest.getJogoId()));
            Inscricao InscricaoSave = inscricaoRepository.save(inscricao);
            return modelMapper.map(InscricaoSave, InscricaoDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza inscricao inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public InscricaoDTOUpdateResponse atualizarStatusInscricao(Integer inscricaoId, InscricaoDTORequest InscricaoDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Inscricao inscricao = inscricaoRepository.obterInscricaoPeloId(inscricaoId);
        //se encontra o registro a ser atualizado
        if (inscricao != null) {
            // atualiza o status do Inscricao a partir do DTO
            inscricao.setStatus(InscricaoDTORequest.getStatus());
            Inscricao InscricaoSave = inscricaoRepository.save(inscricao);
            return modelMapper.map(InscricaoSave, InscricaoDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza inscricao inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarInscricao(Integer inscricaoId) {
        inscricaoRepository.apagadoLogicoInscricao(inscricaoId);
    }
}
