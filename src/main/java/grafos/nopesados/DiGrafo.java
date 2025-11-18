package grafos.nopesados;

import grafos.excepciones.ExcepcionAristaYaExiste;

import java.util.*;

public class DiGrafo <T extends Comparable<T>> extends Grafo<T>{
    public DiGrafo(){
        super();
    }

    public DiGrafo(Iterable<T> vertices){
        super(vertices);
    }

    @Override
    public void insertarArista(T verticeOrigen, T verticeDestino) throws ExcepcionAristaYaExiste {
        if(super.existeAdyacencia(verticeOrigen, verticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }

        int posDeVerticeOrigen = super.getPosicionDeVertice(verticeOrigen);
        int posDeVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<Integer> adyacentesDelOrigen = listaDeAdyacencias.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(posDeVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public void eliminarArista(T verticeOrigen, T verticeDestino){
        if(super.existeAdyacencia(verticeOrigen, verticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }

        int posDeVerticeOrigen = super.getPosicionDeVertice(verticeOrigen);
        int posDeVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<Integer> adyacentesDelOrigen = listaDeAdyacencias.get(posDeVerticeOrigen);
        adyacentesDelOrigen.remove(posDeVerticeDestino);
    }

    public void eliminarVertice(T unVertice){

    }

    @Override
    public int cantidadDeAristas(){
        int cantidadDeAristas = 0;
        for (int i = 0; i < listaDeVertices.size(); i++){
            T vertice = this.getVerticePorPosicion(i);
            Iterable<T> adyacentes = this.getAdyacentesDeVertices(vertice);
            for(T adyacente : adyacentes){
                cantidadDeAristas++;
            }
        }
        return cantidadDeAristas;
    }

    @Override
    public int gradoDelVertice(T unVertice){
        throw new IllegalArgumentException("Metodo no soportado por DiGrafo");
    }

    public int gradoDeEntradaDelVertice(T unVertice){
        int cantidad = 0;
        int posicionVertice = getPosicionDeVertice(unVertice);
        for(int i = 0; i < listaDeVertices.size(); i++){
            Iterable<T> adyacentes = getAdyacentesDeVertices(getVerticePorPosicion(i));
            for(T adyacente : adyacentes){
                int posicionAdyacente = getPosicionDeVertice(adyacente);
                if(posicionAdyacente == posicionVertice){
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    public int gradoSalidaDelVertice(T unVertice){
        return super.gradoDelVertice(unVertice);
    }

    @Override
    public boolean esFuertementeConexo(){
        MatrizGrafo<T> matrizGrafo = new MatrizGrafo<>(this);
        int [][] matrizDeWarshall = matrizGrafo.getMatrizDeWarshall();
        int n = matrizDeWarshall.length;
        boolean banderaConexo = true;
        for (int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (matrizDeWarshall[i][j] == 0){
                    return false;
                }
            }
        }
        return banderaConexo;
    }

    @Override
    public boolean esDebilmenteConexo(){
        if(!esFuertementeConexo()){
            int posicionVerticeNoMarcado = 0;
            T vertice = getVerticePorPosicion(posicionVerticeNoMarcado);
            RecorridoGrafo<T> recorridoGrafo = new BFS<>(this,vertice);
            do{
                vertice = getVerticePorPosicion(posicionVerticeNoMarcado);
                recorridoGrafo.ejecutarRecorrido(vertice);
                if(recorridoGrafo.seVisitoTodosLosVertices()){
                    return true;
                }else{
                    vertice = verticeNoMarcadoAdyacenteSi(recorridoGrafo);
                    if(verticeNoMarcadoAdyacenteSi(recorridoGrafo) != null){
                        posicionVerticeNoMarcado = getPosicionDeVertice(vertice);
                    }
                }
            }while(vertice != null);
            return false;

        }else{
            return false;
        }
    }

    public T verticeNoMarcadoAdyacenteSi(RecorridoGrafo<T> recorridoGrafo){
        for (int j = 0; j < listaDeVertices.size(); j++){
            if(!recorridoGrafo.controlMarcados.estaVerticeMarcado(j)){
                Iterable<T> adyacentes = getAdyacentesDeVertices(getVerticePorPosicion(j));
                for(T adyacente : adyacentes) {
                    int posicionAdyacente = getPosicionDeVertice(adyacente);
                    if(recorridoGrafo.controlMarcados.estaVerticeMarcado(posicionAdyacente)){
                        return getVerticePorPosicion(j);
                    }
                }
            }
        }
        return null;
    }


    public boolean esACiclico(){
        MatrizGrafo<T> matriz = new MatrizGrafo<>(this);
        int[][] matrizDeWarshall = matriz.getMatrizDeWarshall();
        int n = matrizDeWarshall.length;
        for(int i = 0; i < n; i++){
            if(matrizDeWarshall[i][i] == 1){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<T> ordenamientoTopologico(){
            //Conexo
        if(this.esDebilmenteConexo() && this.esACiclico()){
            Queue<T> colaDeVertices = new LinkedList<>();
            List<T> listaDeOrdenamiento = new ArrayList<>();
            List<Integer> gradosDeVertices = new ArrayList<>();
            for(int i = 0; i < listaDeVertices.size(); i++){
                T verticeActual = getVerticePorPosicion(i);
                gradosDeVertices.add(gradoDeEntradaDelVertice(verticeActual));
                if(gradoDeEntradaDelVertice(verticeActual) == 0){
                    colaDeVertices.add(verticeActual);
                }
            }

            while(!colaDeVertices.isEmpty()){
                T verticeEnTurno = colaDeVertices.poll();
                listaDeOrdenamiento.add(verticeEnTurno);
                Iterable<T> adyacentes = getAdyacentesDeVertices(verticeEnTurno);
                for(T adyacente : adyacentes) {
                    int posicionAdyacente = getPosicionDeVertice(adyacente);
                    gradosDeVertices.set(posicionAdyacente, gradosDeVertices.get(posicionAdyacente) - 1);

                    if (gradosDeVertices.get(posicionAdyacente) == 0) {
                        colaDeVertices.add(adyacente);
                    }
                }
            }
            return listaDeOrdenamiento;
        }else{
            throw new IllegalArgumentException("No se puedes hacer ordenamiento topologico");
        }
    }
}
