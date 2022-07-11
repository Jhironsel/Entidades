package sur.softsurena.entidades;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class Render_CheckBox extends JCheckBox implements TableCellRenderer {

    private final JComponent component = new JCheckBox();

    public Render_CheckBox() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        //Color de fondo de la celda
//        ( (JCheckBox) component).setBackground( new Color(0,200,0) );
        ((JCheckBox) component).setHorizontalAlignment(SwingConstants.CENTER);
        //obtiene valor boolean y coloca valor en el JCheckBox
        boolean b = ((Boolean) value);
        ((JCheckBox) component).setSelected(b);


        return ((JCheckBox) component);
    }
}
