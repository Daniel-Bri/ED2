package grafos.pesados;

public class MatrizGrafoPesados <T extends Comparable<T>>{
    protected GrafoPesado<T> grafo;
    protected  double[][] matrizDePesos;
    protected int n;

    public MatrizGrafoPesados(GrafoPesado<T> unGrafo){
        grafo = unGrafo;
        n = grafo.cantidadDeVertices();
        matrizDePesos = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrizDePesos[i][j] = 0;
            }

            Iterable<T> adyacentes = grafo.getAdyacentesDeVertices(grafo.getVerticePorPosicion(i));
            for (T adyacente : adyacentes){
                matrizDePesos[i][grafo.getPosicionDeVertice(adyacente)] = grafo.getPeso(unGrafo.getVerticePorPosicion(i), adyacente);
            }
        }
    }

    public double[][] getMatrizDePesos(){
        return matrizDePesos;
    }
}
