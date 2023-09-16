package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class ARS {

    private static final Logger LOG = Logger.getLogger(ARS.class.getName());

    private final Integer id;
    private final String descripcion;
    private final BigDecimal covertura;
    private final Boolean estado;
    private final Integer cantidad_registro;
    
    public static final String ERROR_AL_BORRAR_ARS = "Error al borrar ARS";
    public static final String BORRADO_CORRECTAMENTE = "Borrado correctamente.";

    /**
     * Metodo que nos permite obtener una lista de Seguros Sociales del sistema.
     * 
     * @return retorna una lista completa de los seguros sociales del sistema.
     */
    public synchronized static List<ARS> getARS() {
        final String SELECT_CANTIDAD
                = "SELECT ID, DESCRIPCION, COVERTURA_CONSULTA_PORCIENTO, ESTADO "
                + "FROM V_ARS ";

        List<ARS> arsList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT_CANTIDAD,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while(rs.next()){
                    arsList.add(ARS.builder().
                        id(rs.getInt("ID")).
                        descripcion(rs.getString("DESCRIPCION")).
                        covertura(rs.getBigDecimal("COVERTURA_CONSULTA_PORCIENTO")).
                        estado(rs.getBoolean("ESTADO")).build());
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return arsList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Trata de eliminar un registro de la tabla ARS, la cual debe tener una 
     * 
     * @param idARS
     * @return
     */
    public synchronized static Resultados<Object> borrarSeguro(int idARS) {
        final String DELETE = "EXECUTE PROCEDURE SP_DELETE_ARS (?);";
        Resultados<Object> r;
        try (PreparedStatement ps = getCnn().prepareStatement(
                DELETE,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            ps.setInt(1, idARS);
            
            int cantidad = ps.executeUpdate();
            
            r = Resultados.builder().
                    cantidad(cantidad).
                    id(-1).
                    mensaje(BORRADO_CORRECTAMENTE).build();
            
            return r;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados.builder().
                    cantidad(-1).
                    id(-1).
                    mensaje(ERROR_AL_BORRAR_ARS).build();
            return r;
        }
    }
    

    /**
     *
     * @param a
     * @return
     */
    public synchronized static String agregarSeguro(ARS ars) {
        final String INSERT
                = "EXECUTE PROCEDURE SP_INSERT_ARS (?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {

            ps.setString(1, ars.getDescripcion());
            ps.setBigDecimal(2, ars.getCovertura());
            ps.setBoolean(3, ars.getEstado());

            ps.executeUpdate();

            return "Seguro agregado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar Seguro...";
        }
    }

    /**
     *
     * @param ars
     * @return
     */
    public synchronized static String modificarSeguro(ARS ars) {
        String sql = "EXECUTE PROCEDURE SP_UPDATE_ARS (?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, ars.getId());
            ps.setString(2, ars.getDescripcion());
            ps.setBigDecimal(3, ars.getCovertura());
            ps.setBoolean(4, ars.getEstado());

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

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
