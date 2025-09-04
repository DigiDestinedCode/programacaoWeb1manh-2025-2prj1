package com.senac.games.service;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import com.senac.games.repository.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrocinadorService {

    private final PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }

    public List<Patrocinador> listarPatrocinadors(){

        return this.patrocinadorRepository.listarPatrocinadors();
    }

    public Patrocinador listarPorPatrocinadorId(Integer patrocinadorId) {
        return this.patrocinadorRepository.obterPatrocinadorPeloId(patrocinadorId);
    }

    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest) {

        Patrocinador patrocinador = modelMapper.map(patrocinadorDTORequest, Patrocinador.class);
        Patrocinador patrocinadorSave = this.patrocinadorRepository.save(patrocinador);
        PatrocinadorDTOResponse patrocinadorDTOResponse = modelMapper.map(patrocinadorSave, PatrocinadorDTOResponse.class);
        return patrocinadorDTOResponse;
    }

    public PatrocinadorDTOResponse atualizarPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTORequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Patrocinador patrocinador = this.listarPorPatrocinadorId(patrocinadorId);
        //se encontra o registro a ser atualizado
        if (patrocinador != null){
            modelMapper.map(patrocinadorDTORequest,patrocinador);
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            //com o objeto no formato correto tipo "patrocinador" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Patrocinador tempResponse = patrocinadorRepository.save(patrocinador);
            return modelMapper.map(tempResponse,PatrocinadorDTOResponse.class);
        } else{
            return null;
        }

    }

    public PatrocinadorDTOUpdateResponse atualizarStatusPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTOUpdateRequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Patrocinador patrocinador = this.listarPorPatrocinadorId(patrocinadorId);
        //se encontra o registro a ser atualizado
        if (patrocinador != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            patrocinador.setStatus(patrocinadorDTOUpdateRequest.getStatus());
            //modelMapper.map(patrocinadorDTOUpdateRequest,patrocinador);

            //com o objeto no formato correto tipo "patrocinador" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Patrocinador tempResponse = patrocinadorRepository.save(patrocinador);
            return modelMapper.map(tempResponse, PatrocinadorDTOUpdateResponse.class);
        } else{
            return null;
        }
    }
    public void apagarPatrocinador(Integer patrocinadorId){
        patrocinadorRepository.apagadoLogicoPatrocinador(patrocinadorId);
    }
}
