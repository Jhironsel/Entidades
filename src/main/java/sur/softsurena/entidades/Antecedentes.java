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

    private final int id;
    @NonNull private final int idDoctor;
    @NonNull private final Date fecha;
    @NonNull private String descripcion;
    private String UserName;
    private String Rol;

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
            return "Borrado o inactivo correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar paciente...";
        }
    }

    /**
     * Metodo que permite agregar los antecendentes de los pacientes del
     * sistema.
     * 
     * Los antecedentes son aquellos que el paciente a tenido antes de la 
     * consulta programada.
     *
     * @param id Identificador de la consulta, dicha consulta ya contiene el id 
     *          Del paciente.
     * 
     * @param antecedente Es una pequeña descripcion del antecendete que el 
     *      paciente padeció antes de la consulta.
     * 
     * @return retorna una cadena o mensaje con la accion realizada por el 
     * sistema.
     */
    public synchronized static String agregarAntecedente(int id, String antecedente) {
        final String INSERT
            = "EXECUTE PROCEDURE SP_INSERT_ANTECEDENTE(?,?)";
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                INSERT,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
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

    /**
     * Metodo que permite actualizar los antecendente de un paciente. Se utiliza
     * el identificador del antecedente para ser actualizado. 
     * 
     * @param idAntecedente 
     * @param descrpcion
     * @return 
     */
    public synchronized String modificarAntecedente(int idAntecedente,
            String descrpcion) {
        
        final String UPDATE
            = "EXECUTE PROCEDURE SP_UPDATE_ANTECEDENTE (?, ?);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {

            ps.setInt(1, idAntecedente);
            ps.setString(2, descrpcion);

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
                + "WHERE IDPACIENTE = ?;";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
