
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ConexionTester extends JFrame {

    private JTextField txtURL;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JTextArea txtResultado;
    private JButton btnProbar;

    public ConexionTester() {

        setTitle("ConexionTester - Prueba de conexión JDBC");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Panel de configuración
        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblURL = new JLabel("URL de conexión:");
        txtURL = new JTextField("jdbc:sqlite:datos/inventario.db");

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();

        btnProbar = new JButton("Probar conexión");

        panelDatos.add(lblURL);
        panelDatos.add(txtURL);

        panelDatos.add(lblUsuario);
        panelDatos.add(txtUsuario);

        panelDatos.add(lblContrasena);
        panelDatos.add(txtContrasena);

        panelDatos.add(new JLabel(""));
        panelDatos.add(btnProbar);

        // Área de resultados
        txtResultado = new JTextArea(8, 35);
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtResultado);

        panelPrincipal.add(panelDatos, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        add(panelPrincipal);

        // Evento del botón
        btnProbar.addActionListener(e -> probarConexion());
    }

    private void probarConexion() {

        String url = txtURL.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        txtResultado.setText("Probando conexión...\n");

        try {
            // Cargar el controlador JDBC de SQLite
            Class.forName("org.sqlite.JDBC");

            // Establecer y cerrar la conexión automáticamente
            try (Connection conexion = usuario.isEmpty()
                    && contrasena.isEmpty()
                    ? DriverManager.getConnection(url)
                    : DriverManager.getConnection(url, usuario, contrasena)) {

                txtResultado.setForeground(
                    new java.awt.Color(0, 128, 0)
                );

                txtResultado.setText(
                    "¡CONEXIÓN EXITOSA!\n\n" +
                    "La conexión con la base de datos se estableció " +
                    "correctamente.\n\n" +
                    "URL: " + url +
                    "\nEstado: Conectado correctamente."
                );

            }

        } catch (ClassNotFoundException e) {

            txtResultado.setForeground(
                new java.awt.Color(200, 0, 0)
            );

            txtResultado.setText(
                "ERROR: No se encontró el controlador JDBC.\n\n" +
                "Detalle técnico: " + e.getMessage()
            );

        } catch (SQLException e) {

            txtResultado.setForeground(
                new java.awt.Color(200, 0, 0)
            );

            txtResultado.setText(
                "FALLO DE CONEXIÓN.\n\n" +
                "No fue posible conectar con la base de datos.\n\n" +
                "Detalle técnico: " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ConexionTester ventana = new ConexionTester();
            ventana.setVisible(true);
        });
    }
}
