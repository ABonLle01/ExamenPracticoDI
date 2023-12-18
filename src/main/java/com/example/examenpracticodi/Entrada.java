package com.example.examenpracticodi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrada {
    private String matricula;
    private String modelo;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String cliente;
    private String tarifa;
    private int coste;



}
