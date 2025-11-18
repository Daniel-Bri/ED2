package igu;

import com.danielolmos.proyecto.adt.ArbolMViasBusqueda;
import com.danielolmos.proyecto.adt.NodoMVias;
import java.awt.*;
import javax.swing.*;

public class PanelArbolMVias<T extends Comparable<T>> extends JPanel {
    private ArbolMViasBusqueda<T> arbol;
    private final int ANCHO_RECTANGULO = 60;
    private final int ALTO_RECTANGULO = 35;
    private final int VERTICAL_SPACING = 90;
    private final int HORIZONTAL_BASE = 120;
    private final int RADIO_PUNTO = 4;
    private final int PADDING_DATOS = 5;
    
    // Variables para dimensiones
    private int anchoTotal = 1200;
    private int altoTotal = 800;

    public PanelArbolMVias(ArbolMViasBusqueda<T> arbol) {
        this.arbol = arbol;
        setBackground(Color.WHITE);
        // Tamaño inicial razonable
        setPreferredSize(new Dimension(anchoTotal, altoTotal));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (arbol == null) {
            dibujarMensaje(g2d, "Árbol es null");
            return;
        }

        if (arbol.esArbolVacio()) {
            dibujarMensaje(g2d, "Árbol vacío");
            return;
        }

        NodoMVias<T> raiz = arbol.getRaiz();
        if (raiz == null) {
            dibujarMensaje(g2d, "No se pudo acceder a la raíz");
            return;
        }

        // Calcular dimensiones necesarias
        calcularDimensiones(raiz);
        
        // Dibujar el árbol centrado
        int xCentro = anchoTotal / 2;
        dibujarNodoMVias(g2d, raiz, xCentro, 60, HORIZONTAL_BASE);
    }

    private void calcularDimensiones(NodoMVias<T> raiz) {
        // Calcular altura para estimar dimensiones
        int altura = calcularAltura(raiz);
        int orden = arbol.orden;
        
        // Calcular dimensiones estimadas
        anchoTotal = (int) (Math.pow(orden, Math.min(altura, 4)) * (ANCHO_RECTANGULO * orden + HORIZONTAL_BASE));
        altoTotal = altura * VERTICAL_SPACING + 200;
        
        // Asegurar mínimos y máximos razonables
        anchoTotal = Math.max(1200, Math.min(anchoTotal, 5000));
        altoTotal = Math.max(800, Math.min(altoTotal, 3000));
        
        // 🔧 ACTUALIZAR TAMAÑO Y FORZAR SCROLL
        Dimension nuevoTamano = new Dimension(anchoTotal, altoTotal);
        if (!nuevoTamano.equals(getPreferredSize())) {
            setPreferredSize(nuevoTamano);
            // Forzar actualización del scroll
            SwingUtilities.invokeLater(() -> {
                revalidate();
                if (getParent() != null) {
                    getParent().revalidate();
                }
            });
        }
    }

    private void dibujarMensaje(Graphics2D g, String mensaje) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(mensaje);
        g.drawString(mensaje, (anchoTotal - textWidth) / 2, altoTotal / 2);
    }

    private int calcularXHijo(int xPadre, int xOffset, int indiceHijo, int cantidadSlots) {
    // cantidadSlots = orden - 1
    int totalHijos = cantidadSlots + 1;

    if (totalHijos == 1) return xPadre;

    // Distribuir los hijos equidistantemente entre -xOffset y +xOffset
    double paso = (2.0 * xOffset) / (totalHijos - 1);
    double desplazamiento = -xOffset + indiceHijo * paso;

    return (int) (xPadre + desplazamiento);
}

private void dibujarNodoMVias(Graphics2D g, NodoMVias<T> nodo, int x, int y, int xOffset) {
    if (NodoMVias.esNodoVacio(nodo)) return;

    int orden = arbol.orden;
    int cantidadSlots = orden - 1; 

    int anchoTotalNodo = ANCHO_RECTANGULO * cantidadSlots + PADDING_DATOS * (cantidadSlots - 1);
    int xInicio = x - anchoTotalNodo / 2;

    // 🔧 Espaciado horizontal adaptativo
    int nivelActual = (y - 60) / VERTICAL_SPACING;
    double factorReduccion = Math.pow(0.75, nivelActual);
    int nuevoOffset = (int) Math.max(ANCHO_RECTANGULO * 3.5, xOffset * factorReduccion);

    // 🔧 Dibujar las conexiones hacia los hijos
    for (int i = 0; i < orden; i++) {
        if (!nodo.esHijoVacio(i)) {
            int xHijo = calcularXHijo(x, nuevoOffset, i, cantidadSlots);
            int yHijo = y + VERTICAL_SPACING;

            int xOrigen = calcularXOrigenConexion(xInicio, i, cantidadSlots);

            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(1.3f));
            g.drawLine(xOrigen, y + ALTO_RECTANGULO, xHijo, yHijo);

            g.setColor(Color.RED);
            g.fillOval(xOrigen - RADIO_PUNTO / 2, y + ALTO_RECTANGULO - RADIO_PUNTO / 2,
                    RADIO_PUNTO, RADIO_PUNTO);

            // Recursión al hijo
            dibujarNodoMVias(g, nodo.getHijo(i), xHijo, yHijo, nuevoOffset);
        }
    }

    dibujarDatosNodo(g, nodo, xInicio, y, cantidadSlots);
}

    private int calcularXOrigenConexion(int xInicio, int indiceHijo, int cantidadSlots) {
        if (cantidadSlots == 0) return xInicio + ANCHO_RECTANGULO / 2;
        
        if (indiceHijo == 0) {
            return xInicio - PADDING_DATOS;
        } else if (indiceHijo == cantidadSlots) {
            return xInicio + cantidadSlots * (ANCHO_RECTANGULO + PADDING_DATOS) - PADDING_DATOS;
        } else {
            return xInicio + indiceHijo * (ANCHO_RECTANGULO + PADDING_DATOS) - PADDING_DATOS / 2;
        }
    }

    private void dibujarDatosNodo(Graphics2D g, NodoMVias<T> nodo, int xInicio, int y, int cantidadSlots) {
        // Fondo del nodo completo
        g.setColor(new Color(240, 240, 240, 120));
        g.fillRect(xInicio - PADDING_DATOS, y - PADDING_DATOS,
                  cantidadSlots * (ANCHO_RECTANGULO + PADDING_DATOS) + PADDING_DATOS,
                  ALTO_RECTANGULO + 2 * PADDING_DATOS);

        // 🔧 DIBUJAR TODOS LOS SLOTS, INCLUYENDO LOS VACÍOS
        for (int i = 0; i < cantidadSlots; i++) {
            int xRect = xInicio + i * (ANCHO_RECTANGULO + PADDING_DATOS);
            T dato = nodo.getDato(i);
            boolean esVacio = nodo.esDatoVacio(i);

            // Color diferente para slots vacíos vs llenos
            if (esVacio) {
                g.setColor(new Color(240, 240, 240)); // Gris claro para vacíos
            } else {
                g.setColor(new Color(200, 230, 255)); // Azul claro para llenos
            }
            
            g.fillRect(xRect, y, ANCHO_RECTANGULO, ALTO_RECTANGULO);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(1.5f));
            g.drawRect(xRect, y, ANCHO_RECTANGULO, ALTO_RECTANGULO);

            // Texto - mostrar "vacío" o el dato real
            String texto;
            if (esVacio) {
                texto = "vacío";
                g.setColor(Color.GRAY);
            } else {
                texto = obtenerTextoNodo(dato);
                g.setColor(Color.BLACK);
            }
            
            g.setFont(new Font("Arial", Font.BOLD, esVacio ? 9 : 11));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(texto);
            int textHeight = fm.getAscent();
            g.drawString(texto, xRect + (ANCHO_RECTANGULO - textWidth) / 2,
                        y + (ALTO_RECTANGULO + textHeight) / 2 - 2);

            // 🔧 MOSTRAR ÍNDICE DEL SLOT (pequeño, arriba)
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial", Font.PLAIN, 8));
            g.drawString(String.valueOf(i), xRect + 2, y + 10);
        }

        // Borde del nodo completo
        g.setColor(new Color(100, 100, 100, 100));
        g.setStroke(new BasicStroke(1f));
        g.drawRect(xInicio - PADDING_DATOS, y - PADDING_DATOS,
                  cantidadSlots * (ANCHO_RECTANGULO + PADDING_DATOS) + PADDING_DATOS,
                  ALTO_RECTANGULO + 2 * PADDING_DATOS);

        // 🔧 MOSTRAR INFORMACIÓN DEL ORDEN DEL NODO (opcional)
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.ITALIC, 9));
        String infoOrden = "Orden: " + arbol.orden;
        FontMetrics fm = g.getFontMetrics();
        int infoWidth = fm.stringWidth(infoOrden);
        g.drawString(infoOrden, xInicio - PADDING_DATOS, y - 10);
    }

    private String obtenerTextoNodo(T dato) {
        if (dato == null) return "null";
        try {
            // Intentar con getId()
            java.lang.reflect.Method metodoGetId = dato.getClass().getMethod("getId");
            Object id = metodoGetId.invoke(dato);
            return String.valueOf(id);
        } catch (Exception e) {
            // Intentar con métodos alternativos
            try {
                java.lang.reflect.Method metodoGetIdPrestamo = dato.getClass().getMethod("getIdPrestamo");
                Object id = metodoGetIdPrestamo.invoke(dato);
                return "P-" + String.valueOf(id);
            } catch (Exception e2) {
                // Buscar cualquier método que empiece con "getId"
                for (java.lang.reflect.Method metodo : dato.getClass().getMethods()) {
                    if (metodo.getName().startsWith("getId") && metodo.getParameterCount() == 0) {
                        try {
                            Object id = metodo.invoke(dato);
                            if (id != null) {
                                return "ID: " + String.valueOf(id);
                            }
                        } catch (Exception e3) {
                            // Continuar con el siguiente método
                        }
                    }
                }
                // Fallback a toString()
                String str = dato.toString();
                if (str.contains("@")) {
                    String[] partes = str.split("@");
                    if (partes.length > 1) {
                        return "ID: " + partes[1];
                    }
                }
                return str.length() > 6 ? str.substring(0, 6) : str;
            }
        }
    }

    private int calcularAltura(NodoMVias<T> nodo) {
        if (NodoMVias.esNodoVacio(nodo)) return 0;
        int maxAltura = 0;
        for (int i = 0; i < arbol.orden; i++) {
            if (!nodo.esHijoVacio(i)) {
                maxAltura = Math.max(maxAltura, calcularAltura(nodo.getHijo(i)));
            }
        }
        return 1 + maxAltura;
    }

    // 🔧 MÉTODO PARA FORZAR ACTUALIZACIÓN DEL SCROLL
    public void actualizarScroll() {
        revalidate();
        if (getParent() != null) {
            getParent().revalidate();
        }
    }
}