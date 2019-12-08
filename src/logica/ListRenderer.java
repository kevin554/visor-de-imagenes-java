package logica;

import java.awt.Component;
import java.awt.Font;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

public class ListRenderer extends DefaultListCellRenderer  {

    private Font fuente = new Font("segoe print", Font.BOLD, 12);
    private Map<String, ImageIcon> imageMap;

    public ListRenderer(Map<String, ImageIcon> imageMap) {
        this.imageMap = imageMap;
    }
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, 
            int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, 
                index, isSelected, cellHasFocus);
        label.setIcon(imageMap.get((String) value));
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(fuente);
        return label;
    }
}