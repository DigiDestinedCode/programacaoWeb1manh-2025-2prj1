package com.senac.games.dto.response;

import java.time.LocalDateTime;

public class InscricaoDTOResponse {
    private int id;
    private LocalDateTime data;
    private int status;
    private ParticipanteDTOResponse participante;
    private JogoDTOResponse jogo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
