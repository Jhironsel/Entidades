package sur.softsurena.entidades;

import java.io.File;
import java.math.BigDecimal;
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
public class Productos {

    private static final Logger LOG = Logger.getLogger(Productos.class.getName());

    private final Integer id;
    private final int idCategoria;
    private final String desc_categoria;
    private final String imagen_categoria;
    private final String codigo;
    private final String descripcion;
    private final File pathImagen;
    private final String imagenProducto;
    private final String nota;
    private final Date fechaCreacion;
    private final Boolean estado;
    private final String idUsuario;
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
    public synchronized static ResultSet buscarProducto(String criterio) {
        final String BUSCAR_PRODUCTO_ID_DESCRIPCION_CODIGO
                = "SELECT r.ID, r.CODIGO, r.DESCRIPCION "
                + "FROM V_PRODUCTOS r "
                + "WHERE r.ID = ? OR r.CODIGO LIKE ?% OR r.DESCRIPCION LIKE ?%";

        try (PreparedStatement ps = getCnn().prepareStatement(
                BUSCAR_PRODUCTO_ID_DESCRIPCION_CODIGO)) {

            ps.setInt(1, Integer.parseInt(criterio));
            ps.setString(2, criterio);
            ps.setString(3, criterio);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que permite recuperar las propiedades de los productos del
     * sistema. devolviendo asi un resulset con todos estos productos.
     *
     * OJO! Deberia investigar la cantidad o existencia del producto.
     *
     * Fecha de Actualización el 19/05/2022.
     *
     * @return resuelve un conjunto de datos de la tabla de los productos del
     * sistema.
     */
    public synchronized static List<Productos> getProductos() {
        List<Productos> listaProducto = new ArrayList<>();

        Productos producto;

        final String SELECT
                = "SELECT r.ID, r.CODIGO, r.ID_CATEGORIA, r.DESC_CATEGORIA, "
                + "     r.DESCRIPCION,  r.NOTA, r.FECHA_CREACION, r.ESTADO "
                + "FROM GET_PRODUCTOS r";

        try (PreparedStatement ps = getCnn().prepareStatement(SELECT)) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    producto = Productos.builder().
                            id(rs.getInt("ID")).
                            descripcion(rs.getString("DESCRIPCION")).
                            idCategoria(rs.getInt("ID_CATEGORIA")).
                            desc_categoria(rs.getString("DESC_CATEGORIA")).
                            codigo(rs.getString("CODIGO")).
                            fechaCreacion(rs.getDate("FECHA_CREACION")).
                            nota(rs.getString("NOTA")).
                            estado(rs.getBoolean("ESTADO")).build();

                    listaProducto.add(producto);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
        return listaProducto;
    }

    /**
     * Metodo utilizado para eliminar los productos del sistema, este solo
     * necesita de su ID o codigo de barra para localizarlo en la vista de 
     * V_PRODUCTOS.
     *
     * @param IDCodigo Identificador o codigo del Producto en la vista de 
     * V_PRODUCTOS.
     * 
     *
     * @return Devuelve un mensaje que indica como resultado de la acción.
     */
    public synchronized static String borrarProductoPorID_Codigo(String IDCodigo) {
        final String DELETE
                = "DELETE FROM V_PRODUCTOS WHERE id = ? OR codigo = ?;";

        try (PreparedStatement ps = getCnn().prepareStatement(DELETE)) {
            ps.setString(1, IDCodigo);
            ps.setString(2, IDCodigo);
            ps.executeUpdate();
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
        final String INSERT
                = "INSERT INTO V_PRODUCTOS (ID_CATEGORIA, CODIGO, DESCRIPCION, "
                + "                         IMAGEN_TEXTO, NOTA, ESTADO) "
                + "VALUES (?, ?, ?, ?, ?, ? );";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
            ps.setInt(1, p.getIdCategoria());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, Utilidades.imagenEncode64(p.getPathImagen()));
            ps.setString(5, p.getNota());
            ps.setBoolean(6, p.getEstado());

            ps.executeUpdate();

            r = Resultados.builder().
                    id(-1).
                    mensaje("Producto agregado correctamente.").
                    cantidad(-1).build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            r = Resultados.builder().
                    id(-1).
                    mensaje("Error al Insertar Producto.").
                    cantidad(-1).build();
        }
        return r;
    }

    /**
     * Metodo que permite modificar los productos del sistema como a la
     * categoria a la que pertenece el producto, su codigo de barra, la
     * descripcion, la imagen de este, la nota del producto y su estado.
     *
     * Metodo actualizado el dia 23 de abril, segun la vista productos.
     *
     * @param p perteneciente a la clase Producto, define los productos del 
     * sistema.
     * @return
     */
    public synchronized static String modificarProducto(Productos p) {
        final String UPDATE
                = "update V_PRODUCTOS set "
                + "idCategoria = ?, "
                + "codigo = ?, "
                + "descripcion = ?, "
                + "imagen_texto = ?, "
                + "nota = ?, "
                + "estado = ? "
                + "where id = ?; ";
        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {

            ps.setInt(1, p.getIdCategoria());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, Utilidades.imagenEncode64(p.getPathImagen()));
            ps.setString(5, p.getNota());
            ps.setBoolean(6, p.getEstado());
            ps.setInt(7, p.getId());

            ps.executeUpdate();
            return "Producto Modificado Correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "Error al Modificar Producto...";
    }

    /**
     *
     * @param idCategoria
     * @return
     */
    public synchronized static boolean existeCategoriaProductos(int idCategoria) {
        final String EXISTE_CATEGORIA
                = "SELECT (1) FROM V_PRODUCTOS WHERE idCategoria = ?";

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
     * @param codigo este valor representa el valor del codigo de barra del
     * producto o nombre de 25 caracteres.
     * @return
     */
    public synchronized static boolean existeProducto(String codigo) {
        final String EXISTE_PRODUCTO
                = "SELECT (1) "
                + "FROM v_productos "
                + "WHERE codigo = ? or descripcion starting ?";

        try (PreparedStatement ps = getCnn().prepareStatement(EXISTE_PRODUCTO)) {

            ps.setString(1, codigo);
            ps.setString(2, codigo);

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
     *
     * @param idProducto
     * @param codigo
     * @return
     */
    public synchronized static ResultSet getProductoById(Integer idProducto,
            String codigo) {
        final String SELECT_CODIGO_ID
                = "select id, codigo, descripcion, estado, image, "
                + "idCategoria, Cantidad "
                + "from v_Productos "
                + "where codigo = ? or IDPRODUCTO = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(SELECT_CODIGO_ID)) {

            ps.setString(1, codigo);
            if (idProducto == null) {
                idProducto = 0;
            }
            ps.setInt(2, idProducto);

            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para obtener un producto identificado por su ID.
     *
     *
     * <b>Metodo actualizado el 22 abril 2022</b>, Nota: Agregado el campo id.
     *
     * <b>Metodo actualizado el 19 05 2022</b>, Nota: Se ha agregado nueva vista
     * para traer los datos del producto consultado. Esta vista agrega dos
     * campos mas que son DESC_CATEGORIA y IMAGEN_CATEGORIA
     *
     * <b>Actualizado el 09 julio 2022</b>, Nota: He modificado la clase
     * producto la cual ahora se construye utilizando Metodologia Builder.
     * Tambien, se modificó el nombre del metodo de plural a singular.
     *
     * @param id identificador del producto.
     *
     * @return Devuelve un objecto de la clase Producto consultado a la base de
     * datos por su identificador.
     */
    public synchronized static Productos getProducto(int id) {
        final String sql
                = "SELECT r.ID, r.ID_CATEGORIA, r.DESC_CATEGORIA, "
                + "     r.IMAGEN_CATEGORIA, r.CODIGO, r.DESCRIPCION, "
                + "     r.IMAGEN_PRODUCTO, r.NOTA, r.FECHA_CREACION, r.ESTADO "
                + "FROM GET_PRODUCTOS r"
                + "WHERE r.ID = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, id);
            Productos miProducto = null;
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    miProducto = Productos.builder().
                            id(rs.getInt("ID")).
                            idCategoria(rs.getInt("ID_CATEGORIA")).
                            desc_categoria(rs.getString("DESC_CATEGORIA")).
                            imagen_categoria(rs.getString("IMAGEN_CATEGORIA")).
                            codigo(rs.getString("CODIGO")).
                            descripcion(rs.getString("DESCRIPCION")).
                            imagenProducto(rs.getString("IMAGEN_PRODUCTO")).
                            nota(rs.getString("NOTA")).
                            fechaCreacion(rs.getDate("FECHA_CREACION")).
                            estado(rs.getBoolean("ESTADO")).build();
                }
            }
            return miProducto;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getProductosCriterios(int tipo,
            String criterio, boolean image) {

        String sql = "SELECT p.IdProducto, p.Descripcion, p.Codigo " + (image ? ", image " : "")
                + "FROM TABLA_PRODUCTOS p ";

        switch (tipo) {
            case 1:
                sql = sql + "WHERE p.Codigo CONTAINING ? and estado order by 1";
                break;
            case 2:
                sql = sql + "WHERE p.Descripcion CONTAINING ? and estado order by 2";
                break;
            case 3:
                sql = sql
                        + "inner join tabla_entradas_Producto e on p.idProducto = e.idProducto "
                        + "WHERE p.idCategoria = ? and e.entrada > 0 and estado";
                break;
            case 4:
                sql = sql + "WHERE idCategoria = ? ";
                break;
            default:
                sql = sql + "WHERE estado order by 1 ";
        }

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            if (tipo != 0) {
                ps.setString(1, criterio);
            }
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idProducto
     * @return
     */
    public synchronized static BigDecimal getPrecioProducto(int idProducto) {

        try (PreparedStatement ps = getCnn().prepareStatement(
                "SELECT precio "
                + "FROM TABLA_ENTRADAS_PRODUCTO "
                + "WHERE idProducto = ?")) {

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

    @Override
    public String toString() {
        return descripcion;
    }
}
