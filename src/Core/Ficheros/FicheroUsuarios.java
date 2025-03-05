package Core.Ficheros;

import Core.Usuarios.Administrador;
import Core.Usuarios.Alumno;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;

import java.io.*;
import java.util.ArrayList;

public class FicheroUsuarios {
    private final String filePath;

    // Constructor to allow flexible file path
    public FicheroUsuarios(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Usuario> crearUsuarios() {
        ArrayList<Usuario> objetos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.trim().split(",");
                if (datos.length != 5) {
                    System.err.println("Línea inválida: " + linea);
                    continue; // Saltar líneas inválidas
                }

                String idUsuario = datos[0].trim();
                String nombre = datos[1].trim();
                String contrasenia = datos[3].trim();
                String rol = datos[4].trim().toLowerCase();
                String correo = datos[2].trim();

                // Validación básica
                if (idUsuario.isEmpty() || nombre.isEmpty() || contrasenia.isEmpty() || rol.isEmpty() || correo.isEmpty()) {
                    System.err.println("Campos vacíos en línea: " + linea);
                    continue;
                }

                switch (rol) {
                    case "admin":
                        objetos.add(new Administrador(idUsuario, nombre, contrasenia, correo));
                        break;
                    case "alumno":
                        objetos.add(new Alumno(idUsuario, nombre, contrasenia, correo));
                        break;
                    case "profesor":
                        objetos.add(new Profesor(idUsuario, nombre, contrasenia, correo));
                        break;
                    default:
                        System.err.println("Rol desconocido: " + rol + " en línea: " + linea);
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo " + filePath + ": " + e.getMessage(), e);
        }
        return objetos;
    }

    /**
     * Escribe la lista de usuarios en un archivo CSV.
     *
     * @param usuarios Lista de usuarios a guardar
     */
    public void escribirUsuarios(ArrayList<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Usuario usuario : usuarios) {
                String rol = "";
                if (usuario instanceof Administrador) rol = "admin";
                else if (usuario instanceof Alumno) rol = "alumno";
                else if (usuario instanceof Profesor) rol = "profesor";

                String linea = String.format("%s,%s,%s,%s,%s",
                        usuario.getIdusuario(), usuario.getNombre(), usuario.getContrasenia(), rol, usuario.getCorreo());
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo " + filePath + ": " + e.getMessage(), e);
        }
    }
}