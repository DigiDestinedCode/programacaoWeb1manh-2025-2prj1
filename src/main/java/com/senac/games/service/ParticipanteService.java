package com.senac.games.service;

import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.entity.Participante;
import com.senac.games.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ParticipanteService {

    private ParticipanteRepository participanteRepository;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public List<Participante> listarParticipantes(){
        return this.participanteRepository.findAll();
    }

    public Participante listarPorParticipanteId(Integer participanteId) {
        return this.participanteRepository.findById(participanteId).orElse(null);
    }

    public Participante criarParticipante(ParticipanteDTOResponse participanteDTO) {
        Participante participante = new Participante();
        participante.setNome(participanteDTO.getNome());
        participante.setIdentificacao(participanteDTO.getIdentificacao());
        participante.setEndereco(participanteDTO.getEndereco());
        participante.setStatus(participanteDTO.getStatus());

        Participante partipanteSave = this.participanteRepository.save(participante);

        ParticipanteDTOResponse participanteDTOResponse = new ParticipanteDTOResponse();
        ParticipanteDTOResponse.setId(partipanteSave.getId());
        ParticipanteDTOResponse.setNome(partipanteSave.getNome());
        ParticipanteDTOResponse.setEmail(partipanteSave.getEmail());
        ParticipanteDTOResponse.setIdentificacao(partipanteSave.getIdentificacao());
        ParticipanteDTOResponse.setEndereco(partipanteSave.getEndereco());
        ParticipanteDTOResponse.setStatus(partipanteSave.getStatus());


        return this.participanteRepository.save(participante);

    }

}
