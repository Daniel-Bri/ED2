package grafos.pesados;

import grafos.nopesados.Grafo;
import grafos.nopesados.RecorridoGrafo;

import java.util.Stack;

public class DFS
{
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
                int posDeVerticeAProcesar = pilaDeVertices.pop();
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
