package GUI.Admin;

import Core.LearnTrack;
import GUI.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInicioAdmin extends JPanel {
    private static final Font FONT = new Font("Arial", Font.BOLD, 12);

    public PanelInicioAdmin(VentanaPrincipal ventanaPrincipal, LearnTrack learnTrack) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 40)));


        add(botonRegistro(ventanaPrincipal, learnTrack));
        add(Box.createRigidArea(new Dimension(0, 40)));




    }
    /**
     * Crea un botón de para ir al panel de Registro con su ActionListener correspondiente.
     *
     * @param ventana Ventana principal que contiene el panel.
     * @return El botón de inicio de sesión configurado.
     */
    private JButton botonRegistro(VentanaPrincipal ventana, LearnTrack learnTrack) {
        JButton boton = new JButton("Agregar nuevos usuarios");
        boton.setFont(FONT);
        boton.setForeground(Color.BLACK);
        boton.setBackground(Color.WHITE);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.mostrarPanel(new UserManagementPanel(ventana, learnTrack), "panelMenuAdmin");
            }
        });
        return boton;
    }


    /**
     * Crea un botón de para ir al panel de Mostrar estudiantes con su ActionListener correspondiente.
     *
     * @param ventana Ventana principal que contiene el panel.
     * @return El botón de inicio de sesión configurado.
     */
    private JButton botonCursos(VentanaPrincipal ventana, LearnTrack learnTrack) {
        JButton boton = new JButton("Mostrar Estudiantes");
        boton.setFont(FONT);
        boton.setForeground(Color.BLACK);
        boton.setBackground(Color.WHITE);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return boton;
    }
}
