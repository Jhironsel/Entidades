package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Almacen;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Almacen {

    public synchronized static List<Almacen> getAlmacenesList(int id,
            String criterioBusqueda) {
        final String sql
                = "SELECT ID, NOMBRE, UBICACION, ESTADO "
                + "FROM V_ALMACENES "
                + "WHERE ID = ? OR UPPER(NOMBRE) STARTING WITH UPPER(?);";

        List<Almacen> almacenList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

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
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para agregar almacenes fisico o virtuales de las
     * mercancias.
     *
     * @param almacen
     * @return
     */
    public synchronized static Resultados agregarAlmacen(Almacen almacen) {
        final String sql
                = "SELECT O_ID FROM SP_INSERT_ALMACEN (?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, almacen.getNombre());
            ps.setString(2, almacen.getUbicacion());
            ps.setBoolean(3, almacen.getEstado());

            try (ResultSet rs = ps.executeQuery();) {

                rs.next();

                return Resultados
                        .builder()
                        .id(rs.getInt("O_ID"))
                        .mensaje(ALMACEN_AGREGADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR__ALMACEN)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR__ALMACEN = "Error al insertar Almacen...";
    public static final String ALMACEN_AGREGADO_CORRECTAMENTE = "Almacen agregado correctamente";
}
