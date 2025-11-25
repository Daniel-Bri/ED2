/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoYaExiste;
import grafos.nopesados.*;
import grafos.pesados.*;
import igu.Pantalla;

import java.util.List;

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
        GrafoPesado<Integer> grafo = new GrafoPesado<>();
        grafo.insertarVertice(1);
        grafo.insertarVertice(2);
        grafo.insertarVertice(3);
        grafo.insertarVertice(4);
        grafo.insertarVertice(5);
        grafo.insertarVertice(6);
        grafo.insertarVertice(7);
        grafo.insertarVertice(8);
        grafo.insertarVertice(9);
        grafo.insertarVertice(10);


        grafo.insertarArista(1,2,5);
        grafo.insertarArista(1,4,8);
        grafo.insertarArista(1,3,10);
        grafo.insertarArista(2,6,5);
        grafo.insertarArista(2,4,6);
        grafo.insertarArista(3,4,7);
        grafo.insertarArista(3,5,8);
        grafo.insertarArista(3,8,15);
        grafo.insertarArista(4,6,11);
        grafo.insertarArista(4,5,5);
        grafo.insertarArista(5,7,4);
        grafo.insertarArista(5,8,3);
        grafo.insertarArista(6,7,9);
        grafo.insertarArista(6,9,7);
        grafo.insertarArista(7,8,12);
        grafo.insertarArista(7,9,4);
        grafo.insertarArista(7,10,6);
        grafo.insertarArista(8,10,12);
        grafo.insertarArista(9,10,7);

        /*grafo.insertarVertice("M");
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
        grafo.insertarArista("K","T",70);*/

        System.out.println("Hay ciclos: " + grafo.hayCiclos());
        //lista de kruskal
        Prim algoritmoDePrim = new Prim(grafo, 1);
        /*List<Arista>  listaPrim = algoritmoDeKruskal.getListaDeKruskal();
        for(int i = 0; i < listaKruskal.size(); i++){
            Arista arista = listaKruskal.get(i);
            int origen = arista.getOrigen();
            int destino = arista.getDestino();
            double peso = arista.getPeso();
            System.out.println(grafo.getVerticePorPosicion(origen) + " " + grafo.getVerticePorPosicion(destino) + " " + peso);

        }*/

        GrafoPesado<Integer> grafoPrim = algoritmoDePrim.getPrim();

        algoritmoDePrim.mostrarGrafoAuxReal();



        //matriZ DE GRAFOS PESADOS

        MatrizGrafoPesados<Integer> matrizGrafo1 = new MatrizGrafoPesados<>(grafo);
        double[][] matriz1 = matrizGrafo1.getMatrizDePesos();
        System.out.println("Matriz de caminos grafos original:");
        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz1[i].length; j++) {
                System.out.print(matriz1[i][j] + "  ,  ");
            }
            System.out.println();
        }

        MatrizGrafoPesados<Integer> matrizGrafo = new MatrizGrafoPesados<>(grafoPrim);
        double[][] matriz = matrizGrafo.getMatrizDePesos();
        System.out.println("Matriz de caminos de kruskal:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "  ,  ");
            }
            System.out.println();
        }

        /*Dijkstra<String> algoritmoDijkstra = new Dijkstra<>(grafo,"M", "K");
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
