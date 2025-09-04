package com.senac.games.service;

import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.dto.response.PremioDTOUpdateResponse;
import com.senac.games.entity.Premio;
import com.senac.games.entity.Premio;
import com.senac.games.repository.PremioRepository;
import com.senac.games.repository.PremioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {

    private final PremioRepository premioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    public List<Premio> listarPremios(){

        return this.premioRepository.listarPremios();
    }

    public Premio listarPorPremioId(Integer premioId) {
        return this.premioRepository.obterPremioPeloId(premioId);
    }

    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest) {

        Premio premio = modelMapper.map(premioDTORequest, Premio.class);
        Premio premioSave = this.premioRepository.save(premio);
        PremioDTOResponse premioDTOResponse = modelMapper.map(premioSave, PremioDTOResponse.class);
        return premioDTOResponse;
    }

    public PremioDTOResponse atualizarPremio(Integer premioId, PremioDTORequest premioDTORequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Premio premio = this.listarPorPremioId(premioId);
        //se encontra o registro a ser atualizado
        if (premio != null){
            modelMapper.map(premioDTORequest,premio);
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            //com o objeto no formato correto tipo "premio" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Premio tempResponse = premioRepository.save(premio);
            return modelMapper.map(tempResponse,PremioDTOResponse.class);
        } else{
            return null;
        }

    }

    public PremioDTOUpdateResponse atualizarStatusPremio(Integer premioId, PremioDTORequest premioDTOUpdateRequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Premio premio = this.listarPorPremioId(premioId);
        //se encontra o registro a ser atualizado
        if (premio != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            premio.setStatus(premioDTOUpdateRequest.getStatus());
            //modelMapper.map(premioDTOUpdateRequest,premio);

            //com o objeto no formato correto tipo "premio" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Premio tempResponse = premioRepository.save(premio);
            return modelMapper.map(tempResponse, PremioDTOUpdateResponse.class);
        } else{
            return null;
        }
    }
    public void apagarPremio(Integer premioId){
        premioRepository.apagadoLogicoPremio(premioId);
    }
}
