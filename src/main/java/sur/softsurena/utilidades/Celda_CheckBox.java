package sur.softsurena.utilidades;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class Celda_CheckBox extends DefaultCellEditor implements TableCellRenderer  {

    private final JComponent component = new JCheckBox();    
    private boolean value = false; 
    
    public Celda_CheckBox() {
        super( new JCheckBox() );
    }

    @Override
    public Object getCellEditorValue() {
        return ((JCheckBox) component).isSelected();        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, 
            boolean isSelected, int row, int column) {
        //Color de fondo en modo edicion
        //((JCheckBox) component).setBackground( new Color(37,45,223) );
        //obtiene valor de celda y coloca en el JCheckBox
        boolean b = ((Boolean) value);
        ( (JCheckBox) component).setSelected( b );
        return ( (JCheckBox) component);     
    }

    @Override
    public boolean stopCellEditing() {        
        value = ((Boolean) getCellEditorValue()) ;
        ((JCheckBox) component).setSelected( value );
        return super.stopCellEditing();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
         if (value == null)
            return null;
         return ( (JCheckBox) component );
    }
}

