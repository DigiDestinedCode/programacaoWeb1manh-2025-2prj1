package com.senac.games.dto.response;

public class JogoDTOResponse {
    private Integer id;
    private String nome;
    private Integer status;

    private Integer inscricaoId;
    private CategoriaDTOResponse categoria;

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

    public Integer getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(Integer inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public CategoriaDTOResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTOResponse categoria) {
        this.categoria = categoria;
    }
}
