package logica.animaciones;

    public class FactoryAnimaciones {

    public static Animacion obtenerAnimacion(String tipoAnimacion) {
        if (tipoAnimacion.equals("Slide"))
            return new AnimacionSlide();
        
        if (tipoAnimacion.equals("Zoom"))
            return new AnimacionZoom();
        
        return null;
    }
}