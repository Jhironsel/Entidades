package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Codigo_Postal;
import sur.softsurena.entidades.Direccion;
import sur.softsurena.entidades.Distrito_municipal;
import sur.softsurena.entidades.Municipio;
import sur.softsurena.entidades.Provincia;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Direccion {

    /**
     * Metodo utilizado para agregar una lista de direcciones del cliente al
     * sistema.
     *
     * @param direccion Objecto de la clase direccion de una persona.
     *
     * @return Devuelve true si la operacion fue exitosa, false caso contrario.
     */
    public static Resultado agregarDireccion(Direccion direccion) {
        final String sql
                = "SELECT O_ID FROM SP_I_DIRECCION(?,?,?,?,?,?,?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            ps.setInt(1, direccion.getId_persona());
            ps.setInt(2, direccion.getProvincia().getId());
            ps.setInt(3, direccion.getMunicipio().getId());
            ps.setInt(4, direccion.getDistrito_municipal().getId());
            ps.setInt(
                    5,
                    Objects.isNull(direccion.getCodigo_postal())
                    ? 0 : direccion.getCodigo_postal().getId()
            );
            ps.setString(6, direccion.getDireccion());
            ps.setBoolean(7, direccion.getPor_defecto());
            
            ResultSet rs = ps.executeQuery();

            rs.next();
            return Resultado
                    .builder()
                    .id(rs.getInt(1))
                    .mensaje(DIRECCION_AGREGADA_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_INSERTAR_O_MODIFICAR_DIRECCION,
                    ex
            );
        }
        return Resultado
                .builder()
                .id(-1)
                .mensaje(ERROR_AL_INSERTAR_O_MODIFICAR_DIRECCION)
                .icono(JOptionPane.ERROR_MESSAGE)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String ERROR_AL_INSERTAR_O_MODIFICAR_DIRECCION
            = "Error al insertar o modificar direccion.";
    public static final String DIRECCION_AGREGADA_CORRECTAMENTE
            = "Direccion agregada o modificada correctamente.";

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
    public synchronized static List<Direccion> getDireccionByID(Integer id_persona) {
        List<Direccion> direcciones = new ArrayList<>();
        final String sql
                = "SELECT ID, ID_PROVINCIA, PROVINCIA, ID_MUNICIPIO, "
                + "     MUNICIPIO, ID_DISTRITO_MUNICIPAL, DISTRITO_MUNICIPAL, "
                + "     ID_CODIGO_POSTAL, CODIGO_POSTAL, DIRECCION, FECHA, "
                + "     ESTADO, POR_DEFECTO "
                + "FROM GET_DIRECCION_BY_ID  "
                + (Objects.isNull(id_persona) ? "ROWS (10);" : "WHERE ID_PERSONA = ?;");
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            if (!Objects.isNull(id_persona)) {
                ps.setInt(1, id_persona);
            }
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    direcciones.add(
                            Direccion
                                    .builder()
                                    .id(rs.getInt("ID"))
                                    .provincia(
                                            Provincia
                                                    .builder()
                                                    .id(rs.getInt("ID_PROVINCIA"))
                                                    .nombre(rs.getString("PROVINCIA"))
                                                    .build()
                                    )
                                    .municipio(
                                            Municipio
                                                    .builder()
                                                    .id(rs.getInt("ID_MUNICIPIO"))
                                                    .nombre(rs.getString("MUNICIPIO"))
                                                    .build()
                                    )
                                    .distrito_municipal(
                                            Distrito_municipal
                                                    .builder()
                                                    .id(rs.getInt("ID_DISTRITO_MUNICIPAL"))
                                                    .nombre(rs.getString("DISTRITO_MUNICIPAL"))
                                                    .build()
                                    )
                                    .codigo_postal(
                                            Codigo_Postal
                                                    .builder()
                                                    .id(rs.getInt("ID_CODIGO_POSTAL"))
                                                    .codigo_postal(rs.getInt("CODIGO_POSTAL"))
                                                    .build()
                                    )
                                    .direccion(rs.getString("DIRECCION"))
                                    .fecha(rs.getDate("FECHA"))
                                    .estado(rs.getBoolean("ESTADO"))
                                    .por_defecto(rs.getBoolean("POR_DEFECTO"))
                                    .build()
                    );
                }
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_REALIZAR_LA_CONSULTA_EN_LA_VISTA,
                    ex
            );
        }
        return direcciones;
    }
    public static final String ERROR_AL_REALIZAR_LA_CONSULTA_EN_LA_VISTA
            = "Error al realizar la consulta en la vista GET_DIRECCION_BY_ID.";
}
