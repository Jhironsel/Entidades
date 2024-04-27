package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.D_Factura;
import sur.softsurena.entidades.Factura;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_D_Factura {

    /**
     * Metodo utilizado para agregar los datos de los detalle de la factura del
     * sistema.
     *
     * Metodo actualizado el dia 19 05 2022, Nota: se actualizó el nombre de la
     * consulta por V_DETALLE_FACTURA. Se agrega el metodo execute() para que se
     * devuelto como true o false.
     *
     * TODO CREAR SP.
     *
     * @return Ahora devuelve un entero que indica las cantidades de registros
     * que fueron afectadas en la inserción del registro.
     *
     * Antes: Devuelve un valor booleano que indica true si el registro se hizo
     * con exito y false si hubo un error al insertarla.
     */
    public static synchronized Integer agregarDetalleFactura(Factura f) {
        List<D_Factura> d = f.getDetalleFactura();
        final String sql
                = "INSERT INTO V_D_FACTURAS (ID_FACTURA, ID_LINEA, ID_PRODUCTO, "
                + "     PRECIO, CANTIDAD) "
                + "VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            for (Iterator<D_Factura> i = d.iterator(); i.hasNext();) {
                D_Factura next = i.next();

                ps.setInt(1, f.getId());
                ps.setInt(2, next.getIdLinea());
                ps.setInt(3, next.getIdProducto());
                ps.setBigDecimal(4, next.getPrecio());
                ps.setBigDecimal(5, next.getCantidad());

                ps.addBatch();

            }

            return ps.executeUpdate();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @param idFactura
     * @return
     */
    public synchronized static ResultSet getBuscarTemporal(Integer idFactura) {
        final String sql
                = "SELECT r.ID_FACTURA, r.ID_LINEA, r.ID_PRODUCTO, r.DESCRIPCION, r.PRECIO, "
                + "     r.CANTIDAD, r.TOTAL "
                + "FROM GET_D_FACTURAS r"
                + "WHERE ID_FACTURA = ? "
                + "ORDER BY 1 2;";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * TODO CREAR VISTA.
     * @param idFactura
     * @return
     */
    public synchronized static ResultSet getFacturasDetalladas(String idFactura) {
        final String sql 
                = "SELECT factura.idFactura, factura.idCliente, nombres||' '||apellidos AS nombreFull, "
                + "        fecha, idLinea, (SELECT p.Descripcion "
                + "                            FROM TABLA_PRODUCTOS p "
                + "                            WHERE p.idProducto = DETALLEFACTURA.IDPRODUCTO ) as Descripcion, "
                + "        idProducto, precio, cantidad, precio * cantidad AS Valor "
                + "FROM TABLA_FACTURAS "
                + "INNER JOIN TABLA_CLIENTES ON factura.idCliente = cliente.idCliente "
                + "INNER JOIN TABLA_DETALLEFACTURA ON factura.idFactura = detalleFactura.idFactura "
                + "WHERE factura.idFactura = ? ";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * TODO CREAR VISTA.
     * @param idCliente
     * @return
     */
    public synchronized static ResultSet getFacturasDetalladaPorCliente(String idCliente) {
        final String sql
                = "SELECT f.idFactura, f.estado , f.fecha, f.USUARIO, "
                + "COALESCE(SUM(d.precio * d.cantidad), 0.00) AS Valor "
                + "FROM TABLA_FACTURAS f "
                + "LEFT JOIN TABLA_CLIENTES c ON f.idCliente = c.idCliente "
                + "LEFT JOIN TABLA_DETALLEFACTURA d ON f.idFactura = d.idFactura "
                + "WHERE f.idCliente = ? "
                + "GROUP BY f.idFactura, f.estado , f.fecha, f.USUARIO "
                + "order by 1";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * TODO CREAR VISTA.
     * @param idCliente
     * @param idFactura
     * @return
     */
    public synchronized static ResultSet getFacturasDetalladaPorCliente(
            String idCliente, int idFactura) {
        final String sql
                = "SELECT f.idFactura, f.estado , f.fecha, f.USUARIO, "
                + "COALESCE(SUM(d.precio * d.cantidad), 0.00) AS Valor "
                + "FROM TABLA_FACTURAS f "
                + "LEFT JOIN TABLA_CLIENTES c ON f.idCliente = c.idCliente "
                + "LEFT JOIN TABLA_DETALLEFACTURA d ON f.idFactura = d.idFactura "
                + "WHERE f.idCliente = ? and f.idFactura = ? "
                + "GROUP BY f.idFactura, f.estado , f.fecha, f.USUARIO "
                + "order by 1";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, idCliente);
            ps.setInt(2, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
