package Core.Ficheros;

import Core.Cursos.Curso;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;

import java.io.*;
import java.util.ArrayList;

public class FicheroCursos {
    private final String filePath;
    private final ArrayList<Usuario> usuarios; // To resolve professor IDs

    // Constructor with file path and user list for professor lookup
    public FicheroCursos(String filePath, ArrayList<Usuario> usuarios) {
        this.filePath = filePath;
        this.usuarios = usuarios;
    }

    public ArrayList<Curso> crearCursos() {
        ArrayList<Curso> objetos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.trim().split(",");
                if (datos.length != 3) {
                    System.err.println("Línea inválida: " + linea);
                    continue; // Saltar líneas inválidas
                }

                String idCurso = datos[0].trim();
                String nombre = datos[1].trim();
                String profesorId = datos[2].trim();

                // Validación básica
                if (idCurso.isEmpty() || nombre.isEmpty()) {
                    System.err.println("Campos vacíos en línea: " + linea);
                    continue;
                }

                // Buscar el profesor por ID
                Profesor profesor = null;
                if (!profesorId.equals("null")) {
                    profesor = findProfesorById(profesorId);
                    if (profesor == null) {
                        System.err.println("Profesor con ID " + profesorId + " no encontrado para el curso: " + linea);
                        continue;
                    }
                }

                // Crear el Curso con el objeto Profesor, no el ID
                objetos.add(new Curso(idCurso, nombre, profesor));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo " + filePath + ": " + e.getMessage(), e);
        }
        return objetos;
    }

    /**
     * Escribe la lista de cursos en un archivo CSV.
     *
     * @param cursos Lista de cursos a guardar
     */
    public void escribirCursos(ArrayList<Curso> cursos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Curso curso : cursos) {
                String profesorId = curso.getProfesor() != null ? curso.getProfesor().getIdusuario() : "null";
                String linea = String.format("%s,%s,%s",
                        curso.getId(), curso.getNombre(), profesorId);
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo " + filePath + ": " + e.getMessage(), e);
        }
    }

    // Helper method to find a professor by ID in the usuarios list
    private Profesor findProfesorById(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Profesor && usuario.getIdusuario().equals(id)) {
                return (Profesor) usuario;
            }
        }
        return null;
    }
}