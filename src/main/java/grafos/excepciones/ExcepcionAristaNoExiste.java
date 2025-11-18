package grafos.excepciones;

public class ExcepcionAristaNoExiste extends RuntimeException {
    public ExcepcionAristaNoExiste(String message) {
        super(message);
    }

  public ExcepcionAristaNoExiste(){
    super("Arista indicada no existe en su grafo");
  }
}
