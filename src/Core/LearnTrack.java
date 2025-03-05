package Core;

import Core.Cursos.Curso;
import Core.Ficheros.FicheroUsuarios;
import Core.Usuarios.Administrador;
import Core.Usuarios.Alumno;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;


import java.util.ArrayList;

public class LearnTrack {
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    private ArrayList<Usuario> usuarios;
    private ArrayList<Curso> cursos;

    public LearnTrack(){
        this.cursos = new ArrayList<>();
        this.usuarios = new ArrayList<>();

    }


    /**
     * Carga los datos de libros, usuarios y préstamos desde los archivos correspondientes.
     */
    public void iniciarLearnTrack() {
        FicheroUsuarios ficheroUsuarios = new FicheroUsuarios("usuarios.csv");
        usuarios=ficheroUsuarios.crearUsuarios();

    }

    /*public void cerrarLearnTrack() {
        FicheroGenerico<Alumno> ficheroUsuario = new FicheroGenerico<>(null);
        ficheroUsuario.escribirFichero("alumnos.txt", alumnos);

    }*/
}
