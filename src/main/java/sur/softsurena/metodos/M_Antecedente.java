package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Antecedente;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Antecedente {

    /**
     * Permite eliminar un antecedente registrado previamente del sistema.
     *
     * @param idAntecedente
     * @return
     */
    public synchronized static Resultado borrarAntecedente(int idAntecedente) {
        final String sql
                = "EXECUTE PROCEDURE SP_D_ANTECEDENTE(?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idAntecedente);

            ps.execute();
            
            return Resultado
                    .builder()
                    .mensaje(BORRADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_BORRAR_PACIENTE, 
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_BORRAR_PACIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String BORRADO_CORRECTAMENTE 
            = "Antecendente borrado correctamente";
    public static final String ERROR_AL_BORRAR_PACIENTE 
            = "Error al borrar paciente...";

    /**
     * Metodo que permite agregar los antecendentes de los pacientes del
     * sistema.
     *
     * Los antecedentes son aquellos que el paciente a tenido antes de la
     * consulta programada.
     *
     * @param id_consulta Identificador de la consulta, dicha consulta ya
     * contiene el id Del paciente.
     *
     * @param descripcion Es una pequeña descripcion del antecendete que el
     * paciente padeció antes de la consulta.
     *
     * @return retorna una cadena o mensaje con la accion realizada por el
     * sistema.
     */
    public synchronized static Resultado agregarAntecedente(int id_consulta, String descripcion) {
        final String sql
                = "SELECT O_ID FROM SP_I_ANTECEDENTE (?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, id_consulta);
            ps.setString(2, descripcion);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return Resultado
                        .builder()
                        .id(rs.getInt("O_ID"))
                        .mensaje(ANTECEDENTE_AGREGADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .build();
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_INSERTAR__ANTECEDENTES,
                    ex
            );
        }
        return Resultado
                .builder()
                .id(-1)
                .mensaje(ERROR_AL_INSERTAR__ANTECEDENTES)
                .icono(JOptionPane.ERROR_MESSAGE)
                .build();
    }
    public static final String ANTECEDENTE_AGREGADO_CORRECTAMENTE
            = "Antecedente agregado correctamente";
    public static final String ERROR_AL_INSERTAR__ANTECEDENTES
            = "Error al insertar Antecedentes...";

    /**
     * Metodo que permite actualizar los antecendente de un paciente. Se utiliza
     * el identificador del antecedente para ser actualizado.
     *
     * @param idAntecedente
     * @param descrpcion
     * @return
     */
    public static synchronized Resultado modificarAntecedente(int idAntecedente,
            String descrpcion) {
        final String sql = "EXECUTE PROCEDURE SP_U_ANTECEDENTE(?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idAntecedente);
            ps.setString(2, descrpcion);

            ps.executeUpdate();

            return Resultado
                    .builder()
                    .mensaje(ANTECEDENTE_MODIFICADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_MODIFICAR_ANTECEDENTE,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_MODIFICAR_ANTECEDENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ANTECEDENTE_MODIFICADO_CORRECTAMENTE
            = "Antecedente modificado correctamente";
    public static final String ERROR_AL_MODIFICAR_ANTECEDENTE
            = "Error al modificar el antecendete...";

    /**
     * [INFO TABLA] Es una tabla utilizada para almacenar los antecedentes de
     * los pacientes del sistema, dicho antecedente describe la condicion de los
     * paciente en el momento de la consulta.
     *
     * @param idPaciente Identificador del paciente, que tiene registros de
     * historico de los antecedentes.
     *
     * @return Se obtiene una lista de todos los antecendentes de los registros
     * del paciente.
     */
    public synchronized static List<Antecedente> getAntecedentes(
            int idPaciente) {
        final String sql
                = "SELECT ID, ID_CONSULTA, DESCRIPCION "
                + "FROM V_ANTECEDENTES "
                + "WHERE ID = ?;";
        List<Antecedente> lista = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery();) {

                while (rs.next()) {
                    lista.add(
                            Antecedente
                                    .builder()
                                    .id(rs.getInt("ID"))
                                    .id_consulta(rs.getInt("ID_Consulta"))
                                    .descripcion(rs.getString("Descripcion"))
                                    .build()
                    );
                }
            }
            return lista;
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    "Error al consultar la vista V_ANTECEDENTES del sistema.",
                    ex
            );
            return lista;
        }
    }
}
