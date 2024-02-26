package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Distrito_municipal;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Distrito_Municipal {
    
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
    public synchronized static List<Distrito_municipal> getDistritosMunicipales(int id_municipio) {
        final String SELECT
                = "SELECT ID, NOMBRE "
                + "FROM V_DISTRITOS_MUNICIPALES "
                + "WHERE IDMUNICIPIO = ?  OR ID = 0 ORDER BY 1";
        
        List<Distrito_municipal> distritos_municipaleses_list = new ArrayList<>();

        try (PreparedStatement ps1 = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps1.setInt(1, id_municipio);

            try (ResultSet rs = ps1.executeQuery();) {
                while (rs.next()) {
                    distritos_municipaleses_list.add(Distrito_municipal.builder().
                            id(rs.getInt("id")).
                            nombre(rs.getString("nombre")).build()
                    );
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return distritos_municipaleses_list;
    }
}
