package sur.softsurena.utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderCeldas extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Boolean b;
        JComponent jcbComponente = new JCheckBox();
        
        try {
            b = ((Boolean) value);
            ((JCheckBox) jcbComponente).setHorizontalAlignment(SwingConstants.CENTER);
            ((JCheckBox) jcbComponente).setOpaque(true);
            ((JCheckBox) jcbComponente).setSelected(b);
        } catch (java.lang.ClassCastException e) {
            b = null;
        }

        JLabel l = (JLabel) super.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);
        
        l.setFont(new Font("Tahoma", Font.BOLD, 14));
        l.setHorizontalAlignment(SwingConstants.LEFT);
        
//        try {
//            Double d = Double.valueOf(l.getText());
//            if (d != null) {
//                l.setHorizontalAlignment(SwingConstants.RIGHT);
//            }
//        } catch (NumberFormatException e) {
//            //Instalar Logger
//        }

        if (hasFocus) {
            l.setForeground(Color.white);
        } else {
            float[] cs = Color.RGBtoHSB(0, 112, 192, null);
            l.setForeground(Color.getHSBColor(cs[0], cs[1], cs[2]));
        }

        if ((row % 2) == 0) {
            l.setBackground(Color.white);
            ((JCheckBox) jcbComponente).setBackground(Color.white);
            
        } else {
            float[] cs = Color.RGBtoHSB(204, 204, 204, null);
            l.setBackground(Color.getHSBColor(cs[0], cs[1], cs[2]));
            ((JCheckBox) jcbComponente).setBackground(Color.getHSBColor(cs[0],
                    cs[1], cs[2]));
        }

        if (isSelected) {
            l.setForeground(Color.white);
            float[] cs = Color.RGBtoHSB(0, 112, 192, null);
            l.setBackground(Color.getHSBColor(cs[0], cs[1], cs[2]));
            ((JCheckBox) jcbComponente).setBackground(Color.getHSBColor(cs[0], cs[1], cs[2]));
        }

        if (b == null) {
            return l;
        } else {
            return ((JCheckBox) jcbComponente);
        }

    }
    
}
