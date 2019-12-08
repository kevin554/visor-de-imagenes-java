package logica.efectos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class BlancoNegro extends Efecto {

    private final int umbral = 100;

    public BlancoNegro() {
    }
    
    @Override
    public void aplicarEfecto() {
        BufferedImage bf = leerImagenDeDirectorio(laImagen.getDireccion());
        
        //La imagen a matriz
        for (int i = 0; i < ancho; i++) 
            for (int j = 0; j < alto; j++) 
                arreglo[i][j] = new Color(bf.getRGB(i, j));
        
        //La matriz a blanco y negro
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color pix = arreglo[i][j];
                int promedio = (pix.getRed() + pix.getGreen() + pix.getBlue()) / 3;
                if (promedio < umbral)
                    arreglo[i][j] = Color.BLACK;
                 else
                    arreglo[i][j] = Color.WHITE;
            }
        }
        
        //La matriz a imagen
        for (int i = 0; i < ancho; i++) 
            for (int j = 0; j < alto; j++) 
                bf.setRGB(i, j, arreglo[i][j].getRGB());
        
        
        elPanel.establecerImagen(new ImageIcon(bf));
    }
}