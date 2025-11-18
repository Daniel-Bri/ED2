/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.adt;

import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoNoExiste;
import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoYaExiste;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class ArbolesAVL <T extends Comparable<T>> extends ArbolBinarioBusqueda<T>{
    private static final byte RANGO_LIMITE = 1;

    @Override
    public void eliminar(T datoAEliminar) throws ExcepcionDatoNoExiste {
        if(datoAEliminar == null){
            throw new IllegalArgumentException("No se pueden eliminar valores nulos");
        }
        this.raiz = eliminar(this.raiz, datoAEliminar);
    }
    
    public NodoBinario<T> eliminar(NodoBinario<T> nodoEnTurno, T datoAEliminar) throws ExcepcionDatoNoExiste{
        if(NodoBinario.esNodoVacio(nodoEnTurno)){
            throw new ExcepcionDatoNoExiste();
        }
        
        if(datoAEliminar.compareTo(nodoEnTurno.getDato())<0){
            NodoBinario<T> supuestoNuevoHijo = eliminar(nodoEnTurno.getHijoIzquierdo(), datoAEliminar);
            nodoEnTurno.setHijoIzquierdo(supuestoNuevoHijo);
            return balancear(nodoEnTurno);
        }
        
        if(datoAEliminar.compareTo(nodoEnTurno.getDato())>0){
            NodoBinario<T> supuestoNuevoHijo = eliminar(nodoEnTurno.getHijoDerecho(), datoAEliminar);
            nodoEnTurno.setHijoDerecho(supuestoNuevoHijo);
            return balancear(nodoEnTurno);
        }
        
        //Lo encontro
        //caso 1
        if(nodoEnTurno.esHoja()){
            return NodoBinario.nodoVacio();
        }
        
        //caso2
        if(!nodoEnTurno.esVacioHijoIzquierdo() && nodoEnTurno.esVacioHijoDerecho()){
            NodoBinario<T> nodoARetornar = nodoEnTurno.getHijoIzquierdo();
            nodoEnTurno.setHijoIzquierdo(NodoBinario.nodoVacio());
            return nodoARetornar;
        }
        
        if(nodoEnTurno.esVacioHijoIzquierdo() && !nodoEnTurno.esVacioHijoDerecho()){
            NodoBinario<T> nodoARetornar = nodoEnTurno.getHijoDerecho();
            nodoEnTurno.setHijoDerecho(NodoBinario.nodoVacio());
            return nodoARetornar;
        }
        
        //caso3
        T datoSucesorInOrden = super.buscarSucesorInOrden(nodoEnTurno.getHijoDerecho());
        nodoEnTurno.setDato(datoSucesorInOrden);
        NodoBinario<T> supuestoNuevoHijo = eliminar(nodoEnTurno.getHijoDerecho(), datoSucesorInOrden);
        nodoEnTurno.setHijoDerecho(supuestoNuevoHijo);
        return balancear(nodoEnTurno);
    }
    
    

    @Override
    public void insertar(T datoAInsertar) throws ExcepcionDatoYaExiste {
        if(datoAInsertar == null){
            throw new IllegalArgumentException("No se pueden insertar valores nulos");
        }
        super.raiz = insertar(super.raiz, datoAInsertar);
    }
    
    private NodoBinario<T> insertar(NodoBinario<T> nodoEnTurno, T datoAInsertar) throws ExcepcionDatoYaExiste {
        if(NodoBinario.esNodoVacio(nodoEnTurno)){
            return new NodoBinario<>(datoAInsertar);
        }
        
        if(datoAInsertar.compareTo(nodoEnTurno.getDato())<0){
            NodoBinario<T> supuestoNuevoHijo = insertar(nodoEnTurno.getHijoIzquierdo(),datoAInsertar);
            nodoEnTurno.setHijoIzquierdo(supuestoNuevoHijo);
            return balancear(nodoEnTurno);
        }
        
        if(datoAInsertar.compareTo(nodoEnTurno.getDato())>0){
            NodoBinario<T> supuestoNuevoHijo = insertar(nodoEnTurno.getHijoDerecho(), datoAInsertar);
            nodoEnTurno.setHijoDerecho(supuestoNuevoHijo);
            return balancear(nodoEnTurno);
        } 
        
        throw new ExcepcionDatoYaExiste();
    }
    
    
    private NodoBinario<T> balancear(NodoBinario<T> nodoEnTurno){
        int altXIzq =  super.altura(nodoEnTurno.getHijoIzquierdo());
        int altXDer = super.altura(nodoEnTurno.getHijoDerecho());
        int dif = altXIzq - altXDer;
        if(dif > RANGO_LIMITE){ //altura x izq mayor
            NodoBinario<T> hijoIzquierdo = nodoEnTurno.getHijoIzquierdo();
            altXIzq = super.altura(hijoIzquierdo.getHijoIzquierdo());
            altXDer = super.altura(hijoIzquierdo.getHijoDerecho());
            if(altXDer>altXIzq){
                return this.rotacionDobleALaDerecha(nodoEnTurno);
            }
            return this.rotacionSimpleALaDerecha(nodoEnTurno);
        }
        
        if(dif < -RANGO_LIMITE){
            NodoBinario<T> hijoDerecho = nodoEnTurno.getHijoDerecho();
            altXIzq = super.altura(hijoDerecho.getHijoIzquierdo());
            altXDer = super.altura(hijoDerecho.getHijoDerecho());
            if(altXIzq > altXDer){
                return this.rotacionDobleALaIzquierda(nodoEnTurno);
            }
            return this.rotacionSimpleALaIzquierda(nodoEnTurno);
        }
        return nodoEnTurno;
    }
    
    
    
    
    
    
    
    
    
    
    /*private NodoBinario<T> balancear(NodoBinario<T> nodoEnTurno){
        int altXIzq = super.altura(nodoEnTurno.getHijoIzquierdo());
        int altXDer = super.altura(nodoEnTurno.getHijoDerecho());
        int dif = altXIzq - altXDer;
        if(dif > RANGO_LIMITE){//Lado mas largo el izquierdo
            NodoBinario<T> hijoIzqDeNodoEnTurno = nodoEnTurno.getHijoIzquierdo();
            altXIzq = super.altura(hijoIzqDeNodoEnTurno.getHijoIzquierdo());
            altXDer = super.altura(hijoIzqDeNodoEnTurno.getHijoDerecho());
            if(altXDer > altXIzq){
                return rotacionDobleALaDerecha(nodoEnTurno);
            }
            
            return rotacionSimpleALaDerecha(nodoEnTurno);
        }
        
        if(dif < -RANGO_LIMITE){//Lado mas largo el derecho
            NodoBinario<T> hijoDerDeNodoEnTurno = nodoEnTurno.getHijoDerecho();
            altXIzq = super.altura(hijoDerDeNodoEnTurno.getHijoIzquierdo());
            altXDer = super.altura(hijoDerDeNodoEnTurno.getHijoDerecho());
            if(altXIzq > altXDer){
                return rotacionDobleALaIzquierda(nodoEnTurno);
            }
            return rotacionSimpleALaIzquierda(nodoEnTurno);
        }
        
        return nodoEnTurno;       
    }*/
    
    
    private NodoBinario<T> rotacionSimpleALaIzquierda(NodoBinario<T> nodoEnTurno){
        NodoBinario<T> nodoQueRota = nodoEnTurno.getHijoDerecho();
        nodoEnTurno.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoEnTurno);
        return nodoQueRota;
    }
    
    private NodoBinario<T> rotacionSimpleALaDerecha(NodoBinario<T> nodoEnTurno){
        NodoBinario<T> nodoQueRota = nodoEnTurno.getHijoIzquierdo();
        nodoEnTurno.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoEnTurno);
        return nodoQueRota;    
    }
    
    private NodoBinario<T> rotacionDobleALaIzquierda(NodoBinario<T> nodoEnTurno){
        NodoBinario<T> nodoQueRota = this.rotacionSimpleALaDerecha(nodoEnTurno.getHijoDerecho());
        nodoEnTurno.setHijoDerecho(nodoQueRota);
        return rotacionSimpleALaIzquierda(nodoEnTurno);
    }
    
    private NodoBinario<T> rotacionDobleALaDerecha(NodoBinario<T> nodoEnTurno){
        NodoBinario<T> nodoQueRota = this.rotacionSimpleALaIzquierda(nodoEnTurno.getHijoIzquierdo());
        nodoEnTurno.setHijoIzquierdo(nodoQueRota);
        return this.rotacionSimpleALaDerecha(nodoEnTurno);
    }
    /*public void insertar(T dato) throws ExcepcionDatoYaExiste {
        super.raiz = insertar(super.raiz, dato);
    }

    public NodoBinario<T> insertar (NodoBinario<T> nodoEnTurno, T dato) throws ExcepcionDatoYaExiste{
        if (NodoBinario.esNodoVacio(nodoEnTurno)){
            return new NodoBinario<>(dato);
        }

        T datoEnTurno = nodoEnTurno.getDato();
        if (dato.compareTo(datoEnTurno)< 0){
            NodoBinario<T> supuestoHijoIzq = insertar(nodoEnTurno.getHijoIzquierdo(),dato);
            nodoEnTurno.setHijoIzquierdo(supuestoHijoIzq);
            return balancear(nodoEnTurno);
        }

        if (dato.compareTo(datoEnTurno)>0){
            NodoBinario<T> supuestoHijoDer = insertar(nodoEnTurno.getHijoDerecho(),dato);
            nodoEnTurno.setHijoDerecho(supuestoHijoDer);
            return balancear(nodoEnTurno);
        }
        throw new ExcepcionDatoYaExiste();
    }*/

    /*public NodoBinario<T> balancear(NodoBinario<T> nodoAux){
        int altPorIzq = super.altura(nodoAux.getHijoIzquierdo());
        int altPorDer = super.altura(nodoAux.getHijoDerecho());
        int dif = altPorIzq - altPorDer;
        if (dif > RANGO_LIMITE) {
            //El lado izquierdo es mas largo
            //ROTACION SIMPLE A LA DER
            NodoBinario<T> hijoIzqDeNodoEnTurno = nodoAux.getHijoIzquierdo();
            altPorIzq = super.altura(hijoIzqDeNodoEnTurno.getHijoIzquierdo());
            altPorDer = super.altura(hijoIzqDeNodoEnTurno.getHijoDerecho());
            if (altPorDer > altPorIzq) { //Rotacion Simple a la derecha
                return rotacionDobleADerecha(nodoAux);
            }
            return rotacionSimpleADerecha(nodoAux);
        } else if (dif < -RANGO_LIMITE) {
            NodoBinario<T> hijoDerDeNodoEnTurno = nodoAux.getHijoDerecho();
            altPorDer = super.altura(hijoDerDeNodoEnTurno.getHijoDerecho());
            altPorIzq = super.altura(hijoDerDeNodoEnTurno.getHijoIzquierdo());
            if (altPorIzq>altPorDer){
                return rotacionDobleAIzq(nodoAux);
            }
            return rotacionSimpleAIzq(nodoAux);
        }
        return nodoAux;
    }

    private NodoBinario<T> rotacionSimpleAIzq(NodoBinario<T> nodoAux) {
        NodoBinario<T> nodoQueRota = nodoAux.getHijoDerecho();
        nodoAux.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoAux);
        return nodoQueRota;
    }

    private NodoBinario<T> rotacionDobleADerecha(NodoBinario<T> nodoAux) {
        NodoBinario<T> nodoQueRota = this.rotacionSimpleAIzq(nodoAux.getHijoIzquierdo());
        nodoAux.setHijoIzquierdo(nodoQueRota);
        return (this.rotacionSimpleADerecha(nodoAux));
    }

    private NodoBinario<T> rotacionSimpleADerecha(NodoBinario<T> nodoAux) {
        NodoBinario<T> nodoQueRota = nodoAux.getHijoIzquierdo();
        nodoAux.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoAux);
        return nodoQueRota;
    }

    private NodoBinario<T> rotacionDobleAIzq(NodoBinario<T> nodoAux) {
       NodoBinario<T> nodoQueRota = this.rotacionSimpleADerecha(nodoAux.getHijoDerecho());
       nodoAux.setHijoDerecho(nodoQueRota);
       return (this.rotacionSimpleAIzq(nodoAux));
    }*/


}