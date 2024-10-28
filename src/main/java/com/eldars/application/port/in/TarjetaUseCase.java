package com.eldars.application.port.in;

import com.eldars.domain.Tarjeta;

public interface TarjetaUseCase {
    void registrarTarjeta(Tarjeta tarjeta, String userEmail);
}
