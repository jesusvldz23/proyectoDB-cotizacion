package com.mycompany.proyectodb;

import java.sql.Date;

public class Cotizacion {
    // atributos
    private int idCotizacion;
    private String numCotizacion;
    private String descripcion;
    private float porcentaje;
    private float precio;
    private int plazos;
    private Date fecha;
    private int status; // 0 = activa, 1 = deshabilitada

    // constructor por omision
    public Cotizacion() {
        this.idCotizacion = 0;
        this.numCotizacion = "";
        this.descripcion = "";
        this.porcentaje = 0.0f;
        this.precio = 0.0f;
        this.plazos = 0;
        this.fecha = null;
        this.status = 0;
    }

    // constructor por argumento
    public Cotizacion(int idCotizacion, String numCotizacion, String descripcion, float porcentaje,
            float precio, int plazos, Date fecha, int status) {
        this.idCotizacion = idCotizacion;
        this.numCotizacion = numCotizacion;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
        this.precio = precio;
        this.plazos = plazos;
        this.fecha = fecha;
        this.status = status;
    }

    // metodos set/get
    public int getIdCotizacion() { return idCotizacion; }
    public void setIdCotizacion(int idCotizacion) { this.idCotizacion = idCotizacion; }

    public String getNumCotizacion() { return numCotizacion; }
    public void setNumCotizacion(String numCotizacion) { this.numCotizacion = numCotizacion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public float getPorcentaje() { return porcentaje; }
    public void setPorcentaje(float porcentaje) { this.porcentaje = porcentaje; }

    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }

    public int getPlazos() { return plazos; }
    public void setPlazos(int plazos) { this.plazos = plazos; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    // metodos de comportamiento

    // pago inicial = precio * (porcentaje / 100)
    public float calcularPagoInicial() {
        float pago = 0.0f;
        pago = this.precio * (this.porcentaje / 100);
        return pago;
    }

    // total a financiar = precio - pago inicial
    public float calcularTotalFin() {
        float total = 0.0f;
        total = this.precio - this.calcularPagoInicial();
        return total;
    }

    // pago mensual = total a financiar / plazos
    public float calcularPagoMensual() {
        float pago = 0.0f;
        if (this.plazos > 0) {
            pago = this.calcularTotalFin() / this.plazos;
        }
        return pago;
    }
}