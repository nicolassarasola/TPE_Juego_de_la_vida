package estados;
// celda latente: se considera muerta, revive con exactamente 1 vecino vivo

public class EstadoLatente extends Estado {

    @Override
    public Estado comprobarEstadoNuevo(int vecinosVivos) {
        if (vecinosVivos == 1) {
            return new EstadoViva();
        }
        return new EstadoLatente();
    }

    @Override
    public boolean esViva() {
        return false;
    }

    @Override
    public char getCaracter() {
        return 'l';
    }

    @Override
    public String getNombre() {
        return "Latente";
    }
}