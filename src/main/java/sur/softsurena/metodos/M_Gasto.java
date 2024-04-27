package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Gasto;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Gasto {
    
    public synchronized static Resultado agregarGastosPorTurno(Gasto gasto) {
        
        final String sql = "EXECUTE PROCEDURE CAJERO_GASTOS (?, ?, ?)";
        
        try(CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setInt(1, gasto.getId_turno());
            cs.setString(2, gasto.getDescripcion());
            cs.setBigDecimal(3, gasto.getMonto());            
            cs.execute();
            return Resultado
                    .builder()
                    .mensaje(GASTO_REGISTRADOS_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_REGISTRAR_EL_GASTO)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }

    public static final String GASTO_REGISTRADOS_CORRECTAMENTE 
            = "Gasto registrados correctamente.";

    public static final String ERROR_AL_REGISTRAR_EL_GASTO 
            = "Error al registrar el gasto.";
}
