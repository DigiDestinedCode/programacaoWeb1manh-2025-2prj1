package com.senac.games.dto.response;

import java.time.LocalDateTime;

public class InscricaoDTOResponse {
    private Integer id;
    private LocalDateTime data;
    private Integer status;
    private ParticipanteDTOResponse participante;
    private JogoDTOResponse jogo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ParticipanteDTOResponse getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteDTOResponse participante) {
        this.participante = participante;
    }

    public JogoDTOResponse getJogo() {
        return jogo;
    }

    public void setJogo(JogoDTOResponse jogo) {
        this.jogo = jogo;
    }
}
