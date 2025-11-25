package grafos.pesados;

import grafos.nopesados.Grafo;
import grafos.utils.ControlMarcados;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Prim <T extends Comparable<T>>{
    GrafoPesado<T> grafo;
    GrafoPesado<T> grafoAux;
    ControlMarcados controlMarcados;
    List<Arista> listaDeAdyacentes;

    public Prim(GrafoPesado<T> unGrafo, T verticeInicial){
        grafo = unGrafo;
        controlMarcados = new ControlMarcados(grafo.cantidadDeVertices());
        grafoAux = new GrafoPesado<>();
        listaDeAdyacentes = new LinkedList<>();
        ejecutarAlgoritmoDePrim(verticeInicial);
    }

    public void ejecutarAlgoritmoDePrim(T verticeInicial) {

        int posInicial = grafo.getPosicionDeVertice(verticeInicial);
        controlMarcados.marcarVertice(posInicial);
        grafoAux.insertarVertice(verticeInicial);

        List<Arista> candidatos = new LinkedList<>();

        // Agregar aristas iniciales
        agregarCandidatos(verticeInicial, candidatos);

        while (!controlMarcados.estanTodosLosVerticesMarcados()) {

            Collections.sort(candidatos);

            Arista mejor = candidatos.remove(0);

            // Si el destino ya está marcado, ignorar
            if (controlMarcados.estaVerticeMarcado(mejor.getDestino()))
                continue;

            // Tomar la arista
            T origen = grafo.getVerticePorPosicion(mejor.getOrigen());
            T destino = grafo.getVerticePorPosicion(mejor.getDestino());

            if (!grafoAux.existeVertice(destino))
                grafoAux.insertarVertice(destino);

            grafoAux.insertarArista(origen, destino, mejor.getPeso());

            controlMarcados.marcarVertice(mejor.getDestino());

            // Agregar nuevas aristas para este nuevo vértice
            agregarCandidatos(destino, candidatos);
        }
    }

    private void agregarCandidatos(T vertice, List<Arista> candidatos) {
        int pos = grafo.getPosicionDeVertice(vertice);
        Iterable<T> adyacentes = grafo.getAdyacentesDeVertices(vertice);

        for(T ady : adyacentes) {

            int destino = grafo.getPosicionDeVertice(ady);

            // Solo agregar aristas hacia vértices NO marcados
            if (!controlMarcados.estaVerticeMarcado(destino)) {
                double peso = grafo.getPeso(vertice, ady);
                candidatos.add(new Arista(pos, destino, peso));
            }
        }
    }

    public void mostrarGrafoAuxReal() {
        System.out.println("GRAFO AUX REAL (Prim):");
        for (int i = 0; i < grafoAux.cantidadDeVertices(); i++) {
            T verticeI = grafoAux.getVerticePorPosicion(i);
            for (int j = 0; j < grafoAux.cantidadDeVertices(); j++) {
                T verticeJ = grafoAux.getVerticePorPosicion(j);
                if (grafoAux.existeAdyacencia(verticeI, verticeJ)) {
                    double peso = grafoAux.getPeso(verticeI, verticeJ);
                    System.out.print(peso + "  ,  ");
                } else {
                    System.out.print("0.0  ,  ");
                }
            }
            System.out.println();
        }
    }

    public GrafoPesado<T> getPrim(){
        return grafoAux;
    }

}
