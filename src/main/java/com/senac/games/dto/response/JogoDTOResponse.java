package com.senac.games.dto.response;

public class JogoDTOResponse {
    private int id;
    private String nome;
    private int status;
    private int inscricaoId;
    private CategoriaDTOResponse categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(int inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public CategoriaDTOResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTOResponse categoria) {
        this.categoria = categoria;
    }
}
