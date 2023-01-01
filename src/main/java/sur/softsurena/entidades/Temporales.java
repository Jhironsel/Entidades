package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sur.softsurena.conexion.Conexion.getCnn;

public class Temporales {

    private static final Logger LOG = Logger.getLogger(Temporales.class.getName());
    
    private static String GET_TEMPORALES
            = "SELECT r.ID_FACTURA, r.NOMBRE_TEMP, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.FECHA, "
            + "     r.IDUSUARIO, r.HORA, r.ID_TURNO, r.EFECTIVO, r.CAMBIO, r.ESTADO_FACTURA, "
            + "     r.MONTO, r.ID_CLIENTE "
            + "FROM GET_TEMPORALES r"
            + "ORDER BY 1";
    
    /**
     * Para obtener las facturas temporales del sistema.
     *
     * @return
     */
    public synchronized static ResultSet getTemporales() {
        try ( PreparedStatement ps = getCnn().prepareStatement(GET_TEMPORALES)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}
