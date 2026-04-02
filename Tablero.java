import java.util.ArrayList;
import java.util.Random;

import estados.Estado;

// maneja la matriz, recorre celdas, cuenta vecinos, detecta cambios
public class Tablero {

    private Celda[][] celdas;
    private int filas;
    private int col;

    // constructor aleatorio
    public Tablero(int filas, int col,ArrayList<Estado>estados) {
        this.filas = filas;
        this.col = col;
        this.celdas = new Celda[filas][col];
        this.inicializarAleatoriamente(estados);
    }

    // constructor sin inicializacion aleatoria (para archivo o manual)
    public Tablero(int filas, int col) {
        this.filas = filas;
        this.col = col;
        this.celdas = new Celda[filas][col];

    }

    // inicializa el tablero con celdas aleatoriamente
    private void inicializarAleatoriamente(ArrayList <Estado>estados) {	
        Random random = new Random();
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.col; j++) {
                int indice = random.nextInt(estados.size());
                Estado estadoAleatorio = estados.get(indice);
                
                this.celdas[i][j] = new Celda(estadoAleatorio);
            }
        }
    }

    // ejecuta una generacion completa, devuelve si hubo cambios
    public boolean siguienteGeneracion() {
        // pasada 1: calcula el nuevo estado de cada celda
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.col; j++) {
                int vecinos = this.getCantidadVecinosVivos(i, j);
                this.celdas[i][j].calcularNuevoEstado(vecinos);
            }
        }
        // pasada 2: actualiza todas las celdas y detecta cambios
        boolean huboCambios = false;
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.col; j++) {
                if (this.celdas[i][j].actualizarEstado()) {
                    huboCambios = true;
                }
            }
        }
        return huboCambios;
    }

    // cuenta los vecinos vivos alrededor de una celda
    public int getCantidadVecinosVivos(int fila, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    int nuevaFila = fila + i;
                    int nuevaCol  = col + j;
                    if (posicionValida(nuevaFila, nuevaCol)) {
                        if (this.celdas[nuevaFila][nuevaCol].estaViva()) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean posicionValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < col;
    }

    // asigna una celda en una posicion especifica
    public void setCelda(int fila, int col, Celda celda) {
        this.celdas[fila][col] = celda;
    }

    
    public int getFilas() { 
        return this.filas;
    }

    public int getCol() {
        return this.col;
    }

    public Celda getCelda(int fila, int col) {
        return this.celdas[fila][col];
    }
}