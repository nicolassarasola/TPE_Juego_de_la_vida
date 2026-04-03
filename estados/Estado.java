package estados;


// define el comportamiento de cada estado posible de una celda
public abstract class Estado {
    //devuelve su simbolo
    public abstract char getCaracter();
    //devuelve su nombre
    public abstract String getNombre();
    //retorna true si se considera viva
    public abstract boolean esViva();
    //considera cual es su nuevo estado dependiendo de los vecinos vivos
    public abstract Estado comprobarEstadoNuevo(int vecinosVivos);


}
