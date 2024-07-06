package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Consulta_Aprobada;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Consulta_Aprobada {

    /**
     * 
     *
     * @param consultaAprobada
     * @return
     */
    public synchronized static Resultado agregarConsultaAprovada(
            Consulta_Aprobada consultaAprobada
    ) {
        final String sql
                = """
                  SELECT O_ID
                  FROM SP_I_CONSULTA_APROBADAS (?, ?, ?);
                  """;
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            
            ps.setString(1, consultaAprobada.getCodAutorizacion());
            ps.setBigDecimal(2, consultaAprobada.getCosto());
            ps.setBigDecimal(3, consultaAprobada.getDescuento());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.absolute(0)){
                return Resultado
                        .builder()
                        .id(rs.getInt("O_ID"))
                        .mensaje(CONSULTA__APROBADA_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return Resultado
                .builder()
                .id(-1)
                .mensaje(ERROR_AL_INSERTAR_REGISTRO)
                .icono(JOptionPane.ERROR_MESSAGE)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String ERROR_AL_INSERTAR_REGISTRO
            = "Error al insertar registro";
    public static final String CONSULTA__APROBADA_CORRECTAMENTE
            = "Consulta Aprobada correctamente";
}
