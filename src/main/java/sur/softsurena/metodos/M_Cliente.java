package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Cliente {

    /**
     * Permite agregar un cliente ya registrado en la tabla de Personas a
     * Personas_Cliente.
     *
     * @param id Identificador de la personas en el sistema.
     *
     * @return Un objecto de la clase
     */
    public synchronized static Resultado agregarClienteById(int id) {
        final String sql
                = "EXECUTE PROCEDURE SP_I_PERSONA_CLIENTE(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setInt(1, id);

            cs.execute();

            return Resultado
                    .builder()
                    .mensaje(CLIENTE__AGREGADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_INSERTAR__CLIENTE,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_INSERTAR__CLIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR__CLIENTE
            = "Error al insertar Cliente al sistema.";
    public static final String CLIENTE__AGREGADO__CORRECTAMENTE
            = "Cliente Agregado Correctamente";
    

    /**
     * Este procedimiento tiene la habilidad de borrar los registros de las
     * vistas siguiente: V_CONTACTS_TEL, V_CONTACTS_EMAIL, V_CLIENTES,
     * V_DIRECCIONES, V_GENERALES y V_PERSONAS.
     *
     * Para eliminar un cliente, no debe de tener registros en el sistema.
     *
     * @param idCliente
     * @return
     */
    public synchronized static Resultado borrarCliente(int idCliente) {
        final String sql = "EXECUTE PROCEDURE SP_D_PERSONA_CLIENTE (?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idCliente);

            ps.execute();

            return Resultado
                    .builder()
                    .mensaje(CLIENTE_BORRADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    CLIENTE_NO_PUEDE_SER_BORRADO,
                    ex
            );
        }
        return Resultado
                .builder()
                .id(-1)
                .mensaje(CLIENTE_NO_PUEDE_SER_BORRADO)
                .icono(JOptionPane.ERROR_MESSAGE)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String CLIENTE_BORRADO_CORRECTAMENTE
            = "Cliente borrado correctamente.";
    public static final String CLIENTE_NO_PUEDE_SER_BORRADO
            = "Cliente no puede ser borrado.";

}
