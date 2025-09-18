package com.senac.games.dto.request;

public class PremioDTORequest {
    private String descricao;
    private Integer ordemPremiacao;
    private Integer categoria;
    private Integer status;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getOrdemPremiacao() {
        return ordemPremiacao;
    }

    public void setOrdemPremiacao(Integer ordemPremiacao) {
        this.ordemPremiacao = ordemPremiacao;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
