/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in).useDelimiter("\n");

        MyFile file = new MyFile();
        int opcion = 0;
        do {

            System.out.println("\n--------------------------------------");
            System.out.println("1 - Set Archivo/Folder");
            System.out.println("2 - Ver Informacion");
            System.out.println("3 - Crear Archivo");
            System.out.println("4 - Crear Folder");
            System.out.println("5 - Rescribir la Informacion");
            System.out.println("6 - Anadir algo al archivo");
            System.out.println("7 - Leer Archivo");
            System.out.println("8 - Borrar");
            System.out.println("9 - DIR");
            System.out.println("10 - ARBOL");
            System.out.println("11 -  Salir");
            System.out.println("12 - Ver Archivo");
            System.out.println("---------------------------------------");

            System.out.println("\nIngrese una opcion");
            try {
                opcion = entrada.nextInt();
                entrada.nextLine();
                String direc = "";

                switch (opcion) {
                    case 1:

                        System.out.println("\nIngrese direccion");
                        direc = entrada.next();

                        file.setFile(direc);

                        break;

                    case 2:
                        System.out.println("\n - INFORMACION DEL ARCHIVO - \n");
                        file.info();
                        break;

                    case 3:
                        System.out.println("\n - CREAR ARCHIVO - \n");
                        if (file.crearFile()) {
                            System.out.println("Archivo creado");

                        } else {
                            System.out.println("Este archivo ya existe");
                        }

                        break;

                    case 4:
                        System.out.println("\n - CREAR CARPETA - \n");
                        if (file.crearFolder()) {
                            System.out.println("Folder creado");
                        } else {
                            System.out.println("Esta carpeta ya existe");
                        }

                        break;

                    case 5:

                        System.out.println("\n - REESCRIBIR CONTENIDO - \n");
                        System.out.println("Que desee reescribir?");
                        String contenido = entrada.next();
                        if (file.reescribir(file.getFile(), contenido)) {
                            System.out.println("Contenido reescrito exitosamente");
                        }

                        break;

                    case 6:

                        System.out.println("\n - ANADIR AL CONTENIDO - \n");
                        System.out.println("Ingrese el texto a añadir:");
                        String agregar = entrada.next();
                            if (file.anadir(file.getFile(), agregar)) {
                                System.out.println("Contenido anadido exitosamente");
                            }
                            break;
                        

                    case 7:

                        System.out.println("\n - LEER EL ARCHIVO - \n");
                        System.out.println(file.leer());

                        break;

                    case 8:
                        System.out.println("\n - BORRAR ARCHIVO - ");
                        file.hacerBorrar();

                        break;

                    case 9:
                        System.out.println("\n - DIR - ");
                        file.dir();
                        break;

                    case 10:
                        System.out.println("\n - ARBOL - ");
                        file.tree();
                        break;

                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese una opcion valida");
                entrada.nextLine();

            } catch (NullPointerException e) {
                System.out.println("Asegurese de ingresar la direccion en la primera opcion");

            } catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado: " + e.getMessage());

            } catch (IOException e) {
                System.out.println("Error de salida: " + e.getMessage());

            } catch (SecurityException e) {
                System.out.println("No tienes permisos para acceder a este archivo o carpeta: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

        } while (opcion != 11);

    }
}
