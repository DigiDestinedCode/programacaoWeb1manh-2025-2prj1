package com.senac.games.dto.response;

import com.senac.games.entity.Inscricao;

import java.util.Set;

public class ParticipanteDTOResponse {
    private Integer id;
    private String nome;
    private String email;
    private String identificacao;
    private String endereco;
    private Integer status;
    private Set<Inscricao> Inscricoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Inscricao> getInscricoes() {
        return Inscricoes;
    }

    public void setInscricoes(Set<Inscricao> inscricoes) {
        Inscricoes = inscricoes;
    }
}
