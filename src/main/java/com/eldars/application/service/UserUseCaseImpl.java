package com.eldars.application.service;

import com.eldars.application.port.in.UserUseCase;
import com.eldars.application.service.exception.BusinessException;
import com.eldars.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserUseCaseImpl implements UserUseCase {
    private final List<Usuario> users = new ArrayList<>(); // Almacenamiento en memoria

    @Override
    public void registerUser(Usuario usuario) {
        // Validar que el DNI no esté duplicado
        if (users.stream().anyMatch(u -> u.getDni().equals(usuario.getDni()))) {
            throw new BusinessException("El DNI ya está registrado.");
        }
        users.add(usuario);
    }

    public List<Usuario> getUsers() {
        return users;
    }

}
