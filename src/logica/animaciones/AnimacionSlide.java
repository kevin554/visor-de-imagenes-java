package logica.animaciones;

import grafica.PanelImagen;

/**
 * La imagen aparece de derecha a izquierda
 */
public class AnimacionSlide extends Animacion {

    public AnimacionSlide() {
    }

    public AnimacionSlide(PanelImagen elPanel) {
        super(elPanel);
    }

    @Override
    public void comenzarAnimacion() {
        for (int i = elPanel.getWidth(); i > 0; i--) {
            colocarAnimacion();
            elPanel.cambiarUbicacionImagen(i, 0, laImagen.getAncho(), laImagen.getAlto());
        }
        detenerAnimacion();
    }
}