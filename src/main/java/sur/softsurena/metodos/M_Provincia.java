package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Provincia;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Provincia {
    
    /**
     * Metodo que me trae un conjunto de datos de las provincias del pais.
     *
     * @nota Metodo creado el dia 16 de agosto 2022.
     *
     * @return retorna un conjunto de datos encapsulados en un ResultSet.
     */
    public synchronized static List<Provincia> getProvincias() {
        final String SELECT
            = "SELECT ID, NOMBRE FROM V_PROVINCIAS r";
        
        List<Provincia> provinciaList = new ArrayList<>();
        
        try (PreparedStatement ps1 = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            
            try (ResultSet rs = ps1.executeQuery();) {
                while (rs.next()) {
                    provinciaList.add(Provincia.builder().
                                    id(rs.getInt("ID")).
                                    nombre(rs.getString("NOMBRE")).build()
                    );
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return provinciaList;
    }
}
