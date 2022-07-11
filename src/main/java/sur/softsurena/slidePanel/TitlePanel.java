package sur.softsurena.slidePanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {

    int width = 0;
    private Color startColor = Color.WHITE;//new Color(238, 238, 238);
    private Color endColor = Color.GRAY;//new Color(255, 255, 255);
    GeneralPath path;
    Color accentColor = new Color(0x80ffffff);
    Color textColor = new Color(0x0f0f0f);
    private Image imageIcon;
    private String title = "Test";
    private Container parent;
    private boolean isToggleIcon;
    
    public TitlePanel(String title, Image imageIcon, int width) {
        super();
        this.width = width;
        this.imageIcon = imageIcon;
        this.title = title;
        MouseListener mouseListener = new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                getParentNode((Container) e.getSource());
                if (parent != null) {
                    parent.dispatchEvent(e);
                }
            }

            public void mouseEntered(MouseEvent e) {
                setCursor("HAND");
            }

            public void mouseExited(MouseEvent e) {
                setCursor("DEFAULT");
            }
        };

        addMouseListener(mouseListener);
        setPreferredSize(new Dimension(getWidth(), 30));
    }

    private void getParentNode(Container panel) {
        if (panel == null) {
            return;
        }
        if ((panel instanceof SlidePaneFactory)) {
            parent = panel;
        }

        getParentNode(panel.getParent());
    }

    private void setCursor(String state) {
        if ("HAND".equals(state)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public TitlePanel(Color color1, Color color2) {
        super();
        startColor = color1;
        endColor = color2;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return 30;
    }

    /**
     * Override the default paintComponent method to paint the gradient in the
     * panel.
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int h = getHeight();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        /**
         * Top Polygon
         */
        GeneralPath path = new GeneralPath();
        path.moveTo(70, 0);
        path.lineTo(8, 0);
        path.quadTo(0, 0, 0, 7);
        path.lineTo(0, 55);
        path.lineTo(getWidth() - 1, 55);
        path.lineTo(getWidth() - 1, 7);
        path.quadTo(getWidth() - 1, 0, getWidth() - 8, 0);
        path.lineTo(30, 0);

        Rectangle bounds1 = path.getBounds();
        GradientPaint painter = new GradientPaint(0, path.getBounds().y,
                true ? endColor : startColor, 0,
                bounds1.y + bounds1.height - 1, true ? startColor : endColor);
        g2d.setPaint(painter);
        g2d.fill(path);


        /**
         * Middle Rectangle
         */
        g2d.setPaint(new Color(240, 240, 240));
        g2d.fillRect(0, 31, getWidth() - 1, h - 50);

        /**
         *  Title
         */
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(
                (float) g2d.getFont().getSize() + 1));

        if (title != null) {
            g2d.setColor(accentColor);
            g2d.drawString(title, 40, 22);
            g2d.setColor(textColor);
            g2d.drawString(title, 40, 21);
        }

        /**
         *  image
         */
        if (imageIcon != null) {
            //new ImageIcon("images/self.png").getImage();
            g2d.drawImage(imageIcon, 5, 5, 20, 20,
                    null, null);
        }
        if(isToggleIcon){
            g2d.drawImage(new ImageIcon("images/arrowUp.png").getImage(), getWidth() - 20, 10, 10, 10,
                null, null);
        }else{
            g2d.drawImage(new ImageIcon("images/arrowDown.png").getImage(), getWidth() - 20, 10, 10, 10,
                null, null);
        }
    }

    /**
     *  This method sets the Actual Background Color of the Button
     */
    public void setStartColor(Color color) {
        startColor = color;
    }

    /**
     *  This method sets the Pressed Color of the Button
     */
    public void setEndColor(Color pressedColor) {
        endColor = pressedColor;
    }

    /**
     * @return  Starting Color of the Button
     */
    public Color getStartColor() {
        return startColor;
    }

    /**
     * @return  Ending Color of the Button
     */
    public Color getEndColor() {
        return endColor;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void toggleState(boolean state){
        this.isToggleIcon = state;
    }
}
