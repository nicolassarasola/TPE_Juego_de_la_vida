package estados;


// define el comportamiento de cada estado posible de una celda
public abstract class Estado {

    public abstract char getCaracter();
    public abstract String getNombre();
    public abstract boolean esViva();
    public abstract Estado comprobarEstadoNuevo(int vecinosVivos);


}