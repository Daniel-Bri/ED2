/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import com.danielolmos.proyecto.excepciones.adt.ExcepcionDatoYaExiste;
import grafos.nopesados.*;
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
        Grafo<Integer> grafo = new DiGrafo<>();

        grafo.insertarVertice(1);
        grafo.insertarVertice(2);
        grafo.insertarVertice(3);
        /*grafo.insertarVertice(4);
        grafo.insertarVertice(5);*/

        grafo.insertarArista(2,3);
        grafo.insertarArista(1,3);
        /*grafo.insertarArista(1,4);
        grafo.insertarArista(1,5);
        grafo.insertarArista(2,4);
        grafo.insertarArista(3,2);
        grafo.insertarArista(3,5);
        grafo.insertarArista(4,5);*/

        RecorridoGrafo<Integer> recorrido = new BFS<>(grafo, 1);
        System.out.println("El recorrido BFS es: " + recorrido.getRecorrido());
        System.out.println("Estan todos marcados: " + recorrido.seVisitoTodosLosVertices());
        //ORDENTAMIENTO TOPOLOGICO

        System.out.println("Fuertemente conexo: " + grafo.esFuertementeConexo());
        System.out.println("Debilmente conexo: " + grafo.esDebilmenteConexo());
        System.out.println("El ordenamiento topologico es: " + grafo.ordenamientoTopologico());


        //Matriz de caminos



        MatrizGrafo<Integer> matrizDeAdyacencia = new MatrizGrafo<>(grafo);
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
        System.out.println("Cantidad de aristas: " + grafo.cantidadDeAristas());
    }
}
