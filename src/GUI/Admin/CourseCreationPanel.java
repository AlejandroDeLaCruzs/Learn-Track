package GUI.Admin;

import Core.Cursos.Curso;
import Core.LearnTrack;
import Core.Usuarios.Profesor;
import Core.Usuarios.Usuario;
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

    public CourseCreationPanel(VentanaPrincipal ventanaPrincipal, LearnTrack learnTrack) {
        this.learnTrack = learnTrack;
        setLayout(new BorderLayout());

        // Panel superior con título a la derecha
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Creación de Cursos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(titleLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Panel contenedor para la lista de cursos
        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setPreferredSize(new Dimension(600, 300));
        add(scrollPane, BorderLayout.CENTER);

        usuarios = learnTrack.getUsuarios();
        cursos = learnTrack.getCursos(); // Load courses from LearnTrack memory
        updateCourseDisplay();
    }

    private void updateCourseDisplay() {
        containerPanel.removeAll();

        JPanel courseListPanel = new JPanel();
        courseListPanel.setLayout(new BoxLayout(courseListPanel, BoxLayout.Y_AXIS));

        // Headers
        JPanel headerPanel = new JPanel(new GridLayout(1, 4));
        headerPanel.add(new JLabel("ID Curso"));
        headerPanel.add(new JLabel("Nombre"));
        headerPanel.add(new JLabel("Profesor"));
        headerPanel.add(new JLabel("Acción"));
        courseListPanel.add(headerPanel);

        // Lista de cursos
        for (Curso curso : cursos) {
            JPanel coursePanel = new JPanel(new GridLayout(1, 4));
            JTextField idField = new JTextField(curso.getId());
            idField.setEditable(false);
            idField.setPreferredSize(new Dimension(80, 25));
            JTextField nameField = new JTextField(curso.getNombre());
            nameField.setEditable(false);
            nameField.setPreferredSize(new Dimension(150, 25));
            // Mostrar el nombre del profesor, no el ID del curso
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

        // Control del scroll
        if (cursos.size() + 1 > ROWS_BEFORE_SCROLL) {
            courseListPanel.setPreferredSize(null); // Permitir crecimiento natural
        } else {
            int rowHeight = headerPanel.getPreferredSize().height;
            courseListPanel.setPreferredSize(new Dimension(600, rowHeight * (cursos.size() + 1)));
        }

        // Botón para añadir curso
        JButton addCourseButton = new JButton("Añadir Curso");
        addCourseButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidad de añadir curso no implementada aún",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        containerPanel.add(courseListPanel, BorderLayout.CENTER);
        containerPanel.add(addCourseButton, BorderLayout.SOUTH);

        containerPanel.revalidate();
        containerPanel.repaint();
    }

    private void showAssignProfessorForm(Curso curso) {
        ArrayList<Profesor> availableProfessors = getAvailableProfessors();
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

        // Preseleccionar el profesor actual si ya tiene uno
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
            curso.setProfesor(selectedProfesor); // Update the professor in memory
            JOptionPane.showMessageDialog(this, "Profesor asignado con éxito",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            updateCourseDisplay();
        }
    }

    private ArrayList<Profesor> getAvailableProfessors() {
        ArrayList<Profesor> available = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Profesor && !isProfessorAssigned((Profesor) usuario)) {
                available.add((Profesor) usuario);
            }
        }
        return available;
    }

    private boolean isProfessorAssigned(Profesor profesor) {
        for (Curso curso : cursos) {
            if (curso.getProfesor() == profesor) {
                return true;
            }
        }
        return false;
    }
}