
// guarda su estado actual y calcula/aplica el proximo

import estados.Estado;

public class Celda {

    private Estado estadoActual;
    private Estado estadoNuevo;

    public Celda(Estado estado) {
        this.estadoActual = estado;
        this.estadoNuevo = estado;
    }

    // calcula el nuevo estado segun los vecinos vivos pero no lo aplica todavia
    public void calcularNuevoEstado(int vecinosVivos) {
        this.estadoNuevo = this.estadoActual.comprobarEstadoNuevo(vecinosVivos);
    }

    // aplica el nuevo estado y devuelve si hubo cambio
    public boolean actualizarEstado() {
        boolean huboCambio = this.estadoActual.getCaracter() != this.estadoNuevo.getCaracter();;
        this.estadoActual = this.estadoNuevo;
        return huboCambio;
    }
    //consulta si el estado actual se considera viva
    public boolean estaViva() {
        return this.estadoActual.esViva();
    }
    //le pide el simbolo al estado actual
    public char getCaracter() {
        return this.estadoActual.getCaracter();
    }
}
