package GUI.Admin.Menu;

import Core.LearnTrack;
import Core.Usuarios.Alumno;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;
import GUI.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class PanelRegistroUsuarios extends JPanel {

    private static final Font FONT = new Font("Arial", Font.BOLD, 12);
    private static final int MAX_FIELD_WIDTH = 300;
    private static final int MAX_FIELD_HEIGHT = 30;

    /**
     * Constructor del panel de Registro.
     *
     * @param ventanaPrincipal La ventana principal que contiene este panel.
     * @param learnTrack       La instancia del registro que almacena los estudiantes.
     *                         Constructor de la clase VentanaPrincipal. Configura la interfaz gráfica, la barra de menú
     *                         y los paneles de la aplicación.
     */
    public PanelRegistroUsuarios(VentanaPrincipal ventanaPrincipal, LearnTrack learnTrack) {
        // Configuración del diseño del panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Espaciador superior
        add(Box.createRigidArea(new Dimension(0, 30)));

        JTextField nombre = createTextField("Nombre");
        add(nombre);
        add(Box.createRigidArea(new Dimension(0, 50)));

        JTextField contraseña = createTextField("Contraseña");
        add(contraseña);
        add(Box.createRigidArea(new Dimension(0, 30)));


        JButton botonEstudiante = new JButton("Agregar Estudiante");
        botonEstudiante.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(botonEstudiante);

        JButton botonProfesor = new JButton("Agregar Profesor");
        botonProfesor.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(botonProfesor);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(botonSalir);


        // Acciones de los botones
        botonEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombre.getText().isEmpty() || contraseña.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe introducir todos los datos");
                } else {
                    String idAleatorio = generarIdAleatorioUnico(5, learnTrack.getUsuarios());
                    learnTrack.getUsuarios().add(new Alumno(idAleatorio, nombre.getText(), contraseña.getText()));
                    JOptionPane.showMessageDialog(null, "idUsuario:" + idAleatorio + "Nombre:" + nombre + "Contraseña:" + contraseña);
                }
            }
        });

        botonProfesor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombre.getText().isEmpty() || contraseña.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe introducir todos los datos");
                } else {
                    String idAleatorio = generarIdAleatorioUnico(5, learnTrack.getUsuarios());
                    learnTrack.getUsuarios().add((new Profesor(idAleatorio, nombre.getText(), contraseña.getText())));
                    JOptionPane.showMessageDialog(null, "idUsuario:" + idAleatorio + "Nombre:" + nombre + "Contraseña:" + contraseña);
                }
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.cambiarPanel("panelMenuAdmin");
            }
        });

    }


    /**
     * Crea un campo de texto para ingresar la información del usuario.
     *
     * @param labelText El texto de la etiqueta para el campo.
     * @return El campo de texto configurado.
     */
    private JTextField createTextField(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(FONT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(MAX_FIELD_WIDTH, MAX_FIELD_HEIGHT));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(label);
        return textField;
    }

    private static String generarIdAleatorioUnico(int longitudMaxima, ArrayList<Usuario> usuarios) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        String idCandidato;
        boolean esUnico;

        do {
            int longitud = random.nextInt(longitudMaxima) + 1; // Longitud entre 1 y 5
            StringBuilder sb = new StringBuilder(longitud);
            for (int i = 0; i < longitud; i++) {
                int indice = random.nextInt(caracteres.length());
                sb.append(caracteres.charAt(indice));
            }
            idCandidato = sb.toString();

            // Verificar unicidad
            esUnico = true;
            for (Usuario usuario : usuarios) {
                if (usuario.getIdusuario().equals(idCandidato)) {
                    esUnico = false;
                    break;
                }
            }
        } while (!esUnico); // Seguir generando hasta que sea único

        return idCandidato;
    }
}

