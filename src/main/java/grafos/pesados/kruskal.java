package grafos.pesados;

import grafos.nopesados.Grafo;
import grafos.utils.ControlMarcados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class kruskal<T extends Comparable<T>>{
    protected List<Arista> listaDeKruskal;
    protected GrafoPesado<T> grafo;
    protected GrafoPesado<T> grafoAux;
    protected ControlMarcados controlMarcados;

    public kruskal(GrafoPesado<T> unGrafo){
        grafo = unGrafo;
        grafoAux = new GrafoPesado<>();
        listaDeKruskal = new ArrayList<>();
        controlMarcados = new ControlMarcados(grafo.cantidadDeVertices());
        for (int i = 0; i < grafo.cantidadDeVertices(); i++){
            Iterable<T> adyacentes = grafo.getAdyacentesDeVertices(grafo.getVerticePorPosicion(i));
            controlMarcados.marcarVertice(i);
            for(T adyacente : adyacentes){
                if(!controlMarcados.estaVerticeMarcado(grafo.getPosicionDeVertice(adyacente))) {
                    Arista arista = new Arista(i, grafo.getPosicionDeVertice(adyacente), grafo.getPeso(grafo.getVerticePorPosicion(i), adyacente));
                    listaDeKruskal.add(arista);
                }
            }
        }
        Collections.sort(listaDeKruskal);
        Iterable<T> vertices = grafo.getVertices();
        for(T vertice : vertices){
            grafoAux.insertarVertice(vertice);
        }

        ejecutarAlgoritmoKruskal();
    }

    public void ejecutarAlgoritmoKruskal(){
        for(int i = 0; i < listaDeKruskal.size(); i++){
            Arista arista = listaDeKruskal.get(i);
            int origen = arista.getOrigen();
            int detino = arista.getDestino();
            double peso = arista.getPeso();
            grafoAux.insertarArista(grafo.getVerticePorPosicion(origen), grafo.getVerticePorPosicion(detino), peso);
            if(grafoAux.hayCiclos()){
                grafoAux.eliminarArista(grafoAux.getVerticePorPosicion(origen), grafo.getVerticePorPosicion(detino));
            }
        }
    }

    public List<Arista> getListaDeKruskal(){
        return listaDeKruskal;
    }

    public GrafoPesado<T> getArbolDeCostoMinimo(){
        return grafoAux;
    }

}
