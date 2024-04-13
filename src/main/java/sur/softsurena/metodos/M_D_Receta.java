package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.D_Receta;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_D_Receta {

    /**
     *
     * @param detalleReceta
     */
    public synchronized static Resultado agregarRecetaDetalle(List<D_Receta> detalleReceta) {

        final String sql
                = "EXECUTE PROCEDURE SP_I_D_RECETA(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql
        )) {
            detalleReceta.stream().forEach(dReceta -> {
                try {
                    ps.setInt(1, dReceta.getId_receta());
                    ps.setInt(2, dReceta.getLinea());
                    ps.setInt(3, dReceta.getId_Medicamento());
                    ps.setBigDecimal(4, dReceta.getCantidad());
                    ps.setString(5, dReceta.getDetalleDosis());
                    
                    ps.addBatch();
                } catch (SQLException ex) {
                    LOG.log(
                            Level.SEVERE, 
                            ERROR_AL_INGRESAR_RECETAS_AL_SISTEMA, 
                            ex
                    );
                }
            });

            ps.executeBatch();
            
            return Resultado
                    .builder()
                    .mensaje(RECETA_INGRESADA_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
            
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_INGRESAR_RECETAS_AL_SISTEMA, 
                    ex
            );
            
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_INGRESAR_RECETAS_AL_SISTEMA)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_INGRESAR_RECETAS_AL_SISTEMA
            = "Error al ingresar recetas al sistema.";
    public static final String RECETA_INGRESADA_CORRECTAMENTE 
            = "Receta ingresada correctamente.";
}
