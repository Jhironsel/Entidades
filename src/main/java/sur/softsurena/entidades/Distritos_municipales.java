package sur.softsurena.entidades;

import RSMaterialComponent.RSComboBox;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Distritos_municipales {

    private static final Logger LOG = Logger.getLogger(Distritos_municipales.class.getName());

    private final int id;
    private final String nombre;
    private final int idMunicipio;

    /**
     * Metodo que me trae un conjunto de datos de los Distritos Municipales del
     * pais.
     *
     * @nota Metodo creado el dia 16 de agosto 2022.
     *
     * @param id_municipio
     *
     * @return
     */
    public synchronized static void getDistritosMunicipales(int id_municipio,
            RSComboBox jcbDistritoMunicipal) {

        final String SELECT
                = "SELECT r.ID, r.NOMBRE "
                + "FROM V_DISTRITOS_MUNICIPALES r "
                + "WHERE r.IDMUNICIPIO = ? ";

        try (PreparedStatement ps1 = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps1.setInt(1, id_municipio);

            try (ResultSet rs = ps1.executeQuery();) {

                jcbDistritoMunicipal.removeAllItems();

                Distritos_municipales dm = Distritos_municipales.builder().
                        id(0).
                        nombre("Inserte Distritos").build();

                jcbDistritoMunicipal.addItem(dm);

                while (rs.next()) {
                    dm = Distritos_municipales.builder().
                            id(rs.getInt("id")).
                            nombre(rs.getString("nombre")).build();

                    jcbDistritoMunicipal.addItem(dm);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
