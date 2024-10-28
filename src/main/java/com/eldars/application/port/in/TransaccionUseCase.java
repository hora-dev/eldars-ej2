package com.eldars.application.port.in;

import com.eldars.domain.Transaccion;

public interface TransaccionUseCase {
    void processTransaction(Transaccion transaccion);
}
