package Core.Usuarios;

public class Alumno extends Usuario{
    public Alumno(String idusuario, String nombre,String contrasenia) {
        super(idusuario, nombre, contrasenia);
    }
    public String rol(){
        return "Alumno";
    }

}
