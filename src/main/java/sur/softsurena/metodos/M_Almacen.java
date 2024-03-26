package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Almacen;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Almacen {

    public synchronized static List<Almacen> getAlmacenesList(int id,
            String criterioBusqueda) {
        final String sql
                = "SELECT "
                + "     ID, "
                + "     NOMBRE, "
                + "     UBICACION, "
                + "     ESTADO "
                + "FROM V_ALMACENES "
                + "WHERE ID = ? OR UPPER(NOMBRE) STARTING WITH UPPER(?);";

        List<Almacen> almacenList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, id);
            ps.setString(2, criterioBusqueda);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    almacenList.add(
                            Almacen
                                    .builder()
                                    .id(rs.getInt("ID"))
                                    .nombre(rs.getString("NOMBRE"))
                                    .ubicacion(rs.getString("UBICACION"))
                                    .estado(rs.getBoolean("ESTADO"))
                                    .build()
                    );
                }
                return almacenList;
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    "Error al consultar la vista V_ALMACENES del sistema.", 
                    ex
            );
            return almacenList;
        }
    }

    /**
     * Metodo utilizado para agregar almacenes fisico o virtuales de las
     * mercancias.
     *
     * @param almacen
     * @return
     */
    public synchronized static Resultado agregarAlmacen(Almacen almacen) {
        final String sql
                = "SELECT O_ID FROM SP_I_ALMACEN (?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, almacen.getNombre());
            ps.setString(2, almacen.getUbicacion());
            ps.setBoolean(3, almacen.getEstado());

            try (ResultSet rs = ps.executeQuery();) {

                rs.next();

                return Resultado
                        .builder()
                        .id(rs.getInt("O_ID"))
                        .mensaje(ALMACEN_AGREGADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR__ALMACEN)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR__ALMACEN
            = "Error al insertar Almacen...";
    public static final String ALMACEN_AGREGADO_CORRECTAMENTE 
            = "Almacen agregado correctamente";
    
    /**
     * Elimina los registro de almacenes en el sistema.
     * 
     * Para poder eliminar un almacen este no debe contener productos registrado
     * o relacionado. 
     * 
     * 
     * @param id
     * @return 
     */
    public synchronized static Resultado eliminarAlmacen(int id) {
        final String sql
                = "EXECUTE PROCEDURE SP_D_ALMACEN(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setInt(1, id);
            cs.execute();
            return Resultado
                    .builder()
                    .mensaje(ALMACEN_ELIMINADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_ELIMINAR_ALMACEN, 
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_ELIMINAR_ALMACEN)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ALMACEN_ELIMINADO_CORRECTAMENTE 
            = "Almacen eliminado correctamente.";
    public static final String ERROR_AL_ELIMINAR_ALMACEN 
            = "Error al eliminar almacen.";
    
    public synchronized static Resultado actualizarAlmacen(Almacen almacen) {
        final String sql
                = "EXECUTE PROCEDURE SP_U_ALMACEN(?,?,?,?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setInt(1, almacen.getId());
            cs.setString(1, almacen.getNombre());
            cs.setString(1, almacen.getUbicacion());
            cs.setBoolean(1, almacen.getEstado());
            cs.execute();
            return Resultado
                    .builder()
                    .mensaje(ALMACEN_ACTUALIZADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ERROR_AL_ELIMINAR_ALMACEN, ex);
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_ACTUALIZAR_EL_REGISTRO_DEL_ALMAC)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
    public static final String ERROR_AL_ACTUALIZAR_EL_REGISTRO_DEL_ALMAC 
            = "Error al actualizar el registro del almacen.";
    public static final String ALMACEN_ACTUALIZADO_CORRECTAMENTE 
            = "Almacen actualizado correctamente.";
}
