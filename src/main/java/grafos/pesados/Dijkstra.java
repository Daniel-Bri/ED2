package grafos.pesados;

import grafos.utils.ControlMarcados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Dijkstra <T extends Comparable<T>> {
    protected GrafoPesado<T> grafo;
    List<Integer> listaPredecesores;
    List<Double> listaDeCostos;
    protected ControlMarcados controlMarcados;
    protected final static Double INFINITO = Double.POSITIVE_INFINITY;
    double costoMinimo = INFINITO;
    protected List<T> camino;

    public Dijkstra(GrafoPesado<T> unGrafo, T verticeOrigen, T verticeDestino){
        grafo = unGrafo;
        controlMarcados = new ControlMarcados(grafo.cantidadDeVertices());
        camino = new ArrayList<>();
        listaPredecesores= new ArrayList<>();
        listaDeCostos = new ArrayList<>();
        ejecutarAlgorimoDijkstra(verticeOrigen, verticeDestino);
    }

    public void ejecutarAlgorimoDijkstra(T verticeOrigen, T verticeDestino){
        grafo.validarVertice(verticeOrigen);
        grafo.validarVertice(verticeDestino);
        for (int i = 0; i < grafo.cantidadDeVertices(); i++){
            if( i == grafo.getPosicionDeVertice(verticeOrigen)){
                listaDeCostos.add(0.0);
            }else{
                listaDeCostos.add(INFINITO);
            }
            listaPredecesores.add(-1);
        }

        //ITERACIONES
        do{
            int posicionDeVerticeMenorCosto = this.verticeNoMarcadoConMenorCosto(listaDeCostos);
            Iterable<T> adyacentes = grafo.getAdyacentesDeVertices(grafo.getVerticePorPosicion(posicionDeVerticeMenorCosto));
            controlMarcados.marcarVertice(posicionDeVerticeMenorCosto);
            for(T adyacente : adyacentes){
                if(!controlMarcados.estaVerticeMarcado(grafo.getPosicionDeVertice(adyacente))){
                    double suma = listaDeCostos.get(posicionDeVerticeMenorCosto) +
                            grafo.getPeso(grafo.getVerticePorPosicion(posicionDeVerticeMenorCosto), adyacente);
                    if(listaDeCostos.get(grafo.getPosicionDeVertice(adyacente)) > suma){
                        listaDeCostos.set(grafo.getPosicionDeVertice(adyacente), suma);
                        listaPredecesores.set(grafo.getPosicionDeVertice(adyacente), posicionDeVerticeMenorCosto);
                    }
                }
            }
        }while(!controlMarcados.estaVerticeMarcado(grafo.getPosicionDeVertice(verticeDestino)));
        costoMinimo = listaDeCostos.get(grafo.getPosicionDeVertice(verticeDestino));



        //Camino
        if(costoMinimo != INFINITO){
            Stack<Integer> pilaDeVertices = new Stack<>();
            int posicionVertice = listaPredecesores.get(grafo.getPosicionDeVertice(verticeDestino));
            do{
                pilaDeVertices.add(posicionVertice);
                posicionVertice = listaPredecesores.get(posicionVertice);
            }while(posicionVertice != -1);
            do{
                camino.add(grafo.getVerticePorPosicion(pilaDeVertices.pop()));
            }while(!pilaDeVertices.isEmpty());
            camino.add(verticeDestino);
        }
    }

    public int verticeNoMarcadoConMenorCosto(List<Double> listaDeCostos) {
        if (!controlMarcados.estanTodosLosVerticesMarcados()){
            double menorCosto = INFINITO;
            int posicionDeVertice = -1;
            for (int i = 0; i < listaDeCostos.size(); i++) {
                if (!controlMarcados.estaVerticeMarcado(i) && (listaDeCostos.get(i) < menorCosto)) {
                    menorCosto = listaDeCostos.get(i);
                    posicionDeVertice = i;
                }
            }
            return posicionDeVertice;
        }else{
            return -1;
        }
    }

    public List<T> getCamino(){
        return camino;
    }

    public double getCostoMinimo(){
        return costoMinimo;
    }

    public List<Integer> getListaPredecesores(){
        return listaPredecesores;
    }

    public List<Double> getListaCostos(){
        return listaDeCostos;
    }
}
