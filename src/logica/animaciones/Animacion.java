package logica.animaciones;

import grafica.PanelImagen;
import logica.Imagen;

/**
 * La animacion
 */
public abstract class Animacion implements Runnable {

    protected PanelImagen elPanel;
    protected Thread hilo;
    /**
     * La imagen sobre la cual aplicar las animaciones
     */
    protected Imagen laImagen;
    protected boolean pleaseStop = false;

    public Animacion() {
    }

    public Animacion(PanelImagen elPanel) {
        this.elPanel = elPanel;
    }
    
    /**
     * Comienza la animacion
     */
    public void animar() {
        pleaseStop = false;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        while (!pleaseStop)
            comenzarAnimacion();
    }
    
    public abstract void comenzarAnimacion();
    
    /**
     * Detiene la animacion
     */
    public void detenerAnimacion() {
        pleaseStop = true;
    }
    
    /**
     * Refrezca el panel
     */
    public void colocarAnimacion() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            //TODO-CODE HERE
        }
        elPanel.repaint();
    }

    public Imagen getLaImagen() {
        return laImagen;
    }
    
    public void setImagen(Imagen nuevaImagen) {
        this.laImagen = nuevaImagen;
    }

    public PanelImagen getElPanel() {
        return elPanel;
    }

    public void setElPanel(PanelImagen elPanel) {
        this.elPanel = elPanel;
    }
}