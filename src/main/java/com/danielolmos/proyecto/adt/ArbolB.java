/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danielolmos.proyecto.adt;

import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoNoExiste;
import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoYaExiste;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class ArbolB <T extends Comparable<T>> extends ArbolMViasBusqueda<T>{
    
    public ArbolB(int orden) {
        super(orden);
    }
    
    private int getNroMaximoDeDatos(){
        return super.orden - 1;
    }
    
    private int getNroMinimoDeDatos(){
        return getNroMaximoDeDatos() / 2;
    }
    
    private int getNroMinimoDeHijos(){
        return getNroMinimoDeDatos()+1;
    }

    /*@Override
    public void insertar(T datoAInsertar) throws ExcepcionDatoYaExiste {
        if(datoAInsertar == null){
            throw new IllegalArgumentException("No insertar datos nulos");
        }
        
        if(this.esArbolVacio()){
            this.raiz = new NodoMVias<>(this.orden, datoAInsertar);
            return;
        }
        
        NodoMVias<T> nodoEnTurno = this.raiz;
        Stack<NodoMVias<T>> pilaDeAncestros= new Stack<>();
        do{
            int posicionDeDato = super.obtenerPosicionDelDato(nodoEnTurno, datoAInsertar);
            if(posicionDeDato != POSICION_INVALIDA){
                throw new ExcepcionDatoYaExiste();
            }
            
            if(nodoEnTurno.esHoja()){
                if(nodoEnTurno.estanDatosLlenos()){
                    dividirYEmpujar(nodoEnTurno, pilaDeAncestros, datoAInsertar, NodoMVias.nodoVacio());
                }else{
                    super.insertarDatoOrdenado(nodoEnTurno, datoAInsertar);
                }
                nodoEnTurno = NodoMVias.nodoVacio();
            }else{
                int posicionPorDondeBajar = super.buscarPorDondeBajar(nodoEnTurno, datoAInsertar);
                pilaDeAncestros.push(nodoEnTurno);
                nodoEnTurno = nodoEnTurno.getHijo(posicionPorDondeBajar);
            }
        }while(!NodoMVias.esNodoVacio(nodoEnTurno));
    }
    
    private void dividirYEmpujar(NodoMVias<T> nodoEnTurno, Stack<NodoMVias<T>> pilaDeAncestros, T datoAInsertar, NodoMVias<T> nodoQueSobra){
        List<T> listaDeDatos = new LinkedList<>();
        for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++) {
            listaDeDatos.add(nodoEnTurno.getDato(i));
        }
        listaDeDatos.add(datoAInsertar);
        Collections.sort(listaDeDatos);
        int posicionDelMedio = this.getNroMinimoDeDatos();

        NodoMVias<T> nodoIzquierdo = new NodoMVias<>(this.orden);
        NodoMVias<T> nodoDerecho = new NodoMVias<>(this.orden);

        // Repartir datos
        for (int i = 0; i < posicionDelMedio; i++)
            nodoIzquierdo.setDato(i, listaDeDatos.get(i));

        for (int i = posicionDelMedio + 1, j = 0; i < listaDeDatos.size(); i++, j++)
            nodoDerecho.setDato(j, listaDeDatos.get(i));
        
        // Repartir hijos si no es hoja
        if (!nodoEnTurno.esHoja()) {
            for (int i = 0; i <= posicionDelMedio; i++)
                nodoIzquierdo.setHijo(i, nodoEnTurno.getHijo(i));
            nodoDerecho.setHijo(0, nodoQueSobra); // este es el primer hijo derecho

            for (int i = posicionDelMedio + 1, j = 1; i < this.orden; i++, j++)
                nodoDerecho.setHijo(j, nodoEnTurno.getHijo(i));
        }

        T datoDelMedio = listaDeDatos.get(posicionDelMedio);

        if (pilaDeAncestros.isEmpty()) {
            NodoMVias<T> nuevaRaiz = new NodoMVias<>(this.orden, datoDelMedio);
            nuevaRaiz.setHijo(0, nodoIzquierdo);
            nuevaRaiz.setHijo(1, nodoDerecho);
            this.raiz = nuevaRaiz;
        } else {
            NodoMVias<T> nodoPadre = pilaDeAncestros.pop();
            int pos = super.buscarPorDondeBajar(nodoPadre, datoDelMedio);
            if (nodoPadre.estanDatosLlenos()) {
                nodoPadre.setHijo(pos+1, nodoIzquierdo);
                this.dividirYEmpujar(nodoPadre, pilaDeAncestros, datoDelMedio, nodoDerecho);
            } else {
                
                super.insertarDatoOrdenado(nodoPadre, datoDelMedio);
                dezplazarHijos(nodoPadre, pos+1);
                nodoPadre.setHijo(pos, nodoIzquierdo);
                nodoPadre.setHijo(pos + 1, nodoDerecho);
            }
        }
    }
    
    private void dezplazarHijos(NodoMVias<T> nodoEnTurno, int posicionDondeEmpezar){
        for (int i = nodoEnTurno.nroDeDatosNoVacios(); i > posicionDondeEmpezar; i--){
            nodoEnTurno.setHijo(i, nodoEnTurno.getHijo(i-1));
        }
    }*/
    @Override
    public void insertar(T datoAInsertar) throws ExcepcionDatoYaExiste {
        if(datoAInsertar == null){
            throw new IllegalArgumentException("No se puede insertar valores nulos");
        }
        if(super.esArbolVacio()){
            this.raiz = new NodoMVias<>(this.orden, datoAInsertar);
            return;
        }
        
        NodoMVias<T> nodoEnTurno = this.raiz;
        Stack<NodoMVias<T>> pilaDeAncestros = new Stack<>();
        do{
            int posicionDeDato = super.obtenerPosicionDelDato(nodoEnTurno, datoAInsertar);
            if(posicionDeDato != POSICION_INVALIDA){
                throw new ExcepcionDatoYaExiste();
            }
            if(nodoEnTurno.esHoja()){
                if(nodoEnTurno.estanDatosLlenos()){
                    this.dividirYEmpujar(nodoEnTurno, pilaDeAncestros, datoAInsertar, NodoMVias.nodoVacio());
                }else{
                    this.insertarDatoOrdenado(nodoEnTurno, datoAInsertar);
                }
                nodoEnTurno = NodoMVias.nodoVacio();
            }else{
                int posicionPorDondeBajar = super.buscarPorDondeBajar(nodoEnTurno, datoAInsertar);
                pilaDeAncestros.push(nodoEnTurno);
                nodoEnTurno = nodoEnTurno.getHijo(posicionPorDondeBajar);
            }
        }while(!NodoMVias.esNodoVacio(nodoEnTurno));
    }
    
   private void dividirYEmpujar(NodoMVias<T> nodoEnTurno, Stack<NodoMVias<T>> pilaDeAncestros, 
                             T datoAInsertar, NodoMVias<T> nodoQueSobra) {

    // 1️⃣ Combinar los datos actuales con el nuevo a insertar
    List<T> listaDeDatos = new ArrayList<>();
    for (int i = 0; i < nodoEnTurno.nroDeDatosNoVacios(); i++) {
        listaDeDatos.add(nodoEnTurno.getDato(i));
    }
    listaDeDatos.add(datoAInsertar);
    Collections.sort(listaDeDatos); // mantener orden ascendente

    int posicionDelMedio = getNroMinimoDeDatos();
    T datoMedio = listaDeDatos.get(posicionDelMedio);

    // 2️⃣ Crear nodos hijos izquierdo y derecho
    NodoMVias<T> izquierdo = new NodoMVias<>(this.orden);
    NodoMVias<T> derecho = new NodoMVias<>(this.orden);

    // Cargar datos al hijo izquierdo
    for (int i = 0; i < posicionDelMedio; i++) {
        izquierdo.setDato(i, listaDeDatos.get(i));
    }

    // Cargar datos al hijo derecho
    int j = 0;
    for (int i = posicionDelMedio + 1; i < listaDeDatos.size(); i++, j++) {
        derecho.setDato(j, listaDeDatos.get(i));
    }

    // 3️⃣ Reasignar los hijos si el nodo no era hoja
    if (!nodoEnTurno.esHoja()) {
        int totalHijos = nodoEnTurno.nroDeDatosNoVacios() + 1; // hijos válidos en nodoEnTurno
        // Copiar hijos al izquierdo: índices 0 .. posicionDelMedio
        for (int i = 0; i <= posicionDelMedio; i++) {
            // proteger contra índice >= orden
            if (i < this.orden) {
                izquierdo.setHijo(i, nodoEnTurno.getHijo(i));
            }
        }

        // Copiar hijos al derecho: desde posicionDelMedio+1 hasta totalHijos-1
        int k = 0;
        for (int i = posicionDelMedio + 1; i < totalHijos; i++, k++) {
            // k es el índice dentro del nodo derecho; debe ser < orden
            if (k < this.orden) {
                derecho.setHijo(k, nodoEnTurno.getHijo(i));
            }
        }
    }

    // Si existe un nodo que sobra, lo intentamos asignar al hijo apropiado del derecho
    if (!NodoMVias.esNodoVacio(nodoQueSobra)) {
        int idx = derecho.nroDeDatosNoVacios() + 1; // posición natural para el sobrante
        // garantizar que idx esté dentro de 0..orden-1
        if (idx >= this.orden) {
            idx = this.orden - 1;
            if (idx < 0) {
                idx = 0; // protección redundante por si orden < 1 (no debería pasar)
            }
        }
        // sólo llamar si idx < orden (evita IndexOutOfBounds en la LinkedList interna)
        if (idx < this.orden) {
            derecho.setHijo(idx, nodoQueSobra);
        }
    }

    // 4️⃣ Si no hay ancestros, crear nueva raíz
    if (pilaDeAncestros.isEmpty()) {
        NodoMVias<T> nuevaRaiz = new NodoMVias<>(this.orden);
        nuevaRaiz.setDato(0, datoMedio);
        // asignar hijos 0 y 1 con protección por orden
        if (0 < this.orden) {
            nuevaRaiz.setHijo(0, izquierdo);
        }
        if (1 < this.orden) {
            nuevaRaiz.setHijo(1, derecho);
        }
        this.raiz = nuevaRaiz;
        return;
    }

    // 5️⃣ Si hay ancestros, subir el dato medio al padre
    NodoMVias<T> padre = pilaDeAncestros.pop();

    if (padre.estanDatosLlenos()) {
        // Si el padre está lleno, dividirlo recursivamente
        dividirYEmpujar(padre, pilaDeAncestros, datoMedio, derecho);
    } else {
        // Insertar el dato medio ordenadamente en el padre (usa tu método existente)
        insertarDatoOrdenado(padre, datoMedio);
        int pos = buscarPorDondeBajar(padre, datoMedio);

        // Reasignar hijos del padre con protección de índices (pos y pos+1 deben < orden)
        if (pos < this.orden) {
            padre.setHijo(pos, izquierdo);
        } else {
            // si pos fuera igual a orden (improbable), ajustamos al último índice válido
            padre.setHijo(this.orden - 1, izquierdo);
        }

        int posSig = pos + 1;
        if (posSig < this.orden) {
            padre.setHijo(posSig, derecho);
        } else {
            padre.setHijo(this.orden - 1, derecho);
        }
    }
}





    
    
    

    @Override
    public void eliminar(T datoAEliminar) throws ExcepcionDatoNoExiste {
        if(datoAEliminar == null){
            throw new IllegalArgumentException("No se pueden eliminar valores nulos");
        }
        
        Stack<NodoMVias<T>> pilaDeAncestros = new Stack<>();
        NodoMVias<T> nodoEnTurno = this.raiz;
        NodoMVias<T> nodoDelDatoAEliminar = NodoMVias.nodoVacio();
        int posicionDelDato = POSICION_INVALIDA;
        while(!NodoMVias.esNodoVacio(nodoEnTurno)){
            posicionDelDato = super.obtenerPosicionDelDato(nodoEnTurno, datoAEliminar); super.obtenerPosicionDelDato(nodoEnTurno, datoAEliminar);
            if(posicionDelDato != POSICION_INVALIDA){
                nodoDelDatoAEliminar = nodoEnTurno;
                nodoEnTurno = NodoMVias.nodoVacio();
            }else{
                int posicionPorDondeBajar = super.buscarPorDondeBajar(nodoEnTurno, datoAEliminar);
                pilaDeAncestros.push(nodoEnTurno);
                nodoEnTurno = nodoEnTurno.getHijo(posicionPorDondeBajar);
            }
        }// fin while
        
        if(NodoMVias.esNodoVacio(nodoDelDatoAEliminar)){
            throw new ExcepcionDatoNoExiste();
        }
        
        if(nodoDelDatoAEliminar.esHoja()){
            super.eliminarDatoDePosicion(nodoEnTurno, posicionDelDato);
            if(nodoDelDatoAEliminar.nroDeDatosNoVacios() < getNroMinimoDeDatos()){
                prestarOFusionarse(nodoDelDatoAEliminar, pilaDeAncestros);
            }
        }else{
            pilaDeAncestros.push(nodoDelDatoAEliminar);
            NodoMVias<T> nodoDelPredecesor = obtenerPredecesorInOrden(nodoDelDatoAEliminar.getHijo(posicionDelDato), pilaDeAncestros);
            T datoDelPredecesor = nodoDelPredecesor.getDato(nodoDelPredecesor.nroDeDatosNoVacios()-1);
            nodoDelPredecesor.setDato(nodoDelPredecesor.nroDeDatosNoVacios()-1, (T)NodoMVias.nodoVacio());
            nodoDelDatoAEliminar.setDato(posicionDelDato, datoDelPredecesor);
            if(nodoDelPredecesor.nroDeDatosNoVacios() < getNroMinimoDeDatos()){
                prestarOFusionarse(nodoDelPredecesor, pilaDeAncestros);
            }
        }
    }
    
    private NodoMVias<T> obtenerPredecesorInOrden(NodoMVias<T> nodoEnTurno, Stack<NodoMVias<T>> pilaDeAncestros){
        while (!NodoMVias.esNodoVacio(nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios()))) {
            pilaDeAncestros.push(nodoEnTurno);
            nodoEnTurno = nodoEnTurno.getHijo(nodoEnTurno.nroDeDatosNoVacios());
        }
        return nodoEnTurno;
     }
    
    public void prestarOFusionarse(NodoMVias<T> nodoEnTurno, Stack<NodoMVias<T>> pilaDeAncestros){
        
    }

    /* private void prestarOFusionarse(NodoMVias<T> nodoEnTurno, Stack<NodoMVias<T>> pilaDeAncestros) {
        if (pilaDeAncestros.isEmpty()) {
            // Si llegamos a la raíz y quedó vacía → ajustar raíz
            if (nodoEnTurno.nroDeDatosNoVacios() == 0 && !nodoEnTurno.esHoja()) {
                this.raiz = nodoEnTurno.getHijo(0);
            }
            return;
     }

        NodoMVias<T> padre = pilaDeAncestros.pop();
        int posHijo = buscarPosicionDeHijo(padre, nodoEnTurno);

        // Intentar prestar del hermano izquierdo
        if (posHijo > 0) {
            NodoMVias<T> hermanoIzq = padre.getHijo(posHijo - 1);
            if (hermanoIzq.nroDeDatosNoVacios() > getNroMinimoDeDatos()) {
                prestarDesdeIzquierda(padre, hermanoIzq, nodoEnTurno, posHijo - 1);
                return;
            }
        }

        // Intentar prestar del hermano derecho
        if (posHijo < padre.nroDeDatosNoVacios()) {
            NodoMVias<T> hermanoDer = padre.getHijo(posHijo + 1);
            if (hermanoDer.nroDeDatosNoVacios() > getNroMinimoDeDatos()) {
                prestarDesdeDerecha(padre, nodoEnTurno, hermanoDer, posHijo);
                return;
            }
        }

        // Si no se puede prestar, toca fusionar
        if (posHijo > 0) {
            NodoMVias<T> hermanoIzq = padre.getHijo(posHijo - 1);
            fusionarConIzquierda(padre, hermanoIzq, nodoEnTurno, posHijo - 1);
            if (padre.nroDeDatosNoVacios() < getNroMinimoDeDatos()) {
                prestarOFusionarse(padre, pilaDeAncestros);
            }
        } else {
            NodoMVias<T> hermanoDer = padre.getHijo(posHijo + 1);
            fusionarConDerecha(padre, nodoEnTurno, hermanoDer, posHijo);
            if (padre.nroDeDatosNoVacios() < getNroMinimoDeDatos()) {
                prestarOFusionarse(padre, pilaDeAncestros);
            }
        }
    }

    private void prestarDesdeIzquierda(NodoMVias<T> padre, NodoMVias<T> hermanoIzq, NodoMVias<T> nodo, int posPadre) throws ExcepcionDatoNoExiste{
        // Traer dato del padre hacia el inicio del nodo
        T datoPadre = padre.getDato(posPadre);
        super.insertarDatoOrdenado(nodo, datoPadre);

        // Subir último dato del hermano izquierdo al padre
        T datoHermano = hermanoIzq.getDato(hermanoIzq.nroDeDatosNoVacios() - 1);
        padre.setDato(posPadre, datoHermano);

        // Si tiene hijos, mover el último hijo del hermano izquierdo al nodo actual
        if (!hermanoIzq.esHoja()) {
            NodoMVias<T> hijoTransferido = hermanoIzq.getHijo(hermanoIzq.nroDeDatosNoVacios());
            nodo.correrHijosADerechaDesde(0);
            nodo.setHijo(0, hijoTransferido);
            hermanoIzq.setHijo(hermanoIzq.nroDeDatosNoVacios(), NodoMVias.nodoVacio());
        }

         eliminar(hermanoIzq.getDato(hermanoIzq.nroDeDatosNoVacios()-1));
    }

    private void prestarDesdeDerecha(NodoMVias<T> padre, NodoMVias<T> nodo, NodoMVias<T> hermanoDer, int posPadre) throws ExcepcionDatoNoExiste{
        // Traer dato del padre hacia el final del nodo
        T datoPadre = padre.getDato(posPadre);
        super.insertarDatoOrdenado(nodo, datoPadre);

        // Subir primer dato del hermano derecho al padre
        T datoHermano = hermanoDer.getDato(0);
        padre.setDato(posPadre, datoHermano);

        // Si tiene hijos, mover el primer hijo del hermano derecho al nodo actual
        if (!hermanoDer.esHoja()) {
            NodoMVias<T> hijoTransferido = hermanoDer.getHijo(0);
            nodo.setHijo(nodo.nroDeDatosNoVacios(), hijoTransferido);
            hermanoDer.correrHijosAIzquierdaDesde(1);
        }

        eliminar(hermanoDer.getDato(0));
    }

    private void fusionarConIzquierda(NodoMVias<T> padre, NodoMVias<T> hermanoIzq, NodoMVias<T> nodo, int posPadre) {
        // Bajar dato del padre
        T datoDelPadre = padre.getDato(posPadre);
        super.insertarDatoOrdenado(hermanoIzq, datoDelPadre);

        // Copiar todos los datos del nodo actual al hermano izquierdo
        for (int i = 0; i < nodo.nroDeDatosNoVacios(); i++) {
            hermanoIzq.insertarDatoOrdenado(nodo.getDato(i));
        }

        // Copiar hijos
        if (!nodo.esHoja()) {
            int posInicial = hermanoIzq.nroDeDatosNoVacios() - nodo.nroDeDatosNoVacios();
            for (int i = 0; i <= nodo.nroDeDatosNoVacios(); i++) {
                hermanoIzq.setHijo(posInicial + i, nodo.getHijo(i));
            }
        }

        // Eliminar el dato del padre y correr los hijos
        super.eliminarDatoDePosicion(padre, posPadre);
        padre.correrHijosAIzquierdaDesde(posPadre + 1);
    }

    private void fusionarConDerecha(NodoMVias<T> padre, NodoMVias<T> nodo, NodoMVias<T> hermanoDer, int posPadre) {
        // Bajar dato del padre
        T datoDelPadre = padre.getDato(posPadre);
        super.insertarDatoOrdenado(nodo, datoDelPadre);

        // Copiar todos los datos del hermano derecho al nodo actual
        for (int i = 0; i < hermanoDer.nroDeDatosNoVacios(); i++) {
            superinsertarDatoOrdenado(nodo, hermanoDer.getDato(i));
        }

        // Copiar hijos
        if (!hermanoDer.esHoja()) {
            int inicio = nodo.nroDeDatosNoVacios() - hermanoDer.nroDeDatosNoVacios();
            for (int i = 0; i <= hermanoDer.nroDeDatosNoVacios(); i++) {
                nodo.setHijo(inicio + i, hermanoDer.getHijo(i));
            }
        }

        // Eliminar el dato del padre y correr los hijos
        super.eliminarDatoDePosicion(padre,posPadre);
        padre.correrHijosAIzquierdaDesde(posPadre + 1);
    }

    private int buscarPosicionDeHijo(NodoMVias<T> padre, NodoMVias<T> hijo) {
        for (int i = 0; i <= padre.nroDeDatosNoVacios(); i++) {
            if (padre.getHijo(i) == hijo) {
                return i;
            }
        }
        return -1;
    }*/


    /*public void eliminar(T datoAEliminar) throws ExcepcionDatoNoExiste {
        if(datoAEliminar == null){
            throw new IllegalArgumentException("No se pueden eliminar valores nulos");
        }
        
        Stack<NodoMVias<T>> pilaDeAncestros =  new Stack<>();
        NodoMVias<T> nodoEnTurno = this.raiz;
        NodoMVias<T> nodoDelDatoAEliminar = NodoMVias.nodoVacio();
        int posicionDeDato = POSICION_INVALIDA;
        while(NodoMVias.esNodoVacio(nodoEnTurno)){
            posicionDeDato = super.obtenerPosicionDelDato(nodoEnTurno, datoAEliminar);
            if(posicionDeDato == POSICION_INVALIDA){
                int posicionPorDondeBajar = this.buscarPorDondeBajar(nodoEnTurno, datoAEliminar);
                pilaDeAncestros.push(nodoEnTurno);
                nodoEnTurno = nodoEnTurno.getHijo(posicionPorDondeBajar);
            }else{
                nodoDelDatoAEliminar = nodoEnTurno;
                nodoEnTurno = NodoMVias.nodoVacio();
            }
        }//fin de while
        
        if(NodoMVias.esNodoVacio(nodoDelDatoAEliminar)){
            throw new ExcepcionDatoNoExiste();
        }
        
        if(nodoDelDatoAEliminar.esHoja()){
            super.eliminarDatoDePosicion(nodoDelDatoAEliminar, posicionDeDato);
            if(nodoDelDatoAEliminar.nroDeDatosNoVacios() < this.getNroMinimoDeDatos()){
                prestarOFusionar(nodoDelDatoAEliminar, pilaDeAncestros);
            }
        }else{
             pilaDeAncestros.push(nodoDelDatoAEliminar);
             NodoMVias<T> nodoDelPredecesor = buscarNodoDelPredecesor(nodoDelDatoAEliminar.getHijo(posicionDeDato), pilaDeAncestros);
             T datoPredecesor = nodoDelPredecesor.getDato(nodoDelPredecesor.nroDeDatosNoVacios()-1);
             nodoDelPredecesor.setDato(nodoDelPredecesor.nroDeDatosNoVacios()-1, (T)NodoMVias.nodoVacio());
             nodoDelDatoAEliminar.setDato(posicionDeDato, datoPredecesor);
             if(nodoDelPredecesor.nroDeDatosNoVacios() < this.getNroMinimoDeDatos()){
                 prestarOFusionar(nodoDelPredecesor, pilaDeAncestros);
             }
        }
    }*/
    
    /*private NodoMVias<T> buscarNodoDelPredecesor(NodoMVias<T> nodoEnTurno, Stack<NodoMVias<T>> pilaDeAncestros){
        
        return null;
    }
    
    private void prestarOFusionar(NodoMVias<T> nodoEnTurno, Stack<NodoMVias<T>> pilaDeAncestros){
        
    }*/
    
    
    
}
