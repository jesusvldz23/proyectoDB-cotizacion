package com.mycompany.proyectodb;

import java.sql.*;

public class CotizacionDb implements dbMetodos {
    // atributos
    private String MYSQLDRIVER;
    private String MYSQLURLDB;
    private Connection conexion;
    private PreparedStatement sqlConsulta;
    private ResultSet registro;
    private String consulta;

    // constructor
    public CotizacionDb() {
        this.MYSQLDRIVER = "com.mysql.cj.jdbc.Driver";
        //
        this.MYSQLURLDB = "jdbc:mysql://localhost:3306/sistemas?user=root&password=230520Jfvv";
    }

    // implementar los metodos de la interface dbMetodos
    @Override
    public boolean abrir() {
        boolean exito = false;
        try {
            // abrir la base de datos
            this.conexion = DriverManager.getConnection(this.MYSQLURLDB);
            exito = true;
        } catch (SQLException e) {
            System.out.println("Surgio un error al abrir " + e.getMessage());
            exito = false;
        }
        return exito;
    }

    @Override
    public boolean cerrar() {
        boolean exito = false;
        try {
            if (this.conexion != null && !this.conexion.isClosed()) {
                this.conexion.close();
                exito = true;
            }
        } catch (SQLException e) {
            exito = false;
        }
        return exito;
    }

    @Override
    public int insertar(Object obj) {
        Cotizacion cot = (Cotizacion) obj;
        int id = 0;
        try {
            this.abrir();
            this.consulta = "INSERT INTO Cotizacion(numCotizacion,descripcion,"
                    + "precio,porcentaje,plazos,fecha,status) "
                    + "VALUES(?,?,?,?,?,?,?);";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            // agregar valores a la consulta
            this.sqlConsulta.setString(1, cot.getNumCotizacion());
            this.sqlConsulta.setString(2, cot.getDescripcion());
            this.sqlConsulta.setFloat(3, cot.getPrecio());
            this.sqlConsulta.setFloat(4, cot.getPorcentaje());
            this.sqlConsulta.setInt(5, cot.getPlazos());
            this.sqlConsulta.setDate(6, cot.getFecha()); // debe ser java.sql.Date
            this.sqlConsulta.setInt(7, cot.getStatus());

            id = this.sqlConsulta.executeUpdate();
            this.cerrar();
        } catch (SQLException e) {
            System.out.println("Surgio un error " + e.getMessage());
            id = -1;
        }
        return id;
    }

    @Override
    public int actualizar(Object obj) {
        Cotizacion cot = (Cotizacion) obj;
        int id = 0;
        try {
            this.abrir();
            this.consulta = "UPDATE Cotizacion SET numCotizacion = ?, descripcion = ?, "
                    + "precio = ?, porcentaje = ?, plazos = ?, "
                    + "fecha = ?, status = ? WHERE idCotizacion = ?;";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            this.sqlConsulta.setString(1, cot.getNumCotizacion());
            this.sqlConsulta.setString(2, cot.getDescripcion());
            this.sqlConsulta.setFloat(3, cot.getPrecio());
            this.sqlConsulta.setFloat(4, cot.getPorcentaje());
            this.sqlConsulta.setInt(5, cot.getPlazos());
            this.sqlConsulta.setDate(6, cot.getFecha());
            this.sqlConsulta.setInt(7, cot.getStatus());
            this.sqlConsulta.setInt(8, cot.getIdCotizacion());

            id = this.sqlConsulta.executeUpdate();
            this.cerrar();
        } catch (SQLException e) {
            System.out.println("Surgio un error " + e.getMessage());
            id = -1;
        }
        return id;
    }

    // habilitar: status = 0 (activa)
    @Override
    public int habilitar(int id) {
        int idres = 0;
        try {
            this.abrir();
            this.consulta = "UPDATE Cotizacion SET status = ? WHERE idCotizacion = ?;";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            this.sqlConsulta.setInt(1, 0);
            this.sqlConsulta.setInt(2, id);
            idres = this.sqlConsulta.executeUpdate();
            this.cerrar();
        } catch (SQLException e) {
            System.err.println("Surgio un error " + e.getMessage());
            idres = -1;
        }
        return idres;
    }

    // deshabilitar: status = 1 (no activa)
    @Override
    public int deshabilitar(int id) {
        int idres = 0;
        try {
            this.abrir();
            this.consulta = "UPDATE Cotizacion SET status = ? WHERE idCotizacion = ?;";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            this.sqlConsulta.setInt(1, 1);
            this.sqlConsulta.setInt(2, id);
            idres = this.sqlConsulta.executeUpdate();
            this.cerrar();
        } catch (SQLException e) {
            System.err.println("Surgio un error " + e.getMessage());
            idres = -1;
        }
        return idres;
    }

    // buscar por id: regresa una Cotizacion o null si no existe
    @Override
    public Object buscarId(int id) {
        Cotizacion cot = null;
        try {
            this.abrir();
            this.consulta = "SELECT * FROM Cotizacion WHERE idCotizacion = ?;";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            this.sqlConsulta.setInt(1, id);
            this.registro = this.sqlConsulta.executeQuery();
            if (this.registro.next()) {
                cot = this.leerCotizacion(this.registro);
            }
            this.cerrar();
        } catch (SQLException e) {
            System.out.println("Surgio un error " + e.getMessage());
        }
        return cot;
    }

    // regresa las cotizaciones activas (status = 0)
    // Quien lo use debe llamar a cerrar() cuando termine de leerlo.
    @Override
    public ResultSet buscarActivos() {
        this.registro = null;
        try {
            this.abrir();
            this.consulta = "SELECT * FROM Cotizacion WHERE status = 0;";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            this.registro = this.sqlConsulta.executeQuery();
        } catch (SQLException e) {
            System.out.println("Surgio un error " + e.getMessage());
        }
        return this.registro;
    }

    // regresa las cotizaciones no activas (status = 1)
    @Override
    public ResultSet buscarNoActivos() {
        this.registro = null;
        try {
            this.abrir();
            this.consulta = "SELECT * FROM Cotizacion WHERE status = 1;";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            this.registro = this.sqlConsulta.executeQuery();
        } catch (SQLException e) {
            System.out.println("Surgio un error " + e.getMessage());
        }
        return this.registro;
    }

    // borrar fisicamente el registro
    @Override
    public int borrar(int id) {
        int idres = 0;
        try {
            this.abrir();
            this.consulta = "DELETE FROM Cotizacion WHERE idCotizacion = ?;";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            this.sqlConsulta.setInt(1, id);
            idres = this.sqlConsulta.executeUpdate();
            this.cerrar();
        } catch (SQLException e) {
            System.out.println("Surgio un error " + e.getMessage());
            idres = -1;
        }
        return idres;
    }

    // buscar por numero de cotizacion: regresa una Cotizacion o null
    @Override
    public Object buscar(String num) {
        Cotizacion cot = null;
        try {
            this.abrir();
            this.consulta = "SELECT * FROM Cotizacion WHERE numCotizacion = ?;";
            this.sqlConsulta = this.conexion.prepareStatement(this.consulta);
            this.sqlConsulta.setString(1, num);
            this.registro = this.sqlConsulta.executeQuery();
            if (this.registro.next()) {
                cot = this.leerCotizacion(this.registro);
            }
            this.cerrar();
        } catch (SQLException e) {
            System.out.println("Surgio un error " + e.getMessage());
        }
        return cot;
    }

    // metodo auxiliar: convierte la fila actual del ResultSet en un objeto Cotizacion
    private Cotizacion leerCotizacion(ResultSet rs) throws SQLException {
        Cotizacion cot = new Cotizacion();
        cot.setIdCotizacion(rs.getInt("idCotizacion"));
        cot.setNumCotizacion(rs.getString("numCotizacion"));
        cot.setDescripcion(rs.getString("descripcion"));
        cot.setPrecio(rs.getFloat("precio"));
        cot.setPorcentaje(rs.getFloat("porcentaje"));
        cot.setPlazos(rs.getInt("plazos"));
        cot.setFecha(rs.getDate("fecha"));
        cot.setStatus(rs.getInt("status"));
        return cot;
    }
}