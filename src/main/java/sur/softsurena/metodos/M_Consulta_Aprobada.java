package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Consulta_Aprobada;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Consulta_Aprobada {

    /**
     * TODO CREAR SP.
     * @param consultaAprobada
     * @return 
     */
    public synchronized static String agregarConsultaVerificada(Consulta_Aprobada consultaAprobada) {
        final String sql
                = "INSERT INTO V_CONSULTAS_APROBADAS (ID, COD_AUTORIZACION, COSTO, DESCUENTO) "
                + "VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql, 
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, consultaAprobada.getId());
            ps.setString(2, consultaAprobada.getCodAutorizacion());
            ps.setBigDecimal(3, consultaAprobada.getCosto());
            ps.setBigDecimal(4, consultaAprobada.getDescuento());
            ps.executeUpdate();
            return CONSULTA__APROBADA_CORRECTAMENTE;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return ERROR_AL_INSERTAR_REGISTRO;
        }
    }
    public static final String ERROR_AL_INSERTAR_REGISTRO = "Error al insertar registro";
    public static final String CONSULTA__APROBADA_CORRECTAMENTE = "Consulta Aprobada correctamente";
}
