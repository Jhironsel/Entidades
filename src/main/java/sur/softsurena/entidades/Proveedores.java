package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@Getter
@SuperBuilder
public class Proveedores extends Personas {

    private static final Logger LOG = Logger.getLogger(Proveedores.class.getName());

    private String codigoProveedor;

    public synchronized static String agregarProveedor(Proveedores proveedor) {
        final String sql
                = "EXECUTE PROCEDURE SP_INSERT_PROVEEDOR (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
                    
            //ps.setInt(1, proveedor.getCodigoProveedor());
            ps.setString(2, proveedor.getPnombre());
            ps.setString(3, proveedor.getSnombre());
            ps.setBoolean(4, proveedor.getEstado());

            ps.executeUpdate();
            return "🆗 Proveedor agregado correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "⛔ Error al insertar Proveedor...";
        }
    }

    /**
     * Metodo que permite la actualizacion de los proveedores del sistema de
     * factura.
     *
     * @param p
     * @return
     */
    public synchronized static String modificarProveedor(Proveedores p) {
        final String sql = "EXECUTE PROCEDURE SP_UPDATE_PROVEEDORES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setInt(1, p.getId_persona());
            ps.setString(2, p.getCodigoProveedor());
            ps.setString(3, p.getPersona() + "");
            ps.setString(4, p.getPnombre());
            ps.setString(5, p.getSnombre());
            ps.setString(6, p.getApellidos());
            ps.setString(7, p.getSexo() + "");
            ps.setDate(8, p.getFecha_nacimiento());
            ps.setBoolean(9, p.getEstado());

            ps.executeUpdate(sql);
            return "Consulta modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar consulta...";
        }
    }

    public synchronized static ResultSet getProveedor() {
        final String sql = "SELECT IDPROVEEDOR, CODIGO_PROVEEDOR, NOMBRE_PROVEEDOR, "
                + "TELEFONO_PROVEEDOR, ESTADO "
                + "FROM V_PROVEEDORES ";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
