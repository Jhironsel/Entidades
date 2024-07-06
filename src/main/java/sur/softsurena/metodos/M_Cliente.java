package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import lombok.Cleanup;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Cliente;
import sur.softsurena.entidades.Generales;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
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
        try (CallableStatement cs = getCnn().prepareCall(
                "EXECUTE PROCEDURE SP_I_PERSONA_CLIENTE(?)",
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

    /**
     * Metodo que permite obtener los cliente del sistema.
     *
     * @param filtro
     * @return
     */
    public synchronized static List<Cliente> getPersonasClientes(FiltroBusqueda filtro) {
        final String sql = """
                            SELECT 
                                ID, CEDULA, PERSONA, PNOMBRE, SNOMBRE, APELLIDOS, 
                                SEXO, FECHA_NACIMIENTO, ESTADO_CIVIL, 
                                FECHA_INGRESO, ESTADO
                            FROM GET_PERSONA_CLIENTES
                           """;
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            @Cleanup
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientes.add(
                        Cliente
                                .builder()
                                .id_persona(rs.getInt("ID"))
                                .generales(
                                        Generales
                                                .builder()
                                                .id_persona(rs.getInt("ID"))
                                                .cedula(rs.getString("CEDULA"))
                                                .estado_civil(rs.getString("ESTADO_CIVIL").charAt(0))
                                                .build()
                                )
                                .persona(rs.getString("PERSONA").charAt(0))
                                .pnombre(rs.getString("PNOMBRE"))
                                .snombre(rs.getString("SNOMBRE"))
                                .apellidos(rs.getString("APELLIDOS"))
                                .sexo(rs.getString("SEXO").charAt(0))
                                .fecha_nacimiento(rs.getDate("FECHA_NACIMIENTO"))
                                .fecha_ingreso(rs.getDate("FECHA_INGRESO"))
                                .estado(rs.getBoolean("ESTADO"))
                                .build()
                );
            }

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_CONSULTA_LA_VISTA_GET_PERSONA_CL,
                    ex
            );
        }
        return clientes;
    }
    private static final String ERROR_AL_CONSULTA_LA_VISTA_GET_PERSONA_CL
            = "Error al consulta la vista GET_PERSONA_CLIENTES";

    /**
     * Metodo que permite obtener los cliente del sistema.
     *
     * @param filtro
     * @return
     */
    public synchronized static Cliente getPersonaCliente(FiltroBusqueda filtro) {
        final String sql = """
                            SELECT 
                                ID, CEDULA, PERSONA, PNOMBRE, SNOMBRE, APELLIDOS, 
                                SEXO, FECHA_NACIMIENTO, ESTADO_CIVIL, 
                                FECHA_INGRESO, ESTADO
                            FROM GET_PERSONA_CLIENTES
                            WHERE ID = ?
                           """;
        Cliente cliente = Cliente.builder().build();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            ps.setInt(1, filtro.getId());
            
            @Cleanup
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                cliente = Cliente
                        .builder()
                        .id_persona(rs.getInt("ID"))
                        .generales(
                                Generales
                                        .builder()
                                        .cedula(rs.getString("CEDULA"))
                                        .estado_civil(rs.getString("ESTADO_CIVIL").charAt(0))
                                        .build()
                        )
                        .persona(rs.getString("PERSONA").charAt(0))
                        .pnombre(rs.getString("PNOMBRE"))
                        .snombre(rs.getString("SNOMBRE"))
                        .apellidos(rs.getString("APELLIDOS"))
                        .sexo(rs.getString("SEXO").charAt(0))
                        .fecha_nacimiento(rs.getDate("FECHA_NACIMIENTO"))
                        .fecha_ingreso(rs.getDate("FECHA_INGRESO"))
                        .estado(rs.getBoolean("ESTADO"))
                        .build();
            }

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_CONSULTA_LA_VISTA_GET_PERSONA_CL,
                    ex
            );
        }
        return cliente;
    }
}
