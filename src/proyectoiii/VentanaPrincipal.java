/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoiii;

/**
 *
 * @author Melannie, Christian, Carlos
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private MatrizOrtogonal02 matriz;
    private MatrizPanel panelMatriz;

    private JTextField txtPlaca, txtColor, txtLinea, txtModelo, txtPropietario;
    private JTextField txtBuscar;

    public VentanaPrincipal() {
        matriz = new MatrizOrtogonal02();
        panelMatriz = new MatrizPanel(matriz);

        setTitle("Gestión de Vehículos - Matriz Ortogonal");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(panelMatriz, BorderLayout.CENTER);
        add(crearPanelControles(), BorderLayout.NORTH);
        add(crearPanelBusqueda(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelControles() {
        JPanel panel = new JPanel();

        txtPlaca = new JTextField(7);
        txtColor = new JTextField(7);
        txtLinea = new JTextField(7);
        txtModelo = new JTextField(7);
        txtPropietario = new JTextField(7);
        JButton btnInsertar = new JButton("Insertar");
        JButton btnEliminar = new JButton("Eliminar por Placa");

        panel.add(new JLabel("Placa:"));
        panel.add(txtPlaca);
        panel.add(new JLabel("Color:"));
        panel.add(txtColor);
        panel.add(new JLabel("Línea:"));
        panel.add(txtLinea);
        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Propietario:"));
        panel.add(txtPropietario);
        panel.add(btnInsertar);
        panel.add(btnEliminar);

        btnInsertar.addActionListener(e -> insertarVehiculo());
        btnEliminar.addActionListener(e -> eliminarVehiculo());

        return panel;
    }

    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel();

        txtBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        JButton btnMostrarTodo = new JButton("Mostrar Todo");

        panel.add(new JLabel("Buscar:"));
        panel.add(txtBuscar);
        panel.add(btnBuscar);
        panel.add(btnMostrarTodo);

        btnBuscar.addActionListener(e -> buscarVehiculos());
        btnMostrarTodo.addActionListener(e -> {
            panelMatriz.actualizarTablaCompleta();
            txtBuscar.setText("");
        });

        return panel;
    }

    private void insertarVehiculo() {
        String placa = txtPlaca.getText().trim().toUpperCase();
        String color = txtColor.getText().trim();
        String linea = txtLinea.getText().trim();
        String modelo = txtModelo.getText().trim();
        String propietario = txtPropietario.getText().trim();

        if (placa.isEmpty() || color.isEmpty() || linea.isEmpty() || modelo.isEmpty() || propietario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        // Validar que color solo tenga letras
        if (!validarColor(color)) {
            JOptionPane.showMessageDialog(this, "El color solo debe contener letras.");
            return;
        }

        // Validar que modelo tenga exactamente 4 dígitos numéricos
        if (!validarModelo(modelo)) {
            JOptionPane.showMessageDialog(this, "El modelo debe ser un número de 4 dígitos.");
            return;
        }

        if (!matriz.insertar(placa, color, linea, modelo, propietario)) {
            JOptionPane.showMessageDialog(this, "No se pudo insertar el vehículo. Verifica la placa.");
        } else {
            panelMatriz.actualizarTablaCompleta();
            limpiarCampos();
        }
    }

    private void eliminarVehiculo() {
        String placa = txtPlaca.getText().trim().toUpperCase();
        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa la placa del vehículo a eliminar.");
            return;
        }

        if (matriz.eliminarPorPlaca(placa)) {
            JOptionPane.showMessageDialog(this, "Vehículo con placa " + placa + " eliminado.");
            panelMatriz.actualizarTablaCompleta();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún vehículo con la placa " + placa + ".");
        }
    }

    private void buscarVehiculos() {
        String criterio = txtBuscar.getText().trim();
        if (!criterio.isEmpty()) {
            List<Nodo> resultados = matriz.buscar(criterio);
            panelMatriz.actualizarTablaBusqueda(resultados);
        } else {
            JOptionPane.showMessageDialog(this, "Ingresa un criterio de búsqueda.");
        }
    }

    private void limpiarCampos() {
        txtPlaca.setText("");
        txtColor.setText("");
        txtLinea.setText("");
        txtModelo.setText("");
        txtPropietario.setText("");
    }

    private boolean validarColor(String color) {
        // Solo letras (mayúsculas, minúsculas, espacios y caracteres comunes en español)
        return color.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+");
    }

    private boolean validarModelo(String modelo) {
        // Solo números, exactamente 4 dígitos
        return modelo.matches("\\d{4}");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}