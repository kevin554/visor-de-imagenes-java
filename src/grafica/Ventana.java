package grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import logica.animaciones.FactoryAnimaciones;
import logica.Imagen;
import logica.efectos.FactoryEfectos;

public class Ventana extends JFrame {

    private PanelImagen elPanel;
    private ListaGrafica listaGrafica;

    public Ventana() {
        init();
    }

    private void init() {
        elPanel = new PanelImagen();
        this.add(elPanel, BorderLayout.CENTER);
        
        listaGrafica = new ListaGrafica();
        listaGrafica.setElPanel(elPanel);
        listaGrafica.setLaVentana(this);
        this.add(listaGrafica, BorderLayout.WEST);
        
        JMenuBar barra = new JMenuBar();
        JMenu mnArchivo = new JMenu("Archivo");
        JMenu mnAnimaciones = new JMenu("Animaciones");
        JMenu mnEfectos = new JMenu("Efectos");
        JMenuItem itCargar = new JMenuItem("Cargar");
        JMenuItem itSalir = new JMenuItem("Salir");
        JMenuItem itSlide = new JMenuItem("Slide");
        JMenuItem itZoom = new JMenuItem("Zoom");
        JMenuItem itBlancoNegro = new JMenuItem("Blanco y Negro");
        
        setTitle("Slideshow");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        setJMenuBar(barra);
        barra.add(mnArchivo);
        mnArchivo.add(itCargar);
        mnArchivo.addSeparator();
        mnArchivo.add(itSalir);
        barra.add(mnAnimaciones);
        mnAnimaciones.add(itSlide);
        mnAnimaciones.add(itZoom);
        barra.add(mnEfectos);
        mnEfectos.add(itBlancoNegro);
        
        //Creamos un atajo para un item
        itCargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                InputEvent.CTRL_MASK));
        
        itCargar.addActionListener(this::cargarImagen);
        itSalir.addActionListener(this::salir);
        itSlide.addActionListener(this::aplicarAnimacion);
        itZoom.addActionListener(this::aplicarAnimacion);
        itBlancoNegro.addActionListener(this::aplicarEfecto);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir(null);
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Carga una imagen al programa y su respectiva miniatura
     * @param e
     */
    public void cargarImagen(ActionEvent e) {        
        File fichero;
        JFileChooser cuadro = new JFileChooser();
        //Filtro para nuestro FileChooser
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Extensiones de Imagenes", "jpg", "png", "gif", "jpeg");
        //Asignamos el filtro a nuestro FileChooser
        cuadro.setFileFilter(filtro);
        int comparar = cuadro.showOpenDialog(this);

        if (comparar == JFileChooser.APPROVE_OPTION) {
            fichero = cuadro.getSelectedFile();
            Imagen nuevaImagen = new Imagen(new ImageIcon(fichero.toString()));
            
            if (listaGrafica.esImagenRepetida(nuevaImagen.getDireccion())) {
                JOptionPane.showMessageDialog(null, "La imagen ya está cargada");
                return;
            }
            
            nuevaImagen.setNombre(fichero.getName());
            listaGrafica.guardarImagen(nuevaImagen);
            
            elPanel.setImagen(nuevaImagen);
            elPanel.mostrarImagen();
            pack();
            listaGrafica.actualizarLista();
        }
        
        if (comparar == JFileChooser.CANCEL_OPTION) {
            
        }
    }

    /**
     * Aplica una animacion
     * @param e 
     */
    public void aplicarAnimacion(ActionEvent e) {
        String tipoAnimacion = e.getActionCommand();
        
        if (elPanel.tieneImagenCargada())
            elPanel.establecerAnimacion(
                    FactoryAnimaciones.obtenerAnimacion(tipoAnimacion));
         else 
            JOptionPane.showMessageDialog(null, "Cargá una imagen antes!");
    }
    
    /**
     * Aplica un efecto
     * @param e 
     */
    public void aplicarEfecto(ActionEvent e) {
        String tipoEfecto = e.getActionCommand();
        
        if (elPanel.tieneImagenCargada())
            elPanel.establecerEfecto(FactoryEfectos.obtenerEfecto(tipoEfecto));
        else
            JOptionPane.showMessageDialog(null, "Visualiza una imagen antes!");
    }
    
    /**
     * Sale del programa
     * @param e 
     */
    public void salir(ActionEvent e) {
        System.exit(0);
    }
}
