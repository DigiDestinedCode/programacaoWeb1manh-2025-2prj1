package com.senac.games.dto.response;

import java.util.Set;

public class CategoriaDTOResponse {
    private Integer id;
    private String nome;
    private Integer status;
    private Set<JogoDTOResponse> jogos;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<JogoDTOResponse> getJogos() {
        return jogos;
    }

    public void setJogos(Set<JogoDTOResponse> jogos) {
        this.jogos = jogos;
    }
}
