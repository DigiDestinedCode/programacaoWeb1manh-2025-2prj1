package com.senac.games.dto.request;

public class PatrocinadorDTORequest {
    private String nome;
    private String representanteNome;
    private Integer status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRepresentanteNome() {
        return representanteNome;
    }

    public void setRepresentanteNome(String representanteNome) {
        this.representanteNome = representanteNome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
