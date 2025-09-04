package com.senac.games.entity;

import jakarta.persistence.*;

@Entity
@Table(name="premio")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="premio_id")
    private int id;

    @Column(name="premio_descricao")
    private String descricao;

    @Column(name="premio_ordem_premiacao")
    private int ordemPremiacao;

    @Column(name="premio_categoria")
    private int categoria;

    @Column(name="premio_status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
