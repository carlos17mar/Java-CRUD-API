/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg14;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class OficinaDAO {

    /* 
     *  Crea la conexion con la base de datos
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

    /* 
     *  Introduce un objeto la base de datos
     */
    public static void intrOficina(Oficina oficina) {
        if (oficina != null) {
            if (!checkOficina(oficina.getOficina()) && oficina.getOficina() != 0) {
                String sql = "INSERT INTO oficinas (oficina, ciudad, superficie, ventas)" + "VALUES ( ?,    ?,     ?,     ? )";
                try {
                    Connection conexion = conexion();
                    PreparedStatement sentencia = conexion.prepareStatement(sql);
                    sentencia.setInt(1, oficina.getOficina());
                    sentencia.setString(2, oficina.getCiudad());
                    sentencia.setInt(3, oficina.getSuperficie());
                    sentencia.setDouble(4, oficina.getVentas());
                    sentencia.executeUpdate();
                    conexion.close();
                    System.out.println("La oficina se ha introducido correctamente");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Introduce un numero de oficina que no este repetido");
            }

        } else {
            System.out.println("Introduce un valor correcto");
        }
    }

    /* 
     *  Chequea si una oficina tiene el id de un objeto oficina introducido.
     */
    public static boolean checkOficina(Oficina oficina) {
        boolean siono = false;
        String sql = "SELECT oficina FROM oficinas where oficina=?";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, oficina.getOficina());
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
     *  Chequea si un numero tiene el id de un objeto oficina en la BD.
     */
    public static boolean checkOficina(int num) {
        boolean siono = false;
        String sql = "SELECT oficina FROM oficinas where oficina=?";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, num);
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
    public static void refreshObj(Oficina oficina) {

        String sql = "SELECT *"
                + "FROM oficinas "
                + "WHERE oficina = ?";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, oficina.getOficina());
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                int oficinaa = rs.getInt("oficina");
                String ciudad = rs.getString("ciudad");
                int superficie = rs.getInt("superficie");
                double ventas = rs.getDouble("ventas");
                oficina.setCiudad(ciudad);
                oficina.setSuperficie(superficie);
                oficina.setVentas(ventas);
                conexion.close();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /* 
     *  Updatea los valores de un objeto en la base de datos.
     */
    public static void updateBD(Oficina oficina) {
        if (!checkOficina(oficina.getOficina())) {
            System.out.println("No se a podido actualizar porque no existe ningun registro que actualizar");
        } else {
            String sql = "UPDATE oficinas SET ciudad=?, superficie=?, ventas=? WHERE oficina = ?";
            try {
                Connection conexion = conexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, oficina.getCiudad());
                sentencia.setInt(2, oficina.getSuperficie());
                sentencia.setDouble(3, oficina.getVentas());
                sentencia.setInt(4, oficina.getOficina());

                sentencia.executeUpdate();
                System.out.println("El registro se a modificado con exito");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    /* 
     *  Borra un objeto en la base de datos con un id dado.
     */
    public static void deleteID(int id) {
        String sql = "DELETE FROM oficinas "
                + "WHERE oficina = ?";
        if (checkOficina(id)) {

            try {
                Connection conexion = conexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, id);
                sentencia.executeUpdate();
                conexion.close();
                System.out.println("La tabla se a eliminado correctamente");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("El valor no existe");
        }
    }

    /* 
     *  Lee un objeto en la base de datos con un id dado y lo hace un objeto OFICINA
     */
    public static Oficina readID(int id) {
        Oficina oficina = null;
        String sql = "SELECT *"
                + "FROM oficinas "
                + "WHERE oficina = ?";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                int oficinaa = rs.getInt("oficina");
                String ciudad = rs.getString("ciudad");
                int superficie = rs.getInt("superficie");
                double ventas = rs.getDouble("ventas");

                oficina = new Oficina(id, ciudad, superficie, ventas);
                conexion.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return oficina;

    }

    /* 
     *  Lee todos los objetos en la tabla de oficinas
     */
    public static void readAllOffice() {

        List<Oficina> List = insertColec();
        System.out.println(List.size());
        for (Oficina oficina: List) {
            System.out.println(oficina.largo());
        }
        
    }

    / **
     *
     * Devielve todos los registros como objetos guardados en una coleccion
     * @return 
     */
    public static List<Oficina> insertColec() {
        Oficina oficina = null;
        List<Oficina> List = new ArrayList<Oficina>();
        String sql = "SELECT * FROM oficinas";
        try {
            Connection conexion = conexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                int oficinaa = rs.getInt("oficina");
                String ciudad = rs.getString("ciudad");
                int superficie = rs.getInt("superficie");
                double ventas = rs.getDouble("ventas");

                oficina = new Oficina(oficinaa, ciudad, superficie, ventas);
                List.add(oficina);
                oficina = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return List;
    }

}
