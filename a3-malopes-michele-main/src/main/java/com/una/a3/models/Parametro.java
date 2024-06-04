package com.una.a3.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parametros")
public class Parametro {

    @Id
    @Column(name = "id_parametro")
    private Long idParametro;


    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}