package sur.softsurena.entidades;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Imagen {

    private static final Logger LOG = Logger.getLogger(Imagen.class.getName());
    
    private final ImageIcon imagen;
    private final String nombre;

    /**
     * Metodo que permite recibir imagenes en bits.
     *
     * @param idUsuario
     * @return
     */
    public static synchronized ImageIcon getImagen(String idUsuario) {

        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT imagen "
                + "FROM Tabla_Imagenes "
                + "WHERE TRIM(idUsuario) like ?")) {

            ps.setString(1, idUsuario);

            try ( ResultSet rs = ps.executeQuery()) {
                BufferedImage img = null;

                while (rs.next()) {
                    Blob blob = rs.getBlob("imagen");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(data));
                    } catch (IOException ex) {

                        LOG.log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }

                rs.close();
                return new ImageIcon(img);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    
}
