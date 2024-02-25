package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Antecedente;
import sur.softsurena.utilidades.Resultados;

public class M_Antecedente {

    private static final Logger LOG = Logger.getLogger(M_Antecedente.class.getName());

    /**
     * Permite eliminar un antecedente registrado previamente del sistema.
     *
     * @param idAntecedente
     * @return
     */
    public synchronized static String borrarAntecedente(int idAntecedente) {
        final String DELETE
                = "EXECUTE PROCEDURE SP_DELETE_ANTECEDENTE (?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                DELETE,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setInt(1, idAntecedente);

            ps.executeUpdate();
            return BORRADO_CORRECTAMENTE;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return ERROR_AL_BORRAR_PACIENTE;
        }
    }
    public static final String BORRADO_CORRECTAMENTE = "Antecendente borrado correctamente";
    public static final String ERROR_AL_BORRAR_PACIENTE = "Error al borrar paciente...";

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
    public synchronized static Resultados agregarAntecedente(int id_consulta, String descripcion) {
        final String INSERT
                = "SELECT O_ID FROM SP_INSERT_ANTECEDENTE (?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                INSERT,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setInt(1, id_consulta);
            ps.setString(2, descripcion);

            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                return Resultados
                        .builder()
                        .id(rs.getInt("O_ID"))
                        .mensaje(ANTECEDENTE_AGREGADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .build();
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR__ANTECEDENTES)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
    public static final String ANTECEDENTE_AGREGADO_CORRECTAMENTE = "Antecedente agregado correctamente";
    public static final String ERROR_AL_INSERTAR__ANTECEDENTES = "Error al insertar Antecedentes...";

    /**
     * Metodo que permite actualizar los antecendente de un paciente. Se utiliza
     * el identificador del antecedente para ser actualizado.
     *
     * @param idAntecedente
     * @param descrpcion
     * @return
     */
    public static synchronized String modificarAntecedente(int idAntecedente,
            String descrpcion) {

        final String UPDATE
                = "EXECUTE PROCEDURE SP_UPDATE_ANTECEDENTE(?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {

            ps.setInt(1, idAntecedente);
            ps.setString(2, descrpcion);

            ps.executeUpdate();

            return ANTECEDENTE_MODIFICADO_CORRECTAMENTE;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return ERROR_AL_MODIFICAR_CLIENTE;
        }
    }
    public static final String ANTECEDENTE_MODIFICADO_CORRECTAMENTE = "Antecedente modificado correctamente";
    public static final String ERROR_AL_MODIFICAR_CLIENTE = "Error al modificar cliente...";

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
        final String sql = "SELECT ID, ID_CONSULTA, DESCRIPCION "
                + "FROM V_ANTECEDENTES "
                + "WHERE ID = ?;";

        List<Antecedente> lista = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

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
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
