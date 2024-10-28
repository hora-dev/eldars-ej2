package com.eldars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.eldars.domain.Tarjeta;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {
    private String id;
    private Tarjeta tarjeta;
    private double monto;
    private String detalle;
    private String cvv;
}
