package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.response.*;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.entity.Participante;
import com.senac.games.entity.Participante;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import com.senac.games.repository.ParticipanteRepository;
import com.senac.games.repository.ParticipanteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public ParticipanteService(ParticipanteRepository participanteRepository,
                         ModelMapper modelMapper) {
        this.participanteRepository = participanteRepository;
        this.modelMapper = modelMapper;
    }

    public List<ParticipanteDTOResponse> listarParticipantes() {
        return participanteRepository.listarParticipantes()
                .stream()
                .map(participante -> modelMapper.map(participante, ParticipanteDTOResponse.class))
                .toList()
                ;
    }

    public ParticipanteDTOResponse listarPorParticipanteId(Integer participanteId) {
        Participante participante = participanteRepository.obterParticipantePeloId(participanteId);
        return (participante != null) ? modelMapper.map(participante, ParticipanteDTOResponse.class) : null;
    }

    @Transactional
    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest participanteDTORequest) {
        Participante participante = modelMapper.map(participanteDTORequest, Participante.class);
        Participante ParticipanteSave = this.participanteRepository.save(participante);
        return modelMapper.map(ParticipanteSave, ParticipanteDTOResponse.class);
    }

    @Transactional
    public ParticipanteDTOResponse atualizarParticipante(Integer participanteId, ParticipanteDTORequest participanteDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Participante participante = participanteRepository.obterParticipantePeloId(participanteId);
        //se encontra o registro a ser atualizado
        if (participante != null) {
            // atualiza dados do participante a partir do DTO
            modelMapper.map(participanteDTORequest, participante);
            // atualiza a categoria vinculada
            Participante tempResponse = participanteRepository.save(participante);
            return modelMapper.map(tempResponse, ParticipanteDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza participante inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ParticipanteDTOUpdateResponse atualizarStatusParticipante(Integer participanteId, ParticipanteDTORequest participanteDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Participante participante = participanteRepository.obterParticipantePeloId(participanteId);
        //se encontra o registro a ser atualizado
        if (participante != null) {
            // atualiza o status do Participante a partir do DTO
            participante.setStatus(participanteDTOUpdateRequest.getStatus());
            Participante ParticipanteSave = participanteRepository.save(participante);
            return modelMapper.map(ParticipanteSave, ParticipanteDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza participante inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarParticipante(Integer participanteId) {
        participanteRepository.apagadoLogicoParticipante(participanteId);
    }
}