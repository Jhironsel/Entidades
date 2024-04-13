package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Proveedor;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Proveedor {
    
    /**
     * 
     * @param proveedor
     * @return 
     */
    public synchronized static Resultado agregarProveedor(Proveedor proveedor) {
        final String sql
                = "EXECUTE PROCEDURE SP_I_PROVEEDOR(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            /*
            EXECUTE PROCEDURE SP_I_PROVEEDOR (
            'I_ID_PROVINCIA', 
            'I_ID_MUNICIPIO', 
            'I_PERSONA', 
            'I_CEDULA', 
            'I_PNOMBRE', 
            'I_SNOMBRE',
            'I_APELLIDOS', 
            'I_SEXO', 
            'I_DIRECCION', 
            'I_ESTADO', 
            'I_CODIGO_PROVEEDOR'
            );
            */
            //ps.setInt(1, proveedor.getCodigoProveedor());
            ps.setString(2, proveedor.getPnombre());
            ps.setString(3, proveedor.getSnombre());
            ps.setBoolean(4, proveedor.getEstado());

            ps.executeUpdate();
            return Resultado
                    .builder()
                    .mensaje(__PROVEEDOR_AGREGADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    __ERROR_AL_INSERTAR__PROVEEDOR, 
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(__ERROR_AL_INSERTAR__PROVEEDOR)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String __ERROR_AL_INSERTAR__PROVEEDOR 
            = "⛔ Error al insertar Proveedor...";
    public static final String __PROVEEDOR_AGREGADO_CORRECTAMENTE 
            = "🆗 Proveedor agregado correctamente.";

    /**
     * Metodo que permite la actualizacion de los proveedores del sistema de
     * factura.
     *
     * @param p
     * @return
     */
    public synchronized static Resultado modificarProveedor(Proveedor p) {
        final String sql
                = "EXECUTE PROCEDURE SP_U_PROVEEDOR(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, p.getId_persona());
            ps.setString(2, p.getCodigoProveedor());
            ps.setString(3, p.getPersona() + "");
            ps.setString(4, p.getPnombre());
            ps.setString(5, p.getSnombre());
            ps.setString(6, p.getApellidos());
            ps.setString(7, p.getSexo() + "");
            ps.setDate(8, p.getFecha_nacimiento());
            ps.setBoolean(9, p.getEstado());

            ps.executeUpdate();
            return Resultado
                    .builder()
                    .mensaje(CONSULTA_MODIFICADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_MODIFICAR_CONSULTA, 
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_MODIFICAR_CONSULTA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_MODIFICAR_CONSULTA 
            = "Error al modificar proveedor...";
    public static final String CONSULTA_MODIFICADO_CORRECTAMENTE 
            = "Proveedor modificado correctamente";

    /**
     * 
     * @return 
     */
    public synchronized static ResultSet getProveedor() {
        final String sql = "SELECT IDPROVEEDOR, CODIGO_PROVEEDOR, NOMBRE_PROVEEDOR, "
                + "TELEFONO_PROVEEDOR, ESTADO "
                + "FROM V_PROVEEDORES ";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
