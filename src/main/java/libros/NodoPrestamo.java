/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libros;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class NodoPrestamo implements Comparable<NodoPrestamo> {
     private int id;
    private int idLibro;
    private int idPersona;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean activo;
    
    public NodoPrestamo(int idPrestamo, int idLibro, int idPersona) {
        this.id = idPrestamo;
        this.idLibro = idLibro;
        this.idPersona = idPersona;
        this.fechaPrestamo = new Date();
        this.activo = true;
    }
    

    public int getId() { 
        return id; 
    }
    
    public int getIdLibro() { 
        return idLibro; 
    }
    
    public int getIdPersona() { 
        return idPersona; 
    }
    
    public Date getFechaPrestamo() { 
        return fechaPrestamo; 
    }
    
    public Date getFechaDevolucion() { 
        return fechaDevolucion; 
    }
    
    public boolean getActivo(){
        return this.activo;
    }
    
    public void setFechaDevolucion(Date fecha) { 
        this.fechaDevolucion = fecha; 
    }
    
    public void setDevuelto(){
        this.activo = false;
    }
    
    @Override
    public int compareTo(NodoPrestamo otro) {
        return Integer.compare(this.id, otro.id);
    }
    
}
