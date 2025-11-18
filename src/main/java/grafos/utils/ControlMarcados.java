package grafos.utils;

import java.util.ArrayList;
import java.util.List;

public class ControlMarcados {
    private List<Boolean> marcados;
    public ControlMarcados(int nroDeVertices){
       if(nroDeVertices < 0 ){
           throw new IllegalArgumentException("Cantidad de vertices invalidos");
       }
       marcados = new ArrayList<>();
       for (int i = 0; i < nroDeVertices; i++){
           marcados.add(Boolean.FALSE);
       }
    }

    public void marcarVertice(int posDeVertice){
        marcados.set(posDeVertice, Boolean.TRUE);
    }

    public void desmarcarVertice(int posDeVertice){
        marcados.set(posDeVertice,Boolean.FALSE);
    }

    public boolean estaVerticeMarcado(int posDeVertice){
       return marcados.get(posDeVertice);
    }

    public void desmarcarTodosLosVertices(){
        for (int i = 0; i < marcados.size(); i++){
            this.desmarcarVertice(i);
        }
    }

    public boolean estanTodosLosVerticesMarcados(){
        return !marcados.contains(Boolean.FALSE);
    }


}
