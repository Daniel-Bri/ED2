package grafos.pesados;

import grafos.excepciones.ExcepcionAristaNoExiste;
import grafos.excepciones.ExcepcionAristaYaExiste;
import grafos.nopesados.Grafo;
import grafos.utils.ControlMarcados;

import java.util.*;

public class GrafoPesado <T extends Comparable<T>>{
    protected List<T> listaDeVertices;
    protected List<List<AdyacenteConPeso>> listaDeAdyacencias;
    protected static final int POS_DE_VERTICE_INVALIDO = -1;

    public GrafoPesado(){
        listaDeVertices = new ArrayList<>();
        listaDeAdyacencias = new ArrayList<>();
    }


    public GrafoPesado(Iterable<T> vertices){
        this();
        for(T unVertice : vertices){
            this.insertarVertice(unVertice);
        }
    }

    public int getPosicionDeVertice(T unVertice){
        for (int i = 0; i <listaDeVertices.size(); i++){
            T verticeEnTurno = listaDeVertices.get(i);
            if(verticeEnTurno.compareTo(unVertice) == 0){
                return i;
            }
        }
        return POS_DE_VERTICE_INVALIDO;
    }

    public boolean existeVertice(T unVertice){
        int pos = getPosicionDeVertice(unVertice);
        if(pos != POS_DE_VERTICE_INVALIDO){
            return true;
        }
        return false;

    }

    public void validarVertice(T unVertice){
        int posDelVertice = getPosicionDeVertice(unVertice);
        if(posDelVertice == POS_DE_VERTICE_INVALIDO){
            throw new IllegalArgumentException("El vertice "+ unVertice + " no existe en su grafo");
        }
    }

    public int cantidadDeVertices(){
        return listaDeVertices.size();
    }

    public void insertarVertice(T unVertice){
        int posDeVertice = getPosicionDeVertice(unVertice);
        if(posDeVertice != POS_DE_VERTICE_INVALIDO){
            throw new IllegalArgumentException("El vertice "+ unVertice + " yo existe en su grafo");
        }
        listaDeVertices.add(unVertice);
        listaDeAdyacencias.add(new ArrayList<>());
    }

    public Iterable<T> getVertices(){
        return listaDeVertices;
    }

    public Iterable<T> getAdyacentesDeVertices(T unVertice){
        validarVertice(unVertice);
        int posDelVertice = getPosicionDeVertice(unVertice);
        List<AdyacenteConPeso> adyacentesDelVertice = listaDeAdyacencias.get(posDelVertice);
        List<T> listaDeVerticesAdyacentes = new ArrayList<>();
        for (AdyacenteConPeso posDeAdyacente : adyacentesDelVertice){
            listaDeVerticesAdyacentes.add(listaDeVertices.get(posDeAdyacente.getIndiceVertice()));
        }
        return listaDeVerticesAdyacentes;
    }

    public boolean existeAdyacencia(T verticeOrigen, T verticeDestino){
        validarVertice(verticeOrigen);
        if(!existeVertice(verticeDestino)){
            return false;
        }
        int posDelVerticeOrigen = getPosicionDeVertice(verticeOrigen);
        int posDelVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<AdyacenteConPeso> adyacentesDelOrigen = listaDeAdyacencias.get(posDelVerticeOrigen);
        AdyacenteConPeso adyacenciaDestino = new AdyacenteConPeso(posDelVerticeDestino);
        return adyacentesDelOrigen.contains(adyacenciaDestino);
    }

    public int gradoDelVertice(T unVertice){
        validarVertice(unVertice);
        int posDelVertice = getPosicionDeVertice(unVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice =  this.listaDeAdyacencias.get(posDelVertice);
        return adyacenciasDelVertice.size();
    }

    public T getVerticePorPosicion(int posDeVertice){
        if(posDeVertice < 0 || posDeVertice >= (listaDeVertices.size())){
            throw new IllegalArgumentException("La posicion del vertice no es valida");

        }
        return listaDeVertices.get(posDeVertice);
    }

    public void insertarArista(T verticeOrigen, T verticeDestino, double peso) throws ExcepcionAristaYaExiste {

        if(existeAdyacencia(verticeOrigen,verticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }

        int posDeVerticeOrigen = getPosicionDeVertice(verticeOrigen);
        int posDeVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<AdyacenteConPeso> adyacentesDelOrigen = listaDeAdyacencias.get(posDeVerticeOrigen);
        adyacentesDelOrigen.add(new AdyacenteConPeso(posDeVerticeDestino, peso));
        Collections.sort(adyacentesDelOrigen);
        if(posDeVerticeDestino != posDeVerticeOrigen){
            List<AdyacenteConPeso> adyacentesDelDestino = listaDeAdyacencias.get(posDeVerticeDestino);
            adyacentesDelDestino.add(new AdyacenteConPeso(posDeVerticeOrigen, peso));
            Collections.sort(adyacentesDelDestino);
        }
    }

    public void eliminarVertice(T unVertice){ //hacer nosotros

    }

    public int cantidadDeAristas(){
        int cantidaDeAristas = 0;

        for (int i = 0; i < listaDeVertices.size(); i++){
            T vertice = getVerticePorPosicion(i);
            Iterable<T> adyacentes = this.getAdyacentesDeVertices(vertice);
            for (T adyacente : adyacentes){
                if(adyacente.compareTo(vertice)>=0) {
                    cantidaDeAristas++;
                }
            }
        }
        return cantidaDeAristas;
    }

    public void eliminarArista(T verticeOrigen, T verticeDestino) throws ExcepcionAristaNoExiste{
        if(!existeAdyacencia(verticeOrigen,verticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }

        int posDeVerticeOrigen = getPosicionDeVertice(verticeOrigen);
        int posDeVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<AdyacenteConPeso> adyacentesDelOrigen = listaDeAdyacencias.get(posDeVerticeOrigen);
        adyacentesDelOrigen.remove(new AdyacenteConPeso(posDeVerticeDestino));
        if(posDeVerticeDestino != posDeVerticeOrigen){
            List<AdyacenteConPeso> adyacentesDelDestino = listaDeAdyacencias.get(posDeVerticeDestino);
            AdyacenteConPeso adyacenciaOrigen = new AdyacenteConPeso(posDeVerticeOrigen);
            adyacentesDelDestino.remove(adyacenciaOrigen);
        }
    }

    public double getPeso(T verticeOrigen, T verticeDestino) throws ExcepcionAristaNoExiste{
        validarVertice(verticeDestino);
        validarVertice(verticeOrigen);
        if(!existeAdyacencia(verticeOrigen, verticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }

        int posDeVerticeOrigen = getPosicionDeVertice(verticeOrigen);
        int posDeVerticeDestino = getPosicionDeVertice(verticeDestino);
        List<AdyacenteConPeso> adyacentesDelOrigen = listaDeAdyacencias.get(posDeVerticeOrigen);
        AdyacenteConPeso adyacenciaDestino = new AdyacenteConPeso(posDeVerticeDestino);
        int indiceDeLaAdyacencia = adyacentesDelOrigen.indexOf(adyacenciaDestino);
        adyacenciaDestino = adyacentesDelOrigen.get(indiceDeLaAdyacencia);
        return adyacenciaDestino.getPeso();
    }

    public boolean hayCiclos(){
        GrafoPesado<T> grafoAux = new GrafoPesado<>();
        ControlMarcados controlMarcados = new ControlMarcados(this.cantidadDeVertices());
        Iterable<T> vertices = this.getVertices();
        for(T vertice : vertices){
            grafoAux.insertarVertice(vertice);
        }
        int posDeVertice = 0;
        do {
            T verticeEnTurno = grafoAux.getVerticePorPosicion(posDeVertice);

            this.validarVertice(verticeEnTurno);
            Stack<Integer> pilaDeVertices = new Stack<>();
            pilaDeVertices.push(posDeVertice);
            controlMarcados.marcarVertice(posDeVertice);
            do{
                int posDeVerticeAProcesar = pilaDeVertices.pop();
                //recorrido.add(elGrafo.getVerticePorPosicion(posDeVertice));
                Iterable<T> adyacentesDelVertice = this.getAdyacentesDeVertices(getVerticePorPosicion(posDeVerticeAProcesar));
                for (T adyacente : adyacentesDelVertice){
                    int posDeAdyacente = this.getPosicionDeVertice(adyacente);
                    if(!controlMarcados.estaVerticeMarcado(posDeAdyacente)){
                        pilaDeVertices.push(posDeAdyacente);
                        controlMarcados.marcarVertice(posDeAdyacente);
                        grafoAux.insertarArista(getVerticePorPosicion(posDeVerticeAProcesar),adyacente, getPeso(getVerticePorPosicion(posDeVerticeAProcesar),adyacente));
                    }else{
                        if(!grafoAux.existeAdyacencia(getVerticePorPosicion(posDeVerticeAProcesar),adyacente)){
                            return true;
                        }
                    }
                }
            }while(!pilaDeVertices.isEmpty());
            posDeVertice = posDeVerticeNoMarcado(grafoAux,controlMarcados);
        }while(posDeVertice != POS_DE_VERTICE_INVALIDO);
        return false;
    }

    public int posDeVerticeNoMarcado(GrafoPesado<T> grafoAux, ControlMarcados controlMarcados){
        for(int i = 0; i < grafoAux.cantidadDeVertices(); i++){
            if(!controlMarcados.estaVerticeMarcado(i)){
                return i;
            }
        }
        return POS_DE_VERTICE_INVALIDO;
    }

    public boolean esConexo(double[][] matrizDeWarshall){
        double n = matrizDeWarshall.length;
        boolean banderaConexo = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (matrizDeWarshall[i][j] == 0){
                    banderaConexo = false;
                }
            }
        }
        return banderaConexo;
    }
}
