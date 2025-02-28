package Core.Ficheros;


import Core.Usuarios.Administrador;
import Core.Usuarios.Alumno;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;

import java.io.*;
import java.util.ArrayList;


public class FicheroUsuarios {
    public ArrayList<Usuario> crearUsuarios() {
        ArrayList<Usuario> objetos = new ArrayList<>();

        // Leer el archivo
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separar los datos por espacios
                String[] datos = linea.trim().split(",");
                if (datos.length != 4) continue; // Saltar líneas inválidas

                String idUsurio = datos[0];
                String nombre = datos[1];
                String contrasenia = datos[2];
                String rol = datos[3];

                switch (rol.toLowerCase()) {
                    case "admin":
                        objetos.add(new Administrador(idUsurio,nombre, contrasenia));
                        System.out.println("aa");
                        break;
                    case "alumno":
                        objetos.add(new Alumno(idUsurio,nombre, contrasenia));
                        break;
                    case "profesor":
                        objetos.add(new Profesor(idUsurio,nombre,contrasenia));
                        break;
                    default:
                        break;
                }

            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return objetos;
    }


    /**
     * Escribe una lista de objetos en un archivo de texto.
     * Cada objeto se convierte en su representación en texto utilizando el método toString().
     *
     * @param nombreArchivo El nombre del archivo donde se escribirán los objetos.
     * @param objetos Lista de objetos que se guardarán en el archivo.
     */
   /* public void escribirFichero(String nombreArchivo, ArrayList<T> objetos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (T objeto : objetos) {
                writer.write(objeto.toString());
                writer.newLine(); // Agrega una nueva línea después de cada objeto
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] parchearCampos(String linea){
        String[] partes = linea.split(",");

        return partes;
    }*/

}