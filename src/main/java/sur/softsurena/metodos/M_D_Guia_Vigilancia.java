package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_D_Guia_Vigilancia {

    /**
     * Permite agregar guias de vigilancias de los pacientes en el sistema.
     * @param idGVD Es el identificador de la guia.
     * @param idPaciente 
     * @return 
     */
    public synchronized static Resultado update(int idGVD, int idPaciente) {
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                " EXECUTE PROCEDURE SP_U_GUIA_VIGILANCIA_DESARROLLO(?,?)",
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idGVD);
            ps.setInt(2, idPaciente);

            ps.executeUpdate();

            return Resultado
                    .builder()
                    .mensaje(GUIA_DE__DESARROLLO_AGREGADA_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_INSERTAR__GUIA_DE__VIGILANCIA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR__GUIA_DE__VIGILANCIA 
            = "Error al insertar Guia de Vigilancia...";
    public static final String GUIA_DE__DESARROLLO_AGREGADA_CORRECTAMENTE 
            = "Guia de Desarrollo agregada correctamente";
}
