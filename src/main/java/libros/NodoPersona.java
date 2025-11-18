/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libros;

/**
 *
 * @author Daniel
 */
public class NodoPersona implements Comparable<NodoPersona>{
    private int id;
    private String nombre;
    private int telefono;
    
    
    public NodoPersona(int id, String nombre, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }    
     
        
    
    // Getters y Setters
    public int getId() { 
        return id; 
    }
    public String getNombre() { return nombre; }
    public int getTelefono() { return telefono; }
    
    @Override
    public int compareTo(NodoPersona otro) {
        return Integer.compare(this.id, otro.id);
    }
}
