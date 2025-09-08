package com.senac.games.service;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateResponse;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.entity.Patrocinador;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import com.senac.games.repository.PatrocinadorRepository;
import com.senac.games.repository.PatrocinadorRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatrocinadorService {

    private final PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository,
                         ModelMapper modelMapper) {
        this.patrocinadorRepository = patrocinadorRepository;
        this.modelMapper = modelMapper;
    }

    public List<PatrocinadorDTOResponse> listarPatrocinadores() {
        return patrocinadorRepository.listarPatrocinadores()
                .stream()
                .map(patrocinador -> modelMapper.map(patrocinador, PatrocinadorDTOResponse.class))
                .toList()
                ;
    }

    public PatrocinadorDTOResponse listarPorPatrocinadorId(Integer patrocinadorId) {
        Patrocinador patrocinador = patrocinadorRepository.obterPatrocinadorPeloId(patrocinadorId);
        return (patrocinador != null) ? modelMapper.map(patrocinador, PatrocinadorDTOResponse.class) : null;
    }

    @Transactional
    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest) {
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTORequest, Patrocinador.class);
        Patrocinador PatrocinadorSave = this.patrocinadorRepository.save(patrocinador);
        return modelMapper.map(PatrocinadorSave, PatrocinadorDTOResponse.class);
    }

    @Transactional
    public PatrocinadorDTOResponse atualizarPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Patrocinador patrocinador = patrocinadorRepository.obterPatrocinadorPeloId(patrocinadorId);
        //se encontra o registro a ser atualizado
        if (patrocinador != null) {
            // atualiza dados do patrocinador a partir do DTO
            modelMapper.map(patrocinadorDTORequest, patrocinador);
            // atualiza a categoria vinculada
            Patrocinador tempResponse = patrocinadorRepository.save(patrocinador);
            return modelMapper.map(tempResponse, PatrocinadorDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza patrocinador inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public PatrocinadorDTOUpdateResponse atualizarStatusPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Patrocinador patrocinador = patrocinadorRepository.obterPatrocinadorPeloId(patrocinadorId);
        //se encontra o registro a ser atualizado
        if (patrocinador != null) {
            // atualiza o status do Patrocinador a partir do DTO
            patrocinador.setStatus(patrocinadorDTOUpdateRequest.getStatus());
            Patrocinador PatrocinadorSave = patrocinadorRepository.save(patrocinador);
            return modelMapper.map(PatrocinadorSave, PatrocinadorDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza patrocinador inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarPatrocinador(Integer patrocinadorId) {
        patrocinadorRepository.apagadoLogicoPatrocinador(patrocinadorId);
    }
}

