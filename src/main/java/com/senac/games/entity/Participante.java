package com.senac.games.entity;


import jakarta.persistence.*;

import java.io.File;
import java.util.Set;

@Entity
@Table(name="participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="participante_id")
    private Integer id;

    @Column(name="participante_nome")
    private String nome;


    @Column(name="participante_email")
    private String email;
    @Column(name="participante_identificacao")
    private String identificacao;

    @Column(name="participante_endereco")
    private String endereco;

    @Column(name="participante_foto_perfil")
    private File fotoPerfil;

    @Column(name="participante_status")
    private Integer status;

    @OneToMany(mappedBy = "participante")
    private Set<Inscricao> Inscricoes;

    public Set<Inscricao> getInscricoes() {
        return Inscricoes;
    }

    public void setInscricoes(Set<Inscricao> Inscricoes) {
        this.Inscricoes = Inscricoes;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public File getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(File fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
