package logica.efectos;

import grafica.PanelImagen;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import logica.Imagen;

public abstract class Efecto {
    
    /**
     * La imagen en matriz
     */
    protected Color arreglo[][];
    protected PanelImagen elPanel;
    protected Imagen laImagen;
    protected int alto;
    protected int ancho;
    
    public Efecto() {
    }
    
    public abstract void aplicarEfecto();
    
    /**
     * 
     * @param directorio
     * @return 
     */
    protected BufferedImage leerImagenDeDirectorio(String directorio) {
        BufferedImage bf = null;
        
        try {
            bf = ImageIO.read(new File(directorio));
        } catch (IOException e) {
            //HANDLE YOUR CODE here
        }
        
        return bf;
    }

    public Imagen getImagen() {
        return laImagen;
    }

    public void setImagen(Imagen laImagen) {
        this.laImagen = laImagen;
        this.alto = laImagen.getAlto();
        this.ancho = laImagen.getAncho();
        arreglo = new Color[ancho][alto];
    }

    public PanelImagen getElPanel() {
        return elPanel;
    }

    public void setElPanel(PanelImagen elPanel) {
        this.elPanel = elPanel;
    }
}