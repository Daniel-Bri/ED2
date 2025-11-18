/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.adt;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class NodoMVias<T>{

    static boolean esNodoVacio;
    List<T> listaDeDatos;
    List<NodoMVias<T>> listaDeHijos;

    public NodoMVias(int orden) {
        this.listaDeDatos = new LinkedList<>();
        this.listaDeHijos= new LinkedList<>();
        for (int i = 0; i < orden-1; i++){
            listaDeDatos.add((T)NodoMVias.datoVacio());
            listaDeHijos.add(NodoMVias.nodoVacio());
        }
        listaDeHijos.add(NodoMVias.nodoVacio());
    }
   
    
    public static Object datoVacio(){
        return null;
    }
    
     public static NodoMVias nodoVacio(){
         return null;
     }
     
     public NodoMVias(int orden, T datoAInsertar) {
        this(orden);
        listaDeDatos.set(0,datoAInsertar);
    }
    
    public void setDato(int posicion, T dato){
        this.listaDeDatos.set(posicion, dato);
    } 
    
    public T getDato(int posicion){
        return listaDeDatos.get(posicion);
    }
    
    public boolean esDatoVacio(int posicion){
        return this.listaDeDatos.get(posicion)== NodoMVias.datoVacio();
    }
    
    public static boolean esNodoVacio(NodoMVias nodoActual){
        return NodoMVias.nodoVacio() == nodoActual;
    }
    
    public void setHijo(int posicion, NodoMVias<T> hijo){
        this.listaDeHijos.set(posicion, hijo);
    }
    
    public NodoMVias<T> getHijo(int posicion){
        return listaDeHijos.get(posicion);
    }
    
    public boolean esHijoVacio(int posicion){
        return listaDeHijos.get(posicion) == NodoMVias.nodoVacio();
    }
    
    public int nroDeDatosNoVacios(){
        int cantidad = 0;
        for(int i=0;i<this.listaDeDatos.size();i++){
            if(!this.esDatoVacio(i)){
                cantidad++;
            }
        }
        return cantidad;
    }
            
    public boolean estanDatosLlenos(){
        return this.nroDeDatosNoVacios() == this.listaDeDatos.size();
    }
    
    public boolean esHoja(){
        for(int i = 0; i < this.listaDeHijos.size();i++){
            if(!this.esHijoVacio(i)){
                return false;
            }
        }
        return true;
    }
}
