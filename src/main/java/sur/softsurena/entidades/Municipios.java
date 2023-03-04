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
public class Municipios {

    private static final Logger LOG = Logger.getLogger(Municipios.class.getName());

    private final int id;
    private final String nombre;
    private final int idProvincia;

    /**
     * Metodo que me trae un conjunto de datos de los municipios del pais.
     *
     * @nota Metodo creado el dia 16 de agosto 2022.
     *
     * @param id_provincia Identificador que permite obtener los municipios de
     * un lugar determinado de la provincia.
     *
     * @return retorna un conjunto de datos encapsulados en un ResultSet.
     */
    public synchronized static List<Municipios> getMunicipio(int id_provincia) {

        final String SELECT
                = "SELECT ID, NOMBRE "
                + "FROM V_MUNICIPIOS "
                + "WHERE IDPROVINCIA = ? OR ID = 0 ORDER BY 1";

        List<Municipios> municipioList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, id_provincia);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    municipioList.add(
                            Municipios.
                                    builder().
                                    id(rs.getInt("id")).
                                    nombre(rs.getString("nombre")).
                                    build()
                    );
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return municipioList;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
