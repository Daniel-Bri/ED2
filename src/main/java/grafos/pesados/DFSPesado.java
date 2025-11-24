package grafos.pesados;

import grafos.nopesados.Grafo;
import grafos.nopesados.RecorridoGrafo;

import java.util.Stack;

public class DFSPesado <T extends Comparable<T>> extends RecorridoGrafoPesado {

    public DFSPesado(GrafoPesado unGrafo, T verticeInicial){
        super(unGrafo,verticeInicial);
    }

    @Override
    public void ejecutarRecorrido(Comparable verticeEnTurno) {
        elGrafo.validarVertice(verticeEnTurno);
        int posDeVertice = elGrafo.getPosicionDeVertice(verticeEnTurno);
        Stack<Integer> pilaDeVertices = new Stack<>();
        pilaDeVertices.push(posDeVertice);
        controlMarcados.marcarVertice(posDeVertice);
        do{
            int posDeVerticeAProcesar = pilaDeVertices.pop();
            recorrido.add(elGrafo.getVerticePorPosicion(posDeVertice));
            Iterable<T> adyacentesDelVertice = elGrafo.getAdyacentesDeVertices(verticeEnTurno);
            for (T adyacente : adyacentesDelVertice){
                int posDeAdyacente = elGrafo.getPosicionDeVertice(adyacente);
                if(!controlMarcados.estaVerticeMarcado(posDeAdyacente)){
                    pilaDeVertices.push(posDeAdyacente);
                    controlMarcados.marcarVertice(posDeAdyacente);
                }
            }
        }while(!pilaDeVertices.isEmpty());
    }
}
