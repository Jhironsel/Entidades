package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Municipio;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Municipio {

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
    public synchronized static List<Municipio> getMunicipio(int id_provincia) {

        final String sql
                = "SELECT ID, NOMBRE "
                + "FROM V_T_MUNICIPIOS "
                + "WHERE ID_PROVINCIA = ? OR ID = 0 ORDER BY 1";

        List<Municipio> municipioList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, id_provincia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    municipioList.add(Municipio.
                                    builder().
                                    id(rs.getInt("id")).
                                    nombre(rs.getString("nombre")).
                                    build()
                    );
                }
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ex.getMessage(), 
                    ex
            );
        }
        return municipioList;
    }
    
}
