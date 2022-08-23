/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg14;

import java.sql.*;

/**
 *
 * @author carlos
 */
public class Oficina extends OficinaDAO {

    private int oficina;
    private String ciudad;
    private int superficie;
    private double ventas;

    Oficina(int oficina) {
        this.oficina = oficina;
    }

    Oficina(int oficina, String ciudad, int superficie, double ventas) {
        this.oficina = oficina;
        this.ciudad = ciudad;
        this.superficie = superficie;
        this.ventas = ventas;
    }

    public int getOficina() {
        return oficina;
    }

    /**
     * @param oficina the oficina to set
     */
    private void intOficina(int oficina) {
        if (!checkOficina(oficina)) {
            this.oficina = oficina;
        } else {
            System.out.println("El numero de oficina esta repetido");
        }

    }

    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the superficie
     */
    public int getSuperficie() {
        return superficie;
    }

    /**
     * @param superficie the superficie to set
     */
    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    /**
     * @return the ventas
     */
    public double getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        String oficinaa = ""+oficina;
        return oficinaa;
    }
    public String largo(){
                return "Oficina[" + "Numero de Oficina=" + oficina + "1, ciudad=" + ciudad + ", superficie=" + superficie + ", ventas=" + ventas + ']';

    }

}
