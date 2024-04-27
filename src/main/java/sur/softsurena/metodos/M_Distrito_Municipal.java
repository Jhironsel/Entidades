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
    public synchronized static List<Distrito_municipal> getDistritosMunicipales(
            int id_municipio
    ) {
        final String sql = """
            SELECT 
                    ID, NOMBRE 
            FROM V_T_DISTRITOS_MUNICIPALES 
            WHERE 
                    ID = 0 OR ID_MUNICIPIO = ?
            ORDER BY 1
        """;

        List<Distrito_municipal> distritos_municipaleses_list = new ArrayList<>();

        try (PreparedStatement ps1 = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            ps1.setInt(1, id_municipio);

            ResultSet rs = ps1.executeQuery();
            
            while (rs.next()) {
                distritos_municipaleses_list.add(
                        Distrito_municipal
                                .builder()
                                .id(rs.getInt("id"))
                                .nombre(rs.getString("nombre"))
                                .build()
                );
            }
            
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_CONSUTAR_LA_VISTA_V_DISTRITOS_MU,
                    ex
            );
        }
        
        return distritos_municipaleses_list;
    }
    
    public static final String ERROR_AL_CONSUTAR_LA_VISTA_V_DISTRITOS_MU 
            = "Error al consutar la vista V_DISTRITOS_MUNICIPALES.";
}
