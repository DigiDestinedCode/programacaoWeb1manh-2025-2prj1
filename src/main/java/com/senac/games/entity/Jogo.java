package com.senac.games.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue
    @Column(name = "jogo_id")
    private Integer id;

    @Column(name = "jogo_nome")
    private String nome;

    @Column(name = "jogo_status")
    private Integer status;

    @Transient
    @JsonProperty("idCategoria")
    private Integer idCategoria;
    public Integer getIdCategoria() {
        return this.categoria.getId();
    }


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "jogo")
    private Set<Inscricao> inscricoes;

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

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(Set<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }
}
