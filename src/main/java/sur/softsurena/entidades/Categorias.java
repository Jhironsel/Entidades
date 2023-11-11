package sur.softsurena.entidades;

import java.io.File;
import java.sql.CallableStatement;
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
import sur.softsurena.utilidades.Utilidades;

@SuperBuilder
@Getter
public class Categorias implements Comparable {

    private static final Logger LOG = Logger.getLogger(Categorias.class.getName());

    private static final String CATEGORIA_AGREGADA_CON_EXITO = "Categoria agregada con exito.";
    private static final String CATEGORIA__BORRADO__CORRECTAMENTE = "Categoria Borrado Correctamente.";
    private static final String ERROR_AL_INSERTAR_CATEGORIA = "Error al insertar categoria.";

    private final Integer id_categoria;
    private final String descripcion;
    private final File pathImage;
    private final String image_texto;
    private final Date fecha_creacion;
    private final Boolean estado;
    private final String idUsuario;

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
    public synchronized static Resultados agregarCategoria(Categorias c) {
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

            return Resultados.builder().
                    id(-1).
                    mensaje(CATEGORIA_AGREGADA_CON_EXITO).
                    cantidad(cantidad).
                    build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    id(-1).
                    mensaje(ERROR_AL_INSERTAR_CATEGORIA).
                    cantidad(-1).build();
        }
    }

    /**
     * Metodo utilizado para modificar las categorias de los productos.
     * En este se puede modificar la descripción, la imagen de la categoria y
     * el estado de la categoria.
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
    public synchronized static Resultados modificarCategoria(Categorias c) {
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
            return Resultados.builder().
                    mensaje("Se modificó la categoria correctamente.").
                    cantidad(cantidad).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    mensaje("Error al modificar la categoria.").
                    cantidad(-1).
                    build();
        }
    }

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

            return Resultados.builder().
                    mensaje(CATEGORIA__BORRADO__CORRECTAMENTE).
                    cantidad(cant).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    mensaje("Ocurrio un error al intentar borrar la Categoria...").
                    cantidad(-1).
                    build();
        }
    }

    /**
     * Metodo utilizado para llenar los comboBox de las categorias de productos
     * del sistema, solo nos trae el Identificador y la descripcion de todas las
     * CATEGORIA del sistema.
     *
     * @return Devuelve una lista de categoria donde el estado es TRUE.
     *
     */
    public synchronized static List<Categorias> getCategirias() throws SQLException {
        List<Categorias> categoriaList = new ArrayList<>();

        final String SELECT
                = "SELECT ID, DESCRIPCION, FECHA_CREACION "
                + "FROM V_CATEGORIAS "
                + "WHERE ESTADO;";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT); 
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categoriaList.add(
                        Categorias.
                                builder().
                                id_categoria(rs.getInt("ID")).
                                descripcion(rs.getString("Descripcion")).
                                fecha_creacion(rs.getDate("FECHA_CREACION")).
                                build()
                );
            }
        } 
        
        return categoriaList;
    }

    /**
     * Metodo utilizado para obtener todas las categorias del sistema.
     *
     * Esta consulta nos trae todas las categorias registrada en el sistema.
     * Incluyendo los campos de la imagen y estado.
     *
     * Metodo creado 11 Julio 2022.
     *
     *
     * @return Devuelve un conjunto de datos de la tabla Categoria del sistema,
     * donde contiene todos los campos de la tabla.
     */
    public synchronized static List<Categorias> getCategorias() {
        final String sql
                = "SELECT ID, DESCRIPCION, IMAGEN_TEXTO, FECHA_CREACION, ESTADO "
                + "FROM V_CATEGORIAS "
                + "ORDER BY 1";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            List<Categorias> categorias = new ArrayList<>();

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    categorias.add( 
                            Categorias.builder().
                            id_categoria(rs.getInt("ID")).
                            descripcion(rs.getString("DESCRIPCION")).
                            image_texto(rs.getString("IMAGEN_TEXTO")).
                            estado(rs.getBoolean("ESTADO")).
                            fecha_creacion(rs.getDate("FECHA_CREACION"))
                            .build()
                    );
                }
                return categorias;
            } 
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
    public synchronized static List<Categorias> getCategoriaActivas() {
        /**
         * Esta consulta nos trae aquellas CATEGORIAS que se encuentra asignado
         * a un producto solamente.
         */
        final String sql
                = "SELECT ID, DESCRIPCION, IMAGEN_TEXTO " +
                  "FROM GET_CATEGORIA_ACTIVAS;";

        List<Categorias> categoriasList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    categoriasList.add(Categorias.builder().
                            id_categoria(rs.getInt("ID")).
                            descripcion(rs.getString("DESCRIPCION")).
                            image_texto(rs.getString("IMAGEN_TEXTO")).build());
                }

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
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
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public int compareTo(Object o) {
        Categorias c = (Categorias) o;

        return this.id_categoria.compareTo(c.id_categoria);
    }

}
