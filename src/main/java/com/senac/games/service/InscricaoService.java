package com.senac.games.service;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.InscricaoDTOUpdateResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.repository.InscricaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InscricaoService(InscricaoRepository inscricaoRepository) {
        this.inscricaoRepository = inscricaoRepository;
    }

    public List<Inscricao> listarInscricoes(){

        return this.inscricaoRepository.listarInscricoes();
    }

    public Inscricao listarPorInscricaoId(Integer inscricaoId) {
        return this.inscricaoRepository.obterInscricaoPeloId(inscricaoId);
    }

    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest inscricaoDTORequest) {

        Inscricao inscricao = modelMapper.map(inscricaoDTORequest, Inscricao.class);
        Inscricao inscricaoSave = this.inscricaoRepository.save(inscricao);
        InscricaoDTOResponse inscricaoDTOResponse = modelMapper.map(inscricaoSave, InscricaoDTOResponse.class);
        return inscricaoDTOResponse;
    }

    public InscricaoDTOResponse atualizarInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTORequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Inscricao inscricao = this.listarPorInscricaoId(inscricaoId);
        //se encontra o registro a ser atualizado
        if (inscricao != null){
            modelMapper.map(inscricaoDTORequest,inscricao);
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            //com o objeto no formato correto tipo "inscricao" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Inscricao tempResponse = inscricaoRepository.save(inscricao);
            return modelMapper.map(tempResponse,InscricaoDTOResponse.class);
        } else{
            return null;
        }

    }

    public InscricaoDTOUpdateResponse atualizarStatusInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTOUpdateRequest){
        //antes de atualizar busca se existe o registro a ser atualizar
        Inscricao inscricao = this.listarPorInscricaoId(inscricaoId);
        //se encontra o registro a ser atualizado
        if (inscricao != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto
            //que é compatível com o repository para atualizar
            inscricao.setStatus(inscricaoDTOUpdateRequest.getStatus());
            //modelMapper.map(inscricaoDTOUpdateRequest,inscricao);

            //com o objeto no formato correto tipo "inscricao" o comando "save" salva;;
            //no banco de dados o objeto é atualizado
            Inscricao tempResponse = inscricaoRepository.save(inscricao);
            return modelMapper.map(tempResponse, InscricaoDTOUpdateResponse.class);
        } else{
            return null;
        }
    }
    public void apagarInscricao(Integer inscricaoId){
        inscricaoRepository.apagadoLogicoInscricao(inscricaoId);
    }
}
