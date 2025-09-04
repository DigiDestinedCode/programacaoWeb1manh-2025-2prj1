package com.senac.games.service;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.dto.response.ParticipanteDTOUpdateResponse;
import com.senac.games.entity.Participante;
import com.senac.games.repository.ParticipanteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public List<Participante> listarParticipantes(){

        return this.participanteRepository.listarParticipantes();
    }

    public Participante listarPorParticipanteId(Integer participanteId) {
        return this.participanteRepository.obterParticipantePeloId(participanteId);
    }

    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest participanteDTORequest) {

        Participante participante = modelMapper.map(participanteDTORequest, Participante.class);
        Participante participanteSave = this.participanteRepository.save(participante);
        ParticipanteDTOResponse participanteDTOResponse = modelMapper.map(participanteSave, ParticipanteDTOResponse.class);
        return participanteDTOResponse;
    }

    public ParticipanteDTOResponse atualizarParticipante(Integer participanteId, ParticipanteDTORequest participanteDTORequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Participante participante = this.listarPorParticipanteId(participanteId);
        //se encontra o registro a ser atualizado
        if (participante != null){
            modelMapper.map(participanteDTORequest,participante);
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            //com o objeto no formato correto tipo "participante" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Participante tempResponse = participanteRepository.save(participante);
            return modelMapper.map(tempResponse,ParticipanteDTOResponse.class);
        } else{
            return null;
        }

    }

    public ParticipanteDTOUpdateResponse atualizarStatusParticipante(Integer participanteId, ParticipanteDTORequest participanteDTOUpdateRequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Participante participante = this.listarPorParticipanteId(participanteId);
        //se encontra o registro a ser atualizado
        if (participante != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            participante.setStatus(participanteDTOUpdateRequest.getStatus());
            //modelMapper.map(participanteDTOUpdateRequest,participante);

            //com o objeto no formato correto tipo "participante" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Participante tempResponse = participanteRepository.save(participante);
            return modelMapper.map(tempResponse, ParticipanteDTOUpdateResponse.class);
        } else{
            return null;
        }
    }
    public void apagarParticipante(Integer participanteId){
        participanteRepository.apagadoLogicoParticipante(participanteId);
    }
}
