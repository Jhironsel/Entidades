package sur.softsurena.utilidades;

import java.awt.*;
import java.awt.image.*;
import java.net.URL;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import static sur.softsurena.utilidades.Utilidades.LOG;
 
public class PanelConFondo {

    private static TexturePaint cargaTextura(URL imageFile, Component c) {
        TexturePaint imageDev;
        try {
            Image img = (new ImageIcon(imageFile)).getImage();
            BufferedImage image = getBufferedImage(img, c);
            imageDev = new TexturePaint(image,
                    new Rectangle(0, 0, image.getWidth(), image.getHeight())
            );
        } catch (Exception ex) {
            imageDev = null;
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return imageDev;
    }

    private static BufferedImage getBufferedImage(Image image, Component c) {
        waitForImage(image, c);
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(c), image.getHeight(c), BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, c);
        return (bufferedImage);
    }

    private static boolean waitForImage(Image image, Component c) {
        MediaTracker tracker = new MediaTracker(c);
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException ie) {
        }
        return (!tracker.isErrorAny());
    }

    /**
     * Crea un textura de una imagen para un componente concreto
     *
     * @param s imagen a cargar
     * @param c componente sobre el cual pintaremos
     * @return TexturePaint
     */
    public static TexturePaint carga(URL s, Component c) {
        TexturePaint image;
        
        image = cargaTextura(s, c);
        
        if (image == null) {
            LOG.log(Level.SEVERE, "OMG! No puedo leer la imagen");
        }
        
        return image;
    }
}
