package Core.Usuarios;

public class Profesor extends Usuario{
    public Profesor(String idusuario, String nombre,String contrasenia){
        super(idusuario, nombre, contrasenia);
    }

    public String rol(){
        return "Profesor";
    }

    @Override
    public String mostrardatos() {
        return super.mostrardatos() + "Rol: profesor";
    }
}
