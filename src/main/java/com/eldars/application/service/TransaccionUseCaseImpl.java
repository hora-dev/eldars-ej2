package com.eldars.application.service;

import com.eldars.application.port.in.TransaccionUseCase;
import com.eldars.application.service.exception.BusinessException;
import com.eldars.domain.Tarjeta;
import com.eldars.domain.Transaccion;
import com.eldars.infra.adapter.outbound.EmailServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransaccionUseCaseImpl implements TransaccionUseCase {
    private final List<Transaccion> transacciones = new ArrayList<>(); // Almacenamiento en memoria
    private final List<Tarjeta> tarjetas; // Suponiendo que tienes acceso a las tarjetas (podrías inyectar el CardService)
    private final TarjetaUseCaseImpl tarjetaUseCaseImpl;

    private final EmailServiceImpl emailServiceImpl;

    public TransaccionUseCaseImpl(TarjetaUseCaseImpl tarjetaUseCaseImpl, EmailServiceImpl emailServiceImpl) {
        this.tarjetaUseCaseImpl = tarjetaUseCaseImpl;
        this.emailServiceImpl = emailServiceImpl;
        this.tarjetas = tarjetaUseCaseImpl.getTarjetas();
    }

    @Override
    public void processTransaction(Transaccion transaccion) {
        // Validar el monto de la transacción
        if (transaccion.getMonto() > 10000) {
            throw new BusinessException("El monto no puede ser mayor a 10.000 pesos.");
        }

        // Validar que la tarjeta esté activa y su fecha de vencimiento
        if (transaccion.getTarjeta().getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new BusinessException("La tarjeta ha expirado.");
        }

        // Validar el CVV
        if (!transaccion.getCvv().equals(transaccion.getTarjeta().getCvv())) {
            throw new BusinessException("El CVV es incorrecto.");
        }

        // Lógica para calcular y notificar la tasa al usuario
        double fee = calculateFee(transaccion);

        // Guardar la transacción en memoria
        transacciones.add(transaccion);

        // Notificar al usuario sobre la transacción
        emailServiceImpl.sendEmail(transaccion.getTarjeta().getUsuarioTarjeta().getEmail(),
                "Transacción realizada", "Compra con tasa " + fee + " " + transaccion.toString());
    }

    private double calculateFee(Transaccion transaction) {
        // Calcular la tasa en función de la marca de la tarjeta
        switch (transaction.getTarjeta().getMarca()) {
            case "VISA":
                return transaction.getMonto() * (LocalDate.now().getYear() / (double) LocalDate.now().getMonthValue());
            case "NARA":
                return transaction.getMonto() * (LocalDate.now().getDayOfMonth() * 0.5);
            case "AMEX":
                return transaction.getMonto() * (LocalDate.now().getMonthValue() * 0.1);
            default:
                throw new BusinessException("Marca de tarjeta no soportada.");
        }
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
}
