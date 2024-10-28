package com.eldars.infra.adapter.inbound;

import com.eldars.application.service.TransaccionUseCaseImpl;
import com.eldars.domain.Transaccion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
@RequiredArgsConstructor
public class TransaccionController {
    private final TransaccionUseCaseImpl transactionService;

    @PostMapping
    public ResponseEntity<Void> process(@RequestBody Transaccion transaction) {
        transactionService.processTransaction(transaction);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Transaccion>> getTransactions() {
        return ResponseEntity.ok(transactionService.getTransacciones());
    }
}