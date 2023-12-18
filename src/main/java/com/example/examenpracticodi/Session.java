package com.example.examenpracticodi;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Session {

    @Getter
    @Setter
    private static Entrada entradaActual = null;

    @Getter
    @Setter
    private static ArrayList<Entrada> entradas = new ArrayList<>(0);
}
