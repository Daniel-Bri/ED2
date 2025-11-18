package grafos.excepciones;

public class ExcepcionAristaYaExiste extends RuntimeException {
    public ExcepcionAristaYaExiste(String message) {
        super(message);
    }

    public ExcepcionAristaYaExiste(){
        super("Arista indicada ya existe en su grafo");
    }
}
