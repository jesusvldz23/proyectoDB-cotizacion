package com.mycompany.proyectodb;

import java.sql.Date;

public class TestDb {
    public static void main(String[] args) {
        // declarar objetos
        Cotizacion cot = new Cotizacion();
        CotizacionDb db = new CotizacionDb();

        // llenar la cotizacion de prueba
        cot.setNumCotizacion("COT-001");
        cot.setDescripcion("Laptop de prueba");
        cot.setPrecio(20000.0f);
        cot.setPorcentaje(20.0f);
        cot.setPlazos(12);
        cot.setFecha(Date.valueOf("2026-11-22"));
        cot.setStatus(0);

        // 1. insertar
        int res = db.insertar(cot);
        System.out.println("Insertar -> " + res + " (1 = exito, -1 = error)");

        // 2. buscar por numero
        Cotizacion encontrada = (Cotizacion) db.buscar("COT-001");
        if (encontrada != null) {
            System.out.println("Encontrada: " + encontrada.getDescripcion());
            System.out.println("Pago inicial: " + encontrada.calcularPagoInicial());
            System.out.println("Total a financiar: " + encontrada.calcularTotalFin());
            System.out.println("Pago mensual: " + encontrada.calcularPagoMensual());
        } else {
            System.out.println("No se encontro la cotizacion");
        }
    }
}