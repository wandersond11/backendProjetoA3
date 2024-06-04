package com.una.a3.dto;

public class UsuarioDTO {
    private final Long id;
    private final String nome;

    public UsuarioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
