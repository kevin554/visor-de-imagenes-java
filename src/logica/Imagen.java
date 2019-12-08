package logica;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase que representa a la imagen mostrada en pantalla
 */
public class Imagen {

    private String nombre;
    private String direccion;
    private int ancho;
    private int alto;

    public Imagen() {
    }

    public Imagen(ImageIcon imagen) {
        this.direccion = imagen.toString();
        this.ancho = imagen.getIconWidth();
        this.alto = imagen.getIconHeight();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
    
     /**
     * Redimensiona una imagen 
     * La imagen tendra el alto especificado por
     * parametro, el ancho se obtendra en base a la proporcion(ratio) de la
     * imagen original La proporcion se obtiene dividiendo el ancho entre el
     * alto
     *
     * @param archivo La ubicacion de la imagen a redimensionar
     * @param nuevoAlto el nuevo alto asignado a la imagen
     * @return La imagen redimensionada
     */
    public BufferedImage redimensionar(String archivo, int nuevoAlto) {
        BufferedImage bf = leerImagenDeDirectorio(archivo);

        double ratio = (double) ancho / (double) alto;
        int nuevoAncho = (int) (nuevoAlto * ratio);

        BufferedImage buffer = new BufferedImage(nuevoAncho, nuevoAlto, bf.getType());
        Graphics2D g = buffer.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bf, 0, 0, nuevoAncho, nuevoAlto, 0, 0, ancho, alto, null);
        g.dispose();

        return buffer;
    }
    
    private BufferedImage leerImagenDeDirectorio(String directorio) {
        BufferedImage bf = null;
        
        try {
            bf = ImageIO.read(new File(directorio));
            ancho = bf.getWidth();
            alto = bf.getHeight();
        } catch (IOException e) {
            //HANDLE YOUR CODE here
        }
        
        return bf;
    }
    
    /**
     * Guarda la imagen
     *
     * @param imagen La imagen a guardar
     * @param nombre
     */
    public void guardarImagenEnDirectorio(BufferedImage imagen, String nombre) {
        try {
            ImageIO.write(imagen, "jpg", new File(nombre));
        } catch (IOException ex) {
            //TODO-CODE HERE
        }
    }
}