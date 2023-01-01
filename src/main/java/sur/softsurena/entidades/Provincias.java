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
public class Provincias {

    private static final Logger LOG = Logger.getLogger(Provincias.class.getName());
    
    private final int id;
//    private final int id_provincias;
    private final String nombre;
    private final String zona;
    
    public final static String SELECT
            = "SELECT ID, NOMBRE FROM V_PROVINCIAS r";
    
    /**
     * Metodo que me trae un conjunto de datos de las provincias del pais.
     *
     * @nota Metodo creado el dia 16 de agosto 2022.
     *
     * @return retorna un conjunto de datos encapsulados en un ResultSet.
     */
    public synchronized static void getProvincias(RSComboBox jcbProvincias) {
        try ( PreparedStatement ps1 = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ResultSet rs = ps1.executeQuery();
            Provincias p;
            jcbProvincias.removeAllItems();

            try {
                while (rs.next()) {
                    p = Provincias.builder().
                            id(rs.getInt("ID")).
                            nombre(rs.getString("NOMBRE")).build();
                    jcbProvincias.addItem(p);
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
