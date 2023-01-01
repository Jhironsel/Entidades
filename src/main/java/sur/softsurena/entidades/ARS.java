package sur.softsurena.entidades;

import java.math.BigDecimal;
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
public class ARS {

    private static final Logger LOG = Logger.getLogger(ARS.class.getName());
    
    private final Integer id;
    @NonNull private final String descripcion;
    @NonNull private final BigDecimal covertura;
    @NonNull private final Boolean estado;
    private final String userName;
    private final String rol;
    
    

    /**
     * 
     * @return 
     */
    public synchronized static ResultSet getARS() {
        final String SELECT_CANTIDAD
            = "SELECT IDARS, DESCRIPCION, COVERCONSULTAPORC, ESTADO, CANTIDAD "
                    + "FROM GET_ARS "
                    + "WHERE idArs != 0";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(
                SELECT_CANTIDAD,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * 
     * @param idARS
     * @return 
     */
    public synchronized static String borrarSeguro(int idARS) {
        
        final String DELETE = "DELETE FROM V_ARS WHERE id = ?";
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                DELETE,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)){
            ps.setInt(1, idARS);
            int r = ps.executeUpdate();
            return "Borrado correctamente {"+r+"}";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar...";
        }
    }
    
    
    /**
     * 
     * @param a
     * @return 
     */
    public synchronized static String agregarSeguro(ARS a) {
        final String INSERT 
            = "EXECUTE PROCEDURE SP_INSERT_ARS (?, ?, ?);";
        
        try(PreparedStatement ps = getCnn().prepareStatement(INSERT)) {

            

            ps.setString(1, a.getDescripcion());
            ps.setBigDecimal(2, a.getCovertura());
            ps.setBoolean(3, a.getEstado());

            ps.executeUpdate();

            return "Seguro agregado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar Seguro...";
        }
    }

    public synchronized static String modificarSeguro(ARS ars) {
        String sql = "update V_ARS set "
                + " DESCRIPCION = ? , "
                + " COVERCONSULTAPORC = ?, "
                + " ESTADO = ? "
                + " where idArs = ? ;";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setString(1, ars.getDescripcion());
            ps.setBigDecimal(2, ars.getCovertura());
            ps.setBoolean(3, ars.getEstado());
            ps.setInt(4, ars.getId());

            ps.executeUpdate();
            return "Seguro modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar seguro...";
        }
    }
    
    /**
     * Este metodo proporciona la información de los seguro que están en estado
     * activo en la base de datos, Llenando asi los comboBox de la aplicacion.
     *
     * @return devuelve la lista de seguro que existe en la base de datos
     */
    public synchronized static ResultSet getTipoSeguro() {
        final String sql = "SELECT ID, DESCRIPCION "
                + "FROM V_ARS "
                + "WHERE ESTADO";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}
