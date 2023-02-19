package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@Getter
@SuperBuilder
public class Temporales extends Personas {

    private static final Logger LOG = Logger.getLogger(Temporales.class.getName());

    private final int id;
    private final String nombreTemporal;
    private final HeaderFactura headerFactura;
    private final List<DetalleFactura> detalleFactura;

    /**
     * Para obtener las facturas temporales del sistema.
     *
     * @return
     */
    public synchronized static List<Temporales> getTemporales() {
        final String sql
                = "SELECT ID_FACTURA, ID_CLIENTE, NOMBRE_TEMP, PNOMBRE, SNOMBRE,"
                + "         APELLIDOS, ID_TURNO, FECHA_HORA, USER_NAME "
                + "FROM GET_TEMPORALES "
                + "ORDER BY 1 ";

        List<Temporales> temporalesList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    temporalesList.add(
                            Temporales.builder().
                                    id(rs.getInt("ID_FACTURA")).
                                    id_persona(rs.getInt("ID_CLIENTE")).
                                    nombreTemporal(rs.getString("NOMBRE_TEMP")).
                                    pNombre(rs.getString("PNOMBRE")).
                                    sNombre(rs.getString("SNOMBRE")).
                                    apellidos("APELLIDOS").
                                    headerFactura(HeaderFactura.builder().
                                            idTurno(rs.getInt("ID_TURNO")).
                                            fecha(rs.getDate("FECHA_HORA")).
                                            userName(rs.getString("USER_NAME")).
                                            build()).build());
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }

            return temporalesList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}
