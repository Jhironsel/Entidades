package sur.softsurena.utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.table.*;

public class DefaultTableCellHeaderRenderer extends DefaultTableCellRenderer
        implements UIResource {

    private static final Long serialVersionUID = 1L;
    private Boolean horizontalTextPositionSet;
    private Icon sortArrow;
    private final EmptyIcon emptyIcon = new EmptyIcon();
    
    public DefaultTableCellHeaderRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void setHorizontalTextPosition(int textPosition) {
        horizontalTextPositionSet = true;
        super.setHorizontalTextPosition(textPosition);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Icon sortIcon = null;

        boolean isPaintingForPrint = false;

        if (table != null) {
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                Color fgColor = new Color(37,45,223);
                Color bgColor = new Color(204,204,204);
                if((row % 2) == 0){
                    bgColor = new Color(255,255,255);
                }
                
                setForeground(fgColor);
                setBackground(bgColor);
                
                if (hasFocus || isSelected) {
                    setForeground(bgColor);
                    setBackground(fgColor);
                }

                setFont(new java.awt.Font("Tahoma", Font.BOLD , 12));

                isPaintingForPrint = header.isPaintingForPrint();
            }

            if (!isPaintingForPrint && table.getRowSorter() != null) {
                if (!horizontalTextPositionSet) {
                    // There is a row sorter, and the developer hasn't
                    // set a text position, change to leading.
                    setHorizontalTextPosition(JLabel.LEFT);
                }
                SortOrder sortOrder = getColumnSortOrder(table, column);
                if (sortOrder != null) {
                    switch(sortOrder) {
                    case ASCENDING:
                        sortIcon = javax.swing.UIManager.getIcon("Table.ascendingSortIcon");
                        break;
                    case DESCENDING:
                        sortIcon = javax.swing.UIManager.getIcon("Table.descendingSortIcon");
                        break;
                    case UNSORTED:
                        sortIcon = javax.swing.UIManager.getIcon("Table.naturalSortIcon");
                        break;
                    }
                }
            }
        }

        setText(Objects.isNull(value) ? "" : value.toString());
        setIcon(sortIcon);
        sortArrow = sortIcon;

        Border border = null;
        if (hasFocus) {
            border = javax.swing.UIManager.getBorder("TableHeader.focusCellBorder");
        }
        if (border == null) {
            border = javax.swing.UIManager.getBorder("TableHeader.cellBorder");
        }
        setBorder(border);

        return this;
    }

    public static SortOrder getColumnSortOrder(JTable table, int column) {
        SortOrder rv = null;
        if (Objects.isNull(table) || Objects.isNull(table.getRowSorter()) ) {
            return rv;
        }
        java.util.List<? extends RowSorter.SortKey> sortKeys =
            table.getRowSorter().getSortKeys();
        if (sortKeys.size() > 0 && sortKeys.get(0).getColumn() ==
            table.convertColumnIndexToModel(column)) {
            rv = sortKeys.get(0).getSortOrder();
        }
        return rv;
    }

    @Override
    public void paintComponent(Graphics g) {
        boolean b = javax.swing.UIManager.getBoolean("TableHeader.rightAlignSortArrow", getLocale());
        if (b && sortArrow != null) {
            //emptyIcon is used so that if the text in the header is right
            //aligned, or if the column is too narrow, then the text will
            //be sized appropriately to make room for the icon that is about
            //to be painted manually here.
            emptyIcon.width = sortArrow.getIconWidth();
            emptyIcon.height = sortArrow.getIconHeight();
            setIcon(emptyIcon);
            super.paintComponent(g);
            Point position = computeIconPosition(g);
            sortArrow.paintIcon(this, g, position.x, position.y);
        } else {
            super.paintComponent(g);
        }
    }

    private Point computeIconPosition(Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        Rectangle viewR = new Rectangle();
        Rectangle textR = new Rectangle();
        Rectangle iconR = new Rectangle();
        Insets i = getInsets();
        viewR.x = i.left;
        viewR.y = i.top;
        viewR.width = getWidth() - (i.left + i.right);
        viewR.height = getHeight() - (i.top + i.bottom);
        SwingUtilities.layoutCompoundLabel(
            this,
            fontMetrics,
            getText(),
            sortArrow,
            getVerticalAlignment(),
            getHorizontalAlignment(),
            getVerticalTextPosition(),
            getHorizontalTextPosition(),
            viewR,
            iconR,
            textR,
            getIconTextGap());
        int x = getWidth() - i.right - sortArrow.getIconWidth();
        int y = iconR.y;
        return new Point(x, y);
    }

    private class EmptyIcon implements Icon, Serializable {
        int width = 0;
        int height = 0;
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {}
        @Override
        public int getIconWidth() { return width; }
        @Override
        public int getIconHeight() { return height; }
    }
}
