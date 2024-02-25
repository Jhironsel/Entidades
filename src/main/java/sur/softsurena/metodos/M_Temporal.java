package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.HeaderFactura;
import sur.softsurena.entidades.Temporal;

/**
 *
 * @author jhironsel
 */
public class M_Temporal {
    private static final Logger LOG = Logger.getLogger(Temporal.class.getName());
    /**
     * Para obtener las facturas temporales del sistema.
     *
     * @return
     */
    public synchronized static List<Temporal> getTemporales() {
        final String sql
                = "SELECT ID_FACTURA, ID_CLIENTE, NOMBRE_TEMP, PNOMBRE, SNOMBRE,"
                + "         APELLIDOS, ID_TURNO, FECHA_HORA, USER_NAME "
                + "FROM GET_TEMPORALES "
                + "ORDER BY 1 ";

        List<Temporal> temporalesList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    temporalesList.add(Temporal.builder().
                                    id(rs.getInt("ID_FACTURA")).
                                    id_persona(rs.getInt("ID_CLIENTE")).
                                    nombreTemporal(rs.getString("NOMBRE_TEMP")).
                                    pnombre(rs.getString("PNOMBRE")).
                                    snombre(rs.getString("SNOMBRE")).
                                    apellidos("APELLIDOS").
                                    headerFactura(HeaderFactura.builder().
                                            idTurno(rs.getInt("ID_TURNO")).
                                            fecha(rs.getDate("FECHA_HORA")).
                                            userName(rs.getString("USER_NAME")).
                                            build()).build());
                }
            }
            return temporalesList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
