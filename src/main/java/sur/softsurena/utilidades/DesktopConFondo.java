package sur.softsurena.utilidades;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class DesktopConFondo extends JDesktopPane {

    private Image imagen;

    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(
                    getClass().getResource(nombreImagen)).getImage();
        } else {
            imagen = null;
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        } else {
            setOpaque(true);
        }

        try {
            super.paint(g);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Reinicie la Aplicacion " + e.getLocalizedMessage(),
                    "",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
