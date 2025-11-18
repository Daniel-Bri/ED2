package igu;

import com.danielolmos.proyecto.adt.ArbolBinarioBusqueda;
import com.danielolmos.proyecto.adt.NodoBinario;
import java.awt.*;
import javax.swing.*;

public class PanelArbol<T extends Comparable<T>> extends JPanel {
    private ArbolBinarioBusqueda<T> arbol;
    private final int RADIO = 25;
    private final int VERTICAL = 70;
    private final int HORIZONTAL_MIN = 30;

    // variables para límites
    private int minX, maxX, maxY;

    public PanelArbol(ArbolBinarioBusqueda<T> arbol) {
        this.arbol = arbol;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (arbol == null || arbol.esArbolVacio()) {
            dibujarMensaje(g, (arbol == null ? "Árbol es null" : "Árbol vacío"));
            return;
        }

        NodoBinario<T> raiz = obtenerRaiz();
        if (raiz == null) {
            dibujarMensaje(g, "No se pudo acceder a la raíz");
            return;
        }

        // Reiniciar límites
        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        maxY = 0;

        int altura = calcularAltura(raiz);
        int xCentro = getWidth() / 2;
        
        // 🔧 CÁLCULO MEJORADO DEL ESPACIADO
        int xOffset = calcularOffsetDinamico(raiz, altura);

        // Dibujar recursivamente
        dibujarNodo(g, raiz, xCentro, 60, xOffset);

        // 🔧 CALCULAR Y ACTUALIZAR DIMENSIONES
        if (minX != Integer.MAX_VALUE && maxX != Integer.MIN_VALUE) {
            int margen = 100;
            int anchoCalculado = Math.max(800, maxX - minX + 4 * RADIO + margen);
            int altoCalculado = Math.max(600, maxY + margen);
            
            Dimension nuevaDimension = new Dimension(anchoCalculado, altoCalculado);
            
            if (!nuevaDimension.equals(getPreferredSize())) {
                setPreferredSize(nuevaDimension);
                // Forzar actualización del scroll
                SwingUtilities.invokeLater(() -> {
                    revalidate();
                    if (getParent() != null) {
                        getParent().revalidate();
                    }
                });
            }
        }
    }

    // 🔧 NUEVO MÉTODO: Cálculo dinámico del offset basado en la estructura del árbol
    private int calcularOffsetDinamico(NodoBinario<T> nodo, int altura) {
        if (NodoBinario.esNodoVacio(nodo)) return 80;
        
        // Base del cálculo
        int baseOffset = Math.max(80, getWidth() / 4);
        
        // 🔧 AJUSTE POR ALTURA: A mayor altura, menos espacio para evitar sobreposición
        double factorAltura = 1.0;
        if (altura > 6) {
            factorAltura = 0.4; // Reducir significativamente para árboles muy altos
        } else if (altura > 4) {
            factorAltura = 0.6;
        } else if (altura > 3) {
            factorAltura = 0.8;
        }
        
        // 🔧 AJUSTE POR ANCHURA DEL ÁRBOL: Considerar el ancho máximo en el nivel inferior
        int anchoMaximoNivel = calcularAnchoMaximoNivel(nodo, altura);
        double factorAncho = Math.min(1.0, 800.0 / (anchoMaximoNivel * RADIO * 2));
        
        int offsetCalculado = (int) (baseOffset * factorAltura * factorAncho);
        
        return Math.max(HORIZONTAL_MIN, offsetCalculado);
    }

    // 🔧 NUEVO MÉTODO: Calcular el ancho máximo aproximado en el nivel más bajo
    private int calcularAnchoMaximoNivel(NodoBinario<T> nodo, int altura) {
        if (altura <= 1) return 1;
        
        // El ancho máximo en el nivel inferior es aproximadamente 2^(altura-1)
        int nodosUltimoNivel = (int) Math.pow(2, altura - 1);
        
        // Reducir si el árbol no está completo
        int nodosReales = contarNodosUltimoNivel(nodo, altura, 1);
        if (nodosReales < nodosUltimoNivel) {
            // Ajustar basado en la cantidad real de nodos
            return Math.max(1, nodosReales);
        }
        
        return nodosUltimoNivel;
    }

    // 🔧 NUEVO MÉTODO: Contar nodos en el último nivel
    private int contarNodosUltimoNivel(NodoBinario<T> nodo, int alturaObjetivo, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodo)) return 0;
        
        if (nivelActual == alturaObjetivo) {
            return 1;
        }
        
        return contarNodosUltimoNivel(nodo.getHijoIzquierdo(), alturaObjetivo, nivelActual + 1) +
               contarNodosUltimoNivel(nodo.getHijoDerecho(), alturaObjetivo, nivelActual + 1);
    }

    private int calcularOffsetOptimo(int altura) {
        int baseOffset = Math.max(80, getWidth() / 4);
        
        // 🔧 TABLA MÁS AGRESIVA DE REDUCCIÓN
        if (altura <= 3) {
            return baseOffset;
        } else if (altura == 4) {
            return baseOffset * 3 / 4;
        } else if (altura == 5) {
            return baseOffset / 2;
        } else if (altura == 6) {
            return baseOffset / 3;
        } else {
            return baseOffset / 4; // Muy reducido para árboles de más de 6 niveles
        }
    }

    private NodoBinario<T> obtenerRaiz() {
        try {
            java.lang.reflect.Field field = ArbolBinarioBusqueda.class.getDeclaredField("raiz");
            field.setAccessible(true);
            return (NodoBinario<T>) field.get(arbol);
        } catch (Exception e) {
            System.out.println("Error accediendo a raíz: " + e.getMessage());
            return null;
        }
    }

    private void dibujarMensaje(Graphics g, String mensaje) {
        g.setColor(Color.RED);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(mensaje);
        g.drawString(mensaje, (getWidth() - textWidth) / 2, getHeight() / 2);
    }

    private void dibujarNodo(Graphics g, NodoBinario<T> nodo, int x, int y, int xOffset) {
        if (NodoBinario.esNodoVacio(nodo)) return;

        // Actualizar límites
        minX = Math.min(minX, x - RADIO);
        maxX = Math.max(maxX, x + RADIO);
        maxY = Math.max(maxY, y + RADIO);

        // 🔧 CÁLCULO MEJORADO DE REDUCCIÓN: Más agresivo en niveles profundos
        int nivel = calcularNivel(nodo);
        double factorReduccion = calcularFactorReduccion(nivel);
        int nuevoOffset = Math.max(HORIZONTAL_MIN, (int)(xOffset * factorReduccion));
        
        int yHijo = y + VERTICAL;

        // Conexión hijo izquierdo
        if (!nodo.esVacioHijoIzquierdo()) {
            int xHijoIzq = x - xOffset;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, xHijoIzq, yHijo);
            dibujarNodo(g, nodo.getHijoIzquierdo(), xHijoIzq, yHijo, nuevoOffset);
        }

        // Conexión hijo derecho
        if (!nodo.esVacioHijoDerecho()) {
            int xHijoDer = x + xOffset;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, xHijoDer, yHijo);
            dibujarNodo(g, nodo.getHijoDerecho(), xHijoDer, yHijo, nuevoOffset);
        }

        // Nodo (círculo)
        g.setColor(new Color(135, 206, 235));
        g.fillOval(x - RADIO, y - RADIO, 2 * RADIO, 2 * RADIO);
        g.setColor(Color.BLACK);
        g.drawOval(x - RADIO, y - RADIO, 2 * RADIO, 2 * RADIO);

        // Texto dentro del nodo
        String texto = obtenerTextoNodo(nodo.getDato());
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(texto);
        int textHeight = fm.getAscent();
        g.drawString(texto, x - textWidth / 2, y + textHeight / 4);
    }

    // 🔧 NUEVO MÉTODO: Calcular nivel del nodo
    private int calcularNivel(NodoBinario<T> nodo) {
        if (NodoBinario.esNodoVacio(nodo)) return 0;
        return 1 + Math.max(calcularNivel(nodo.getHijoIzquierdo()), 
                           calcularNivel(nodo.getHijoDerecho()));
    }

    // 🔧 NUEVO MÉTODO: Factor de reducción basado en el nivel
    private double calcularFactorReduccion(int nivel) {
        // Reducción más agresiva a mayor profundidad
        if (nivel <= 2) return 0.6;      // Niveles 1-2: 40% reducción
        else if (nivel <= 4) return 0.5; // Niveles 3-4: 50% reducción  
        else if (nivel <= 6) return 0.4; // Niveles 5-6: 60% reducción
        else return 0.3;                 // Niveles 7+: 70% reducción
    }

    private String obtenerTextoNodo(T dato) {
        if (dato == null) return "null";
        try {
            java.lang.reflect.Method metodoGetId = dato.getClass().getMethod("getId");
            Object id = metodoGetId.invoke(dato);
            return String.valueOf(id);
        } catch (Exception e) {
            try {
                java.lang.reflect.Method metodoGetIdPrestamo = dato.getClass().getMethod("getIdPrestamo");
                Object id = metodoGetIdPrestamo.invoke(dato);
                return "P-" + String.valueOf(id);
            } catch (Exception e2) {
                for (java.lang.reflect.Method metodo : dato.getClass().getMethods()) {
                    if (metodo.getName().startsWith("getId") && metodo.getParameterCount() == 0) {
                        try {
                            Object id = metodo.invoke(dato);
                            if (id != null) {
                                return String.valueOf(id);
                            }
                        } catch (Exception e3) {
                            continue;
                        }
                    }
                }
                String str = dato.toString();
                if (str.contains("@")) {
                    String[] partes = str.split("@");
                    if (partes.length > 1) {
                        return partes[1];
                    }
                }
                return str.length() > 6 ? str.substring(0, 6) : str;
            }
        }
    }

    private int calcularAltura(NodoBinario<T> nodo) {
        if (NodoBinario.esNodoVacio(nodo)) return 0;
        return 1 + Math.max(calcularAltura(nodo.getHijoIzquierdo()), 
                           calcularAltura(nodo.getHijoDerecho()));
    }

    // 🔧 MÉTODO PARA FORZAR ACTUALIZACIÓN DEL SCROLL
    public void actualizarScroll() {
        revalidate();
        if (getParent() != null) {
            getParent().revalidate();
        }
    }
}