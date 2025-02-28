package Core.Validacion;

import Core.Usuarios.Administrador;
import Core.Usuarios.Alumno;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;
import GUI.VentanaPrincipal;

import java.util.ArrayList;

public class ValidadorInicioSesion {
    public static boolean esValido(String email, char[] contrasenia, VentanaPrincipal ventanaContenedor, ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (email.equals(usuario.getCorreo())) {
                String contraseniaIngresada = new String(contrasenia);
                if (contraseniaIngresada.equals(usuario.getContrasenia())) {
                    // Crear el objeto usuarioactivo según el tipo real del usuario
                    Usuario usuarioactivo;
                    if (usuario instanceof Administrador) {
                        usuarioactivo = new Administrador(usuario.getIdusuario(), usuario.getCorreo(), usuario.getContrasenia());
                    } else if (usuario instanceof Alumno) {
                        usuarioactivo = new Alumno(usuario.getIdusuario(), usuario.getCorreo(), usuario.getContrasenia());
                    } else {
                        usuarioactivo = new Profesor(usuario.getIdusuario(), usuario.getCorreo(), usuario.getContrasenia());
                    }

                    // Asignar el usuario activo a la ventana
                    ventanaContenedor.setUsuarioactivo(usuarioactivo);
                    return true;
                }
            }
        }
        return false;
    }

}
