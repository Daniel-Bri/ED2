/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.excepciones.adt;

/**
 *
 * @author Daniel
 */
public class ExcepcionDatoNoExiste extends Exception{
    public ExcepcionDatoNoExiste(String message) {
        super(message);
    }

    public ExcepcionDatoNoExiste(){
        super("Dato no existe");
    }
}
