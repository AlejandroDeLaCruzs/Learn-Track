package Core;

import Core.Cursos.Curso;
import Core.Ficheros.FicheroCursos;
import Core.Ficheros.FicheroUsuarios;
import Core.Usuarios.Administrador;
import Core.Usuarios.Alumno;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;


import java.util.ArrayList;

public class LearnTrack {
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

        FicheroCursos ficheroCursos = new FicheroCursos("cursos.csv", usuarios);
        cursos=ficheroCursos.crearCursos();


    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
}
