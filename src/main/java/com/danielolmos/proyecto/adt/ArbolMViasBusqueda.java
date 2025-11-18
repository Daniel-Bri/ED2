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

/**
 *
 * @author Daniel  
 * @param <T>
 */
public class ArbolMViasBusqueda <T extends Comparable<T>> implements IArbolBusqueda<T> {
    protected NodoMVias<T> raiz;
    public int orden;
    protected static final int ORDEN_MINIMO = 3;
    protected static final int POSICION_INVALIDA = -1;

    public ArbolMViasBusqueda(int orden) {
        this.orden = orden;
    }
    
    public ArbolMViasBusqueda(int orden, T dato) throws ExcepcionDatoYaExiste {
        this.orden = orden;
        this.insertar((T)dato);
    }
    
    public NodoMVias getRaiz(){
        return this.raiz;
    }
    
    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }
    
    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }
    
    /*@Override
    public void insertar(T datoAInsertar) throws ExcepcionDatoYaExiste {
        if(datoAInsertar == null){
            throw new IllegalArgumentException("Error: dato a insertar invalido");
        }
        
        if (this.esArbolVacio()){
            this.raiz = new NodoMVias<>(this.orden,datoAInsertar);
            return;
        }
        NodoMVias<T> nodoEnTurno = this.raiz;
        do{
            int posicionDeDato = buscarPosicionDeDatoEnNodo(nodoEnTurno,datoAInsertar);
            if (posicionDeDato!=POSICION_INVALIDA){
                throw new ExcepcionDatoYaExiste();
            }else{
                if(nodoEnTurno.esHoja()){
                    if(nodoEnTurno.estanDatosLlenos()){
                        int posicionPorDondeBajar = buscarPosicionPorDondeBajar(nodoEnTurno, datoAInsertar);
                        NodoMVias<T> nuevoNodo = new NodoMVias<>(orden, datoAInsertar);
                        nodoEnTurno.setHijo(posicionPorDondeBajar, nuevoNodo);
                    }else{
                        insertarDatoOrdenado(nodoEnTurno, datoAInsertar);
                    }
                    nodoEnTurno = NodoMVias.nodoVacio();
                }else{
                    int posicionPorDondeBajar = buscarPosicionPorDondeBajar(nodoEnTurno, datoAInsertar);
                    if(nodoEnTurno.esHijoVacio(posicionPorDondeBajar)){
                        NodoMVias<T> nuevoNodo = new NodoMVias<>(this.orden,datoAInsertar);
                        nodoEnTurno.setHijo(posicionPorDondeBajar, nuevoNodo);
                        nodoEnTurno = NodoMVias.nodoVacio();
                    }else{
                        nodoEnTurno = nodoEnTurno.getHijo(posicionPorDondeBajar);
                    }
                }
                
            }
        }while(!NodoMVias.esNodoVacio(nodoEnTurno));    
    }
    
    
    protected int buscarPosicionDeDatoEnNodo(NodoMVias<T> nodoEnTurno, T datoABuscar){
        for(int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++){
            T datoDeNodo = nodoEnTurno.getDato(i);
            if(datoABuscar.compareTo(datoDeNodo)==0){
                return i;
            }
        }
        return POSICION_INVALIDA;
    }
    
    protected int buscarPosicionPorDondeBajar(NodoMVias<T> nodoEnTurno, T datoABuscar){
        for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++){
            T datoDeNodo = nodoEnTurno.getDato(i);
            if(datoABuscar.compareTo(datoDeNodo)<0){
                return i;
            }
        }
        return nodoEnTurno.nroDeDatosNoVacios();
    }
    
    protected void insertarDatoOrdenado(NodoMVias<T> nodoEnTurno, T datoAInsertar){
        for(int i = nodoEnTurno.nroDeDatosNoVacios()-1; i>=0; i--){
            T datoDeNodo = nodoEnTurno.getDato(i);
            if(datoAInsertar.compareTo(datoDeNodo)<0){
                nodoEnTurno.setDato(i+1, datoDeNodo);
                if(i==0){
                    nodoEnTurno.setDato(i, datoAInsertar);
                    return;
                }
            }else{
                nodoEnTurno.setDato(i+1, datoAInsertar);
                return;
            }
        }
    }*/
    
    
    @Override
    public void insertar(T datoAInsertar) throws ExcepcionDatoYaExiste {
       if(datoAInsertar==null) {
           throw new IllegalArgumentException("Insertar valores validos");
       }
       
       if(this.esArbolVacio()){
           raiz = new NodoMVias<>(orden,datoAInsertar);
           return;
       }
       NodoMVias<T> nodoEnTurno = this.raiz;
       do{
           int posicionDato = obtenerPosicionDelDato(nodoEnTurno, datoAInsertar);
           if(posicionDato != POSICION_INVALIDA){
               throw new ExcepcionDatoYaExiste();
           }else{
               if(nodoEnTurno.esHoja()){ //Es hoja?
                   //si
                   if(nodoEnTurno.estanDatosLlenos()){ //Esta lleno?
                       //si
                       int posicionPorDondeBajar = buscarPorDondeBajar(nodoEnTurno, datoAInsertar);
                       NodoMVias<T> nuevoNodo = new NodoMVias<>(this.orden, datoAInsertar);
                       nodoEnTurno.setHijo(posicionPorDondeBajar, nuevoNodo);
                   }else{
                       insertarDatoOrdenado(nodoEnTurno,datoAInsertar);
                   }
                   nodoEnTurno = NodoMVias.nodoVacio();
               }else{ //no es hoja
                   int posicionPorDondeBajar = buscarPorDondeBajar(nodoEnTurno, datoAInsertar);
                   if(nodoEnTurno.esHijoVacio(posicionPorDondeBajar)){
                       NodoMVias<T> nuevoNodo = new NodoMVias<>(this.orden, datoAInsertar);
                       nodoEnTurno.setHijo(posicionPorDondeBajar, nuevoNodo);
                       nodoEnTurno = NodoMVias.nodoVacio();
                   }else{
                       nodoEnTurno = nodoEnTurno.getHijo(posicionPorDondeBajar);
                   }
               }
           }  
       }while(!NodoMVias.esNodoVacio(nodoEnTurno));
    }
    
    protected int obtenerPosicionDelDato(NodoMVias<T> nodoEnTurno, T dato){
        for(int i=0; i < nodoEnTurno.nroDeDatosNoVacios();i++){
            T datoEnTurno = nodoEnTurno.getDato(i);
            if(dato.compareTo(datoEnTurno)==0){
                return i;
            }
        }
        return POSICION_INVALIDA;
    }
    
    protected int buscarPorDondeBajar(NodoMVias<T> nodoEnTurno, T dato){
        for(int i=0; i < nodoEnTurno.nroDeDatosNoVacios(); i++){
            if(dato.compareTo(nodoEnTurno.getDato(i))<0){
                return i;
            }
        }
        return nodoEnTurno.nroDeDatosNoVacios();
    }
    
    protected void insertarDatoOrdenado(NodoMVias<T> nodoEnTurno, T datoAInsertar){
        for(int i=nodoEnTurno.nroDeDatosNoVacios()-1; i>=0;i--){
            if(datoAInsertar.compareTo(nodoEnTurno.getDato(i))<0){
                nodoEnTurno.setDato(i+1, nodoEnTurno.getDato(i));
                if(i==0){
                    nodoEnTurno.setDato(i, datoAInsertar);
                    i=-1;
                }
            }else{
                nodoEnTurno.setDato(i+1, datoAInsertar);
                i=-1;
            }
        }
    }
    
    @Override
    public List<T> recorridoPorNiveles() {
        List<T> recorrido = new LinkedList<>();
        if(!this.esArbolVacio()){
            Queue<NodoMVias<T>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            do{
                NodoMVias<T >nodoEnTurno = colaDeNodos.poll();
                for(int i=0; i<nodoEnTurno.nroDeDatosNoVacios();i++){
                    recorrido.add(nodoEnTurno.getDato(i));
                    if(!nodoEnTurno.esHijoVacio(i)){
                        colaDeNodos.offer(nodoEnTurno.getHijo(i));
                    }
                }
                if (!nodoEnTurno.esHijoVacio(nodoEnTurno.nroDeDatosNoVacios())){
                    colaDeNodos.offer(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()));
                }
            }while(!colaDeNodos.isEmpty());
        }
        return recorrido;
    }

    /*@Override
    public void eliminar(T datoAEliminar) throws ExcepcionDatoNoExiste {
        if(datoAEliminar == null){
            throw new IllegalArgumentException();
        }
        eliminar(this.raiz, datoAEliminar);
    }
    
    
    public NodoMVias<T> eliminar(NodoMVias<T> nodoEnTurno, T datoAEliminar) throws ExcepcionDatoNoExiste {
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            throw new ExcepcionDatoNoExiste();
        }
        
        int posicionDeDato = this.obtenerPosicionDelDato(nodoEnTurno, datoAEliminar);
        if(posicionDeDato == POSICION_INVALIDA){
            int posicionPorDondeBajar = this.buscarPorDondeBajar(nodoEnTurno, datoAEliminar);
            NodoMVias<T> supuestoNuevoHijo = this.eliminar(nodoEnTurno.getHijo(posicionPorDondeBajar), datoAEliminar);
            nodoEnTurno.setHijo(posicionPorDondeBajar, supuestoNuevoHijo);
            return nodoEnTurno;
        }
        
        
        //CASO 1: ES HOJA
        if(nodoEnTurno.esHoja()){
            eliminarDatoDePosicion(nodoEnTurno, posicionDeDato);
            if(nodoEnTurno.nroDeDatosNoVacios() == 0){
                return NodoMVias.nodoVacio();
            }
            return nodoEnTurno;
        }else{
            T datoDeReemplazo;
            if(existeHijoDespuesDePosicion(nodoEnTurno, posicionDeDato)){
                datoDeReemplazo = buscarSucesorInOrden(nodoEnTurno, posicionDeDato);
            }else{
                datoDeReemplazo = buscarPredecesorInOrden(nodoEnTurno, posicionDeDato);
            }
            nodoEnTurno = this.eliminar(nodoEnTurno, datoDeReemplazo);        
            nodoEnTurno.setDato(posicionDeDato, datoDeReemplazo);
            return nodoEnTurno;
        }
    }*/
    
    public void eliminar2(T datoAEliminar)throws ExcepcionDatoNoExiste{
        if(datoAEliminar == null){
            throw new IllegalArgumentException("No se pueden eliminar valores nulos");
        }
        this.raiz = eliminar2(this.raiz, datoAEliminar);
    }
    
    private NodoMVias<T> eliminar2(NodoMVias<T> nodoEnTurno, T datoAEliminar) throws ExcepcionDatoNoExiste{
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            throw new ExcepcionDatoNoExiste();
        }
        
        int posicionDeDato = this.obtenerPosicionDelDato(nodoEnTurno, datoAEliminar);
        if(posicionDeDato == POSICION_INVALIDA){
            int posicionPorDondeBajar = this.buscarPorDondeBajar(nodoEnTurno, datoAEliminar);
            NodoMVias<T> supuestoNuevoHijo = eliminar(nodoEnTurno.getHijo(posicionPorDondeBajar),datoAEliminar);
            nodoEnTurno.setHijo(posicionPorDondeBajar, supuestoNuevoHijo);
            return nodoEnTurno;
        }
        
        //LO ENCONTRAMOS
        //Caso 1: es hoja
        if(nodoEnTurno.esHoja()){
            this.eliminarDatoDePosicion(nodoEnTurno, posicionDeDato);
            if(nodoEnTurno.nroDeDatosNoVacios() == 0){
                return NodoMVias.nodoVacio();
            }
            return nodoEnTurno;
        }else{
            if(this.existeHijoDespuesDePosicion(nodoEnTurno, posicionDeDato)){
                T datoDeReemplazo = this.buscarSucesorInOrden(nodoEnTurno, posicionDeDato);
                nodoEnTurno = eliminar2(nodoEnTurno, datoDeReemplazo);
                nodoEnTurno.setDato(posicionDeDato, datoDeReemplazo);
                return nodoEnTurno;
            }else{
                T datoDeReemplazo= this.buscarPredecesorInOrden(nodoEnTurno, posicionDeDato);
                nodoEnTurno = eliminar2(nodoEnTurno, datoDeReemplazo);
                nodoEnTurno.setDato(posicionDeDato, datoDeReemplazo);
                return nodoEnTurno;
                
            }
        }
        
        
    }
    
    @Override
    public void eliminar(T dato) throws ExcepcionDatoNoExiste {
        if (dato == null){
            throw new IllegalArgumentException("No se pueden eliminar datos nulos");
        }
        this.raiz = eliminar(this.raiz, dato);   
    }
    
    private NodoMVias<T> eliminar(NodoMVias<T> nodoEnTurno, T datoAEliminar) throws ExcepcionDatoNoExiste{
        if (NodoMVias.esNodoVacio(nodoEnTurno)){
            throw new ExcepcionDatoNoExiste();
        }    
        
        int posicionDeDato = this.obtenerPosicionDelDato(nodoEnTurno, datoAEliminar);
        if(posicionDeDato == POSICION_INVALIDA){ //No se encuentra
            int posicionPorDondeBajar = this.buscarPorDondeBajar(nodoEnTurno, datoAEliminar);
            NodoMVias<T> supuestoNuevoHijo = this.eliminar(nodoEnTurno.getHijo(posicionPorDondeBajar),datoAEliminar);
            nodoEnTurno.setHijo(posicionPorDondeBajar, supuestoNuevoHijo);
            return nodoEnTurno;
        }
        
        //CASO 1
        if (nodoEnTurno.esHoja()){
            eliminarDatoDePosicion(nodoEnTurno, posicionDeDato);
            if(nodoEnTurno.nroDeDatosNoVacios() == 0){
                return NodoMVias.nodoVacio();
            }
            return nodoEnTurno;
        }else{
            T datoDeReemplazo;
            if(existeHijoDespuesDePosicion(nodoEnTurno, posicionDeDato)){
                datoDeReemplazo = buscarSucesorInOrden(nodoEnTurno, posicionDeDato);
            }else{
                datoDeReemplazo = buscarPredecesorInOrden(nodoEnTurno, posicionDeDato);
            }
            this.eliminar(nodoEnTurno, datoDeReemplazo);
            nodoEnTurno.setDato(posicionDeDato, datoDeReemplazo);
            return nodoEnTurno;
        }
    }
    
    private T buscarSucesorInOrden(NodoMVias<T> nodoEnTurno, int posicionDeDato){
        if (nodoEnTurno.esHijoVacio(posicionDeDato+1)){
            return nodoEnTurno.getDato(posicionDeDato+1);
        }else{
            NodoMVias<T> nodoBuscadorDeReemplazo = nodoEnTurno.getHijo(posicionDeDato+1 );
            while(!nodoBuscadorDeReemplazo.esHijoVacio(0)){
                nodoBuscadorDeReemplazo = nodoBuscadorDeReemplazo.getHijo(0);
            }
            return nodoBuscadorDeReemplazo.getDato(0);
        }
    }
    
    protected T buscarPredecesorInOrden(NodoMVias<T> nodoEnTurno, int posicionDeDato){
       if(nodoEnTurno.esHijoVacio(posicionDeDato)){
           return nodoEnTurno.getDato(posicionDeDato-1);
       }else{
           NodoMVias<T> nodoBuscadorDeReemplazo = nodoEnTurno.getHijo(posicionDeDato);
           while(!nodoBuscadorDeReemplazo.esHijoVacio(0)){
               nodoBuscadorDeReemplazo = nodoBuscadorDeReemplazo.getHijo(0);
           }
           return nodoBuscadorDeReemplazo.getDato(0);
       }
    }
    
    protected void eliminarDatoDePosicion(NodoMVias<T> nodoEnTurno, int posicion){
        for (int i=posicion ; i<nodoEnTurno.nroDeDatosNoVacios()-1; i++){
            nodoEnTurno.setDato(i, nodoEnTurno.getDato(i+1));
            if(i == nodoEnTurno.nroDeDatosNoVacios() - 2){
                nodoEnTurno.setDato(i+1, (T)NodoMVias.datoVacio());
            }
        }
    }
    
    private boolean existeHijoDespuesDePosicion(NodoMVias<T> nodoEnTurno, int posicion){
        for(int i = posicion+1; i<= nodoEnTurno.nroDeDatosNoVacios(); i++){
            if(!nodoEnTurno.esHijoVacio(i)){
                return true;
            }
        }
        return false;
    }

    
    @Override
    public int size() {
        int cantidad = size(this.raiz);
        return cantidad;
    }
    
    private int size(NodoMVias<T> nodoEnTurno){
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            return 0;
        }
        int acumuladora = 0;
        for(int i = 0; i<=nodoEnTurno.nroDeDatosNoVacios(); i++){
           acumuladora += size(nodoEnTurno.getHijo(i));
        }
        
        return acumuladora++;
    }
    @Override
    public int cantidadDeDatos(){
        int cantidad = 0;
        if(!this.esArbolVacio()){
            Queue<NodoMVias<T>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            do{
                NodoMVias<T >nodoEnTurno = colaDeNodos.poll();
                for(int i=0; i<nodoEnTurno.nroDeDatosNoVacios();i++){
                    cantidad++;
                    if(!nodoEnTurno.esHijoVacio(i)){
                        colaDeNodos.offer(nodoEnTurno.getHijo(i));
                    }
                }
                if (!nodoEnTurno.esHijoVacio(nodoEnTurno.nroDeDatosNoVacios())){
                    colaDeNodos.offer(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()));
                }
            }while(!colaDeNodos.isEmpty());
        }
        return cantidad;
    }
    
    @Override
    public int altura() {
        return altura(this.raiz);
    }
    
    private int altura(NodoMVias<T> nodoEnTurno){
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            return 0;
        }
        int alturaMayor = 0;
        for(int i = 0; i<=nodoEnTurno.nroDeDatosNoVacios(); i++){
            int alturaDeHijo = size(nodoEnTurno.getHijo(i));
            if(alturaDeHijo > alturaMayor ){
                alturaMayor = alturaDeHijo;
            }
        }
        
        return alturaMayor++;
    }
    
    @Override
    public T buscar(T datoABuscar) {
        if(datoABuscar==null) {
           throw new IllegalArgumentException("No se pueden buscar valores nulos");
       }
       
       if(this.esArbolVacio()){
           return null;
       }
       NodoMVias<T> nodoEnTurno = this.raiz;
       while(!NodoMVias.esNodoVacio(nodoEnTurno)){
            int posicionDeDato = this.obtenerPosicionDelDato(nodoEnTurno, datoABuscar);
            if(posicionDeDato != POSICION_INVALIDA){
                return nodoEnTurno.getDato(posicionDeDato);
            }
            
            if(nodoEnTurno.esHoja()){
                nodoEnTurno = NodoMVias.nodoVacio();
            }else{
                int posicionPorDondeBajar = this.buscarPorDondeBajar(nodoEnTurno, datoABuscar);
                nodoEnTurno = nodoEnTurno.getHijo(posicionPorDondeBajar);
            }
       }
       return null;
    }

    @Override
    public boolean contiene(T datoABuscar) {
        if(datoABuscar==null) {
           throw new IllegalArgumentException("No se pueden buscar valores nulos");
        }   
       
        if(!this.esArbolVacio()){
            NodoMVias<T> nodoEnTurno = this.raiz;
            do{
                int posicionDeDato = this.obtenerPosicionDelDato(nodoEnTurno, datoABuscar);
                if(posicionDeDato != POSICION_INVALIDA){
                    return true;
                }
                if(nodoEnTurno.esHoja()){
                    nodoEnTurno = NodoMVias.nodoVacio();
                }else{
                    int posicionPorDondeBajar = this.buscarPorDondeBajar(nodoEnTurno, datoABuscar);
                    nodoEnTurno.getHijo(posicionPorDondeBajar);
                }
            }while(!NodoMVias.esNodoVacio(nodoEnTurno));
        }
        return false; 
    }


    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public List recorridoEnInOrden() {
        List<T> recorrido = new LinkedList<>();
        recorridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }
    
    private void recorridoEnInOrden(NodoMVias<T> nodoEnTurno, List<T> recorrido){
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            return;
        }
        
        for (int i=0; i<nodoEnTurno.nroDeDatosNoVacios(); i++){
            recorridoEnInOrden(nodoEnTurno.getHijo(i), recorrido);
            recorrido.add(nodoEnTurno.getDato(i));
        }
        recorridoEnInOrden(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()),recorrido);
    }

    @Override
    public List recorridoEnPreOrden() {
        List<T> recorrido = new LinkedList<>();
        recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;  
    }

    private void recorridoEnPreOrden(NodoMVias<T> nodoEnTurno, List<T> recorrido){
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            return;
        }
        
        for (int i=0; i<nodoEnTurno.nroDeDatosNoVacios(); i++){
            recorrido.add(nodoEnTurno.getDato(i));
            recorridoEnPreOrden(nodoEnTurno.getHijo(i), recorrido);
        }
        recorridoEnPreOrden(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()),recorrido);
    }
    
    @Override
    public List recorridoEnPostOrden() {
        List<T> recorrido = new LinkedList<>();
        recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;  
    }
    
    private void recorridoEnPostOrden(NodoMVias<T> nodoEnTurno, List<T> recorrido){
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            return;
        }
        recorridoEnPostOrden(nodoEnTurno.getHijo(0), recorrido);
        for (int i=0; i<nodoEnTurno.nroDeDatosNoVacios(); i++){
            recorridoEnPreOrden(nodoEnTurno.getHijo(i+1), recorrido);
            recorrido.add(nodoEnTurno.getDato(i));
        }
    }

    
    //EJERCICIOS
    
    //1) Funcion que reotrne la cantidad de hojas quie hay en un deteerminado nivel
    @Override
    public int cantidadDeNodosHojas(int nivel) {
        return cantidadDeNodosHojas(this.raiz, nivel, 0);
    }
    
    private int cantidadDeNodosHojas(NodoMVias<T> nodoEnTurno, int nivel, int nivelActual){
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            return 0;
        }
   
        int cantidad = 0;
        if(nivel == nivelActual){
            if (nodoEnTurno.esHoja()){
                return 1;
            }
        }
        
        for (int i=0; i<= nodoEnTurno.nroDeDatosNoVacios(); i++){
            cantidad = cantidad + cantidadDeNodosHojas(nodoEnTurno.getHijo(i), nivel, nivelActual+1);
        }
        
        return cantidad;
    }

    //2) cantidad de nodos hojas en todo el arbol iterativo
    @Override
    public int cantidadDeNodosHojas() {
        int cantidad = 0;
        if(!this.esArbolVacio()){
            Queue<NodoMVias<T>> colaDeNodos =new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            do{
                NodoMVias<T> nodoEnTurno = colaDeNodos.poll();
                if(nodoEnTurno.esHoja()){
                    cantidad++;
                }
                for (int i = 0; i<=nodoEnTurno.nroDeDatosNoVacios(); i++){      
                    if(!nodoEnTurno.esHijoVacio(i)){
                            colaDeNodos.offer(nodoEnTurno.getHijo(i));
                    }
                }
            }while(!colaDeNodos.isEmpty()); 
        }
        return cantidad;
    }
   
    
    // 3) Implementar un metodo que devuelva verdadero si hasta el nivel n solo hay nodos completos en un arbol m vias (todos tienen hijo der e izq)
    public boolean existeNodoCompletos(int nivel){
        if(nivel<0){
            return false;
        }
        return existeNodoCompletos(this.raiz, nivel,0);
    }
    
    private boolean existeNodoCompletos(NodoMVias<T> nodoEnTurno, int nivel,int nivelActual){
        if(NodoMVias.esNodoVacio(nodoEnTurno)){
            return false;
        }
        
        if(nivelActual == nivel){
            for(int i  = 0; i<= this.orden-1; i++){
                if(NodoMVias.esNodoVacio(nodoEnTurno.getHijo(i))){
                    return false;
                }
            }
            return true;
        }
        
        for (int i=0; i<= this.orden-1; i++){
            if(nodoEnTurno.esHijoVacio(i)){
                return false;
            }
        }
        
        for (int i=0; i<=this.orden-1; i++){
            if(!existeNodoCompletos(nodoEnTurno.getHijo(i), nivel, nivelActual+1)){
                return false;
            }
        }
        return true;
    }
}
