package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Asegurado;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Asegurado {

    /**
     * Metodo que permite registrar los registros de los seguro medicos de los 
     * pacientes del sistema.
     *
     * @param asegurado
     * @return
     */
    public synchronized static Resultado agregarAsegurado(Asegurado asegurado) {
        final String sql
                = """
                EXECUTE PROCEDURE SP_I_ASEGURADO (?, ?, ? ?);
              """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            ps.setInt(1, asegurado.getId_persona());
            ps.setInt(2, asegurado.getId_ars());
            ps.setString(3, asegurado.getNo_nss());
            ps.setBoolean(3, asegurado.getEstado());

            ps.execute();

            return Resultado
                    .builder()
                    .mensaje(REGISTRO_DE_ASEGURADO_CORRECTAMENTE___CODI)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_REGISTRAR_ASEGURADO_EN_EL_SISTEM
                            .formatted(asegurado.getId_persona()),
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(
                            ERROR_AL_REGISTRAR_ASEGURADO_EN_EL_SISTEM
                                    .formatted(asegurado.getId_persona())
                    )
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_REGISTRAR_ASEGURADO_EN_EL_SISTEM
            = "Error al registrar asegurado en el sistema. \n Codigo=[%s]";
    public static final String REGISTRO_DE_ASEGURADO_CORRECTAMENTE___CODI
            = "Registro de asegurado correctamente.";
}
