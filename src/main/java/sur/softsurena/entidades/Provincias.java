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
public class Provincias {

    private static final Logger LOG = Logger.getLogger(Provincias.class.getName());

    private final int id;
    private final String nombre;
    private final String zona;

    /**
     * Metodo que me trae un conjunto de datos de las provincias del pais.
     *
     * @nota Metodo creado el dia 16 de agosto 2022.
     *
     * @return retorna un conjunto de datos encapsulados en un ResultSet.
     */
    public synchronized static List<Provincias> getProvincias() {
        final String SELECT
            = "SELECT ID, NOMBRE FROM V_PROVINCIAS r";
        
        List<Provincias> provinciaList = new ArrayList<>();
        
        try (PreparedStatement ps1 = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {


            try (ResultSet rs = ps1.executeQuery();) {
                while (rs.next()) {
                    provinciaList.add(
                            Provincias.builder().
                                    id(rs.getInt("ID")).
                                    nombre(rs.getString("NOMBRE")).build()
                    );
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return provinciaList;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
