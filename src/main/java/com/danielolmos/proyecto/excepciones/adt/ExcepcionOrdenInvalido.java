/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.excepciones.adt;

/**
 *
 * @author Daniel
 */
public class ExcepcionOrdenInvalido extends Exception{
   public ExcepcionOrdenInvalido(String message) {
        super(message);
    }

    public ExcepcionOrdenInvalido() {
        super("Orden de árbol m-vias invalido");
    } 
}
