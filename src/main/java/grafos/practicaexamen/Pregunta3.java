package grafos.practicaexamen;

import grafos.nopesados.Grafo;
import grafos.utils.ControlMarcados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Pregunta3 <T extends Comparable<T>> extends Grafo<T> {
    protected ControlMarcados controlMarcados;

    public List<T> verticesOrigenParaLlegarADestino(T verticeDestino){
        List<T> listaDeVerticesOrigen = new ArrayList<>();
        controlMarcados = new ControlMarcados(this.cantidadDeVertices());
        Queue<Integer> colaDeVertices = new LinkedList<>();
        for(int i = 0; i < cantidadDeVertices(); i++){
            controlMarcados = new ControlMarcados(this.cantidadDeVertices());
            colaDeVertices.offer(i);
            controlMarcados.marcarVertice(i);
            do{
                int posVerticeEnTurno = colaDeVertices.poll();
                Iterable<T> adyacentes = getAdyacentesDeVertices(getVerticePorPosicion(posVerticeEnTurno));
                for(T adyacente : adyacentes){
                    int posAdyacente = getPosicionDeVertice(adyacente);
                    if(!controlMarcados.estaVerticeMarcado(posAdyacente)){
                        colaDeVertices.offer(posAdyacente);
                        controlMarcados.marcarVertice(posAdyacente);
                    }

                    if(adyacente.compareTo(verticeDestino) == 0){
                        if(!listaDeVerticesOrigen.contains(getVerticePorPosicion(i)) && getVerticePorPosicion(i).compareTo(verticeDestino)!=0 ) {
                            listaDeVerticesOrigen.add(getVerticePorPosicion(i));
                        }
                    }
                }
            }while(!colaDeVertices.isEmpty());
        }
        return listaDeVerticesOrigen;
    }
}
