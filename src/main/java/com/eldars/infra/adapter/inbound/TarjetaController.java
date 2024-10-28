package com.eldars.infra.adapter.inbound;


import com.eldars.application.service.TarjetaUseCaseImpl;
import com.eldars.domain.Tarjeta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tarjetas")
@RequiredArgsConstructor
public class TarjetaController {

    private final TarjetaUseCaseImpl tarjetaService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody Tarjeta card, @RequestParam String email) {
        tarjetaService.registrarTarjeta(card, email);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Tarjeta>> getCards() {
        return ResponseEntity.ok(tarjetaService.getTarjetas());
    }
}