package grafos.practicaexamen;

import grafos.nopesados.DFS;
import grafos.nopesados.DiGrafo;
import grafos.nopesados.Grafo;
import grafos.nopesados.RecorridoGrafo;
import grafos.utils.ControlMarcados;

import java.util.LinkedList;
import java.util.Queue;

public class Pregunta1 <T extends Comparable<T>> extends DiGrafo {
    //Metodo que retorne el numero de islas que hay en un grafo dirigido
    protected DiGrafo<T> grafo;
    protected ControlMarcados controlMarcados;


    public Pregunta1(DiGrafo<T> unGrafo){
        grafo = unGrafo;
        controlMarcados = new ControlMarcados(grafo.cantidadDeVertices());
    }

    public int cantidadDeIslas(){
        int cantidadDeIslas = 0;
        T verticeInicial = grafo.getVerticePorPosicion(0);
        Queue<Integer> colaDeVertices = new LinkedList<>();
        boolean islaActiva= false;
        int posicionDeVerticeNoMarcado = 0;
        do {
            verticeInicial = grafo.getVerticePorPosicion(posicionDeVerticeNoMarcado);
            colaDeVertices.offer(grafo.getPosicionDeVertice(verticeInicial));
            controlMarcados.marcarVertice(grafo.getPosicionDeVertice(verticeInicial));
            do {
                int posicionVerticeEnTurno = colaDeVertices.poll();
                Iterable<T> adyacentes = grafo.getAdyacentesDeVertices(grafo.getVerticePorPosicion(posicionVerticeEnTurno));
                for (T adyacente : adyacentes) {
                    int posAdyacente = grafo.getPosicionDeVertice(adyacente);
                    if (!controlMarcados.estaVerticeMarcado(posAdyacente)) {
                        colaDeVertices.offer(posAdyacente);
                        controlMarcados.marcarVertice(posAdyacente);
                    }else{
                        islaActiva = true;
                    }
                }
            } while (!colaDeVertices.isEmpty());
            if(islaActiva != true){
                cantidadDeIslas++;
            }else{
                islaActiva = false;
            }
            posicionDeVerticeNoMarcado = posDeVerticeNoMarcado();
        }while(posicionDeVerticeNoMarcado != -1);
        return cantidadDeIslas;
    }

    public int posDeVerticeNoMarcado(){
        for(int i = 0; i < grafo.cantidadDeVertices(); i++){
            if(!controlMarcados.estaVerticeMarcado(i)){
                return i;
            }
        }
        return -1;
    }
}
