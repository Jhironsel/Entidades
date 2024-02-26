package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Metrica;
import sur.softsurena.utilidades.Resultados;
import sur.softsurena.utilidades.Utilidades;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Metrica {

    /**
     *
     * @param metrica
     */
    public synchronized static Resultados agregarMetricas(Metrica metrica) {
        final String sql
                = "INSERT INTO V_METRICAS (IDCONSULTA, PESOKG, ESTATURAMETRO, ESCEFALO, "
                + "     ENF_DETECT, HALLAZGOS_POS, ID_DIAG, TX, COMPLEMENTO,"
                + "     IMAGEN_TEXTO) "
                + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql, 
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setInt(1, metrica.getIdConsulta());
            ps.setBigDecimal(2, metrica.getPesoKG());
            ps.setBigDecimal(3, metrica.getEstaturaM());
            ps.setBigDecimal(4, metrica.getEscefalo());
            ps.setString(5, metrica.getEnf_detect());
            ps.setString(6, metrica.getHallazgosPositivo());
            ps.setString(7, metrica.getIdDiagnostico());
            ps.setString(8, metrica.getTx());
            ps.setString(9, metrica.getComplemento());
            ps.setString(10, Utilidades.imagenEncode64(metrica.getImagenPath()));

            ps.executeUpdate();

            return Resultados
                    .builder()
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
