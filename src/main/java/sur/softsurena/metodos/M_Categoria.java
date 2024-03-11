package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.utilidades.Resultado;
import sur.softsurena.utilidades.Utilidades;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 * Esta clase ayuda al sistema a gestionar las categorias del sistemas.
 *
 * - Permite agregar nuevas categorias con el metodo agregarCategoria(Categorias
 * c).
 *
 * - Permite modificar las categorias existente con el metodo
 * modificarCategoria(Categorias c).
 *
 * - Permite eliminar las categorias con el metodo borrarCategoria(int
 * idCategoria).
 *
 * - Permite obtener un listado de categorias del sistema, usando el metodo
 * getCategorias(), getCategoriaActivas().
 *
 * Permite verificar la existencia de una categoria en el sistema. Dicho metodo
 * es existeCategoria(String descripcion).
 *
 * @author jhironsel
 */
public class M_Categoria {

    /**
     * Agregar las categorias de los productos a la base de datos en la tabla
     * Categoria.
     *
     * @test agregarCategoria(), metodo creado para realizar pruebas al metodo.
     *
     * @param categoria Es un objeto de la clase Categoria que contiene los
     * metodos y atributos.
     *
     * @return Retorna un mensaje que permite saber si la categoria fue agregada
     * o no.
     */
    public synchronized static Resultado agregarCategoria(Categoria categoria) {
        final String sql
                = "SELECT V_ID FROM SP_INSERT_CATEGORIA(?, ?, ?)";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, categoria.getDescripcion());
            ps.setString(2, Utilidades.imagenEncode64(categoria.getPathImage()));
            ps.setBoolean(3, categoria.getEstado());

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Resultado
                    .builder()
                    .id(rs.getInt("V_ID"))
                    .mensaje(CATEGORIA_AGREGADA_CON_EXITO)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR_CATEGORIA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    private static final String CATEGORIA_AGREGADA_CON_EXITO
            = "Categoria agregada con exito.";
    private static final String ERROR_AL_INSERTAR_CATEGORIA
            = "Error al insertar categoria.";

    /**
     * Metodo utilizado para modificar las categorias de los productos. En este
     * se puede modificar la descripción, la imagen de la categoria y el estado
     * de la categoria.
     *
     * @param categoria Este objecto de la clase Categoria del sistema.
     *
     * @return Retorna un valor de tipo String que indica si la operación se
     * realizo con exito si o no.
     */
    public synchronized static Resultado modificarCategoria(Categoria categoria) {
        final String sql
                = "EXECUTE PROCEDURE SP_UPDATE_CATEGORIA (?,?,?,?)";

        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setInt(1, categoria.getId_categoria());
            cs.setString(2, categoria.getDescripcion());
            cs.setString(3, Utilidades.imagenEncode64(categoria.getPathImage()));
            cs.setBoolean(4, categoria.getEstado());

            cs.executeUpdate();

            return Resultado
                    .builder()
                    .mensaje(SE_MODIFICÓ_LA_CATEGORIA_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_MODIFICAR_LA_CATEGORIA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_MODIFICAR_LA_CATEGORIA
            = "Error al modificar la categoria.";
    public static final String SE_MODIFICÓ_LA_CATEGORIA_CORRECTAMENTE
            = "Se modificó la categoria correctamente.";

    /**
     * Metodo para eliminar las categorias de la tablas V_CATEGORIAS. Para
     * eliminar una categoria, ningun producto debe estar relacionado con esta
     * categoria a eliminar.
     *
     * Actualizado el 17/05/2022. Actualizado el 05/06/2022. Nota: se le agrega
     * la cantidad de registros afectos al mensaje.
     *
     * @param idCategoria Es el identificador del registro de la categorias.
     *
     * @return Devuelve un mensaje de la acción realizada.
     */
    public static Resultado borrarCategoria(int idCategoria) {
        final String sql
                = "EXECUTE PROCEDURE SP_DELETE_CATEGORIA(?)";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idCategoria);

            ps.executeUpdate();

            return Resultado
                    .builder()
                    .mensaje(CATEGORIA__BORRADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__CA.formatted(idCategoria), 
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__CA.formatted(idCategoria))
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    private static final String CATEGORIA__BORRADO__CORRECTAMENTE
            = "Categoria Borrado Correctamente.";

    public static final String OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__CA
            = "Ocurrio un error al intentar borrar la Categoria {%s}...";

    /**
     * Metodo utilizado para obtener todas las categorias del sistema.
     *
     * Esta consulta nos trae todas las categorias registrada en el sistema.
     * Incluyendo los campos de la imagen y estado.
     *
     * Metodo creado 11 Julio 2022.
     *
     *
     * @param estado Bandera que permite obtener un los estados de las
     * categorias del sistema.
     * @param foto Bandera que permite indicar al metodo si incluye foto de las
     * categorias.
     *
     * @return Devuelve un conjunto de datos de la tabla Categoria del sistema,
     * donde contiene todos los campos de la tabla.
     */
    public synchronized static List<Categoria> getCategorias(Boolean estado, boolean foto) {
        List<Categoria> categorias = new ArrayList<>();
        final String sql
                = "SELECT ID, DESCRIPCION, FECHA_CREACION, ESTADO " + (foto ? ", IMAGEN_TEXTO " : "")
                + "FROM V_CATEGORIAS "
                + "WHERE ID > 0 " + (Objects.isNull(estado) ? " " : estado ? " AND ESTADO " : " AND ESTADO IS FALSE ")
                + "ORDER BY 1;";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categorias.add(Categoria
                        .builder()
                        .id_categoria(rs.getInt("ID"))
                        .descripcion(rs.getString("DESCRIPCION"))
                        .fecha_creacion(rs.getDate("FECHA_CREACION"))
                        .estado(rs.getBoolean("ESTADO"))
                        .image_texto(foto ? rs.getString("IMAGEN_TEXTO") : "")
                        .build()
                );
            }
            return categorias;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que nos permite tener el conjunto de datos de las categorias que
     * estan activas y con un producto que está activo y enlazado a una
     * categoria.
     *
     * @return Retorna un listado de datos de tipo List.
     */
    public synchronized static List<Categoria> getCategoriaActivas() {
        final String sql
                = "SELECT ID, DESCRIPCION, IMAGEN_TEXTO "
                + "FROM GET_CATEGORIA_ACTIVAS "
                + "WHERE ID > 0;";
        List<Categoria> categoriasList = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    categoriasList.add(Categoria.builder().
                            id_categoria(rs.getInt("ID")).
                            descripcion(rs.getString("DESCRIPCION")).
                            image_texto(rs.getString("IMAGEN_TEXTO")).build());
                }
            }
            return categoriasList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que permite investigar si existe una descripcion de una categoria
     * ya existente.
     *
     * Metodo actualizado, 06 julio 2022.: Se le aplicó una restructuración
     * completa al metodo llevando el sql a la clase categoria.
     *
     * @param descripcion Es la descripcion que se pretende dar a la categoria
     * la cual este metodo verifica de comprobar si existe o no. devolviendo
     * true si existe o false si no existe.
     *
     * @return Retorna un valor boolean indicando si existe o no la descripcion
     * de la categoria que se le pretende dar.
     */
    public synchronized static Boolean existeCategoria(String descripcion) {
        final String sql
                = "SELECT (1) FROM V_CATEGORIAS WHERE DESCRIPCION STARTING WITH ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, descripcion);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
