package com.eldars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta {
    private String marca;
    private String numeroTarjeta;
    private LocalDate fechaVencimiento;
    private String cvv;
    private Usuario usuarioTarjeta;
}
