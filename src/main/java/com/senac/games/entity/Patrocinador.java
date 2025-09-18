package com.senac.games.entity;

import jakarta.persistence.*;

@Entity
@Table(name="patrocinador")
public class Patrocinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="patrocinador_id")
    private Integer id;
    @Column(name="patrocinador_nome")
    private String nome;
    @Column(name="patrocinador_representante_nome")
    private String representanteNome;
    @Column(name="patrocinador_status")
    private Integer status;

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
