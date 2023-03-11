package sur.softsurena.entidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import rojerusan.RSTableMetro1;

public class Render_CheckBox extends JCheckBox implements TableCellRenderer {

    private final JComponent component = new JCheckBox();

    public Render_CheckBox() {
        setOpaque(true);
    }

    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        //Color de fondo de la celda
        
        if((row % 2) == 0 ){
            ((JCheckBox) component).setBackground(new Color(255,255,255));
        }else{
            ((JCheckBox) component).setBackground(new Color(204,204,204));
        }
        
        if(isSelected)
            ((JCheckBox) component).setBackground(new Color(37,45,223));
        
        ((RSTableMetro1) table).setShowHorizontalLines(true);
        
        ((JCheckBox) component).setHorizontalAlignment(SwingConstants.CENTER);
        
        //obtiene valor boolean y coloca valor en el JCheckBox
        boolean b = ((Boolean) value);
        ((JCheckBox) component).setSelected(b);


        return ((JCheckBox) component);
    }
}
