package com.una.a3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PainelSolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double potencia;
    private String marca;
    private Double area;
    private Double valor;
    private String descricao; // Adicionando o campo de descrição

    // Construtor completo
    public PainelSolar(Long id, String marca, Double potencia, Double area, Double valor) {
        this.id = id;
        this.potencia = potencia;
        this.marca = marca;
        this.area = area;
        this.valor = valor;
        this.descricao = marca + " " + potencia + "W"; // Definir a descrição com base na marca e potência
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPotencia() {
        return potencia;
    }

    public void setPotencia(Double potencia) {
        this.potencia = potencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return this.descricao = this.marca + " " + this.potencia + "W";
    }

}

