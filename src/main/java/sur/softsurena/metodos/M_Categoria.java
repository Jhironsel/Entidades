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
import sur.softsurena.utilidades.Resultados;
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
     * Actualizado el dia 06 de Julio del 2022: Se agrega el campo static
     * INSERT_CATEGORIA.
     *
     * Actualizado el dia 09 de Julio del 2022: Se cambia el tipo de datos
     * devueltode boolean a String, que permite mostrar mensaje del estado del
     * metodo despues de hacer las operaciones.
     *
     * @test agregarCategoria(), metodo creado para realizar pruebas al metodo.
     *
     * @param c Es un objeto de la clase Categoria que contiene los metodos y
     * atributos.
     *
     * @return Retorna un mensaje que permite saber si la categoria fue agregada
     * o no.
     */
    public synchronized static Resultados agregarCategoria(Categoria c) {
        final String sql
                = "EXECUTE PROCEDURE SP_INSERT_CATEGORIAS(?,?,?)";

        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, c.getDescripcion());
            cs.setString(2, Utilidades.imagenEncode64(c.getPathImage()));
            cs.setBoolean(3, c.getEstado());

            int cantidad = cs.executeUpdate();

            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(CATEGORIA_AGREGADA_CON_EXITO)
                    .cantidad(cantidad)
                    .estado(Boolean.TRUE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR_CATEGORIA)
                    .cantidad(-1)
                    .estado(Boolean.FALSE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
    private static final String CATEGORIA_AGREGADA_CON_EXITO = "Categoria agregada con exito.";
    private static final String ERROR_AL_INSERTAR_CATEGORIA = "Error al insertar categoria.";

    /**
     * Metodo utilizado para modificar las categorias de los productos. En este
     * se puede modificar la descripción, la imagen de la categoria y el estado
     * de la categoria.
     *
     * Actualizacion dia 09 julio 2022: Nota, Este metodo se modifica para que
     * devuelta valores de tipo String que indique si el registro fue modificado
     * o no.
     *
     * @param c Este objecto de la clase Categoria del sistema.
     *
     * @return Retorna un valor de tipo String que indica si la operación se
     * realizo con exito si o no.
     *
     */
    public synchronized static Resultados modificarCategoria(Categoria c) {
        final String sql
                = "EXECUTE PROCEDURE SP_UPDATE_CATEGORIA (?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setInt(1, c.getId_categoria());
            ps.setString(2, c.getDescripcion());
            ps.setString(3, Utilidades.imagenEncode64(c.getPathImage()));
            ps.setBoolean(4, c.getEstado());

            int cantidad = ps.executeUpdate();
            return Resultados
                    .builder()
                    .mensaje(SE_MODIFICÓ_LA_CATEGORIA_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .cantidad(cantidad)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .mensaje(ERROR_AL_MODIFICAR_LA_CATEGORIA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .cantidad(-1)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_MODIFICAR_LA_CATEGORIA = "Error al modificar la categoria.";
    public static final String SE_MODIFICÓ_LA_CATEGORIA_CORRECTAMENTE = "Se modificó la categoria correctamente.";

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
    public static Resultados borrarCategoria(int idCategoria) {
        final String sql
                = "EXECUTE PROCEDURE SP_DELETE_CATEGORIAS (?);";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            int cant = ps.executeUpdate();

            return Resultados
                    .builder()
                    .mensaje(CATEGORIA__BORRADO__CORRECTAMENTE)
                    .cantidad(cant)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .mensaje(OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__CA)
                    .cantidad(-1)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
    public static final String OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__CA = "Ocurrio un error al intentar borrar la Categoria...";
    private static final String CATEGORIA__BORRADO__CORRECTAMENTE = "Categoria Borrado Correctamente.";


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
     *  categorias del sistema.
     * @param foto Bandera que permite indicar al metodo si incluye foto de las 
     *  categorias.
     * 
     * @return Devuelve un conjunto de datos de la tabla Categoria del sistema,
     * donde contiene todos los campos de la tabla.
     */
    public synchronized static List<Categoria> getCategorias(Boolean estado, boolean foto) {
        List<Categoria> categorias = new ArrayList<>();
        final String sql
                = "SELECT ID, DESCRIPCION, FECHA_CREACION, ESTADO "+(foto ? ", IMAGEN_TEXTO ":"")
                + "FROM V_CATEGORIAS "
                + "WHERE ID > 0 "+(Objects.isNull(estado) ? " ": estado ? " AND ESTADO ":" AND ESTADO IS FALSE ")
                + "ORDER BY 1;";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT); ResultSet rs = ps.executeQuery();) {
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
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

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
        final String SELECT_CATEGORIA_DESCRIPCION
                = "SELECT (1) FROM V_CATEGORIAS WHERE descripcion like ?";

        try (PreparedStatement ps = getCnn().prepareStatement(SELECT_CATEGORIA_DESCRIPCION)) {
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
