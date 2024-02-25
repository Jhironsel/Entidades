package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Consulta_Aprobada;

/**
 *
 * @author jhironsel
 */
public class M_Consulta_Aprobada {
    private static final Logger LOG = Logger.getLogger(Consulta_Aprobada.class.getName());
    
    public synchronized static String agregarConsultaVerificada(Consulta_Aprobada consultaAprobada) {
        final String INSERT
            = "INSERT INTO V_CONSULTAS_APROBADAS (ID, COD_AUTORIZACION, COSTO, DESCUENTO) "
            + "VALUES (?, ?, ?, ?);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
            
            ps.setInt(1, consultaAprobada.getId());
            ps.setString(2, consultaAprobada.getCodAutorizacion());
            ps.setBigDecimal(3, consultaAprobada.getCosto());
            ps.setBigDecimal(4, consultaAprobada.getDescuento());

            ps.executeUpdate();
            return "Consulta Aprobada correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar registro";
        }
    }
}
