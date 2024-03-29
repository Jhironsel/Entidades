package sur.softsurena.utilidades;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 * El objetivo de esta clase es utilizar la para cargas las fuentes 
 * seleccionada.
 * @author jhironsel
 */
public class CustomFont {

    private Font font = null;

    public CustomFont(String fontName) {
        try {
            //Se carga la fuente
            InputStream is = getClass().getResourceAsStream("../freefont/"+fontName+".ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            LOG.log(Level.SEVERE, ex.getMessage(), ex); 
            font = new Font("Arial", Font.PLAIN, 14);
        }
    }

    /* Font.PLAIN = 0 , Font.BOLD = 1 , Font.ITALIC = 2
     * tamanio = float
     */
    public Font MyFont(int estilo, float tamanio) {
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }

}
