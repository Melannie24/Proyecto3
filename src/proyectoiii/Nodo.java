/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoiii;

/**
 *
 * @author Melannie, Christian, Carlos
 */
public class Nodo {
    public String placa;
    public String color;
    public String linea;
    public String modelo;
    public String propietario;

    public Nodo derecha, izquierda, arriba, abajo;

    // Constructor para nodo vehículo
    public Nodo(String placa, String color, String linea, String modelo, String propietario) {
        this.placa = placa;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
        this.propietario = propietario;
    }

    // Constructor para cabeceras (modelo o propietario)
    public Nodo(String valor, boolean esModelo) {
        if (esModelo) this.modelo = valor;
        else this.propietario = valor;
    }

    // Método para desconectar las referencias del nodo
    public void desconectar() {
        this.derecha = null;
        this.izquierda = null;
        this.arriba = null;
        this.abajo = null;
    }
}
