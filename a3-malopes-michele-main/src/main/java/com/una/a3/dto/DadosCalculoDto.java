package com.una.a3.dto;

import com.una.a3.models.PainelSolar;

public class DadosCalculoDto {

    private final double mediaKwh;
    private final double valorKwh;
    private final double horasSol;
    private final int tensao;

    private final Long idPainel;
    public DadosCalculoDto(final double mediaKwh,
                           final double valorKwh,
                           final double horasSol,
                           final int tensao,
                           final Long idPainel) {
        this.mediaKwh = mediaKwh;
        this.valorKwh = valorKwh;
        this.horasSol = horasSol;
        this.tensao = tensao;
        this.idPainel = idPainel;
    }

    public Long getIdPainel() {
        return idPainel;
    }

    public double getMediaKwh() {
        return mediaKwh;
    }

    public double getValorKwh() {
        return valorKwh;
    }

    public double getHorasSol() {
        return horasSol;
    }

    public int getTensao() {
        return tensao;
    }
}
