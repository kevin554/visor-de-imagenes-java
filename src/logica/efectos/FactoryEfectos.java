package logica.efectos;

public class FactoryEfectos {
    
    public static Efecto obtenerEfecto(String tipoEfecto) {
        if (tipoEfecto.equals("Blanco y Negro"))
            return new BlancoNegro();
        
        return null;
    }
}