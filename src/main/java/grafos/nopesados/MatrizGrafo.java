package grafos.nopesados;

public class MatrizGrafo <T extends Comparable<T>> {
    protected Grafo<T> grafo;
    protected int [][] matrizDeAdyacencias;
    protected int n;
    public MatrizGrafo(Grafo<T> unGrafo){
        grafo = unGrafo;
        n = grafo.cantidadDeVertices();
        matrizDeAdyacencias = new int[n][n];
        for (int i = 0 ; i < n; i++){
            T verticeEnTurno = grafo.getVerticePorPosicion(i);
            for(int j = 0; j < n ; j++){
                matrizDeAdyacencias[i][j] = 0;
            }
            Iterable<T> adyacentes = grafo.getAdyacentesDeVertices(verticeEnTurno);
            for (T adyacente : adyacentes){
                int posDeAdyacente = grafo.getPosicionDeVertice(adyacente);
                matrizDeAdyacencias[i][posDeAdyacente] = 1;
            }
        }
    }

    public int [][] getMatrizDeAdyacencias(){
        return matrizDeAdyacencias;
    }

    public int[][] getMatrizDeWarshall(){
        int [][] matrizDeWarshall = matrizDeAdyacencias;
        for (int k = 0; k <= n-1; k++){
            for(int i = 0; i<= n-1; i++){
                for (int j = 0; j <= n-1; j++){
                    if((matrizDeWarshall[i][j])==1 || (matrizDeWarshall[i][k] == 1 && matrizDeWarshall[k][j] == 1)){
                        matrizDeWarshall[i][j] = 1;
                    }
                }
            }
        }
        return matrizDeWarshall;
    }

}
