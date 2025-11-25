package grafos.nopesados;

import grafos.utils.ControlMarcados;

import java.util.Stack;

public class DFS <T extends Comparable<T>> extends RecorridoGrafo<T>{
    public DFS(Grafo unGrafo, T verticeInicial){
        super(unGrafo,verticeInicial);
    }

    @Override
    public void ejecutarRecorrido(T verticeEnTurno) {
        elGrafo.validarVertice(verticeEnTurno);
        int posDeVertice = elGrafo.getPosicionDeVertice(verticeEnTurno);
        Stack<Integer> pilaDeVertices = new Stack<>();
        pilaDeVertices.push(posDeVertice);
        controlMarcados.marcarVertice(posDeVertice);
        do{
            int posDeVerticeEnTurno = pilaDeVertices.pop();
            recorrido.add(elGrafo.getVerticePorPosicion(posDeVerticeEnTurno));
            Iterable<T> adyacentes = elGrafo.getAdyacentesDeVertices(elGrafo.getVerticePorPosicion(posDeVerticeEnTurno));
            for(T adyacente : adyacentes){
                int posAdyacente = elGrafo.getPosicionDeVertice(adyacente);
                if(!controlMarcados.estaVerticeMarcado(posAdyacente)){
                    pilaDeVertices.push(posAdyacente);
                    controlMarcados.marcarVertice(posAdyacente);
                }
            }
        }while(!pilaDeVertices.isEmpty());
    }





    /*@Override
    public void ejecutarRecorrido(T verticeEnTurno) {
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
    }*/

}
