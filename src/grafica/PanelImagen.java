package grafica;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logica.animaciones.Animacion;
import logica.Imagen;
import logica.efectos.Efecto;

public class PanelImagen extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private Animacion laAnimacion;
    private Efecto elEfecto;
    private Imagen laImagen;
    private JLabel lbImagen;
    private double zoom = 1;
    private int xImagen, yImagen;
    private int x, y;
    
    public PanelImagen() {
    }

    @Override
    public Dimension getPreferredSize() {
        if (laImagen != null)
            return new Dimension(laImagen.getAncho(), laImagen.getAlto());
        
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D configuracionGrafica = (Graphics2D) g;
        configuracionGrafica.scale(zoom, zoom);
    }

    public void establecerAnimacion(Animacion nuevaAnimacion) {
        nuevaAnimacion.setImagen(laImagen);
        nuevaAnimacion.setElPanel(this);
        
        if (laAnimacion != null) 
            laAnimacion.detenerAnimacion();
        
        this.laAnimacion = nuevaAnimacion;
        this.laAnimacion.animar();
    }

    public void establecerEfecto(Efecto nuevoEfecto) {
        nuevoEfecto.setImagen(laImagen);
        nuevoEfecto.setElPanel(this);
        
        this.elEfecto = nuevoEfecto;
        this.elEfecto.aplicarEfecto();
    }
    
    public void establecerImagen(ImageIcon laImagen) {
        if (lbImagen != null)
            remove(lbImagen);

        lbImagen = new JLabel(laImagen);

        add(lbImagen);
        lbImagen.setBounds(0, 0, laImagen.getIconWidth(), laImagen.getIconHeight());
    }
    
    public boolean tieneImagenCargada() {
        return laImagen != null;
    }
    
    /**
     * Muestra la imagen en pantalla
     */
    public void mostrarImagen() {
        if (this.tieneImagenCargada()) {
            add(lbImagen);
            lbImagen.setBounds(0, 0, laImagen.getAlto(), laImagen.getAncho());
            
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.addMouseWheelListener(this);
        } else {
            JOptionPane.showMessageDialog(null, "Cargá una imagen antes!");
        }
    }

    /**
     * Cambia la posicion y el tamaño de la imagen mostrada en pantalla
     * @param x El punto x
     * @param y El punto y
     * @param ancho El ancho de la Imagen
     * @param alto El alto de la imagen
     */
    public void cambiarUbicacionImagen(int x, int y, int ancho, int alto) {
        lbImagen.setBounds(x, y, ancho, alto);
    }

    /**
     * Cambia la posicion de la imagen mostrada en pantalla
     * @param x
     * @param y 
     */
    public void cambiarUbicacionImagen(int x, int y) {
        lbImagen.setLocation(x, y);
    }
    
    /**
     * Restaura la imagen a su tamaño original
     */
    public void restablecerZoom() {
        zoom = 1;
        this.repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Obtenemos el origen (x, y) de la imagen
        xImagen = lbImagen.getX();
        yImagen = lbImagen.getY();

        //Obtenemos la ubicacion (x, y) del click
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getClickCount() == 2) {
            cambiarUbicacionImagen(0, 0);
            restablecerZoom();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        cambiarUbicacionImagen(e.getX() - x + xImagen, e.getY() - y + yImagen);

        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getPreciseWheelRotation() > 0) 
            zoom -= e.getPreciseWheelRotation() * 0.01;
         else 
            zoom -= e.getPreciseWheelRotation() * 0.01;

        this.repaint();
    }

    public Imagen getImagen() {
        return laImagen;
    }

    public void setImagen(Imagen nuevaImagen) {
        this.laImagen = nuevaImagen;
        
        if (lbImagen != null)
            remove(lbImagen);
        
        lbImagen = new JLabel(new ImageIcon(laImagen.getDireccion()));
    }

    public Efecto getEfecto() {
        return elEfecto;
    }

    public void setEfecto(Efecto elEfecto) {
        this.elEfecto = elEfecto;
    }
}