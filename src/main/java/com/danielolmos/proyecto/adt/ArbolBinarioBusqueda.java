/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.adt;

import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoNoExiste;
import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoYaExiste;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class ArbolBinarioBusqueda <T extends Comparable<T>> implements IArbolBusqueda<T> {

    protected NodoBinario<T> raiz;
    public NodoBinario<T> getRaiz(){
        return raiz;
    }
    @Override
    /*public void insertar(T datoAInsertar) throws ExcepcionDatoYaExiste {
        if (this.esArbolVacio()){
            raiz=new NodoBinario(datoAInsertar);
            return;
        }
        NodoBinario<T> nodoAnt = new NodoBinario();
        NodoBinario<T> nodoAux = this.raiz;
        do{
            T datoAux=nodoAux.getDato();
            nodoAnt=nodoAux;
            if (datoAInsertar.compareTo(datoAux)<0){
                nodoAux=nodoAux.getHijoIzquierdo();
            }else{
                if(datoAInsertar.compareTo(datoAux)>0){
                    nodoAux=nodoAux.getHijoDerecho();
                }else{
                    throw new ExcepcionDatoYaExiste();
                }
            }
        }while(!NodoBinario.esNodoVacio(nodoAux));
        
        NodoBinario<T> nuevoNodo = new NodoBinario(datoAInsertar);
        if(datoAInsertar.compareTo(nodoAnt.getDato())<0){
            nodoAnt.setHijoIzquierdo(nuevoNodo);
        }else{
            nodoAnt.setHijoDerecho(nuevoNodo);
        }
    }*/
    
    public void insertar(T datoAInsertar) throws ExcepcionDatoYaExiste {
        if(datoAInsertar == null){
            throw new IllegalArgumentException("No se pueden insertar valores nulos");
        }
        
        if(this.esArbolVacio()){
            this.raiz = new NodoBinario<T>(datoAInsertar);
            return ;
        }
        
        NodoBinario<T> nodoEnTurno = this.raiz;
        NodoBinario<T> nodoAnt = NodoBinario.nodoVacio();
        do{
            T datoDeNodo = nodoEnTurno.getDato();
            nodoAnt = nodoEnTurno;
            if(datoAInsertar.compareTo(datoDeNodo)<0){
                nodoEnTurno = nodoEnTurno.getHijoIzquierdo();
            }else{
                if(datoAInsertar.compareTo(datoDeNodo)>0){
                    nodoEnTurno =  nodoEnTurno.getHijoDerecho();
                }else{
                    throw new ExcepcionDatoYaExiste();
                }
            }
        }while(!NodoBinario.esNodoVacio(nodoEnTurno));
        
        T datoAnterior = nodoAnt.getDato();
        NodoBinario<T> nuevoNodo = new NodoBinario<>(datoAInsertar);
        if (datoAInsertar.compareTo(datoAnterior)<0){
            nodoAnt.setHijoIzquierdo(nuevoNodo);
        }else{
            nodoAnt.setHijoDerecho(nuevoNodo);
        }
    }
    
    
    
    
    @Override
    public void eliminar(T datoAEliminar) throws ExcepcionDatoNoExiste{
        if(datoAEliminar == null){
            throw new IllegalArgumentException();
        }
        this.raiz =  eliminar(this.raiz , datoAEliminar);
    }
    
    private NodoBinario<T> eliminar(NodoBinario<T> nodoEnTurno, T datoAEliminar) throws ExcepcionDatoNoExiste{
        if(NodoBinario.esNodoVacio(nodoEnTurno)){
            throw new ExcepcionDatoNoExiste();
        }
        
        if(datoAEliminar.compareTo(nodoEnTurno.getDato())<0){
            NodoBinario<T> supuestoNuevoHijo = eliminar(nodoEnTurno.getHijoIzquierdo(), datoAEliminar);
            nodoEnTurno.setHijoIzquierdo(supuestoNuevoHijo);
            return nodoEnTurno;
        }
        
        
        if(datoAEliminar.compareTo(nodoEnTurno.getDato())>0){
            NodoBinario<T> supuestoNuevoHijo = eliminar(nodoEnTurno.getHijoDerecho(), datoAEliminar);
            nodoEnTurno.setHijoDerecho(supuestoNuevoHijo);
            return nodoEnTurno;
        }
        
        //LO ENCONTRAMOS
        //Caso 1
        
        if(nodoEnTurno.esHoja()){
            nodoEnTurno = NodoBinario.nodoVacio();
            return NodoBinario.nodoVacio();
        }
        
        //caso 2
        if(!nodoEnTurno.esVacioHijoIzquierdo() && nodoEnTurno.esVacioHijoDerecho()){
            NodoBinario<T> nodoARetornar = nodoEnTurno.getHijoIzquierdo();
            nodoEnTurno.setHijoIzquierdo(NodoBinario.nodoVacio());
            return nodoARetornar;
        }
        
        if(!nodoEnTurno.esVacioHijoDerecho() && nodoEnTurno.esVacioHijoIzquierdo()){
            NodoBinario<T> nodoARetornar = nodoEnTurno.getHijoDerecho();
            nodoEnTurno.setHijoDerecho(NodoBinario.nodoVacio());
            return nodoARetornar;
        }
        
        //caso 3
        T datoSucesorInOrden = this.buscarSucesorInOrden(nodoEnTurno.getHijoDerecho());
        nodoEnTurno.setDato(datoSucesorInOrden);
        NodoBinario<T> supuestoNuevoHijo = eliminar(nodoEnTurno.getHijoDerecho(), datoSucesorInOrden);
        nodoEnTurno.setHijoDerecho(supuestoNuevoHijo);
        return nodoEnTurno;
    }
    
    protected T buscarSucesorInOrden(NodoBinario<T> nodoEnTurno){
        
        while(!nodoEnTurno.esVacioHijoIzquierdo()){
            nodoEnTurno = nodoEnTurno.getHijoIzquierdo();
        }
        return nodoEnTurno.getDato();
    }
    
    
    
    /*
    @Override
    public void eliminar(T datoAEliminar) throws ExcepcionDatoNoExiste {
        if (datoAEliminar == null){
            throw new IllegalArgumentException();
        }
        
        this.raiz = eliminar(this.raiz, datoAEliminar);
    }
    
    private NodoBinario<T> eliminar (NodoBinario<T> nodoEnTurno, T datoAEliminar) throws ExcepcionDatoNoExiste{
        if(NodoBinario.esNodoVacio(nodoEnTurno)){
            throw new ExcepcionDatoNoExiste();
        }
        
        if(datoAEliminar.compareTo(nodoEnTurno.getDato())<0){
            NodoBinario<T> supuestoHijoIzquierdo = eliminar(nodoEnTurno.getHijoIzquierdo(), datoAEliminar);
            nodoEnTurno.setHijoIzquierdo(supuestoHijoIzquierdo);
            return nodoEnTurno;
        }
        
        if(datoAEliminar.compareTo(nodoEnTurno.getDato())>0){
            NodoBinario<T> supuestoHijoDerecho = eliminar(nodoEnTurno.getHijoDerecho(), datoAEliminar);
            nodoEnTurno.setHijoDerecho(supuestoHijoDerecho);
            return nodoEnTurno;
        }
        
        //ES IGUAL
        
        //CASO1: es hoja
        if(nodoEnTurno.esHoja()){
            nodoEnTurno = NodoBinario.nodoVacio();
            return nodoEnTurno;
        }
        
        //CASO2: solo tiene un hijo
        if(!nodoEnTurno.esVacioHijoDerecho() && nodoEnTurno.esVacioHijoIzquierdo()){
            NodoBinario<T> nodoARetornar = nodoEnTurno.getHijoDerecho();
            nodoEnTurno.setHijoDerecho(NodoBinario.nodoVacio());
            return nodoARetornar;
        }
        if(!nodoEnTurno.esVacioHijoIzquierdo() && nodoEnTurno.esVacioHijoDerecho()){
            NodoBinario<T> nodoARetornar = nodoEnTurno.getHijoIzquierdo();
            nodoEnTurno.setHijoIzquierdo(NodoBinario.nodoVacio());
            return nodoARetornar;
        }
        
        //CASO3: tiene ambos hijos
        T datoDeReemplazo = buscarSucesorInOrden(nodoEnTurno.getHijoDerecho());
        NodoBinario<T> supuestoNuevoHijo = eliminar(nodoEnTurno.getHijoDerecho(), datoDeReemplazo);
        nodoEnTurno.setHijoDerecho(supuestoNuevoHijo);
        nodoEnTurno.setDato(datoDeReemplazo);
        return nodoEnTurno;  
    }
    
    private T buscarSucesorInOrden(NodoBinario<T> nodoEnTurno){
        while(!nodoEnTurno.esVacioHijoIzquierdo()){
            nodoEnTurno = nodoEnTurno.getHijoIzquierdo();
        }
        return nodoEnTurno.getDato();
    }*/
    
    /*public void eliminar(T dato) throws ExcepcionDatoNoExiste {
        if(dato == null){
            throw new IllegalArgumentException("No se pueden eliminar datos nulos");
        }
        this.raiz = eliminar(this.raiz, dato);
    }
    
    private NodoBinario<T> eliminar(NodoBinario<T> nodoEnTurno, T datoAEliminar)throws ExcepcionDatoNoExiste {
        
        if(NodoBinario.esNodoVacio(nodoEnTurno)){
            throw new ExcepcionDatoNoExiste();
        }
        
        T datoDeNodo = nodoEnTurno.getDato();
        if(datoAEliminar.compareTo(datoDeNodo)>0){
            NodoBinario<T> supuestoNuevoHijoDerecho = this.eliminar(nodoEnTurno.getHijoDerecho(), datoAEliminar);
            nodoEnTurno.setHijoDerecho(supuestoNuevoHijoDerecho);
            return nodoEnTurno;
        }
        
        if(datoAEliminar.compareTo(datoDeNodo)<0){
            NodoBinario<T> supuestoNuevoHijoIzquierdo = this.eliminar(nodoEnTurno.getHijoIzquierdo(), datoAEliminar);
            nodoEnTurno.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return nodoEnTurno;
        }
        
        //ES IGUAL
        //CASO1 es hoja
        if(nodoEnTurno.esHoja()){
            nodoEnTurno = NodoBinario.nodoVacio();
            return nodoEnTurno;
        }
        
        //CASO 2
        //no es hoja
        //solo tiene un hijo
        if(nodoEnTurno.esVacioHijoDerecho() && !nodoEnTurno.esVacioHijoIzquierdo()){ //tiene solo hijo izquierdo
            NodoBinario<T> nodoARetornar = nodoEnTurno.getHijoIzquierdo();
            nodoEnTurno.setHijoIzquierdo(NodoBinario.nodoVacio());
            return nodoARetornar;
        }
        
        if(!nodoEnTurno.esVacioHijoDerecho() && nodoEnTurno.esVacioHijoIzquierdo()){ //tiene solo hijo derecho
            NodoBinario<T> nodoARetornar = nodoEnTurno.getHijoDerecho();
            nodoEnTurno.setHijoDerecho(NodoBinario.nodoVacio());
            return nodoARetornar;
        }
        
        //CASO 3 tiene ambos hijos  
        T datoDeReemplazo = buscarSucesorInOrden(nodoEnTurno.getHijoDerecho());
        NodoBinario<T> supuestoNuevoHijoDerecho = this.eliminar(nodoEnTurno.getHijoDerecho(), datoDeReemplazo);
        nodoEnTurno.setHijoDerecho(supuestoNuevoHijoDerecho);
        nodoEnTurno.setDato(datoDeReemplazo);
        return nodoEnTurno;
    }
    
    private T buscarSucesorInOrden(NodoBinario<T> nodoEnTurno) {
        while(!nodoEnTurno.esVacioHijoIzquierdo()){
            nodoEnTurno = nodoEnTurno.getHijoIzquierdo();
        }
        return nodoEnTurno.getDato();
    }*/
    

    @Override
    public T buscar(T dato) {
        if (this.esArbolVacio()){
            return null;
        }else{
            NodoBinario<T> nodoAux = raiz;
            do{
                T datoEnTurno = nodoAux.getDato();
                if(dato.compareTo(datoEnTurno)<0){
                    nodoAux=nodoAux.getHijoIzquierdo();
                }else{
                    if(dato.compareTo(datoEnTurno)>0){
                        nodoAux=nodoAux.getHijoDerecho();
                    }else{
                        return (nodoAux.getDato());
                    }
                }
            }while(!NodoBinario.esNodoVacio(nodoAux));
        }
        return null;
    }

    public int altura(){
        return this.altura(this.raiz);
    }

    protected int altura(NodoBinario<T> nodoAux){
        if (NodoBinario.esNodoVacio(nodoAux)){
            return 0;
        }

        int alturaXIzq = this.altura(nodoAux.getHijoIzquierdo());
        int alturaXDer = this.altura(nodoAux.getHijoDerecho());
        return alturaXIzq > alturaXDer? alturaXIzq+1 : alturaXDer+1;
    }
    @Override
    public boolean contiene(T dato) {
        if (!this.esArbolVacio()){
            NodoBinario<T> nodoAux = this.raiz;
            do{
                T datoEnTurno = nodoAux.getDato();
                if (dato.compareTo(datoEnTurno)<0){
                    nodoAux = nodoAux.getHijoIzquierdo();
                }else{
                    if (dato.compareTo(datoEnTurno)>0){
                        nodoAux = nodoAux.getHijoDerecho();
                    }else{
                        return true;
                    }
                }
            }while(NodoBinario.esNodoVacio(nodoAux));
        }
        return false;
    }

    @Override
    public int size() {
        int contDeNodos = 0;
        if (!this.esArbolVacio()){
            Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            do{
                NodoBinario<T> nodoAux = colaDeNodos.poll();
                contDeNodos++;
                if (!nodoAux.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoAux.getHijoIzquierdo());
                }
                if (!nodoAux.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoAux.getHijoDerecho());
                }
            }while(!colaDeNodos.isEmpty());
        }
        return contDeNodos;
    }


    @Override
    public void vaciar() {
        this.raiz=NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return (NodoBinario.esNodoVacio(raiz));
    }
    
    public List<T> recorridoEnInOrdenIt() {
        List<T> recorrido = new LinkedList<>();
        if (!this.esArbolVacio()){
            NodoBinario<T> nodoAux = this.raiz;
            Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
            insertarInOrden(nodoAux, pilaDeNodos);
            do{
                nodoAux = pilaDeNodos.pop();
                recorrido.add(nodoAux.getDato());
                nodoAux = nodoAux.getHijoDerecho();
                insertarInOrden(nodoAux, pilaDeNodos);
            }while(!pilaDeNodos.isEmpty());
        }
        return recorrido;
    }

    private void insertarInOrden(NodoBinario<T> nodoAux, Stack<NodoBinario<T>> pilaDeNodos) {
        while(!NodoBinario.esNodoVacio(nodoAux)){
            pilaDeNodos.push(nodoAux);
            nodoAux = nodoAux.getHijoIzquierdo();
        }
    }

    public List<T> recorridoEnPreOrdenIt() {
        List<T> recorrido = new LinkedList<>();
        if (!this.esArbolVacio()){
            Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
            NodoBinario<T> nodoAux = this.raiz;
            pilaDeNodos.push(nodoAux);
            do{
                nodoAux = pilaDeNodos.pop();
                T datoActual = nodoAux.getDato();
                recorrido.add(datoActual);
                if(!NodoBinario.esNodoVacio(nodoAux.getHijoDerecho())){
                    pilaDeNodos.push(nodoAux.getHijoDerecho());
                }
                
                if(!NodoBinario.esNodoVacio(nodoAux.getHijoIzquierdo())){
                    pilaDeNodos.push(nodoAux.getHijoIzquierdo());
                }
           
                }while(!pilaDeNodos.isEmpty());
        }
        return recorrido;
    }

    public List<T> recorridoEnPostOrdenIt() {
        List<T> recorrido = new LinkedList<>();
        if (!this.esArbolVacio()){
            Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
            NodoBinario<T> nodoAux = this.raiz;
            
            do{
                
            }while(!pilaDeNodos.isEmpty());
        }
        return recorrido;
    }

    @Override
    public List<T> recorridoEnInOrden() {
        List<T> recorrido = new LinkedList<>();
        recorridoEnInOrden(raiz, recorrido);
        return recorrido;
    }
    
    private void recorridoEnInOrden(NodoBinario<T> nodoEnTurno, List<T> recorrido) {
        if (NodoBinario.esNodoVacio(nodoEnTurno)){
            return ;
        }
        recorridoEnInOrden(nodoEnTurno.getHijoIzquierdo(),recorrido);
        recorrido.add(nodoEnTurno.getDato());
        recorridoEnInOrden(nodoEnTurno.getHijoDerecho(),recorrido);
    }

    @Override
    public List<T> recorridoEnPreOrden() {
        List<T> recorrido = new LinkedList<>();
        recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;
    }
    
    private void recorridoEnPreOrden(NodoBinario<T> nodoEnTurno, List<T> recorrido) {
        if (NodoBinario.esNodoVacio(nodoEnTurno)){
            return ;
        }
        
        recorrido.add(nodoEnTurno.getDato());
        recorridoEnPreOrden(nodoEnTurno.getHijoIzquierdo(),recorrido);
        recorridoEnPreOrden(nodoEnTurno.getHijoDerecho(),recorrido);
    }


    @Override
    public List<T> recorridoEnPostOrden() {
        List<T> recorrido = new LinkedList<>();
        recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;
    }
    
    private void recorridoEnPostOrden(NodoBinario<T> nodoEnTurno, List<T> recorrido) {
        if(NodoBinario.esNodoVacio(nodoEnTurno)){
           return ; 
        }
        recorridoEnPostOrden(nodoEnTurno.getHijoIzquierdo(), recorrido);
        recorridoEnPostOrden(nodoEnTurno.getHijoDerecho(),recorrido);
        recorrido.add(nodoEnTurno.getDato());
    }

    @Override
    public List<T> recorridoPorNiveles() {
        List<T> recorrido = new LinkedList<>();
        if (!this.esArbolVacio()){
            Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(raiz);
            do{
                NodoBinario<T> nodoAux = colaDeNodos.poll();
                recorrido.add(nodoAux.getDato());
                if (!nodoAux.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoAux.getHijoIzquierdo());
                }
                if (!nodoAux.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoAux.getHijoDerecho());
                }
            }while(!colaDeNodos.isEmpty());
        }
        return recorrido;
    }   
    
    public int sizeIt() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int alturaIt() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    //EJERCICIOS
    
    //1) Funcion que devuelva la cantidad de  nodos hojas que hay en un arbol binario iterativo
    
    public int cantidadDeNodosHojasIt(){
        int cantidad = 0;
        if(!this.esArbolVacio()){
             
            Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
            NodoBinario<T> nodoEnTurno = this.raiz;
            this.insertarInOrden(nodoEnTurno, pilaDeNodos);
            do{
                nodoEnTurno = pilaDeNodos.pop();
                if(nodoEnTurno.esHoja()){
                    cantidad++;
                }
                nodoEnTurno = nodoEnTurno.getHijoDerecho();
                this.insertarInOrden(nodoEnTurno, pilaDeNodos);
            }while(!pilaDeNodos.isEmpty());
        }
        return cantidad;
    }

    
    //2) Funcion que devuelva la cantidad de  nodos hojas que hay en un arbol binario recursivo
    public int cantidadDeNodosHojas(){
        return cantidadDeNodosHojas(this.raiz);
    }
    
    public int cantidadDeNodosHojas(NodoBinario<T> nodoEnTurno){
        if(NodoBinario.esNodoVacio(nodoEnTurno)){
            return 0;
        }
        
        int cantidadIzq = cantidadDeNodosHojas(nodoEnTurno.getHijoIzquierdo());
        int cantidadDer = cantidadDeNodosHojas(nodoEnTurno.getHijoDerecho());
        
        if (nodoEnTurno.esHoja()){
            return cantidadIzq + cantidadDer + 1;
        }
        
        return cantidadIzq +cantidadDer;
    }

    @Override
    public int cantidadDeNodosHojas(int nivel) {
        return 0;
    }

    @Override
    public boolean existeNodoCompletos(int nivel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantidadDeDatos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

