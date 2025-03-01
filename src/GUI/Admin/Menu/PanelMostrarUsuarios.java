package GUI.Admin.Menu;

import Core.LearnTrack;
import Core.Usuarios.Usuario;
import GUI.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelMostrarUsuarios extends JPanel {
    private JTextArea ouputArea;

    /**
     * Constructor del panel de Mostrar Usuarios.
     *
     * @param ventanaPrincipal La ventana principal que contiene este panel.
     * @param usuarios La instancia del registro que almacena los estudiantes.
     *
     */
    public PanelMostrarUsuarios(VentanaPrincipal ventanaPrincipal, ArrayList<Usuario> usuarios) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 30)));
        ouputArea = new JTextArea();
        ouputArea.setEditable(false);


        JScrollPane scrollPane = new JScrollPane(ouputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMaximumSize(new Dimension(400, 300));

        for (Usuario estudiante : usuarios) {
            ouputArea.append(estudiante.mostrardatos());
            ouputArea.append("\n");
        }
        add(scrollPane);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(botonSalir);

        // Acciones de los botones
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.cambiarPanel("panelMenuAdmin");
            }
        });
    }
}
