package com.senac.games.service;

import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.response.*;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.dto.response.PremioDTOUpdateResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Premio;
import com.senac.games.entity.Premio;
import com.senac.games.entity.Premio;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.PremioRepository;
import com.senac.games.repository.PremioRepository;
import com.senac.games.repository.PremioRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PremioService {

    private final PremioRepository premioRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public PremioService(PremioRepository premioRepository,
                       ModelMapper modelMapper) {
        this.premioRepository = premioRepository;
        this.modelMapper = modelMapper;
    }

    public List<PremioDTOResponse> listarPremios() {
        return premioRepository.listarPremios()
                .stream()
                .map(premio -> modelMapper.map(premio, PremioDTOResponse.class))
                .toList()
                ;
    }

    public PremioDTOResponse listarPorPremioId(Integer premioId) {
        Premio premio = premioRepository.obterPremioPeloId(premioId);
        return (premio != null) ? modelMapper.map(premio, PremioDTOResponse.class) : null;
    }

    @Transactional
    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest) {
        Premio premio = modelMapper.map(premioDTORequest, Premio.class);
        Premio PremioSave = this.premioRepository.save(premio);
        return modelMapper.map(PremioSave, PremioDTOResponse.class);
    }

    @Transactional
    public PremioDTOResponse atualizarPremio(Integer premioId, PremioDTORequest premioDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Premio premio = premioRepository.obterPremioPeloId(premioId);
        //se encontra o registro a ser atualizado
        if (premio != null) {
            // atualiza dados do premio a partir do DTO
            modelMapper.map(premioDTORequest, premio);
            // atualiza a categoria vinculada
            Premio tempResponse = premioRepository.save(premio);
            return modelMapper.map(tempResponse, PremioDTOResponse.class);
        } else {
            // Error 400 caso tente atualiza premio inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public PremioDTOUpdateResponse atualizarStatusPremio(Integer premioId, PremioDTORequest premioDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizado
        Premio premio = premioRepository.obterPremioPeloId(premioId);
        //se encontra o registro a ser atualizado
        if (premio != null) {
            // atualiza o status do Premio a partir do DTO
            premio.setStatus(premioDTOUpdateRequest.getStatus());
            Premio PremioSave = premioRepository.save(premio);
            return modelMapper.map(PremioSave, PremioDTOUpdateResponse.class);
        } else {
            // Error 400 caso tente atualiza premio inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarPremio(Integer premioId) {
        premioRepository.apagadoLogicoPremio(premioId);
    }
}
