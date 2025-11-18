/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.excepciones.adt;

/**
 *
 * @author Daniel
 */
public class ExcepcionDatoYaExiste extends Exception{
    public ExcepcionDatoYaExiste(String message) {
        super(message);
    }

    public ExcepcionDatoYaExiste() {
        super("Dato ya existe");
    }
}