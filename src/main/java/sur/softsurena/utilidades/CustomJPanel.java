package utilidades;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class CustomJPanel extends JPanel {

    private BufferedImage image;
    private int x, y;
    private static CustomJPanel custom;

    public CustomJPanel(int num, int x, int y) {
        super();
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(getClass().
                    getResource("/imagenesPapeles/figura" + num + ".jpeg"));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    public synchronized static CustomJPanel getCustomPanel(int num, int x, int y){
        if (custom == null) {
            return new CustomJPanel(num, x, y);
        }
        return custom;
    }
    

    public Image getPrevio() {
        return image.getScaledInstance(114, 114, 4);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (image != null) {
            g2d.drawImage(image.getScaledInstance(x, y, Image.SCALE_DEFAULT), 0, 0, this);
        }
        super.paintComponent(g2d);
        g2d.dispose();
    }
}
