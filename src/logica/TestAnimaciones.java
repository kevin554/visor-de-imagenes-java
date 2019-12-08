package logica;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestAnimaciones {
    
    public static void main(String[] args) {                   
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            //TODO-CODE HERE
        }
        
        new grafica.Ventana();
    }
}