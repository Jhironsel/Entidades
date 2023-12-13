package sur.softsurena.entidades;

import java.sql.Date;
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
public class Direcciones {

    private static final Logger LOG = Logger.getLogger(Direcciones.class.getName());
    
    private final Integer id;
    private final Integer id_persona;
    private final Provincias provincia;
    private final Municipios municipio;
    private final Distritos_municipales distrito_municipal;
    private final Codigo_Postal codigo_postal;
    private final String direccion;
    private final Date fecha;
    private final Boolean estado;
    private final Boolean por_defecto;
    private final Character accion;

    /**
     * Metodo utilizado para agregar una lista de direcciones del cliente al
     * sistema.
     *
     * @param id_persona Identificador de la persona a la que se le va almacenar
     * una direccion.
     * @param direcciones Listado de direcciones de una persona.
     *
     * @return Devuelve true si la operacion fue exitosa, false caso contrario.
     */
    public static boolean agregarDirecciones(int id_persona, List<Direcciones> direcciones) {
        final String sql = "EXECUTE PROCEDURE SP_INSERT_DIRECCION(?, ?, ?, ?, 0, ?);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            for (Direcciones direccion : direcciones) {
                ps.setInt(1, id_persona);
                ps.setInt(2, direccion.getProvincia().getId());
                ps.setInt(3, direccion.getMunicipio().getId());
                ps.setInt(4, direccion.getDistrito_municipal().getId());
                ps.setString(5, direccion.getDireccion());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    /**
     *
     * @param id_persona
     * @param direccion
     * @return
     */
    public static boolean modificarDireccion(int id_persona, Direcciones direccion) {
        final String sql
                = "EXECUTE PROCEDURE SP_UPDATE_DIRECCION(?, ?, ?, ?, 0, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setInt(1, direccion.getId());
            ps.setInt(2, direccion.getProvincia().getId());
            ps.setInt(3, direccion.getMunicipio().getId());
            ps.setInt(4, direccion.getDistrito_municipal().getId());
            ps.setString(5, direccion.getDireccion());
            ps.setBoolean(6, direccion.getEstado());
            ps.setBoolean(7, direccion.getPor_defecto());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    /**
     * Obtenemos el historia de direcciones del cliente, lo cual permite tener
     * mejor control de la direcciones de los clientes.
     *
     * @param id_persona identificador del cliente del sistema, la cual ayuda
     * obtener los registros de un usuario en expecifico.
     *
     * Consulta utilizada para obtener la direccion de una persona en
     * particular.
     *
     * @return Retorna un conjunto de datos del tipo resultSet.
     */
    public synchronized static List<Direcciones> getDireccionByID(int id_persona) {
        List<Direcciones> direcciones = new ArrayList<>();
        final String sql
                = "SELECT ID, ID_PERSONA, ID_PROVINCIA, PROVINCIA, ID_MUNICIPIO, "
                + "     MUNICIPIO, ID_DISTRITO_MUNICIPAL, DISTRITO_MUNICIPAL, "
                + "     ID_CODIGO_POSTAL, CODIGO_POSTAL, DIRECCION, FECHA, "
                + "     ESTADO, POR_DEFECTO "
                + "FROM GET_DIRECCION_BY_ID  "
                + "WHERE ID_PERSONA = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setInt(1, id_persona);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    direcciones.add(
                            Direcciones.
                                    builder().
                                    id(rs.getInt("ID")).
                                    provincia(
                                            Provincias.builder().
                                                    id(rs.getInt("ID_PROVINCIA")).
                                                    nombre(rs.getString("PROVINCIA")).
                                                    build()
                                    ).
                                    municipio(
                                            Municipios.builder().
                                                    id(rs.getInt("ID_MUNICIPIO")).
                                                    nombre(rs.getString("MUNICIPIO")).build()
                                    ).
                                    distrito_municipal(
                                            Distritos_municipales.builder().
                                                    id(rs.getInt("ID_DISTRITO_MUNICIPAL")).
                                                    nombre(rs.getString("DISTRITO_MUNICIPAL")).build()
                                    ).
                                    direccion(rs.getString("DIRECCION")).
                                    fecha(rs.getDate("FECHA")).
                                    estado(rs.getBoolean("ESTADO")).
                                    por_defecto(rs.getBoolean("POR_DEFECTO")).
                                    build()
                    );
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return direcciones;
    }

    @Override
    public String toString() {
        return direccion;
    }
}
