package GUI;

import Core.LearnTrack;
import Core.Usuarios.Usuario;


import javax.swing.*;


import java.awt.*;

/**
 * Ventana principal de la aplicación AutoBibliogest, que gestiona la interfaz gráfica del usuario
 * y permite la navegación entre distintos paneles (catálogo, búsqueda, reservas, etc.).
 */
public class VentanaPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private JMenuBar barraMenu;
    private LearnTrack learnTrack;
    private Usuario usuarioactivo;

    public Usuario getUsuarioactivo() {
        return usuarioactivo;
    }

    public void setUsuarioactivo(Usuario usuarioactivo) {
        this.usuarioactivo = usuarioactivo;
    }

    /**
     * Constructor de la clase VentanaPrincipal. Configura la interfaz gráfica, la barra de menú
     * y los paneles de la aplicación.
     */
    public VentanaPrincipal() {
        learnTrack = new LearnTrack();
        learnTrack.iniciarLearnTrack();

        // Configuración del JFrame
        setTitle("Learn Track");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar CardLayout y panel contenedor
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        inicializarPaneles();


        cardLayout.show(panelContenedor, "panelInicio");

        this.add(panelContenedor);

        // Hacer visible el JFrame
        setVisible(true);

    }

    /**
     * Hace visible la barra de menú en la ventana principal.
     */
    public void hacerVisibleMenu() {
        setJMenuBar(barraMenu);
    }

    /**
     * Cambia el panel activo (visible) en el contenedor.
     *
     * @param nombrePanel El nombre del panel a mostrar.
     */
    public void cambiarPanel(String nombrePanel) {
        cardLayout.show(panelContenedor, nombrePanel);
    }

    /**
     * Muestra un panel específico en el contenedor y cambia a él.
     *
     * @param panel          El panel a mostrar.
     * @param nombreDelPanel Nombre del panel para agregarlo al contenedor.
     */
    public void mostrarPanel(JPanel panel, String nombreDelPanel) {
        panelContenedor.add(panel, nombreDelPanel);
        cambiarPanel(nombreDelPanel);
    }

    /**
     * Agrega un panel al contenedor sin cambiar a él.
     *
     * @param panel          El panel a agregar.
     * @param nombreDelPanel Nombre del panel para agregarlo al contenedor.
     */
    public void agregarPanel(JPanel panel, String nombreDelPanel) {
        panelContenedor.add(panel, nombreDelPanel);
    }


    /**
     * Inicializa y agrega los paneles principales al contenedor.
     */
    private void inicializarPaneles() {
        panelContenedor.add(new PanelInicio(this, learnTrack), "panelInicio");
    }
}