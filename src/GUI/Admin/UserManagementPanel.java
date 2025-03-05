package GUI.Admin;

import Core.LearnTrack;
import Core.Usuarios.Administrador;
import Core.Usuarios.Alumno;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;
import GUI.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import static Core.Validacion.ValidacionCrearUsuario.*;

public class UserManagementPanel extends JPanel {
    private ArrayList<Usuario> usuarios;
    private JPanel containerPanel;

    public UserManagementPanel(VentanaPrincipal ventanaPrincipal, LearnTrack learnTrack) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Registro de Usuarios");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        topPanel.add(titleLabel, BorderLayout.WEST);

        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> {
            ventanaPrincipal.cambiarPanel("panelinicioadmin");
        });
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.add(exitButton);
        topPanel.add(exitPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // Asegurar que el scroll aparezca después de 10 filas
        scrollPane.getViewport().setPreferredSize(new Dimension(600, 300)); // Tamaño fijo inicial
        add(scrollPane, BorderLayout.CENTER);

        usuarios = learnTrack.getUsuarios();
        updateUserDisplay();
    }

    private void updateUserDisplay() {
        containerPanel.removeAll();

        JPanel userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));

        JPanel headerPanel = new JPanel(new GridLayout(1, 6));
        headerPanel.add(new JLabel("ID"));
        headerPanel.add(new JLabel("Nombre"));
        headerPanel.add(new JLabel("Correo"));
        headerPanel.add(new JLabel("Contraseña"));
        headerPanel.add(new JLabel("Rol"));
        headerPanel.add(new JLabel("Acción"));
        userListPanel.add(headerPanel);

        for (Usuario usuario : usuarios) {
            JPanel userPanel = new JPanel(new GridLayout(1, 6));
            JTextField idField = new JTextField(usuario.getIdusuario());
            idField.setEditable(false);
            idField.setPreferredSize(new Dimension(50, 25)); // Tamaño mínimo
            JTextField nombreField = new JTextField(usuario.getNombre());
            nombreField.setEditable(false);
            nombreField.setPreferredSize(new Dimension(100, 25));
            JTextField correoField = new JTextField(usuario.getCorreo());
            correoField.setEditable(false);
            correoField.setPreferredSize(new Dimension(150, 25));
            JTextField passField = new JTextField(usuario.getContrasenia());
            passField.setEditable(false);
            passField.setPreferredSize(new Dimension(100, 25));
            JTextField rolField = new JTextField(getRolUsuario(usuario));
            rolField.setEditable(false);
            rolField.setPreferredSize(new Dimension(80, 25));

            JButton deleteButton = new JButton("Eliminar");
            deleteButton.addActionListener(e -> {
                usuarios.remove(usuario);
                updateUserDisplay();
            });

            userPanel.add(idField);
            userPanel.add(nombreField);
            userPanel.add(correoField);
            userPanel.add(passField);
            userPanel.add(rolField);
            userPanel.add(deleteButton);
            userListPanel.add(userPanel);
        }


        JButton addButton = new JButton("Agregar Usuario");
        addButton.addActionListener(e -> showAddUserForm());

        containerPanel.add(userListPanel, BorderLayout.CENTER);
        containerPanel.add(addButton, BorderLayout.SOUTH);

        containerPanel.revalidate();
        containerPanel.repaint();
    }

    private String getRolUsuario(Usuario usuario) {
        if (usuario instanceof Administrador) return "Admin";
        if (usuario instanceof Alumno) return "Alumno";
        if (usuario instanceof Profesor) return "Profesor";
        return "Desconocido";
    }


    private void showAddUserForm() {
        String nombre = "";
        String correo = "";
        String pass = "";
        String tipoUsuario = "";
        String generatedId = generateUniqueId(usuarios);

        boolean validInput = false;
        while (!validInput) {
            JTextField nombreField = new JTextField(nombre, 10);
            JTextField correoField = new JTextField(correo, 10);
            JTextField passField = new JTextField(pass, 10);
            JLabel idLabel = new JLabel(generatedId);

            JRadioButton adminRadio = new JRadioButton("Admin");
            JRadioButton alumnoRadio = new JRadioButton("Alumno");
            JRadioButton profesorRadio = new JRadioButton("Profesor");
            ButtonGroup userTypeGroup = new ButtonGroup();
            userTypeGroup.add(adminRadio);
            userTypeGroup.add(alumnoRadio);
            userTypeGroup.add(profesorRadio);
            // Restaurar selección previa
            if (tipoUsuario.equals("admin")) adminRadio.setSelected(true);
            else if (tipoUsuario.equals("alumno")) alumnoRadio.setSelected(true);
            else if (tipoUsuario.equals("profesor")) profesorRadio.setSelected(true);

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
            formPanel.add(new JLabel("ID (generado):"), gbc);
            gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(idLabel, gbc);

            gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
            formPanel.add(new JLabel("Nombre:"), gbc);
            gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(nombreField, gbc);

            gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
            formPanel.add(new JLabel("Correo:"), gbc);
            gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(correoField, gbc);

            gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
            formPanel.add(new JLabel("Contraseña:"), gbc);
            gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(passField, gbc);

            gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.EAST;
            formPanel.add(new JLabel("Tipo de usuario:"), gbc);
            gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
            JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            radioPanel.add(adminRadio);
            radioPanel.add(alumnoRadio);
            radioPanel.add(profesorRadio);
            formPanel.add(radioPanel, gbc);

            int result = JOptionPane.showConfirmDialog(
                    this, formPanel, "Agregar Usuario",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                nombre = nombreField.getText();
                correo = correoField.getText();
                pass = passField.getText();

                tipoUsuario = "";
                if (adminRadio.isSelected()) tipoUsuario = "admin";
                else if (alumnoRadio.isSelected()) tipoUsuario = "alumno";
                else if (profesorRadio.isSelected()) tipoUsuario = "profesor";

                if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || tipoUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this, "Todos los campos son requeridos, incluido el tipo de usuario",
                            "Error", JOptionPane.ERROR_MESSAGE
                    );
                } else if (emailExists(correo, usuarios)) {
                    JOptionPane.showMessageDialog(
                            this, "El correo ya está registrado",
                            "Error", JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    switch (tipoUsuario) {
                        case "admin":
                            usuarios.add(new Administrador(generatedId, nombre, correo, pass));
                            break;
                        case "alumno":
                            usuarios.add(new Alumno(generatedId, nombre, correo, pass));
                            break;
                        case "profesor":
                            usuarios.add(new Profesor(generatedId, nombre, correo, pass));
                            break;
                    }
                    updateUserDisplay();
                    validInput = true;
                }
            } else {
                validInput = true; // Salir si se cancela
            }
        }
    }
}