/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libros;

import java.util.Comparator;

/**
 *
 * @author Daniel
 */


public class NodoLibro implements Comparable<NodoLibro>{
    private int id;
    private String titulo;
    private String autor;
    private int año;
    private boolean disponible;
    
    
    public NodoLibro(int id, String titulo, String autor, int año) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.disponible = true;
    }
    
    // Getters y Setters
    public int getId() { 
        return id; 
    }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAño() { return año; }
    public boolean esDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    @Override
    public int compareTo(NodoLibro otro) {
        return Integer.compare(this.id, otro.id);
    }
}
