package sur.softsurena.entidades;

import java.io.File;
import java.math.BigDecimal;
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

@SuperBuilder
@Getter
public class Productos {

    private static final Logger LOG = Logger.getLogger(Productos.class.getName());

    private final Integer id;
    private final Categorias categoria;
    private final String codigo;
    private final String descripcion;
    private final File pathImagen;
    private final String imagenProducto;
    private final String nota;
    private final Date fechaCreacion;
    private final Boolean estado;
    private final String userName;
    private final String rol;

    /**
     * Metodo utilizado para obtener los productos ya sea por su ID, Codigo o
     * Descripcion de los registros de la tabla productos.
     *
     * @param criterio Este valor puede ser el identificador, codigo o
     * descripcion del producto.
     *
     * @return Devuelve un conjunto de datos con los criterio de la busqueda
     * espeficicada.
     *
     */
    public static List<Productos> buscarProducto(String criterio) {
        final String sql
                = "SELECT p.ID, p.CODIGO, p.DESCRIPCION "
                + "FROM SP_SELECT_PRODUCTOS_FIND (?,?,?) p;";

        List<Productos> productosList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            Integer id = null;
            try {
                id = Integer.parseInt(criterio);
            } catch (java.lang.NumberFormatException e) {
                id = -1;
            }
            ps.setInt(1, id);
            ps.setString(2, criterio);
            ps.setString(3, criterio);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    productosList.add(
                            Productos.builder().
                                    id(rs.getInt("ID")).
                                    codigo(rs.getString("CODIGO")).
                                    descripcion(rs.getString("DESCRIPCION")).build());
                }

                return productosList;

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que permite recuperar las propiedades de los productos del
     * sistema. Devolviendo asi un Listado de productos con todas sus
     * propiedades.
     *
     * OJO! Deberia investigar la cantidad o existencia del producto.
     *
     * Fecha de Actualización el 19/05/2022.
     *
     * @return Devuelve un conjunto de datos de la tabla de los productos del
     * sistema.
     */
    public synchronized static List<Productos> getProductos(Integer nPaginaNro, Integer nCantidadFilas) {
        List<Productos> listaProducto = new ArrayList<>();

        final String SELECT
                = "SELECT ID, ID_CATEGORIA, DESC_CATEGORIA, CODIGO, DESCRIPCION,"
                + "      NOTA, FECHA_CREACION, ESTADO "
                + "FROM SP_SELECT_GET_PRODUCTOS "
                + "ROWS (? - 1) * ? + 1 TO (? + (1 - 1)) * ?;";

        try (PreparedStatement ps = getCnn().prepareStatement(SELECT)) {
            ps.setInt(1, nPaginaNro);
            ps.setInt(2, nCantidadFilas);
            ps.setInt(3, nPaginaNro);
            ps.setInt(4, nCantidadFilas);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    listaProducto.add(Productos.
                            builder().
                            id(rs.getInt("ID")).
                            descripcion(rs.getString("DESCRIPCION")).
                            categoria(Categorias.
                                    builder().
                                    id_categoria(rs.getInt("ID_CATEGORIA")).
                                    descripcion(rs.getString("DESC_CATEGORIA")).
                                    build()).
                            codigo(rs.getString("CODIGO")).
                            nota(rs.getString("NOTA")).
                            fechaCreacion(rs.getDate("FECHA_CREACION")).
                            estado(rs.getBoolean("ESTADO")).
                            build());
                }
                return listaProducto;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static Productos getProductoById(Integer idProducto,
            String codigo) {
        final String sql
                = "SELECT ID, CODIGO, ID_CATEGORIA, DESCRIPCION, IMAGEN_TEXTO, "
                + "         NOTA, FECHA_CREACION, ESTADO "
                + "FROM V_PRODUCTOS "
                + "WHERE ID = ? OR CODIGO = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {

            if (idProducto == null) {
                idProducto = -1;
            }
            ps.setInt(1, idProducto);
            ps.setString(2, codigo);

            try (ResultSet rs = ps.executeQuery();) {

                if (rs.next()) {
                    return Productos.builder().
                            id(rs.getInt("ID")).
                            codigo(rs.getString("CODIGO")).
                            categoria(
                                    Categorias.
                                            builder().
                                            id_categoria(rs.getInt("ID_CATEGORIA")).
                                            build()
                            ).
                            descripcion(rs.getString("DESCRIPCION")).
                            imagenProducto(rs.getString("IMAGEN_TEXTO")).
                            nota(rs.getString("NOTA")).
                            fechaCreacion(rs.getDate("FECHA_CREACION")).
                            estado(rs.getBoolean("ESTADO"))
                            .build();
                } else {
                    return Productos.builder().id(-1).descripcion("Producto no encontrado.").build();
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static List<Productos> getProductosByCategoria(int idCategoria, boolean estado) {
        final String sql
                = "SELECT r.ID, r.DESCRIPCION, r.IMAGEN_PRODUCTO "
                + "FROM GET_PRODUCTOS r "
                + "WHERE r.ID_CATEGORIA = ? "+(!estado ? " AND r.ESTADO;":";");

        List<Productos> productosList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idCategoria);
            try (ResultSet rs = ps.executeQuery();) {
                while(rs.next()) {
                    productosList.add(
                            Productos.builder().
                                    id(rs.getInt("ID")).
                                    descripcion(rs.getString("descripcion")).
                                    imagenProducto(rs.getString("imagen_producto")).
                                    build()
                    );
                }
            } 
            return productosList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * Metodo utilizado para eliminar los productos del sistema, este solo
     * necesita de su ID o codigo de barra para localizarlo en la vista de
     * V_PRODUCTOS.
     *
     * @param ID Identificador o codigo del Producto en la vista de V_PRODUCTOS.
     *
     *
     * @return Devuelve un mensaje que indica como resultado de la acción.
     */
    public synchronized static String borrarProductoPorID(Integer ID) {
        final String sql
                = "EXECUTE PROCEDURE SP_DELETE_PRODUCTO (?)";

        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setInt(1, ID);

            cs.execute();

            return "Producto Borrado Correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Ocurrio un error al intentar borrar el Producto...";
        }
    }

    /**
     * Agregar producto a la base de datos en la tabla productos.
     *
     * Actualizado el dia 13 Abril del 2022.
     *
     * Actualizado el dia 09 Julio del 2022, Nota: se ha llevado el SQL a la
     * clase producto y por lo tanto se utiliza el nombre del campo que contiene
     * el SQL de la consulta.
     *
     * @test agregarProducto() metodo que realiza la prueba unitaria del metodo.
     *
     * @param p Objecto de la clase producto que permite obtener los valos de
     * del producto agregar.
     *
     * @return Devuelve un mensaje que notifica si el producto fue agregado
     * correctamente o no.
     */
    public synchronized static Resultados agregarProducto(Productos p) {
        Resultados r;
        /**
         * Consulta que permite agregar registro de productos al sistema.
         */
        final String sql
                = "EXECUTE PROCEDURE SP_INSERT_PRODUCTO(?,?,?,?,?,?)";

        try (CallableStatement ps = getCnn().prepareCall(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setInt(1, p.getCategoria().getId_categoria());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, p.getImagenProducto());
            ps.setString(5, p.getNota());
            ps.setBoolean(6, p.getEstado());

            ps.executeUpdate();

            r = Resultados.builder().
                    id(-1).
                    mensaje(PRODUCTO_AGREGADO_CORRECTAMENTE).
                    cantidad(-1).build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados.builder().
                    id(-1).
                    mensaje("Error al Insertar Producto.").
                    cantidad(-1).build();
        }
        return r;
    }
    /**
     * Variable utilizar que indica cuando un producto ha sido agregado
     * correctamente.
     */
    public static final String PRODUCTO_AGREGADO_CORRECTAMENTE = "Producto agregado correctamente.";

    /**
     * Metodo que permite modificar los productos del sistema como a la
     * categoria a la que pertenece el producto, su codigo de barra, la
     * descripcion, la imagen de este, la nota del producto y su estado.
     *
     * Metodo actualizado el dia 23 de abril, segun la vista productos.
     *
     * @param p perteneciente a la clase Producto, define los productos del
     * sistema.
     *
     * @return
     */
    public synchronized static String modificarProducto(Productos p) {
        final String sql
                = "EXECUTE PROCEDURE SP_UPDATE_PRODUCTO (?,?,?,?,?,?,?)";
        try (CallableStatement ps = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, p.getId());
            ps.setInt(2, p.getCategoria().getId_categoria());
            ps.setString(3, p.getCodigo());
            ps.setString(4, p.getDescripcion());
            ps.setString(5, p.getImagenProducto());
            ps.setString(6, p.getNota());
            ps.setBoolean(7, p.getEstado());

            ps.executeUpdate();
            return PRODUCTO__MODIFICADO__CORRECTAMENTE;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "Error al Modificar Producto...";
    }
    /**
     * Variable utilizar que indica cuando un producto ha sido modificado
     * correctamente.
     */
    public static final String PRODUCTO__MODIFICADO__CORRECTAMENTE = "Producto Modificado Correctamente";

    /**
     * Metodo que nos permite verificar si una categoria esta asociada a un
     * producto del sistema, la cual no se permite su eliminacion.
     *
     * @param idCategoria
     *
     * @return
     */
    public synchronized static boolean existeCategoriaProductos(int idCategoria) {
        final String EXISTE_CATEGORIA
                = "SELECT (1) "
                + "FROM RDB$DATABASE "
                + "WHERE EXISTS ("
                + "              SELECT (1) "
                + "              FROM V_PRODUCTOS p "
                + "              WHERE p.ID_CATEGORIA = ?"
                + "             )";

        try (PreparedStatement ps = getCnn().prepareStatement(EXISTE_CATEGORIA)) {
            ps.setInt(1, idCategoria);
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

    /**
     * Metodo que verifica la existencia de un producto por su codigo de barra o
     * descripción. Metodo actualizado el 26 de abril 2022
     *
     * @param criterio este valor representa el valor del codigo de barra del
     * producto o nombre de 25 caracteres.
     * @return
     */
    public synchronized static boolean existeProducto(String criterio) {
        final String EXISTE_PRODUCTO
                = "SELECT (1) "
                + "FROM RDB$DATABASE "
                + "WHERE EXISTS(SELECT (1) "
                + "             FROM V_PRODUCTOS "
                + "             WHERE codigo LIKE ? or "
                + "                    descripcion LIKE ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(EXISTE_PRODUCTO)) {

            ps.setString(1, criterio);
            ps.setString(2, criterio);

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

    /**
     * Metodo que devuelve el precio de producto actual.
     *
     * @param idProducto identificador del producto.
     * @return
     */
    public synchronized static BigDecimal getPrecioProducto(int idProducto) {

        String sql = "";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("precio");
                } else {
                    return new BigDecimal(0);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return new BigDecimal(-1);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return new BigDecimal(-1);
        }
    }

    public static String generarProducto() {
        StringBuilder telefonoMovil = new StringBuilder();

        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);

        telefonoMovil.
                append("Producto de prueba ").
                append(num1).
                append(num2).
                append(num3).
                append(num4);

        return telefonoMovil.toString();
    }

    public static String generarCodigoBarra() {
        StringBuilder telefonoMovil = new StringBuilder();

        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);

        telefonoMovil.
                append("CODE_BAR_TEST").
                append(num1).
                append(num2).
                append(num3).
                append(num4);

        return telefonoMovil.toString();
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
