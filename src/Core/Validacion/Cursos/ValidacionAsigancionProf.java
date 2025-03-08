package Core; // Adjust package as needed

import Core.Cursos.Curso;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;

import java.util.ArrayList;

public class ValidacionAsigancionProf {

    public static ArrayList<Profesor> getAvailableProfessors(ArrayList<Usuario> usuarios, ArrayList<Curso> cursos) {
        ArrayList<Profesor> available = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Profesor && !isProfessorAssigned((Profesor) usuario, cursos)) {
                available.add((Profesor) usuario);
            }
        }
        return available;
    }

    public static boolean isProfessorAssigned(Profesor profesor, ArrayList<Curso> cursos) {
        for (Curso curso : cursos) {
            if (curso.getProfesor() == profesor) {
                return true;
            }
        }
        return false;
    }
}
