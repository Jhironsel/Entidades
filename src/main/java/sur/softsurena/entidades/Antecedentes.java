package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Antecedentes extends Personas {

    private static final Logger LOG = Logger.getLogger(Antecedentes.class.getName());

    private int id;
    @NonNull private int idDoctor;
    @NonNull private Date fecha;
    @NonNull private String descripcion;
    private String UserName;
    private String Rol;

    /**
     *
     */
    public static final String DELETE
            = "DELETE FROM V_ANTECEDENTES WHERE ID = ?";

    public synchronized static String borrarAntecedente(int idAntecedente) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                DELETE,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setInt(1, idAntecedente);
            ps.executeUpdate();
            return "Borrado o inactivo correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar paciente...";
        }
    }

    /**
     * Permite agregar registro de los antecendentes de los paciente, solo debe
     * pasarse el ID_CONSULTA.
     */
    private static final String INSERT
            = "INSERT INTO V_ANTECEDENTES (ID_CONSULTA, DESCRIPCION) VALUES (?, ?)";

    /**
     * Metodo que permite agregar los antecendentes de los pacientes del
     * sistema.
     *
     * @param id
     * @param antecedente
     * @return
     */
    public synchronized static String agregarAntecedente(int id, String antecedente) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                INSERT,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setInt(1, id);
            ps.setString(2, antecedente);

            ps.executeUpdate();
            return "Antecedente agregado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar Antecedentes...";
        }
    }

    private static final String UPDATE
            = "UPDATE V_ANTECEDENTES SET DESCRIPCION = ? WHERE ID = ?";

    /**
     * 
     * @param idAntecedente
     * @param descrpcion
     * @return 
     */
    public synchronized String modificarAntecedente(int idAntecedente,
            String descrpcion) {
        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {

            ps.setString(1, descrpcion);
            ps.setInt(2, idAntecedente);

            ps.executeUpdate();

            return "Paciente modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar cliente...";
        }
    }
    
    /**
     * 
     * @param idPadre
     * @return 
     */
    public synchronized static ResultSet getAntecedentes(int idPadre) {
        final String sql = "SELECT IDANTECEDENTE, DESCRIPCION "
                + "FROM V_ANTECEDENTES "
                + "WHERE IDPACIENTE = ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPadre);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public String toString() {
        return descripcion.trim();
    }
}
