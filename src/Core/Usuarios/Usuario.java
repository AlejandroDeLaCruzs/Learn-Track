package Core.Usuarios;

abstract public class Usuario {

    String idusuario;
    String correo;
    String nombre;
    String contrasenia;

    abstract public String rol();

    public Usuario(String idusuario, String nombre,String contrasenia, String correo){
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.correo = correo;
    }

    public String mostrardatos(){
        return new String("idUsuario:"+ idusuario + "Nombre:" + nombre + "Contraseña:" + contrasenia);
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

}
