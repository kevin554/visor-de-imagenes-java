package logica.animaciones;

import grafica.PanelImagen;

/**
 * La imagen aparece desde la esquina superior izquierda con zoom
 * @author Usuario
 */
public class AnimacionZoom extends Animacion{

    /**
    El porcentaje de zoom aplicado a la imagen
    */
    private double porcentaje;
    /**
     * El ancho de la imagen aplicada con el porcentaje actual
     */
    private int ancho;
    /**
     * El alto de la imagen aplicada con el porcentaje actual
     */
    private int alto;

    public AnimacionZoom() {
    }
    
    public AnimacionZoom(PanelImagen elPanel) {
        super(elPanel);
    }

    @Override
    public void comenzarAnimacion() {
        for (int i = 100; i > 0; i--) {
            colocarAnimacion();
            setAncho((int) (laImagen.getAncho() * getPorcentaje()));
            setAlto((int) (laImagen.getAlto() * getPorcentaje()));
            elPanel.cambiarUbicacionImagen(0, 0, getAncho(), getAlto());
            setPorcentaje(getPorcentaje() + 0.01);
        }
        detenerAnimacion();
    }
    
    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}