/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoiii;

/**
 *
 * @author Melannie, Christian, Carlos
 */
import java.util.ArrayList;
import java.util.List;

public class MatrizOrtogonal02 {
    private Nodo cabeceraFilas;
    private Nodo cabeceraColumnas;

    public MatrizOrtogonal02() {
        cabeceraFilas = new Nodo("FILAS", true);
        cabeceraColumnas = new Nodo("COLUMNAS", false);
    }

    public Nodo getCabeceraFilas() {
        return cabeceraFilas;
    }

    public Nodo getCabeceraColumnas() {
        return cabeceraColumnas;
    }

    public boolean insertar(String placa, String color, String linea, String modelo, String propietario) {
        if (!validarPlacaGuatemala(placa)) {
            System.err.println("Error: Placa inválida según formato.");
            return false;
        }
        if (existePlaca(placa)) {
            System.err.println("Error: La placa ya existe.");
            return false;
        }

        Nodo nodoModelo = buscarCabeceraFila(modelo);
        if (nodoModelo == null) nodoModelo = insertarCabeceraFila(modelo);

        Nodo nodoPropietario = buscarCabeceraColumna(propietario);
        if (nodoPropietario == null) nodoPropietario = insertarCabeceraColumna(propietario);

        Nodo nuevo = new Nodo(placa, color, linea, modelo, propietario);

        // Insertar en fila (modelo)
        Nodo actualFila = nodoModelo;
        while (actualFila.derecha != null) actualFila = actualFila.derecha;
        actualFila.derecha = nuevo;
        nuevo.izquierda = actualFila;

        // Insertar en columna (propietario)
        Nodo actualCol = nodoPropietario;
        while (actualCol.abajo != null) actualCol = actualCol.abajo;
        actualCol.abajo = nuevo;
        nuevo.arriba = actualCol;
        return true;
    }

    private Nodo buscarCabeceraFila(String modelo) {
        Nodo actual = cabeceraFilas.abajo;
        while (actual != null) {
            if (modelo != null && actual.modelo != null && actual.modelo.equals(modelo)) return actual;
            actual = actual.abajo;
        }
        return null;
    }

    private Nodo insertarCabeceraFila(String modelo) {
        Nodo nuevaCabecera = new Nodo(modelo, true);
        Nodo actual = cabeceraFilas;
        while (actual.abajo != null) actual = actual.abajo;
        actual.abajo = nuevaCabecera;
        nuevaCabecera.arriba = actual;
        return nuevaCabecera;
    }

    private Nodo buscarCabeceraColumna(String propietario) {
        Nodo actual = cabeceraColumnas.derecha;
        while (actual != null) {
            if (propietario != null && actual.propietario != null && actual.propietario.equals(propietario)) return actual;
            actual = actual.derecha;
        }
        return null;
    }

    private Nodo insertarCabeceraColumna(String propietario) {
        Nodo nuevaCabecera = new Nodo(propietario, false);
        Nodo actual = cabeceraColumnas;
        while (actual.derecha != null) actual = actual.derecha;
        actual.derecha = nuevaCabecera;
        nuevaCabecera.izquierda = actual;
        return nuevaCabecera;
    }

    public boolean existePlaca(String placa) {
        Nodo filaActual = cabeceraFilas.abajo;
        while (filaActual != null) {
            Nodo nodoActual = filaActual.derecha;
            while (nodoActual != null) {
                if (nodoActual.placa != null && nodoActual.placa.equalsIgnoreCase(placa)) {
                    return true;
                }
                nodoActual = nodoActual.derecha;
            }
            filaActual = filaActual.abajo;
        }
        return false;
    }

    public List<Nodo> buscar(String criterio) {
        List<Nodo> resultados = new ArrayList<>();
        Nodo filaActual = cabeceraFilas.abajo;
        while (filaActual != null) {
            Nodo nodoActual = filaActual.derecha;
            while (nodoActual != null) {
                if (contieneCriterio(nodoActual, criterio)) {
                    resultados.add(nodoActual);
                }
                nodoActual = nodoActual.derecha;
            }
            filaActual = filaActual.abajo;
        }
        return resultados;
    }

    private boolean contieneCriterio(Nodo nodo, String criterio) {
        String criterioLower = (criterio != null) ? criterio.toLowerCase() : "";
        return (nodo.placa != null && nodo.placa.toLowerCase().contains(criterioLower)) ||
               (nodo.color != null && nodo.color.toLowerCase().contains(criterioLower)) ||
               (nodo.linea != null && nodo.linea.toLowerCase().contains(criterioLower)) ||
               (nodo.modelo != null && nodo.modelo.toLowerCase().contains(criterioLower)) ||
               (nodo.propietario != null && nodo.propietario.toLowerCase().contains(criterioLower));
    }

    public boolean eliminarPorPlaca(String placa) {
        boolean eliminado = false;
        Nodo filaActual = cabeceraFilas.abajo;
        while (filaActual != null) {
            Nodo nodoActual = filaActual.derecha;
            while (nodoActual != null) {
                Nodo siguiente = nodoActual.derecha;
                if (nodoActual.placa != null && nodoActual.placa.equalsIgnoreCase(placa)) {
                    String propietario = nodoActual.propietario;
                    String modelo = nodoActual.modelo;

                    if (nodoActual.izquierda != null) nodoActual.izquierda.derecha = nodoActual.derecha;
                    if (nodoActual.derecha != null) nodoActual.derecha.izquierda = nodoActual.izquierda;
                    if (nodoActual.arriba != null) nodoActual.arriba.abajo = nodoActual.abajo;
                    if (nodoActual.abajo != null) nodoActual.abajo.arriba = nodoActual.arriba;

                    nodoActual.desconectar(); // Desconectar referencias para facilitar la recolección

                    eliminado = true;

                    eliminarCabeceraColumnaSiVacia(propietario);
                    eliminarCabeceraFilaSiVacia(modelo);
                    break; // Ya se eliminó, no es necesario seguir en esta fila
                }
                nodoActual = siguiente;
            }
            if (eliminado) break; // Si se eliminó, no es necesario seguir buscando en otras filas
            filaActual = filaActual.abajo;
        }
        return eliminado;
    }

    private void eliminarCabeceraColumnaSiVacia(String propietario) {
        if (propietario == null) return;
        Nodo columnaActual = cabeceraColumnas.derecha;
        while (columnaActual != null) {
            if (propietario.equals(columnaActual.propietario)) {
                if (columnaActual.abajo == null) {
                    if (columnaActual.izquierda != null) columnaActual.izquierda.derecha = columnaActual.derecha;
                    if (columnaActual.derecha != null) columnaActual.derecha.izquierda = columnaActual.izquierda;
                    columnaActual.desconectar(); // Desconectar la cabecera también
                }
                break;
            }
            columnaActual = columnaActual.derecha;
        }
    }

    private void eliminarCabeceraFilaSiVacia(String modelo) {
        if (modelo == null) return;
        Nodo filaActual = cabeceraFilas.abajo;
        while (filaActual != null) {
            if (modelo.equals(filaActual.modelo)) {
                if (filaActual.derecha == null) {
                    if (filaActual.arriba != null) filaActual.arriba.abajo = filaActual.abajo;
                    if (filaActual.abajo != null) filaActual.abajo.arriba = filaActual.arriba;
                    filaActual.desconectar(); // Desconectar la cabecera también
                }
                break;
            }
            filaActual = filaActual.abajo;
        }
    }

    private boolean validarPlacaGuatemala(String placa) {
        if (placa == null) return false;
        placa = placa.toUpperCase();

        String letrasIniciales = "ACMOPTU";
        String letrasPermitidas = "BCDFGHJKLMNPQRSTVWXYZ";

        if (placa.length() != 7) return false;

        char primeraLetra = placa.charAt(0);
        if (letrasIniciales.indexOf(primeraLetra) == -1) return false;

        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(placa.charAt(i))) return false;
        }

        for (int i = 4; i <= 6; i++) {
            if (letrasPermitidas.indexOf(placa.charAt(i)) == -1) return false;
        }

        return true;
    }
}