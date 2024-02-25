package sur.softsurena.utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderCeldasLeft extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel l = (JLabel) super.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);

        l.setFont(new Font("Tahoma", Font.BOLD, 14));
        l.setHorizontalAlignment(SwingConstants.LEFT);
        
        if (hasFocus) {
            l.setForeground(Color.white);
        } else {
            float[] cs = Color.RGBtoHSB(0, 112, 192, null);
            l.setForeground(Color.getHSBColor(cs[0], cs[1], cs[2]));
        }

        if ((row % 2) == 0) {
            l.setBackground(Color.white);
            
        } else {
            float[] cs = Color.RGBtoHSB(204, 204, 204, null);
            l.setBackground(Color.getHSBColor(cs[0], cs[1], cs[2]));
        }

        if (isSelected) {
            l.setForeground(Color.white);
            float[] cs = Color.RGBtoHSB(0, 112, 192, null);
            l.setBackground(Color.getHSBColor(cs[0], cs[1], cs[2]));
        }

        return l;
    }

}
