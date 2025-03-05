package Core.Usuarios;

public class Profesor extends Usuario{
    public Profesor(String idusuario, String nombre,String contrasenia, String correo){
        super(idusuario, nombre, contrasenia, correo);
    }

    public String rol(){
        return "Profesor";
    }

    @Override
    public String mostrardatos() {
        return super.mostrardatos() + "Rol: profesor";
    }
}
