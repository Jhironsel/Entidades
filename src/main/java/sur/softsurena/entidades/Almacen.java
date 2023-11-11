package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@Getter
@SuperBuilder
public class Almacen {

    private static final Logger LOG = Logger.getLogger(Almacen.class.getName());
    private final Integer id;
    private final String nombre;
    private final String ubicacion;
    private final Boolean estado;
    
    public synchronized static List<Almacen> getAlmacenesList(int id, 
            String criterioBusqueda) {
        final String sql
                = "SELECT ID, NOMBRE, UBICACION, ESTADO "
                + "FROM V_ALMACENES "
                + "WHERE ID = ? OR UPPER(NOMBRE) LIKE UPPER(?);";

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
                            Almacen.
                                    builder().
                                    id(rs.getInt("ID")).
                                    nombre(rs.getString("NOMBRE")).
                                    ubicacion(rs.getString("UBICACION")).
                                    estado(rs.getBoolean("ESTADO")).
                                    build()
                    );
                }
                return almacenList;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    
    
    public synchronized static Resultados agregarAlmacen(Almacen almacen) {
        final String sql
            = "EXECUTE PROCEDURE SP_INSERT_ALMACEN (?, ?, ?);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql, 
                ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)){

            ps.setString(1, almacen.getNombre());
            ps.setString(2, almacen.getUbicacion());
            ps.setBoolean(3, almacen.getEstado());

            ps.executeUpdate();
            return Resultados.
                    builder().
                    mensaje("Almacen agregado correctamente").
                    icono(JOptionPane.INFORMATION_MESSAGE).
                    estado(Boolean.TRUE).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.
                    builder().
                    mensaje("Error al insertar Almacen...").
                    icono(JOptionPane.ERROR_MESSAGE).
                    estado(Boolean.FALSE).
                    build();
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}