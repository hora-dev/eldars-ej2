package com.eldars.infra.adapter.inbound;

import com.eldars.application.service.UserUseCaseImpl;
import com.eldars.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController  {

    private final UserUseCaseImpl userService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody Usuario user) {
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}