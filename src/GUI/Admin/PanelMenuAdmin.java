package GUI.Admin;

import Core.LearnTrack;
import Core.Usuarios.Usuario;
import GUI.Admin.Menu.PanelMostrarUsuarios;
import GUI.Admin.Menu.PanelRegistroUsuarios;
import GUI.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelMenuAdmin extends JPanel {
    private static final Font FONT = new Font("Arial", Font.BOLD, 12);

    public PanelMenuAdmin(VentanaPrincipal ventanaPrincipal, LearnTrack learnTrack) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 40)));

        add(botonRegistro(ventanaPrincipal, learnTrack));
        add(Box.createRigidArea(new Dimension(0, 30)));

        add(botonMostrarEstudiantes(ventanaPrincipal, learnTrack.getUsuarios()));
        add(Box.createRigidArea(new Dimension(0, 30)));

        add(botonBusquedaPorMatricula(ventanaPrincipal, learnTrack.getUsuarios()));

        add(Box.createRigidArea(new Dimension(0, 30)));
        add(botonSalir(ventanaPrincipal, learnTrack));


    }

    /**
     * Crea un botón de para ir al panel de Registro con su ActionListener correspondiente.
     *
     * @param ventana Ventana principal que contiene el panel.
     * @return El botón de inicio de sesión configurado.
     */
    private JButton botonRegistro(VentanaPrincipal ventana, LearnTrack learnTrack) {
        JButton boton = new JButton("Agregar nuevos estudiantes/Profesores");
        boton.setFont(FONT);
        boton.setForeground(Color.BLACK);
        boton.setBackground(Color.WHITE);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.mostrarPanel(new PanelRegistroUsuarios(ventana, learnTrack), "panelRegistroUsuarios");
            }
        });
        return boton;
    }

    private JButton botonModifcar(VentanaPrincipal ventana) {
        JButton boton = new JButton("Modificar/Borrar");
        boton.setFont(FONT);
        boton.setForeground(Color.BLACK);
        boton.setBackground(Color.WHITE);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.cambiarPanel("panelRegistro");
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
    private JButton botonMostrarEstudiantes(VentanaPrincipal ventana, ArrayList<Usuario> usuarios) {
        JButton boton = new JButton("Mostrar Estudiantes");
        boton.setFont(FONT);
        boton.setForeground(Color.BLACK);
        boton.setBackground(Color.WHITE);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    ventana.mostrarPanel(new PanelMostrarUsuarios(ventana, usuarios), "PanelListaUsuarios");
            }
        });
        return boton;
    }

    /**
     * Crea un botón de para ir al panel de Busqueda con su ActionListener correspondiente.
     *
     * @param ventana Ventana principal que contiene el panel.
     * @return El botón de inicio de sesión configurado.
     */
    private JButton botonBusquedaPorMatricula(VentanaPrincipal ventana, ArrayList<Usuario> usuarios) {
        JButton boton = new JButton("Busqueda");
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

    private JButton botonSalir(VentanaPrincipal ventana, LearnTrack learnTrack) {
        JButton boton = new JButton("Salir");
        boton.setFont(FONT);
        boton.setForeground(Color.BLACK);
        boton.setBackground(Color.WHITE);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.cambiarPanel("panelinicioadmin");
            }
        });
        return boton;
    }
}
