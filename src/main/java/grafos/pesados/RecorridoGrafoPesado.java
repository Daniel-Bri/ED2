package grafos.pesados;

import grafos.nopesados.Grafo;
import grafos.utils.ControlMarcados;

import java.util.ArrayList;
import java.util.List;

public abstract class RecorridoGrafoPesado  <T extends Comparable<T>>{
    protected GrafoPesado<T> elGrafo;
    protected ControlMarcados controlMarcados;
    protected List<T> recorrido;

    public RecorridoGrafoPesado(GrafoPesado<T> unGrafo, T verticeInicial){
        elGrafo = unGrafo;
        controlMarcados = new ControlMarcados(elGrafo.cantidadDeVertices());
        recorrido = new ArrayList<>();
        ejecutarRecorrido(verticeInicial);
    }

    public abstract void ejecutarRecorrido(T verticeEnTurno);



    public List<T> getRecorrido(){
        return recorrido;
    }

    public boolean seVisitoElVertice(T elVertice){
        elGrafo.validarVertice(elVertice);
        int posDelVertice = elGrafo.getPosicionDeVertice(elVertice);
        return controlMarcados.estaVerticeMarcado(posDelVertice);
    }

    public boolean seVisitoTodosLosVertices(){
        return controlMarcados.estanTodosLosVerticesMarcados();
    }
}
