package sur.softsurena.utilidades;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class CustomTextArea extends JTextArea {

    private BufferedImage image;

    public CustomTextArea(int num) {
        super(2, 2);
        try {
            image = ImageIO.read(getClass().
                    getResource("/imagenesPapeles/figura" + num + ".jpeg"));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public CustomTextArea() {}
    
    public Image getPrevio() {
        return image.getScaledInstance(114, 114, 4);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        if (image != null) {
//                int x = getWidth() - image.getWidth();
//                int y = getHeight() - image.getHeight();
                g2d.drawImage(image, 0, 0, this);    
        }
        
        super.paintComponent(g2d);
        g2d.dispose();
    }
}
