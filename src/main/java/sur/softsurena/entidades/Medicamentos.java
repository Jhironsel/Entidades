package sur.softsurena.entidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.codec.binary.Base64;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.utilidades.Utilidades;

@SuperBuilder
@Getter
public class Medicamentos {

    private static final Logger LOG = Logger.getLogger(Medicamentos.class.getName());
    
    private final int id;
    private final int id_proveedor;
    private final String descripcion;
    private final File pathImagen;
    private final ImageIcon imagen;
    private final boolean estado;
    private final String usuario;

    public synchronized static String modificarMedicamento(Medicamentos m) {
        final String sql = "UPDATE V_MEDICAMENTOS "
                + "SET "
                + "IDPROVEEDOR = ?, "
                + "DESCRIPCION = ?, "
                + "FOTO = ?, "
                + "ESTADO = ? "
                + "WHERE IDMedicamento = ?";

        try {
            FileInputStream imageInFile = null;
            String imageDataString = null;
            if (m.getPathImagen() != null) {
                imageInFile = new FileInputStream(m.getPathImagen());
                byte imageData[] = new byte[(int) m.getPathImagen().length()];
                imageInFile.read(imageData);
                // Converting Image byte array into Base64 String
                imageDataString = Base64.encodeBase64URLSafeString(imageData);
            }
            PreparedStatement ps = null;

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, m.getId_proveedor());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, Utilidades.imagenEncode64(m.getPathImagen()));
            ps.setBoolean(4, m.isEstado());
            ps.setInt(5, m.getId());

            ps.executeUpdate();

            return "Medicamento modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "Error al modificar Medicamento...";
    }
    
    public synchronized static ResultSet getMedicamentoActivo() {
        final String sql = "SELECT IDMEDICAMENTO, DESCRIPCION "
                + "FROM V_MEDICAMENTOS "
                + "WHERE estado ORDER BY 2";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    public synchronized static ResultSet getMedicamentoFoto(String idMedicamento) {

        final String sql = "SELECT FOTO FROM V_MEDICAMENTOS "
                + "WHERE idMedicamento = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, idMedicamento);
            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    public synchronized static ResultSet getMedicamento(boolean estado) {
        final String sql = "SELECT CODIGO_PROVEEDOR, IDMEDICAMENTO, "
                + "          NOMBREMEDICAMENTO, ESTADO "
                + "   FROM GET_MEDICAMENTO"
                + "   WHERE ESTADO IS ? ORDER BY 1, 3";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setBoolean(1, estado);

            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
