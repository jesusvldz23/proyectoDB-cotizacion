package com.mycompany.proyectodb;

import java.sql.ResultSet;

public interface dbMetodos {
    public boolean abrir();
    public boolean cerrar();
    public int insertar(Object obj);
    public int actualizar(Object obj);
    public int habilitar(int id);
    public int deshabilitar(int id);
    public Object buscarId(int id);
    public ResultSet buscarActivos();
    public ResultSet buscarNoActivos();
    public int borrar(int id);
    public Object buscar(String num);
}