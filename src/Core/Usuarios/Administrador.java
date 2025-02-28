package Core.Usuarios;

public class Administrador extends Usuario{
    public Administrador(String idusuario, String nombre,String contrasenia){
        super(idusuario, nombre, contrasenia);
    }
    public String rol(){
        return "Administrador";
    }
}
