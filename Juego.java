import java.util.ArrayList;

import estados.Estado;
import estados.EstadoEnferma;
import estados.EstadoMuerta;
import estados.EstadoViva;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// crea un tablero y le da ordenes, gestiona el pasar de generaciones
public class Juego {

    private ArrayList<Estado> posiblesEstados;
    private ArrayList<Estado> estadosDisponiblesAgregables;
    private ArrayList<String> filasIngresadas;
    private Tablero tablero;
    private CLI cli= new CLI();

    // constructor vacio para modo manual
    public Juego() {
        this.inicializarEstados();
        this.filasIngresadas = new ArrayList<>();
    }


    // inicializa las dos listas de estados
    private void inicializarEstados() {
        this.posiblesEstados = new ArrayList<>();
        posiblesEstados.add(new EstadoViva());
        posiblesEstados.add(new EstadoMuerta());

        this.estadosDisponiblesAgregables = new ArrayList<>();
        estadosDisponiblesAgregables.add(new EstadoEnferma());
    }

    // agrega un estado por caracter si existe y no esta ya agregado
    public boolean addEstado(char caracter) {
        caracter = Character.toLowerCase(caracter);

        for (Estado e : this.estadosDisponiblesAgregables) {
            if (e.getCaracter() == caracter) {
                if (!contiene(e)) {
                    posiblesEstados.add(e);
                    return true;
                }
            }
        }
        return false;
    }

    // verifica si un estado ya esta en la lista activa
    private boolean contiene(Estado nuevoEstado) {
        for (Estado e : this.posiblesEstados) {
            if (e.getCaracter() == nuevoEstado.getCaracter()) {
                return true;
            }
        }
        return false;
    }

    // guarda una fila ingresada manualmente
    public void addFila(String texto) {
        this.filasIngresadas.add(texto);
    }

    // construye el tablero con las filas ingresadas manualmente
    public void construirTablero(int filas, int col) {
        this.tablero = new Tablero(filas, col);

        for (int i = 0; i < filas; i++) {
            String linea = this.filasIngresadas.get(i);

            for (int j = 0; j < col; j++) {
                char c = linea.charAt(j);
                Estado estado = this.crearEstadoDesdeChar(c);
                this.tablero.setCelda(i, j, new Celda(estado));
            }
        }
    }

    // construccion aleatoria
    public void construirTableroAleatorio(int filas, int col) {
        this.tablero = new Tablero(filas, col, posiblesEstados);
    }

    // carga el tablero desde un archivo
    public void cargarDesdeArchivo(String ruta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));

        String primeraLinea = br.readLine();
        if (primeraLinea == null) {
            br.close();
            throw new IOException("Archivo vacio.");
        }

        String[] partes = primeraLinea.trim().split("\\s+");
        int filas = Integer.parseInt(partes[0]);
        int col = Integer.parseInt(partes[1]);

        this.filasIngresadas.clear();

        // leer filas del tablero
        for (int i = 0; i < filas; i++) {
            String linea = br.readLine();

            if (linea == null) {
                br.close();
                throw new IOException("Archivo incompleto.");
            }

            if (linea.length() != col) {
                br.close();
                throw new IOException("Fila con longitud incorrecta en linea " + (i + 1));
            }

            this.addFila(linea);
        }

        br.close();

        this.construirTablero(filas, col);
    }

    // crea un estado a partir de un caracter
    public Estado crearEstadoDesdeChar(char c) {
        c = Character.toLowerCase(c);

        // 1. buscar en estados activos
        for (Estado e : this.posiblesEstados) {
            if (e.getCaracter() == c) {
                return e;
            }
        }

        // 2. buscar en estados agregables
        for (Estado e : this.estadosDisponiblesAgregables) {
            if (e.getCaracter() == c) {

                if (!contiene(e)) {
                    this.posiblesEstados.add(e);
                }

                return e;
            }
        }

        // 3. si no existe, tomar como muerta
        return new EstadoMuerta();
    }

    // devuelve estados disponibles para que CLI los muestre
    public ArrayList<Estado> getEstadosDisponibles() {
        return new ArrayList<>(estadosDisponiblesAgregables);
    }

    // devuelve todos los estados activos
    public ArrayList<Estado> getTodosLosEstados() {
        return new ArrayList<>(posiblesEstados);
    }

    public void ejecutarGeneraciones(int n, int intervalo){
        int i = 0;
        boolean huboCambios=true;

        while (huboCambios && (n < 0 || i < n)){
                cli.showTexto("-- Generacion " + (i + 1) + " --");
                cli.showTablero(this.getTablero());
                huboCambios = tablero.siguienteGeneracion();;

                this.esperar(intervalo);
                
                i++;
                
        }
    }


    // pausa entre generaciones
    public void esperar(int intervalo) {
        try {
            Thread.sleep(intervalo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // devuelve el tablero para que CLI lo muestre
    public Tablero getTablero() {
        return this.tablero;
    }

    public void addEstadoNuevo (Estado e){
        this.estadosDisponiblesAgregables.add(e);
    }
}