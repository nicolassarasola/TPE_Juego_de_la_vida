package estados;
// celda muerta: revive con exactamente 3 vecinos vivos

public class EstadoMuerta extends Estado {

    @Override
    public Estado comprobarEstadoNuevo(int vecinosVivos) {
        if (vecinosVivos == 3) {
            return new EstadoViva();
        }
        return new EstadoMuerta();
    }

    @Override
    public boolean esViva() {
        return false;
    }

    @Override
    public char getCaracter() {
        return '.';
    }

    @Override
    public String getNombre() {
        return "Muerta";
    }
}