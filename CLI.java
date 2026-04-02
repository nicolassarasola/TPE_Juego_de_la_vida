// pide, recibe y muestra datos
import java.io.BufferedReader;
import java.io.InputStreamReader;

import estados.Estado;
import estados.EstadoLatente;
import estados.EstadoZombi;

public class CLI {

    private BufferedReader br;

    public CLI() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.iniciar();
    }

    public void iniciar() {

        // paso 1: elegir modo de inicializacion
        this.showTexto("Inicializar tablero:");
        this.showTexto("1. Desde archivo");
        this.showTexto("2. Manual");
        this.showTexto("3. Aleatorio");

        int modoInicio = this.getEntero();

        Juego juego = new Juego();

        /////////////////////////////////////////////////
        // estados ingresables nuevos 
        Estado latente = new EstadoLatente();
        juego.addEstadoNuevo(latente);
        
        Estado zombi= new EstadoZombi();
        juego.addEstadoNuevo(zombi);

        ///////////////////////////////////////////////

        if (modoInicio == 1) {
            // desde archivo
            this.showTexto("Ingrese ruta del archivo: ");
            String ruta = this.getString();
            try {
                juego.cargarDesdeArchivo(ruta);
            } catch (Exception e) {
                this.showTexto("Error al leer el archivo: " + e.getMessage());
                return;
            }
        } else if (modoInicio == 2) {
            // manual

            // pedir filas y columnas
            int filas = 0;
            int col = 0;

            //lee fila y col con formato "fila col"
            while(filas<=0|| col <= 0) {
                	this.showTexto("Ingrese cantidad de filas y columnas separadas por espacio: ");
                     try {
                    	 String linea = br.readLine(); // Ejemplo: 10 20

                    // 1 dividir el string donde haya espacios
                	 	String[] partes = linea.split(" ");

                    // 2 convertir cada parte a int usando su indice
                   
	                         filas = Integer.parseInt(partes[0]);
	                         col = Integer.parseInt(partes[1]);
                    }
                    catch(Exception e){
                    	System.out.println("error de ingreso de datos");
                    }
                     if (filas <= 0 || col <= 0) {
                    	 this.showTexto("Los valores deben ser mayores a 0.");
                     }
            }

            // ingresar filas hasta completar
            this.showTexto("Ingrese cada fila:");
            int filaActual = 0;
            while (filaActual < filas) {
                String linea = this.getString();
                if (linea.length() != col) {
                    this.showTexto("Error: la fila debe tener " + col + " caracteres.");
                } else {
                    juego.addFila(linea);
                    filaActual++;
                }
            }
            juego.construirTablero(filas, col);


        } else{

         // aleatorio: pide filas y columnas

             this.seleccionarEstados(juego);
            // mostrar simbolos disponibles
            this.showTexto("Simbolos disponibles:");
            for (Estado e : juego.getTodosLosEstados()) {
                this.showTexto(e.getCaracter() + " - " + e.getNombre());
            }
            
            int filas = 0;
            int col = 0;
            
            while (filas <= 0 || col <= 0) {
                this.showTexto("Ingrese cantidad de filas: ");
                filas = this.getEntero();
                this.showTexto("Ingrese cantidad de columnas: ");
                col = this.getEntero();
                if (filas <= 0 || col <= 0) {
                    this.showTexto("Los valores deben ser mayores a 0.");
                }
            }

            juego.construirTableroAleatorio(filas, col);

        }



        // paso 2: modo de ejecucion
        this.showTexto("Modo de ejecucion:");
            this.showTexto("1. N generaciones");
            this.showTexto("2. Indefinidamente");
            int opcion = this.getEntero();

        this.showTexto("Ingrese intervalo entre generaciones (ms): ");
            int intervalo = this.getEntero();

        if (opcion == 1) {
            this.showTexto("Ingrese cantidad de generaciones: ");
            int n = this.getEntero();
            
            juego.ejecutarGeneraciones(n,intervalo);

        } else {
            int n = -1;
            juego.ejecutarGeneraciones(n,intervalo);

            this.showTexto("Sin cambios. Fin.");    
        }
    }

    // permite al usuario agregar estados extra al juego
    public void seleccionarEstados(Juego juego) {
        this.showTexto("Desea agregar estados extra? (0 para continuar)");
        for (Estado e : juego.getEstadosDisponibles()) {
            this.showTexto(e.getCaracter() + " - " + e.getNombre());
        }
        String input = "";
        while (!input.equals("0")) {
            this.showTexto("Seleccione (0 para continuar): ");
            input = this.getString();
            if (!input.equals("0")) {
                boolean agregado = juego.addEstado(input.charAt(0));
                if (!agregado) {
                    this.showTexto("Estado invalido o ya agregado.");
                } else {
                    this.showTexto("Estado agregado correctamente.");
                }
            }
        }
    }

    // muestra el tablero por consola
    public void showTablero(Tablero tablero) {
        for (int i = 0; i < tablero.getFilas(); i++) {
            String fila = "";
            for (int j = 0; j < tablero.getCol(); j++) {
                fila += tablero.getCelda(i, j).getCaracter();
            }
            this.showTexto(fila);
        }
    }

    public int getEntero(){
        while (true) {
            try {
                return Integer.parseInt(br.readLine());
            } catch (Exception e) {
                this.showTexto("Dato invalido, ingrese de nuevo.");
            }
        }
    }

    // lee una linea de texto
    public String getString() {
        try {
            return br.readLine();
        } catch (Exception e) {
            return "";
        }
    }
    //muestra un texto
    public void showTexto(String texto) {
        System.out.println(texto);
    }
}