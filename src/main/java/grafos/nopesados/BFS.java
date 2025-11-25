package grafos.nopesados;

import java.util.LinkedList;
import java.util.Queue;

public class BFS <T extends Comparable<T>> extends RecorridoGrafo<T>{
    public BFS(Grafo unGrafo, T verticeInicial){
        super(unGrafo, verticeInicial);
    }

    @Override
    public void ejecutarRecorrido(T verticeEnTurno) {
        elGrafo.validarVertice(verticeEnTurno);
        int posDeVertice = elGrafo.getPosicionDeVertice(verticeEnTurno);
        Queue<Integer> colaDeVertices = new LinkedList<>();
        colaDeVertices.offer(posDeVertice);
        do{
            int posDeVerticeAProcesar = colaDeVertices.poll();
            recorrido.add(elGrafo.getVerticePorPosicion(posDeVerticeAProcesar));
            Iterable<T> adyacentes = elGrafo.getAdyacentesDeVertices(elGrafo.getVerticePorPosicion(posDeVerticeAProcesar));
            for(T adyacente : adyacentes){
                int posAdyacente = elGrafo.getPosicionDeVertice(adyacente);
                if(!controlMarcados.estaVerticeMarcado(posAdyacente)){
                    colaDeVertices.offer(posAdyacente);
                    controlMarcados.marcarVertice(posAdyacente);
                }

            }
        }while(!colaDeVertices.isEmpty());
    }


    /*public void ejecutarRecorrido(T verticeEnTurno) {
        elGrafo.validarVertice(verticeEnTurno);
        int posDelVerticeEnTurno = elGrafo.getPosicionDeVertice(verticeEnTurno);
        Queue<Integer> colaDeVertices = new LinkedList<>();
        colaDeVertices.add(posDelVerticeEnTurno);
        controlMarcados.marcarVertice(posDelVerticeEnTurno);
        do{
            int posDeVerticeAProcesar = colaDeVertices.poll();
            recorrido.add(elGrafo.getVerticePorPosicion(posDeVerticeAProcesar));
            Iterable<T> adyacentesDelVertice = elGrafo.getAdyacentesDeVertices(verticeEnTurno);
            for (T adyacente : adyacentesDelVertice){
                int posDelAdyacente = elGrafo.getPosicionDeVertice(adyacente);
                if(!controlMarcados.estaVerticeMarcado(posDelAdyacente)){
                    colaDeVertices.add(posDelAdyacente);
                    controlMarcados.marcarVertice(posDelAdyacente);
                }
            }

        }while(!colaDeVertices.isEmpty());
    }*/

}

