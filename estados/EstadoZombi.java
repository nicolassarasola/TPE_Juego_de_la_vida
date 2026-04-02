package estados;

public class EstadoZombi extends Estado {

    @Override
    public char getCaracter() {
        // TODO Auto-generated method stub
       return 'z';
    }

    @Override
    public String getNombre() {
        // TODO Auto-generated method stub
        return "zombi";
    }

    @Override
    public boolean esViva() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Estado comprobarEstadoNuevo(int vecinosVivos) {
        // TODO Auto-generated method stub
        Estado aux= new EstadoZombi();
        
        if(vecinosVivos<1){
            aux= new EstadoMuerta();
        }

        return aux;
    }
    
}
