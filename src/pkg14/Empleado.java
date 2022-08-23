/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg14;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class Empleado {

    private int numemp;
    private String nombre;
    private int edad;
    private String puesto;
    private Oficina oficina;
    private Date fchaContrato;

    public Empleado(int numemp) {
        this.numemp = numemp;
    }

    public Empleado(int numemp, String nombre, int edad,Oficina oficina, String puesto,  Date fchaContrato) {
        this.numemp = numemp;
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.oficina = oficina;
        this.fchaContrato = fchaContrato;
    }

    @Override
    public String toString() {
        return "Empleado{" + "numemp=" + numemp + ", nombre=" + nombre + ", edad=" + edad + ", puesto=" + puesto + ", oficina=" + oficina + ", fchaContrato=" + fchaContrato + '}';
    }

    

   

    /**
     * @return the numemp
     */
    public int getNumemp() {
        return numemp;
    }

    /**
     * @param numemp the numemp to set
     */
    public void setNumemp(int numemp) {
        this.numemp = numemp;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the oficina
     */
    public Oficina getnOficina() {
        return oficina;
    }

    /**
     * @param oficina the oficina to set
     */
    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    /**
     * @return the fchaContrato
     */
    public Date getFchaContrato() {
        return fchaContrato;
    }

    /**
     * @param fchaContrato the fchaContrato to set
     */
    public void setFchaContrato(Date fchaContrato) {
        this.fchaContrato = fchaContrato;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Convierte el tipo Date de java.util al de java.sql
     * @param date
     * @return 
     */
    public static java.sql.Date datetoSql(Date date) {

        long timeInMilliSeconds = date.getTime();
        java.sql.Date fechasql = new java.sql.Date(timeInMilliSeconds);

        return fechasql;
    }

    /**
     * Crea una fecha de tipo java.util con los parametros de yyyy-mm-dd
     * @param yyyy
     * @param mm
     * @param dd
     * @return 
     */
    public static java.util.Date creadorFechas(int yyyy, int mm, int dd) {

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.of(yyyy, mm, dd);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        return date;
    }
/**
     * Crea una fecha de tipo java.sql con los parametros de yyyy-mm-dd
     * @param yyyy
     * @param mm
     * @param dd
     * @return 
     */
    public static java.sql.Date crearContrato(int yyyy, int mm, int dd) {
        Date date = creadorFechas(yyyy,mm, dd);
         java.sql.Date fechamod= datetoSql(date);
         return fechamod;
}
}