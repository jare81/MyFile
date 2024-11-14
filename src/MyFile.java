

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class MyFile {

    private File mifile = null;

    void setFile(String direccion) {
        mifile = new File(direccion);

    }

    File getFile() {
        return mifile;
    }

    void info() {
        if (mifile.exists()) {
            System.out.println("\nNombre: " + mifile.getName());
            System.out.println("Path: " + mifile.getPath());
            System.out.println("Absoluta: " + mifile.getAbsolutePath());
            System.out.println("Bytes: " + mifile.length());
            System.out.println("Modificado en: " + new Date(mifile.lastModified()));
            System.out.println("Padre: " + mifile.getAbsoluteFile().getParentFile().getName());

            if (mifile.isFile()) {
                System.out.println("ES UN FILE");
            } else if (mifile.isDirectory()) {
                System.out.println("ES UN FOLDER");
            }
            System.out.println("-+-+-+-+-+-+-+-+-+");

        } else {
            System.out.println("No existe!");
        }
    }

    boolean crearFile() throws IOException {
        return mifile.createNewFile();
    }

    boolean crearFolder() {
        return mifile.mkdirs();
    }

    boolean crearFolder2() {
        return mifile.mkdir();
    }

    public boolean reescribir(File dir, String contenido) throws IOException {

        if (dir.canWrite()) {
            try (FileWriter creador = new FileWriter(dir); 
                PrintWriter escritor = new PrintWriter(creador)) {
                escritor.println(contenido);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            return true;
        } else {
            System.out.println("Este archivo no se puede sobreescribir o no existe");
            return false;
        }

    }

    public boolean anadir(File dir, String agregar) throws IOException {
        if (dir.canWrite()) {

            try (FileWriter creador = new FileWriter(dir, true); PrintWriter escritor = new PrintWriter(creador)) {
                escritor.println(agregar);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            return true;
        } else {
            System.out.println("Este archivo no se puede escribir o no existe");
            return false;
        }
    }

    public String leer() {

        StringBuilder cont = new StringBuilder();

        try (FileReader archivo = new FileReader(mifile); 
             BufferedReader lector2 = new BufferedReader(archivo)) {

            String linea;
            /*while ((linea = lector2.readLine()) != null) {
                    System.out.println(linea);
                }*/

            while ((linea = lector2.readLine()) != null) {
                cont.append(linea).append("\n");
            }

        } catch (IOException e) {
            System.out.println("No se pudo leer");
        }

        return cont.toString();

    }
    
    public String leer2() {
        StringBuilder cont = new StringBuilder();

        try (FileReader archivo = new FileReader(mifile)) {
            int caracter;
            while ((caracter = archivo.read()) != -1) { 
                cont.append((char) caracter); 
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer");
        }

        return cont.toString();
    }

   

    public void hacerBorrar() {
        if (borrar(mifile)) {
            System.out.println("Borrado");
        } else {
            System.out.println("No se pudo borrar");
        }

    }

    public boolean borrar(File dir) {

        if (dir.isDirectory()) {
            File[] archivos = dir.listFiles();

            for (File archivo : archivos) {

                if (archivo != null) {
                    borrar(dir);
                } else {
                    System.out.println("No se puede borrar este archivo");
                    return false;
                }
            }
        }

        return dir.delete();

    }

    void dir() {
        if (mifile.isDirectory()) {
            System.out.println("Directorio de: " + mifile.getAbsolutePath());
            System.out.println("");

            //contadores
            int cfiles = 0, cdirs = 0, tbytes = 0;

            //recorrido
            for (File hijos : mifile.listFiles()) {
                if (!hijos.isHidden()) {
                    //ultima modificacion
                    Date ultimo = new Date(hijos.lastModified());
                    System.out.print(ultimo + "\t");

                    //Si es file o folder
                    if (hijos.isDirectory()) {
                        cdirs++;
                        System.out.print("<DIR>\t\t");
                    } else {
                        //file
                        cfiles++;
                        tbytes += hijos.length();
                        System.out.print("    \t" + hijos.length() + "\t");
                    }

                    //mostrar objetos
                    System.out.println(hijos.getName());

                }
                System.out.println(cfiles + " archivos\t" + tbytes + " bytes");
                System.out.println(cdirs + " directorios\t");

            }

        }
    }

    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());

            for (File hijos : dir.listFiles()) {
                if (!hijos.isHidden()) {
                    tree(hijos, tab + "--");
                }
            }
        }

    }

    void tree() {
        tree(mifile, "-");
    }

    /* */
    void abrirVentana() {
        if (mifile.exists() && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(mifile);
            } catch (IOException e) {
                System.out.println("No se pudo abrir el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe o no es compatible");
        }
    }

}
