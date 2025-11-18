/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.adt;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class NodoBinario<T> {
    private T dato;
    private NodoBinario<T> hijoIzquierdo;
    private NodoBinario<T> hijoDerecho;

    public NodoBinario() {
    }

    public NodoBinario(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public NodoBinario<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public NodoBinario<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setHijoIzquierdo(NodoBinario<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDerecho(NodoBinario<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
    public static <T> NodoBinario<T> nodoVacio(){
        return null;
    }
    
    public static boolean esNodoVacio(NodoBinario nodoAux){
        return (nodoAux == nodoVacio());
    }
    
    public boolean esVacioHijoIzquierdo(){
        return (NodoBinario.esNodoVacio(this.hijoIzquierdo));
    }
    
    public boolean esVacioHijoDerecho(){
        return (NodoBinario.esNodoVacio(this.hijoDerecho));
    }
    
    public boolean esHoja(){
        return NodoBinario.esNodoVacio(this.hijoIzquierdo)&&NodoBinario.esNodoVacio(this.hijoDerecho);
    }
}
