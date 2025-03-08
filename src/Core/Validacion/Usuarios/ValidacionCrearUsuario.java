package Core.Validacion.Usuarios;

import Core.Usuarios.Usuario;

import java.util.ArrayList;
import java.util.Random;


public class ValidacionCrearUsuario {

    public static boolean emailExists(String email, ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public static String generateUniqueId(ArrayList<Usuario> usuarios) {
        Random random = new Random();
        String id;
        do {
            int number = random.nextInt(1000); // Genera un número entre 0 y 999
            String numberStr = String.format("%03d", number); // Formatea con 3 dígitos (ej. "001")
            char letter = (char) ('A' + random.nextInt(26)); // Genera una letra de A a Z
            id = numberStr + letter;
        } while (idExists(id, usuarios));
        return id;
    }

    private static boolean idExists(String id, ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdusuario().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
