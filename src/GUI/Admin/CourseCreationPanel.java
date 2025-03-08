package GUI.Admin;

import Core.Cursos.Curso;
import Core.Ficheros.FicheroCursos;
import Core.LearnTrack;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;
import Core.ValidacionAsigancionProf;
import GUI.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CourseCreationPanel extends JPanel {
    private ArrayList<Curso> cursos; // Using Curso from Core.Cursos
    private ArrayList<Usuario> usuarios;
    private JPanel containerPanel;
    private static final int ROWS_BEFORE_SCROLL = 10;
    private LearnTrack learnTrack;
    private FicheroCursos ficheroCursos; // For initialization, not direct saving

    public CourseCreationPanel(VentanaPrincipal ventanaPrincipal, LearnTrack learnTrack) {
        this.learnTrack = learnTrack;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Creación de Cursos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT); // Título a la izquierda
        topPanel.add(titleLabel, BorderLayout.WEST); // Cambiado de EAST a WEST
        add(topPanel, BorderLayout.NORTH);

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
        scrollPane.getViewport().setPreferredSize(new Dimension(600, 300));
        add(scrollPane, BorderLayout.CENTER);

        usuarios = learnTrack.getUsuarios();
        cursos = learnTrack.getCursos(); // Load courses from LearnTrack memory
        ficheroCursos = new FicheroCursos("cursos.csv", usuarios); // Solo para referencia, no escritura directa
        updateCourseDisplay();
    }

    private void updateCourseDisplay() {
        containerPanel.removeAll();

        JPanel courseListPanel = new JPanel();
        courseListPanel.setLayout(new BoxLayout(courseListPanel, BoxLayout.Y_AXIS));

        JPanel headerPanel = new JPanel(new GridLayout(1, 4));
        headerPanel.add(new JLabel("ID Curso"));
        headerPanel.add(new JLabel("Nombre"));
        headerPanel.add(new JLabel("Profesor"));
        headerPanel.add(new JLabel("Acción"));
        courseListPanel.add(headerPanel);

        for (Curso curso : cursos) {
            JPanel coursePanel = new JPanel(new GridLayout(1, 4));
            JTextField idField = new JTextField(curso.getId());
            idField.setEditable(false);
            idField.setPreferredSize(new Dimension(80, 25));
            JTextField nameField = new JTextField(curso.getNombre());
            nameField.setEditable(false);
            nameField.setPreferredSize(new Dimension(150, 25));
            JTextField profField = new JTextField(curso.getProfesor() != null ? curso.getProfesor().getNombre() : "Sin asignar");
            profField.setEditable(false);
            profField.setPreferredSize(new Dimension(150, 25));

            JButton assignButton = new JButton("Asignar Profesor");
            assignButton.addActionListener(e -> showAssignProfessorForm(curso));

            coursePanel.add(idField);
            coursePanel.add(nameField);
            coursePanel.add(profField);
            coursePanel.add(assignButton);
            courseListPanel.add(coursePanel);
        }

        if (cursos.size() + 1 > ROWS_BEFORE_SCROLL) {
            courseListPanel.setPreferredSize(null);
        } else {
            int rowHeight = headerPanel.getPreferredSize().height;
            courseListPanel.setPreferredSize(new Dimension(600, rowHeight * (cursos.size() + 1)));
        }

        JButton addCourseButton = new JButton("Añadir Curso");
        addCourseButton.addActionListener(e -> showAddCourseForm());

        containerPanel.add(courseListPanel, BorderLayout.CENTER);
        containerPanel.add(addCourseButton, BorderLayout.SOUTH);

        containerPanel.revalidate();
        containerPanel.repaint();
    }

    private void showAssignProfessorForm(Curso curso) {
        ArrayList<Profesor> availableProfessors = ValidacionAsigancionProf.getAvailableProfessors(usuarios, cursos);
        if (availableProfessors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay profesores disponibles para asignar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox<Profesor> professorCombo = new JComboBox<>(availableProfessors.toArray(new Profesor[0]));
        professorCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Profesor) {
                    setText(((Profesor) value).getNombre());
                }
                return this;
            }
        });

        if (curso.getProfesor() != null) {
            professorCombo.setSelectedItem(curso.getProfesor());
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Seleccione un profesor:"), BorderLayout.NORTH);
        panel.add(professorCombo, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Asignar Profesor",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            Profesor selectedProfesor = (Profesor) professorCombo.getSelectedItem();
            curso.setProfesor(selectedProfesor); // Update in memory only
            JOptionPane.showMessageDialog(this, "Profesor asignado con éxito",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            updateCourseDisplay();
        }
    }

    private void showAddCourseForm() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(20);

        panel.add(new JLabel("ID Curso:"));
        panel.add(idField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nameField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Añadir Nuevo Curso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String nombre = nameField.getText().trim();

            // Validación básica
            if (id.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si el ID ya existe
            for (Curso curso : cursos) {
                if (curso.getId().equals(id)) {
                    JOptionPane.showMessageDialog(this, "El ID ya existe",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Crear y añadir el nuevo curso (sin profesor inicialmente)
            Curso newCurso = new Curso(id, nombre, null);
            cursos.add(newCurso); // Añadir a la lista en memoria
            JOptionPane.showMessageDialog(this, "Curso añadido con éxito",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            updateCourseDisplay();
        }
    }
}