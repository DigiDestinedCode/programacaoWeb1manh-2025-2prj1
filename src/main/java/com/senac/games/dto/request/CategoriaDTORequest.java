package com.senac.games.dto.request;

public class CategoriaDTORequest {
    private String nome;
    private Integer status;

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
}
