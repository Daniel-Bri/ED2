package grafos.nopesados;

import grafos.excepciones.ExcepcionAristaYaExiste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Grafo <T extends Comparable<T>>{
    protected List<T> listaDeVertices;
    protected List<List<Integer>> listaDeAdyacencias;
    protected static final int POS_DE_VERTICE_INVALIDO = -1;

    public Grafo(){
        listaDeVertices = new ArrayList<>();
        listaDeAdyacencias = new ArrayList<>();
    }


    public Grafo(Iterable<T> vertices){
        this();
        for(T unVertice : vertices){
            this.insertarVertice(unVertice);
        }
    }

    public int getPosicionDeVertice(T unVertice){
        for (int i = 0; i <listaDeVertices.size(); i++){
            T verticeEnTurno = listaDeVertices.get(i);
            if(verticeEnTurno.compareTo(unVertice) == 0){
                return i;
            }
        }
        return POS_DE_VERTICE_INVALIDO;
    }

    public void validarVertice(T unVertice){
        int posDelVertice = getPosicionDeVertice(unVertice);
        if(posDelVertice == POS_DE_VERTICE_INVALIDO){
            throw new IllegalArgumentException("El vertice "+ unVertice + " no existe en su grafo");
        }
    }

    public int cantidadDeVertices(){
        return listaDeVertices.size();
    }

    public void insertarVertice(T unVertice){
        int posDeVertice = getPosicionDeVertice(unVertice);
        if(posDeVertice != POS_DE_VERTICE_INVALIDO){
            throw new IllegalArgumentException("El vertice "+ unVertice + " yo existe en su grafo");
        }
        listaDeVertices.add(unVertice);
        listaDeAdyacencias.add(new ArrayList<>());
    }

    public Iterable<T> getVertices(){
        return listaDeVertices;
    }

    public Iterable<T> getAdyacentesDeVertices(T unVertice){
        validarVertice(unVertice);
        int posDelVertice = getPosicionDeVertice(unVertice);
        List<Integer> adyacentesDelVertice = listaDeAdyacencias.get(posDelVertice);
        List<T> listaDeVerticesAdyacentes = new ArrayList<>();
        for (int i = 0; i < adyacentesDelVertice.size(); i++){
            int posDelAdyacente = adyacentesDelVertice.get(i);
            listaDeVerticesAdyacentes.add(listaDeVertices.get(posDelAdyacente));
        }
        return listaDeVerticesAdyacentes;
    }

    public boolean existeAdyacencia(T verticeOrigen, T verticeDestino){
        validarVertice(verticeOrigen);
        validarVertice(verticeDestino);
        int posDelVerticeOrigen = getPosicionDeVertice(verticeOrigen);
        int posDelVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<Integer> adyacentesDelOrigen = listaDeAdyacencias.get(posDelVerticeOrigen);
        return adyacentesDelOrigen.contains(posDelVerticeDestino);
    }

    public int gradoDelVertice(T unVertice){
        validarVertice(unVertice);
        int posDelVertice = getPosicionDeVertice(unVertice);
        List<Integer> adyacenciasDelVertice =  this.listaDeAdyacencias.get(posDelVertice);
        return adyacenciasDelVertice.size();
    }

    public T getVerticePorPosicion(int posDeVertice){
        if(posDeVertice < 0 || posDeVertice >= (listaDeVertices.size())){
            throw new IllegalArgumentException("La posicion del vertice no es valida");

        }
        return listaDeVertices.get(posDeVertice);
    }

    public void insertarArista(T verticeOrigen, T verticeDestino) throws ExcepcionAristaYaExiste {

        if(existeAdyacencia(verticeOrigen,verticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }

        int posDeVerticeOrigen = getPosicionDeVertice(verticeOrigen);
        int posDeVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<Integer> adyacentesDelOrigen = listaDeAdyacencias.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(posDeVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
        if(posDeVerticeDestino != posDeVerticeOrigen){
            List<Integer> adyacentesDelDestino = listaDeAdyacencias.get(posDeVerticeDestino);
            adyacentesDelDestino.add(posDeVerticeOrigen);
            Collections.sort(adyacentesDelDestino);
        }
    }

    public void eliminarVertice(T unVertice){ //hacer nosotros

    }

    public int cantidadDeAristas(){
        int cantidaDeAristas = 0;

        for (int i = 0; i < listaDeVertices.size(); i++){
            T vertice = getVerticePorPosicion(i);
            Iterable<T> adyacentes = this.getAdyacentesDeVertices(vertice);
            for (T adyacente : adyacentes){
                if(adyacente.compareTo(vertice)>=0) {
                    cantidaDeAristas++;
                }
            }
        }
        return cantidaDeAristas;
    }

    public void eliminarArista(T verticeOrigen, T verticeDestino){
        if(existeAdyacencia(verticeOrigen,verticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }

        int posDeVerticeOrigen = getPosicionDeVertice(verticeOrigen);
        int posDeVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<Integer> adyacentesDelOrigen = listaDeAdyacencias.get(posDeVerticeOrigen);
        adyacentesDelOrigen.remove((Integer) posDeVerticeDestino);
        if(posDeVerticeDestino != posDeVerticeOrigen){
            List<Integer> adyacentesDelDestino = listaDeAdyacencias.get(posDeVerticeDestino);
            adyacentesDelDestino.remove((Integer)posDeVerticeOrigen);
        }
    }

    public boolean esDebilmenteConexo(){
        throw new IllegalArgumentException("No se puedes acceder a este metodo");
    }

    public boolean esFuertementeConexo(){
        throw new IllegalArgumentException("No se puedes acceder a este metodo");
    }

    public boolean esConexo(int[][] matrizDeWarshall){
        int n = matrizDeWarshall.length;
        boolean banderaConexo = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (matrizDeWarshall[i][j] == 0){
                    banderaConexo = false;
                }
            }
        }
        return banderaConexo;
    }

    public List<T> ordenamientoTopologico(){
        return new LinkedList<>();
    }


}
