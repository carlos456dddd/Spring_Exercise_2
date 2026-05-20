package com.posexample.springexample;


import com.posexample.springexample.dto.userRequest;
import com.posexample.springexample.dto.userResponse;

import com.posexample.springexample.model.User;
import com.posexample.springexample.repository.userRepository;
import com.posexample.springexample.service.userService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestForUserService {

    @InjectMocks
    private userService service;
    @Mock
    private userRepository repo;

    @Test
    @DisplayName("Creación de usuario")
    void creationDeUsuario() {

        User rpt1 = User.builder()
                .Id(1L)
                .username("carlos")
                .email("cama@sd")
                .password("1234")
                .createdAt(LocalDateTime.now())
                .build();

        when(repo.save(any(User.class))).thenReturn(rpt1);

        userResponse rp = service.createUser(
                userRequest.builder()
                        .username("carlos")
                        .password("1234")
                        .email("cama@sd")
                        .build()
        );

        ArgumentCaptor<User> Eso = ArgumentCaptor.forClass(User.class);

        verify(repo).save(Eso.capture());
        assertEquals("carlos", rp.username());
        assertEquals("cama@sd", rp.email());

    }

    @Test
    @DisplayName("Buscar usuario por el atributo identificador")
    void BuscarUsuarioAtributoIdentificador() {

        User rpt = User.builder()
                .Id(1L)
                .username("carlos")
                .email("st@gmail.com")
                .createdAt(LocalDateTime.now())
                .build();

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(rpt));


        userResponse result = service.findById(1L);

        assertEquals("carlos", result.username());
        assertEquals("st@gmail.com", result.email());
    }


}
