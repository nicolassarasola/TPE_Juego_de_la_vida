package estados;
// celda viva: sobrevive con 2 o 3 vecinos, muere en otro caso

public class EstadoViva extends Estado {

    @Override
    public Estado comprobarEstadoNuevo(int vecinosVivos) {
        if (vecinosVivos < 2 || vecinosVivos > 3) {
            return new EstadoMuerta();
        }
        // 25% de probabilidad de enfermarse
        if (Math.random() < 0.25) {
            return new EstadoEnferma();
        }
        return new EstadoViva();
    }

    @Override
    public boolean esViva() {
        return true;
    }

    @Override
    public char getCaracter() {
        return 'x';
    }

    @Override
    public String getNombre() {
        return "Viva";
    }
}