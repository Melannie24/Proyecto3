/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoiii;

/**
 *
 * @author Melannie, Christian, Carlos
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MatrizPanel extends JPanel {
    private MatrizOrtogonal02 matriz;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public MatrizPanel(MatrizOrtogonal02 matriz) {
        this.matriz = matriz;
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        actualizarTablaCompleta();
    }

    public void actualizarTablaCompleta() {
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        String[] columnas = {"Placa", "Color", "Línea", "Modelo", "Propietario"};
        for (String col : columnas) {
            modeloTabla.addColumn(col);
        }

        List<Nodo> listaVehiculos = obtenerTodosLosVehiculos();
        for (Nodo nodo : listaVehiculos) {
            Object[] fila = {
                nodo.placa,
                nodo.color,
                nodo.linea,
                nodo.modelo,
                nodo.propietario
            };
            modeloTabla.addRow(fila);
        }
    }

    // CORREGIDO: Nombre del método para búsqueda
    public void actualizarTablaBusqueda(List<Nodo> resultados) {
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        String[] columnas = {"Placa", "Color", "Línea", "Modelo", "Propietario"};
        for (String col : columnas) {
            modeloTabla.addColumn(col);
        }

        for (Nodo nodo : resultados) {
            Object[] fila = {
                nodo.placa,
                nodo.color,
                nodo.linea,
                nodo.modelo,
                nodo.propietario
            };
            modeloTabla.addRow(fila);
        }
    }

    private List<Nodo> obtenerTodosLosVehiculos() {
        List<Nodo> lista = new ArrayList<>();
        Nodo filaActual = matriz.getCabeceraFilas().abajo;
        while (filaActual != null) {
            Nodo nodoActual = filaActual.derecha;
            while (nodoActual != null) {
                lista.add(nodoActual);
                nodoActual = nodoActual.derecha;
            }
            filaActual = filaActual.abajo;
        }
        return lista;
    }
}