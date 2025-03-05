package Core.Usuarios;

public class Administrador extends Usuario{
    public Administrador(String idusuario, String nombre,String contrasenia, String correo){
        super(idusuario, nombre, contrasenia, correo);
    }
    public String rol(){
        return "Administrador";
    }
}
