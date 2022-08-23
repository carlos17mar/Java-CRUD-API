/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg14;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static pkg14.Empleado.datetoSql;
import static pkg14.OficinaDAO.*;
import static pkg14.OficinaDAO.readID;

/**
 *
 * @author carlos
 */
public class EmpleadoDAO {

    /**
     *
     * Crea una conexion.
     */
    private static Connection conexion() {
        Connection con = null;

        String url = "jdbc:mysql://localhost/empresa";
        try {
            con = DriverManager.getConnection(url, "pepe", "12345");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return con;

    }

    /**
     *
     * Introduce un empleado a la base de datos.
     */
    public static void introEmpleado(Empleado empleado) {
        if (empleado != null) {
            if (!checkEmpleado(empleado) && empleado.getNumemp() != 0) {
                String sql = "INSERT INTO empleados (numemp, nombre, edad, oficina, puesto, contrato)" + "VALUES ( ?,    ?,     ?,     ?,     ?,     ?)";
                try {

                    java.sql.Date date = datetoSql(empleado.getFchaContrato());
                    Connection conexion = conexion();
                    PreparedStatement sentencia = conexion.prepareStatement(sql);
                    sentencia.setInt(1, empleado.getNumemp());
                    sentencia.setString(2, empleado.getNombre());
                    sentencia.setInt(3, empleado.getEdad());
                    sentencia.setDouble(4, (empleado.getnOficina()).getOficina());
                    sentencia.setString(5, empleado.getPuesto());
                    sentencia.setDate(6, date);
                    sentencia.executeUpdate();
                    conexion.close();
                    System.out.println("El empleado se ha introducido correctamente");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Introduce un numero de empleado que no este repetido");
            }

        } else {
            System.out.println("Introduce un valor correcto");
        }
    }

    /**
     *
     * Chequea si existe el empleado en la BD
     */
    public static boolean checkEmpleado(Empleado empleado) {
        boolean siono = false;
        String sql = "SELECT numemp FROM empleados where numemp=?";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, empleado.getNumemp());
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                siono = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return siono;
    }

    /**
     *
     * Chequea si existe el empleado en la BD pasandole un ID de empleado
     *
     * @param empleado
     * @return
     */
    public static boolean checkEmpleado(int empleado) {
        boolean siono = false;
        String sql = "SELECT numemp FROM empleados where numemp=?";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, empleado);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                siono = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return siono;
    }

    /* 
     *  Refresca los valores del objeto con los de la base de datos.
     */
    public static void refreshEmpleado(Empleado empleado) {
        String sql = "SELECT *"
                + "FROM empleados "
                + "WHERE numemp = ?";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, empleado.getNumemp());
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {

                int numemp = rs.getInt("numemp");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                int numOficina = rs.getInt("oficina");
                String puesto = rs.getString("puesto");
                Date contrato = rs.getDate("contrato");
                Oficina oficina = readID(numOficina);
                empleado.setEdad(edad);
                empleado.setNombre(nombre);
                empleado.setEdad(edad);
                empleado.setOficina(oficina);
                empleado.setPuesto(puesto);
                empleado.setFchaContrato(contrato);
                conexion.close();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*
     *  Refresca los valores del objeto a la BD
     */
    public static void updateEmpleado(Empleado empleado) {
        if (!checkEmpleado(empleado.getNumemp())) {
            System.out.println("No se a podido actualizar porque no existe ningun registro que actualizar");
        } else {
            String sql2 = "UPDATE `empresa`.`empleados` SET `nombre` = ?, `edad` = ?, `oficina` = ?, `puesto` = ?, `contrato` = ? WHERE `numemp` = ?";
            try {
                java.sql.Date date = datetoSql(empleado.getFchaContrato());
                Connection conexion = conexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql2);
                sentencia.setString(1, empleado.getNombre());
                sentencia.setInt(2, empleado.getEdad());
                sentencia.setInt(3, (empleado.getnOficina()).getOficina());
                sentencia.setString(4, empleado.getPuesto());
                sentencia.setDate(5, date);
                sentencia.setInt(6, empleado.getNumemp());

                sentencia.executeUpdate();
                System.out.println("El registro se a modificado con exito");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /*
     *  Borra un registro de la BD con un ID dado.
     */
    public static void deleteEmpleado(int numemp) {
        String sql = "DELETE FROM empleados "
                + "WHERE numemp = ?";
        if (checkEmpleado(numemp)) {

            try {
                Connection conexion = conexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, numemp);
                sentencia.executeUpdate();
                conexion.close();
                System.out.println("El registro se a eliminado correctamente");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("El valor no existe");
        }
    }

    /*
     *  Lee la base de datos con un id dado y hace un registro un objeto Empleado.
     */
    public static Empleado empleadoID(int numemple) {
        Empleado empleado = null;
        String sql = "SELECT *"
                + "FROM empleados "
                + "WHERE numemp = ?";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, numemple);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                int numemp = rs.getInt("numemp");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                int numOficina = rs.getInt("oficina");
                String puesto = rs.getString("puesto");
                Date contrato = rs.getDate("contrato");
                Oficina oficina = readID(numOficina);

                empleado = new Empleado(numemp, nombre, edad, oficina, puesto, contrato);
                conexion.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return empleado;

    }

    /**
     * Muestra una coleccion con todos los empleados
     *
     * @return
     */
    public static List<Empleado> coleccionEmpleados() {
        Empleado empleado = null;
        List<Empleado> List = new ArrayList<Empleado>();
        String sql = "SELECT * FROM empleados";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                int numemp = rs.getInt("numemp");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                int numOficina = rs.getInt("oficina");
                String puesto = rs.getString("puesto");
                Date contrato = rs.getDate("contrato");
                Oficina oficina = readID(numOficina);
                empleado = new Empleado(numemp, nombre, edad, oficina, puesto, contrato);

                List.add(empleado);
                empleado = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return List;
    }

    public static void readAllEmpleados() {
        List<Empleado> Lista = coleccionEmpleados();
        for (Empleado empleado : Lista) {
            System.out.println(empleado);
        }
    }

}
