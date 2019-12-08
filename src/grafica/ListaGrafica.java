package grafica;

import EDD.Lista;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import logica.Imagen;
import logica.ListRenderer;

public class ListaGrafica extends JPanel implements ListSelectionListener {

    private JScrollPane scroll;
    private JList lista;
    private Lista<Imagen> imagenes;
    private Map<String, ImageIcon> imageMap;
    private PanelImagen elPanel;
    private Ventana laVentana;
    
    public ListaGrafica() {
        this.setLayout(new BorderLayout());
        lista = new JList();
        imagenes = new Lista<>();
        leerFichero();
        actualizarLista();
        
        imageMap = crearImageMap(imagenes);
        lista.setCellRenderer(new ListRenderer(imageMap));
        scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(150, this.getHeight()));
        this.add(scroll, BorderLayout.CENTER);
        
        lista.addListSelectionListener(this);
    }

    private boolean laImagenExiste(Imagen imagen) {
        return new File(imagen.getDireccion()).exists();
    }
    
    private void ofrecerEliminarImagen(int posicion) {
        int opcion = JOptionPane.showOptionDialog(null, 
                "La ruta hace referencia a una imagen inexistente "
                        + "\n Desea eliminar la ruta de la lista?",
                "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,
                null, null, JOptionPane.YES_OPTION);
        
        switch (opcion) {
            case JOptionPane.YES_OPTION:
                new File("src\\miniaturas\\" + imagenes.obtener(posicion).getNombre()).delete();
                imagenes.eliminar(posicion);
                this.actualizarLista();
                this.escribirFichero();
                break;
            case JOptionPane.NO_OPTION:
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
            default:
                break;
        }
    }
    
    /**
     * Actualiza la lista con cada imagen cargada al programa
     */
    public void actualizarLista() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        
        for (Imagen img : imagenes) {
            modelo.addElement(img.getNombre());
        }
        
        lista.setModel(modelo);
    }
    
    private void leerFichero() {
        BufferedReader reader;
        
        try {
            String line;
            reader = new BufferedReader(new FileReader("imagenes.txt"));
            Gson gson = new Gson();
            while ((line = reader.readLine()) != null) {
                Imagen img = gson.fromJson(line, Imagen.class);
                imagenes.agregar(img);
            }
        } catch (JsonSyntaxException | IOException e) {
            //TODO-CODE HERE
        }
    }
    
    private void escribirFichero() {
        BufferedWriter writer = null;
        
        try {
            File file = new File("imagenes.txt");
            FileWriter fw = new FileWriter(file, false);
            writer = new BufferedWriter(fw);
            Gson gson = new Gson();
            for (Imagen img : imagenes) {
                writer.write(gson.toJson(img));
                writer.newLine();
            }
        } catch (IOException e) {
            //TODO-CODE HERE
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException ex) {
                    //TODO-CODE HERE
                }
        }
    } 
    
    private Map<String, ImageIcon> crearImageMap(Lista<Imagen> lista) {
        Map<String, ImageIcon> map = new HashMap<>();
        for (Imagen img : lista) 
            map.put(img.getNombre(), new ImageIcon(
                    getClass().getResource("/miniaturas/" + img.getNombre())));
        return map;
    }
    
    /**
     * Verifica que la nueva image cargada no esté previamente cargada en el 
     * programa
     * @param direccionImagen La direccion a comparar de la nueva imagen 
     * @return Verdadero si la imagen ya existe en el programa
     */
    public boolean esImagenRepetida(String direccionImagen) {
        for (Imagen img : imagenes) {
            if (direccionImagen.equals(img.getDireccion()))
                return true;
        }
        
        return false;
    }
    
    /**
     * Guarda la imagen en la lista y crea su miniatura
     * @param laImagen La imagen a guardar
     */
    public void guardarImagen(Imagen laImagen) {
        imagenes.agregar(laImagen);
        laImagen.guardarImagenEnDirectorio(
                laImagen.redimensionar(laImagen.getDireccion(), 35)
                , "src\\miniaturas\\" + laImagen.getNombre());
        escribirFichero();
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (lista.getSelectedIndex() != -1) {
            int indice = lista.getSelectedIndex();
            Imagen imagen = imagenes.obtener(indice);
            
            if (!laImagenExiste(imagen)) 
                ofrecerEliminarImagen(indice);
            
            elPanel.setImagen(imagen);
            elPanel.mostrarImagen();
            laVentana.pack();
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(scroll.getPreferredSize().width, 
                scroll.getPreferredSize().height);
    }

    public Lista<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Lista<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public Ventana getLaVentana() {
        return laVentana;
    }

    public void setLaVentana(Ventana laVentana) {
        this.laVentana = laVentana;
    }

    public PanelImagen getElPanel() {
        return elPanel;
    }

    public void setElPanel(PanelImagen elPanel) {
        this.elPanel = elPanel;
    }
}