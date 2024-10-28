package com.eldars.application.service;

import com.eldars.application.port.in.TarjetaUseCase;
import com.eldars.application.service.exception.BusinessException;
import com.eldars.domain.Tarjeta;
import com.eldars.infra.adapter.outbound.EmailServiceImpl;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class TarjetaUseCaseImpl implements TarjetaUseCase {

    private final List<Tarjeta> tarjetas = new ArrayList<>(); // Almacenamiento en memoria
    private final EmailServiceImpl emailServiceImpl; // Servicio de envío de correos

    public TarjetaUseCaseImpl(EmailServiceImpl emailServiceImpl) {
            this.emailServiceImpl = emailServiceImpl;
    }

    @Override
    public void registrarTarjeta(Tarjeta tarjeta, String userEmail) {
        // Validar que la fecha de vencimiento sea válida
        if (tarjeta.getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new BusinessException("La tarjeta ha expirado.");
        }

        // Guardar la tarjeta en memoria
        tarjetas.add(tarjeta);

        // Enviar CVV y numero de la tarjeta al correo electrónico del usuario
        // TODO encriptar el CVV y NUMERO de la tarjeta
        String emailBody = String.format("Datos de la tarjeta:\nNúmero: %s\nCVV: %s", tarjeta.getNumeroTarjeta(), tarjeta.getCvv());
        emailServiceImpl.sendEmail(userEmail, "Datos de su tarjeta", emailBody);
    }
}
