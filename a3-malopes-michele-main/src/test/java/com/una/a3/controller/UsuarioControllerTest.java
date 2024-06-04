package com.una.a3.controller;


import com.una.a3.dto.LoginDto;
import com.una.a3.dto.UsuarioDTO;
import com.una.a3.models.Usuario;
import com.una.a3.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsuarioById() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("teste");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        ResponseEntity<Usuario> responseEntity = usuarioController.getUsuarioById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }

    @Test
    void testGetUsuarioByIdUsuarioNaoEncontrado() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<Usuario> responseEntity = usuarioController.getUsuarioById(id);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }


    @Test
    void testSaveUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> responseEntity = usuarioController.saveUsuario(usuario);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }

    @Test
    void testUpdateUsuario() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Teste");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> responseEntity = usuarioController.updateUsuario(id, usuario);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }

    @Test
    void testDeleteUsuario() {
        Long id = 1L;
        ResponseEntity<Void> responseEntity = usuarioController.deleteUsuario(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    void testLogin() {
        LoginDto loginDto = new LoginDto("username", "password");
        Usuario usuario = new Usuario();
        usuario.setNome("username");
        usuario.setSenha("password");
        when(usuarioRepository.findByNome("username")).thenReturn(Optional.of(usuario));

        ResponseEntity<?> responseEntity = usuarioController.login(loginDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(UsuarioDTO.class, responseEntity.getBody().getClass());
    }
}
