package Core.Usuarios;

abstract public class Usuario {
    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    String idusuario;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    String correo;

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    String contrasenia;

    abstract public String rol();


    public Usuario(String idusuario, String nombre,String contrasenia){
        this.idusuario = idusuario;
        this.correo = nombre;
        this.contrasenia = contrasenia;
    }


}
