package estados;
// celda enferma: se considera viva, muere en la siguiente generacion si o si
// una celda viva tiene 25% de probabilidad de enfermarse

public class EstadoEnferma extends Estado {

    @Override
    public Estado comprobarEstadoNuevo(int vecinosVivos) {
        // una celda enferma siempre muere en la siguiente generacion
        return new EstadoMuerta();
    }

    @Override
    public boolean esViva() {
        return true;
    }

    @Override
    public char getCaracter() {
        return 'e';
    }

    @Override
    public String getNombre() {
        return "Enferma";
    }
}