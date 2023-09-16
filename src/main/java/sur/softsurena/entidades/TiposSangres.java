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

@SuperBuilder
@Getter
public class TiposSangres {

    private static final Logger LOG = Logger.getLogger(TiposSangres.class.getName());

    private final int id;
    private final String descripcion;

    /**
     * Metodo utilizado para llenar los JCombox del sistema.
     *
     * Metodo verificado el dia 25 de abril, el cual nos permite tener los ID de
     * los tipos de sangre que existe en el mundo, resultset utilizado para
     * llegar los ComboBox.
     *
     * Metodo actualizado el 23 de Junio del 2022, agregamos la clave Conexion y
     * utilizamos el metodo getInstance para tener mejor control.
     *
     * @return
     */
    public synchronized static List<TiposSangres> getTipoSangre() {
        List<TiposSangres> tiposSangresList = new ArrayList<>();
        final String SELECT
                = "SELECT ID, DESCRIPCION "
                + "FROM V_TIPOS_SANGRE "
                + "WHERE ID >= 0 "
                + "ORDER BY 1";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    tiposSangresList.add(
                            TiposSangres.builder().
                                    id(rs.getInt("ID")).
                                    descripcion(rs.getString("DESCRIPCION")).build()
                    );
                }
                return tiposSangresList;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
