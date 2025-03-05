package Core.Usuarios;

public class Alumno extends Usuario{
    public Alumno(String idusuario, String nombre,String contrasenia, String correo) {
        super(idusuario, nombre, contrasenia, correo);
    }
    public String rol(){
        return "Alumno";
    }

    @Override
    public String mostrardatos() {
        return super.mostrardatos() + "Rol: Alumno";
    }
}
