/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoYaExiste;
import grafos.nopesados.*;
import grafos.pesados.Dijkstra;
import grafos.pesados.GrafoPesado;
import igu.Pantalla;

/**
 *
 * @author Daniel
 */
public class Proyecto {
    public static void main(String[] args) throws ExcepcionDatoYaExiste {    
        /*//PANTALLA
        Pantalla panta = new Pantalla();
        panta.setVisible(true);
        panta.setLocationRelativeTo(null); //ponele la pantalla sin referenciaAM*/
        GrafoPesado<String> grafo = new GrafoPesado<>();

        grafo.insertarVertice("M");
        grafo.insertarVertice("H");
        grafo.insertarVertice("T");
        grafo.insertarVertice("A");
        grafo.insertarVertice("E");
        grafo.insertarVertice("K");


        grafo.insertarArista("M","H",50);
        grafo.insertarArista("M","T",10);
        grafo.insertarArista("M","E",60);
        grafo.insertarArista("M","K",100);
        grafo.insertarArista("H","A",50);
        grafo.insertarArista("H","E",15);
        grafo.insertarArista("T","H",5);
        grafo.insertarArista("A","M",80);
        grafo.insertarArista("A","K",20);
        grafo.insertarArista("E","K",20);
        grafo.insertarArista("K","H",40);
        grafo.insertarArista("K","T",70);

        Dijkstra<String> algoritmoDijkstra = new Dijkstra<>(grafo,"M", "K");
        System.out.println("El camino es:" + algoritmoDijkstra.getCamino());
        System.out.println("El costo minimo del camino es: " + algoritmoDijkstra.getCostoMinimo());
        System.out.println("Lista predecesores:" + algoritmoDijkstra.getListaPredecesores());
        System.out.println("Lista de costos:" + algoritmoDijkstra.getListaCostos());

        //RecorridoGrafo<Integer> recorrido = new BFS<>(grafo, "M");
        //System.out.println("El recorrido BFS es: " + recorrido.getRecorrido());
        //System.out.println("Estan todos marcados: " + recorrido.seVisitoTodosLosVertices());
        //ORDENTAMIENTO TOPOLOGICO

        //System.out.println("Fuertemente conexo: " + grafo.esFuertementeConexo());
        //System.out.println("Debilmente conexo: " + grafo.esDebilmenteConexo());
        //System.out.println("El ordenamiento topologico es: " + grafo.ordenamientoTopologico());


        //Matriz de caminos



        /*MatrizGrafo<Integer> matrizDeAdyacencia = new MatrizGrafo<>(grafo);
        int[][] matriz = matrizDeAdyacencia.getMatrizDeAdyacencias();
        System.out.println("Matriz de caminos:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        matriz = matrizDeAdyacencia.getMatrizDeWarshall();;
        System.out.println("Matriz Warshall:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Cantidad de aristas: " + grafo.cantidadDeAristas());*/
    }
}
