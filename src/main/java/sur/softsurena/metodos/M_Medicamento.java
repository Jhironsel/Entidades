package sur.softsurena.metodos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.codec.binary.Base64;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Medicamento;
import sur.softsurena.utilidades.Utilidades;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Medicamento {
    
    /**
     * TODO CREAR SP.
     * @param m
     * @return 
     */
    public synchronized static String modificarMedicamento(Medicamento m) {
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

            ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);

            ps.setInt(1, m.getId_proveedor());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, Utilidades.imagenEncode64(m.getPathImagen()));
            ps.setBoolean(4, m.isEstado());
            ps.setInt(5, m.getId());

            ps.executeUpdate();

            return MEDICAMENTO_MODIFICADO_CORRECTAMENTE;
        } catch (SQLException | IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ERROR_AL_MODIFICAR__MEDICAMENTO;
    }
    public static final String ERROR_AL_MODIFICAR__MEDICAMENTO = "Error al modificar Medicamento...";
    public static final String MEDICAMENTO_MODIFICADO_CORRECTAMENTE = "Medicamento modificado correctamente";

    public synchronized static List<Medicamento> getMedicamentoActivo() {
        final String sql = "SELECT ID, DESCRIPCION "
                + "FROM V_PRODUCTOS "
                + "WHERE estado ORDER BY 2";
        
        List<Medicamento> medicamentoList = null;

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            try (ResultSet rs = ps.executeQuery();) {
                while(rs.next()){
                    medicamentoList.add(Medicamento.builder().
                            id(rs.getInt("ID")).
                            descripcion(rs.getString("DESCRIPCION")).
                            build());
                    
                }
            } 
            return medicamentoList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 
     * @param idMedicamento
     * @return 
     */
    public synchronized static ResultSet getMedicamentoFoto(String idMedicamento) {

        final String sql = "SELECT FOTO FROM V_MEDICAMENTOS "
                + "WHERE idMedicamento = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, idMedicamento);
            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 
     * @param estado
     * @return 
     */
    public synchronized static ResultSet getMedicamento(boolean estado) {
        final String sql = "SELECT CODIGO_PROVEEDOR, IDMEDICAMENTO, "
                + "          NOMBREMEDICAMENTO, ESTADO "
                + "   FROM GET_MEDICAMENTO"
                + "   WHERE ESTADO IS ? ORDER BY 1, 3";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
