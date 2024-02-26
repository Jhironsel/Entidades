package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.ARS;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_ARS {
    
    /**
     * Metodo que nos permite obtener una lista de Seguros Sociales del sistema.
     *
     * @return retorna una lista completa de los seguros sociales del sistema.
     */
    public synchronized static List<ARS> getARS() {
        final String sql
                = "SELECT ID, DESCRIPCION, COVERTURA_CONSULTA_PORCIENTO, ESTADO, "
                + "CANTIDAD_REGISTRO "
                + "FROM V_ARS ";

        List<ARS> arsList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    arsList.add(
                            ARS
                                    .builder()
                                    .id(rs.getInt("ID"))
                                    .descripcion(rs.getString("DESCRIPCION"))
                                    .covertura(rs.getBigDecimal("COVERTURA_CONSULTA_PORCIENTO"))
                                    .estado(rs.getBoolean("ESTADO"))
                                    .cantidad_registro(rs.getInt("CANTIDAD_REGISTRO"))
                                    .build()
                    );
                }
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
        Resultados r;
        try (PreparedStatement ps = getCnn().prepareStatement(
                DELETE,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setInt(1, idARS);

            int cantidad = ps.executeUpdate();

            r = Resultados
                    .builder()
                    .cantidad(cantidad)
                    .id(-1)
                    .mensaje(BORRADO_CORRECTAMENTE)
                    .build();

            return r;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados
                    .builder()
                    .cantidad(-1)
                    .id(-1)
                    .mensaje(ERROR_AL_BORRAR_ARS)
                    .build();
            return r;
        }
    }
    public static final String ERROR_AL_BORRAR_ARS = "Error al borrar ARS";
    public static final String BORRADO_CORRECTAMENTE = "Borrado correctamente.";

    /**
     * Procedimiento que permite agregar los seguros de los paciente al sistema.
     *
     * @param ars Es un objecto que contiene la informacion de los seguros en el
     * sistema.
     *
     * @return
     */
    public synchronized static Resultados agregarSeguro(ARS ars) {
        final String INSERT
                = "SELECT O_ID FROM SP_INSERT_ARS (?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {

            ps.setString(1, ars.getDescripcion());
            ps.setBigDecimal(2, ars.getCovertura());
            ps.setBoolean(3, ars.getEstado());

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return Resultados
                            .builder()
                            .id(rs.getInt("O_ID"))
                            .mensaje(SEGURO_AGREGADO_CORRECTAMENTE)
                            .build();
                } else {
                    return Resultados
                            .builder()
                            .id(-1)
                            .mensaje(ERROR_AL_INSERTAR__SEGURO)
                            .build();
                }

            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR__SEGURO)
                    .build();
        }
    }
    public static final String SEGURO_AGREGADO_CORRECTAMENTE = "Seguro agregado correctamente";
    public static final String ERROR_AL_INSERTAR__SEGURO = "Error al insertar Seguro...";

    /**
     *
     * @param ars
     * @return
     */
    public synchronized static Resultados modificarSeguro(ARS ars) {
        String sql = "EXECUTE PROCEDURE SP_UPDATE_ARS (?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, ars.getId());
            ps.setString(2, ars.getDescripcion());
            ps.setBigDecimal(3, ars.getCovertura());
            ps.setBoolean(4, ars.getEstado());

            ps.executeUpdate();
            return Resultados
                    .builder()
                    .mensaje(SEGURO_MODIFICADO_CORRECTAMENTE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);

            return Resultados
                    .builder()
                    .mensaje(ERROR_AL_MODIFICAR_SEGURO)
                    .build();
        }
    }
    public static final String SEGURO_MODIFICADO_CORRECTAMENTE = "Seguro modificado correctamente";
    public static final String ERROR_AL_MODIFICAR_SEGURO = "Error al modificar seguro...";

    /**
     * Este metodo proporciona la información de los seguro que están en estado
     * activo en la base de datos, Llenando asi los comboBox de la aplicacion.
     *
     * @return devuelve la lista de seguro que existe en la base de datos
     */
    public synchronized static List<ARS> getTipoSeguro() {
        final String sql = "SELECT ID, DESCRIPCION "
                + "FROM V_ARS "
                + "WHERE ESTADO; ";

        List<ARS> arsList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    arsList.add(
                            ARS
                                    .builder()
                                    .id(rs.getInt("ID"))
                                    .descripcion(rs.getString("DESCRIPCION").strip())
                                    .build()
                    );
                }
            }
            return arsList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
