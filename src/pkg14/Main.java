/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg14;

import java.util.Date;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static pkg14.Empleado.*;
import static pkg14.Empleado.datetoSql;
import static pkg14.EmpleadoDAO.*;

import static pkg14.OficinaDAO.*;

/**
 *
 * @author carlos
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
//        List<Oficina> List = insertColec();
//        System.out.println(List.size());
//        for (Oficina oficina : List) {
//            System.out.println(oficina.largo());
//        }

        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        int menu = 1;
        while (menu > 0) {

            System.out.println("Introduce:");
            System.out.println("1.-Si quieres consultar un registro");
            System.out.println("2.-Si quieres modificar un registro");
            System.out.println("3.-Si quieres eliminar un registro");
            System.out.println("4.-Si quieres crear un nuevo registro");
            System.out.println("0.-Si quieres salir");
            menu = sc.nextInt();

            if (menu == 1) {
                int submenu = menu;
                while (submenu > 0) {
                    System.out.println("================================");
                    System.out.println("Introduce:");
                    System.out.println("1.-Si quieres consultar sobre los empleados");
                    System.out.println("2.-Si quieres consultar sobre las oficinas");
                    System.out.println("0.-Si quieres salir");
                    System.out.println("================================");

                    submenu = sc.nextInt();
                    if (submenu == 1) {
                        System.out.println("================================");
                        System.out.println("Introduce:");
                        System.out.println("1.-Si quieres consultar todos los registros");
                        System.out.println("2.-Si quieres consultar solo uno introduciendo el id");
                        System.out.println("================================");
                        submenu = sc.nextInt();
                        if (submenu == 1) {
                            readAllEmpleados();
                        } else if (submenu == 2) {
                            System.out.println("Introduce el numero de empleado que quieres consultar");
                            int numEmpleado = sc.nextInt();
                            if (checkEmpleado(numEmpleado)) {
                                System.out.println(empleadoID(numEmpleado));
                            } else {
                                System.out.println("===========================================");
                                System.out.println("El numero de oficina introducido no existe");
                            }
                        }
                    } else if (submenu == 2) {
                        System.out.println("================================");
                        System.out.println("Introduce:");
                        System.out.println("1.-Si quieres consultar todos los registros");
                        System.out.println("2.-Si quieres consultar solo uno introduciendo el id");
                        System.out.println("================================");
                        submenu = sc.nextInt();
                        if (submenu == 1) {
                            readAllOffice();
                        } else if (submenu == 2) {
                            System.out.println("Introduce el numero de oficina que quieres consultar");
                            int numOficina = sc.nextInt();
                            if (checkOficina(numOficina)) {
                                System.out.println(readID(numOficina));
                            } else {
                                System.out.println("===========================================");
                                System.out.println("El numero de oficina introducido no existe");
                            }
                        }
                    }
                    System.out.println("================================");
                    System.out.println("Introduce cualquier numero para continuar consultando registros.");
                    System.out.println("0.-Si quieres salir");
                    System.out.println("================================");

                    submenu = sc.nextInt();
                }

            } else if (menu == 2) {
                int submenu = menu;
                while (submenu > 0) {
                    System.out.println("================================");
                    System.out.println("Introduce:");
                    System.out.println("1.-Si quieres modificar un registro sobre los empleados");
                    System.out.println("2.-Si quieres modificar un registro sobre las oficinas");
                    System.out.println("0.-Si quieres salir");
                    System.out.println("================================");
                    submenu = sc.nextInt();
                    if (submenu == 1) {
                        System.out.println("================================");
                        System.out.println("Introduce:");
                        System.out.println("1.-Si quieres modificar un atributo en concreto");
                        System.out.println("2.-Si quieres modificar un registro completo");
                        System.out.println("0.-Si quieres salir");
                        System.out.println("================================");
                        int eleccion = sc.nextInt();
                        if (eleccion == 1) {
                            System.out.println("Que registro quieres modificar:");
                            readAllEmpleados();
                            System.out.println("Introduce el ID del registro que quieras modificar");
                            int eleecion = sc.nextInt();
                            Empleado nReg = empleadoID(eleecion);
                            System.out.println(nReg);
                            System.out.println("Que quieres modificar de ese registro");
                            System.out.println("1.-Nombre");
                            System.out.println("2.-Edad");
                            System.out.println("3.-Oficina");
                            System.out.println("4.-Puesto");
                            System.out.println("5.-Fecha del contrato");
                            int modificacion = sc.nextInt();
                            switch (modificacion) {
                                case 1 -> {
                                    System.out.println("Introduce la modificacion");
                                    System.out.println("Nombre:");
                                    String nombre = sc.next();
                                    System.out.println("Apellido:");
                                    String apellido = sc.next();
                                    String nombreCompleto = nombre + " " + apellido;
                                    nReg.setNombre(nombreCompleto);
                                    System.out.println("El registro se quedara asi");
                                    System.out.println(nReg);
                                    System.out.println("Pulsa 1 para confirmar la modificacion");
                                    int confirmacion = sc.nextInt();
                                    if (confirmacion == 1) {
                                        updateEmpleado(nReg);
                                    } else {
                                        System.out.println("El registro no se modifico");
                                    }
                                }
                                case 2 -> {
                                    System.out.println("Introduce la modificacion");
                                    System.out.println("Edad:");
                                    int edad = sc.nextInt();
                                    nReg.setEdad(edad);
                                    System.out.println("El registro se quedara asi");
                                    System.out.println(nReg);
                                    System.out.println("Pulsa 1 para confirmar la modificacion");
                                    int confirmacion = sc.nextInt();
                                    if (confirmacion == 1) {
                                        updateEmpleado(nReg);
                                    } else {
                                        System.out.println("El registro no se modifico");
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Que oficina quieres asignarle a este empleado");
                                    readAllOffice();
                                    System.out.println("Introduce el Numero de oficina de la que le quieras asignar");
                                    int idOficina = sc.nextInt();
                                    Oficina aux = readID(idOficina);
                                    System.out.println(aux.largo());
                                    System.out.println("Estas seguro de asignar esta oficina al empleado");
                                    nReg.setOficina(aux);
                                    System.out.println("Pulsa 1 para confirmar la modificacion");
                                    int confirmacion = sc.nextInt();
                                    if (confirmacion == 1) {
                                        updateEmpleado(nReg);
                                    } else {
                                        System.out.println("El registro no se modifico");
                                    }

                                }
                                case 4 -> {
                                    System.out.println("Introduce el nuevo puesto:");
                                    String oficio1 = sc.next();
                                    String oficio2 = sc.next();
                                    String oficio3 = sc.next();
                                    String oficio = oficio1 + " " + oficio2 + " " + oficio3;
                                    nReg.setPuesto(oficio);
                                    System.out.println("El registro se quedara asi");
                                    System.out.println(nReg);
                                    System.out.println("Pulsa 1 para confirmar la modificacion");
                                    int confirmacion = sc.nextInt();
                                    if (confirmacion == 1) {
                                        updateEmpleado(nReg);
                                    } else {
                                        System.out.println("El registro no se modifico");
                                    }

                                }
                                case 5 -> {
                                    System.out.println("Introduce la nueva fecha");
                                    System.out.println("Introduce el año");
                                    int yyyy = sc.nextInt();
                                    System.out.println("Introduce el mes");
                                    int mm = sc.nextInt();
                                    System.out.println("Introduce el dia");
                                    int dd = sc.nextInt();
                                    System.out.println("La fecha que has introducido es: " + crearContrato(yyyy, mm, dd));
                                    nReg.setFchaContrato(crearContrato(yyyy, mm, dd));
                                    System.out.println("Pulsa 1 para confirmar la modificacion");
                                    int confirmacion = sc.nextInt();
                                    if (confirmacion == 1) {
                                        updateEmpleado(nReg);
                                    } else {
                                        System.out.println("El registro no se modifico");
                                    }
                                }
                            }

                        } else if (eleccion == 2) {
                            System.out.println("Que registro quieres modificar:");
                            readAllEmpleados();
                            System.out.println("Introduce el ID del registro que quieras modificar");
                            int eleecion = sc.nextInt();
                            Empleado nReg = empleadoID(eleecion);
                            System.out.println(nReg);
                            System.out.println("Introduce la modificacion");
                            System.out.println("Nombre:");
                            String nombre = sc.next();
                            System.out.println("Apellido:");
                            String apellido = sc.next();
                            String nombreCompleto = nombre + " " + apellido;
                            nReg.setNombre(nombreCompleto);
                            System.out.println("Edad:");
                            int edad = sc.nextInt();
                            nReg.setEdad(edad);
                            System.out.println("Introduce el nuevo puesto:");
                            String oficio1 = sc.next();
                            String oficio2 = sc.next();
                            String oficio3 = sc.next();
                            String oficio = oficio1 + " " + oficio2 + " " + oficio3;
                            nReg.setPuesto(oficio);
                            System.out.println("Que oficina quieres asignarle a este empleado");
                            readAllOffice();
                            System.out.println("Introduce el Numero de oficina de la que le quieras asignar");
                            int idOficina = sc.nextInt();
                            Oficina aux = readID(idOficina);
                            System.out.println(aux.largo());
                            nReg.setOficina(aux);
                            System.out.println("Introduce la nueva fecha");
                            System.out.println("Introduce el año");
                            int yyyy = sc.nextInt();
                            System.out.println("Introduce el mes");
                            int mm = sc.nextInt();
                            System.out.println("Introduce el dia");
                            int dd = sc.nextInt();
                            System.out.println("La fecha que has introducido es: " + crearContrato(yyyy, mm, dd));
                            nReg.setFchaContrato(crearContrato(yyyy, mm, dd));
                            System.out.println("Tu registro se quedaria asi");
                            System.out.println(nReg);
                            System.out.println("Pulsa 1 para confirmar la modificacion");
                            int confirmacion = sc.nextInt();
                            if (confirmacion == 1) {
                                updateEmpleado(nReg);
                            } else {
                                System.out.println("El registro no se modifico");
                            }

                        }

                    } else if (submenu == 2) {
                        System.out.println("================================");
                        System.out.println("Introduce:");
                        System.out.println("1.-Si quieres modificar un atributo en concreto");
                        System.out.println("2.-Si quieres modificar un registro completo");
                        System.out.println("0.-Si quieres salir");
                        System.out.println("================================");
                        int eleccion = sc.nextInt();
                        if (eleccion == 1) {
                            System.out.println("Que registro quieres modificar:");
                            readAllOffice();
                            System.out.println("Introduce el ID del registro que quieras modificar");
                            int eleecion = sc.nextInt();
                            Oficina nReg = readID(eleecion);

                            System.out.println(nReg.largo());
                            System.out.println("Que quieres modificar de ese registro");
                            System.out.println("Introduce:");
                            System.out.println("1-.Ciudad");
                            System.out.println("2.-Superficie");
                            System.out.println("3.-Ventas");
                            eleecion = sc.nextInt();
                            if (eleecion == 1) {
                                System.out.println("Introduce la nueva ciudad");
                                String ciudad = sc.next();
                                nReg.setCiudad(ciudad);
                                updateBD(nReg);
                                System.out.println("El registro se modifico correctamente");
                                System.out.println(nReg);

                            } else if (eleecion == 2) {
                                System.out.println("Introduce la nueva superficie");
                                int superfi = sc.nextInt();
                                nReg.setSuperficie(superfi);
                                updateBD(nReg);
                                System.out.println("El registro se modifico correctamente");
                                System.out.println(nReg);

                            } else if (eleecion == 3) {
                                System.out.println("Introduce las nuevas ventas");
                                double ventas = sc.nextDouble();
                                nReg.setVentas(ventas);
                                updateBD(nReg);
                                System.out.println("El registro se modifico correctamente");
                                System.out.println(nReg);
                            }

                        } else if (eleccion == 2) {

                            System.out.println("Que registro quieres modificar:");
                            readAllOffice();
                            System.out.println("Introduce el numero de oficina");
                            int numOffice = sc.nextInt();
                            System.out.println("Introduce la ciudad");
                            String ciudad = sc.next();
                            System.out.println("Introduce la superficie");
                            int superficie = sc.nextInt();
                            System.out.println("Introduce las ventas");
                            double ventas = sc.nextDouble();
                            System.out.println("El registro se quedaria asi:");
                            Oficina update = new Oficina(numOffice, ciudad, superficie, ventas);
                            System.out.println(update);
                            System.out.println("Para confirmalo pulsa 1");
                            int leection = sc.nextInt();
                            if (leection == 1) {
                                updateBD(update);
                            } else {
                                System.out.println("El registro no se modifico");
                            }

                        }

                    }
                    System.out.println("================================");
                    System.out.println("Introduce cualquier numero para continuar modificando registros.");
                    System.out.println("0.-Si quieres salir");
                    System.out.println("================================");
                    submenu = sc.nextInt();
                }

            } else if (menu == 3) {
                int submenu = menu;
                while (submenu > 0) {
                    System.out.println("================================");
                    System.out.println("Introduce:");
                    System.out.println("1.-Si quieres eliminar un registro de los empleados :(");
                    System.out.println("2.-Si quieres modificar un registro sobre las oficinas");
                    System.out.println("0.-Si quieres salir");
                    System.out.println("================================");
                    submenu = sc.nextInt();
                    if (submenu == 2) {
                        System.out.println("Introduce el id del registro que quieres eliminar");
                        readAllOffice();
                        int num = sc.nextInt();
                        System.out.println("Para confirma pulsa 1");
                        int confirmacion = sc.nextInt();
                        if (confirmacion == 1) {
                            deleteID(num);
                            readAllOffice();

                        } else {
                            System.out.println("No se elimino nungun registro");
                        }

                    } else if (submenu == 1) {
                        System.out.println("Introduce el id del empleado que quieras despedir");
                        readAllEmpleados();
                        int id = sc.nextInt();
                        System.out.println("Seguro que quieres borrar el siguiente registro: ");
                        System.out.println(empleadoID(id));

                        System.out.println("Para confirmalo pulsa 1");

                        int leection = sc.nextInt();
                        if (leection == 1) {
                            deleteEmpleado(id);
                        } else {
                            System.out.println("El registro no se modifico");
                        }
                    }
                    System.out.println("================================");

                    System.out.println("Introduce cualquier numero para continuar borrando registro.");
                    System.out.println("0.-Si quieres salir");
                    System.out.println("================================");

                    submenu = sc.nextInt();
                }

            } else if (menu == 4) {
                int submenu = menu;
                while (submenu > 0) {
                    System.out.println("================================");
                    System.out.println("Introduce:");
                    System.out.println("1.-Si quieres añadir un registro sobre los empleados");
                    System.out.println("2.-Si quieres añadir un registro sobre las oficinas");
                    System.out.println("0.-Si quieres salir");
                    System.out.println("================================");
                    submenu = sc.nextInt();
                    if (submenu == 1) {
                        System.out.println("Introduce el ID del registro que quieras crear");
                        int id = sc.nextInt();
                        System.out.println("Nombre:");
                        String nombre = sc.next();
                        System.out.println("Apellido:");
                        String apellido = sc.next();
                        String nombreCompleto = nombre + " " + apellido;

                        System.out.println("Edad:");
                        int edad = sc.nextInt();

                        System.out.println("Introduce el puesto:");
                        String oficio1 = sc.next();
                        String oficio2 = sc.next();
                        String oficio3 = sc.next();
                        String oficio = oficio1 + " " + oficio2 + " " + oficio3;
                        System.out.println("Que oficina quieres asignarle al nuevo empleado");
                        readAllOffice();
                        System.out.println("Introduce el Numero de oficina de la que le quieras asignar");
                        int idOficina = sc.nextInt();
                        Oficina aux = readID(idOficina);
                        System.out.println(aux.largo());
                        System.out.println("Introduce la nueva fecha");
                        System.out.println("Introduce el año");
                        int yyyy = sc.nextInt();
                        System.out.println("Introduce el mes");
                        int mm = sc.nextInt();
                        System.out.println("Introduce el dia");
                        int dd = sc.nextInt();
                        System.out.println("La fecha que has introducido es: " + crearContrato(yyyy, mm, dd));
                        System.out.println("Tu registro se quedaria asi");
                        Empleado empleado = new Empleado(id, nombreCompleto, edad, aux, oficio, crearContrato(yyyy, mm, dd));

                        System.out.println(empleado);
                        System.out.println("Pulsa 1 para confirmar la modificacion");
                        int confirmacion = sc.nextInt();
                        if (confirmacion == 1) {
                            introEmpleado(empleado);
                        } else {
                            System.out.println("El registro no se modifico");
                        }

                    } else if (submenu == 2) {
                        System.out.println("Introduce un id de oficina");
                        int id = sc.nextInt();
                        if (checkOficina(id)) {
                            System.out.println("El id ya existe.");
                        } else {

                            System.out.println("Introduce una ciudad");
                            String ciudad = sc.next();
                            System.out.println("Introducec la superficie");
                            int superficie = sc.nextInt();
                            System.out.println("Intruduce el volumen de ventas");
                            double volVentas = sc.nextDouble();
                            Oficina nuevoReg = new Oficina(id, ciudad, superficie, volVentas);
                            System.out.println(nuevoReg);
                            intrOficina(nuevoReg);
                            readAllOffice();

                        }
                    }

                }
                System.out.println("================================");
                System.out.println("Introduce cualquier numero para introducir otro nuevo registro.");
                System.out.println("0.-Si quieres salir");
                System.out.println("================================");
                submenu = sc.nextInt();

            }

            System.out.println("================================");

            System.out.println("Introduce cualquier numero para continuar en el programa.");
            System.out.println("0.-Si quieres salir");
            System.out.println("================================");

            menu = sc.nextInt();
        }
    }

}
