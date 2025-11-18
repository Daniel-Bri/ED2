/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.adt;

import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoNoExiste;
import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoYaExiste;
import java.util.List;

/**
 *
 * @author Daniel
 * @param <T>
 */
public interface IArbolBusqueda <T extends Comparable<T>>{
    void insertar(T dato) throws ExcepcionDatoYaExiste;
    void eliminar(T dato) throws ExcepcionDatoNoExiste;
    T buscar(T dato);
    boolean contiene(T dato);
    int size();
    int altura();
    void vaciar();
    boolean esArbolVacio();
    int nivel();
    List<T> recorridoEnInOrden();
    List<T> recorridoEnPreOrden();
    List<T> recorridoEnPostOrden();
    List<T> recorridoPorNiveles();  
    
    int cantidadDeNodosHojas();
    int cantidadDeNodosHojas(int nivel);
    boolean existeNodoCompletos(int nivel);
    public int cantidadDeDatos();
}
