package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Gastos {

    private static final Logger LOG = Logger.getLogger(Gastos.class.getName());
    
    private final Integer id;
    private final Integer id_turno;
    private final String descripcion;
    private final BigDecimal monto;
    
    public static final String ERROR_AL_REGISTRAR_EL_GASTO = "Error al registrar el gasto.";
    public static final String GASTO_REGISTRADOS_CORRECTAMENTE = "Gasto registrados correctamente.";

    public synchronized static Resultados agregarGastosPorTurno(Gastos gasto) {
        
        final String sql = "EXECUTE PROCEDURE CAJERO_GASTOS (?, ?, ?)";
        
        try(CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            cs.setInt(1, gasto.getId_turno());
            cs.setString(2, gasto.getDescripcion());
            cs.setBigDecimal(3, gasto.getMonto());
            
            cs.execute();
            return Resultados
                    .builder()
                    .mensaje(GASTO_REGISTRADOS_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .mensaje(ERROR_AL_REGISTRAR_EL_GASTO)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
}
