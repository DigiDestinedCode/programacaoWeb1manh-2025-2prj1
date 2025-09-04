package com.senac.games.dto.request;

public class PremioDTORequest {
    private String descricao;
    private int ordemPremiacao;
    private int categoria;
    private int status;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getOrdemPremiacao() {
        return ordemPremiacao;
    }

    public void setOrdemPremiacao(int ordemPremiacao) {
        this.ordemPremiacao = ordemPremiacao;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
