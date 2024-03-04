package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Factura;
import static sur.softsurena.metodos.M_D_Factura.agregarDetalleFactura;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Factura {

    /**
     * Consulta que nos permite obtener los ID de las facturas.
     *
     * @return Devuelve un lista de los identificadores unicos de las faturas.
     */
    public synchronized static List<Factura> getFacturas() {

        final String sql
                = "SELECT ID FROM V_M_FACTURAS ORDER BY 1";

        try (PreparedStatement ps = getCnn().prepareStatement(sql);) {
            List<Factura> facturasList = new ArrayList<>();

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    facturasList.add(Factura.builder().
                            id(rs.getInt("ID")).build());
                }
            }
            return facturasList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que elimina las facturas del sistema por el identificador
     * suministrado.
     *
     * Nota: las facturas pueden eliminarse si el estado es nula.
     *
     * Actualizado el 17/05/2022.
     *
     * TODO CREAR SP.
     *
     * @param id Es el identificador del registro de la factura.
     * @return Devuelve un mensaje de la acción
     */
    public synchronized static String borrarFactura(int id) {
        final String sql
                = "DELETE FROM V_FACTURAS where id = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return FACTURA__BORRADA__CORRECTAMENTE;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA;
        }
    }
    public static final String OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA 
            = "!Ocurrio un error al intentar borrar la Factura...!!!";
    public static final String FACTURA__BORRADA__CORRECTAMENTE 
            = "Factura Borrada Correctamente.";

    /**
     * Metodo para agregar las facturas al temporar del sistema.
     *
     * Este metodo fue actualizado el dia 24 abril del 2022. Este metodo fue
     * actualizado el 19 05 2022: Se agrego un parametro al Insert que le
     * faltaba.
     *
     * TODO CREAR SP.
     *
     * @param f Un objecto de Factura que recibe la funcion.
     * @return Retorna un valor booleando que indica si la factura fue inserta
     * true y false si hubo un error.
     */
    public synchronized static Integer agregarFacturaNombre(Factura f) {
        final String sql
                = "INSERT INTO V_FACTURAS (id_Cliente, id_Turno, efectivo, cambio, "
                + "estado_factura) "
                + "values (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, f.getHeaderFactura().getIdCliente());
            ps.setInt(2, f.getHeaderFactura().getIdTurno());
            ps.setBigDecimal(3, f.getHeaderFactura().getEfectivo());
            ps.setBigDecimal(4, f.getHeaderFactura().getCambio());
            ps.setString(5, String.valueOf(f.getHeaderFactura().getEstado()));

            Integer cantidad = ps.executeUpdate();
            return cantidad + agregarDetalleFactura(f);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }

    }

    /**
     * Metodo que permite modificar las facturas que se encuentran en el sistema
     *
     * Me cuestiono si campos como idCliente efectivo cambio deberian de estar
     * aqui?
     *
     * Metodo Actualizado el 18/05/2022.
     *
     * TODO CREAR SP.
     *
     * @param f Objeto de la clase Factura.
     * @return retorna true si fue modificada y false si hubo un error en la
     * modificacion de la factura.
     */
    public synchronized static boolean modificarFactura(Factura f) {
        final String sql
                = "UPDATE V_FACTURAS a SET "
                + "    a.ID_CLIENTE = ?, "
                + "    a.EFECTIVO = ?, "
                + "    a.CAMBIO = ?, "
                + "    a.ESTADO = ? "
                + "WHERE "
                + "    a.ID = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, f.getHeaderFactura().getIdCliente());
            ps.setBigDecimal(2, f.getHeaderFactura().getEfectivo());
            ps.setBigDecimal(3, f.getHeaderFactura().getCambio());
            ps.setString(4, "" + f.getHeaderFactura().getEstado());
            ps.setInt(5, f.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * TODO CREAR VISTA.
     * @param filtro
     * @return
     */
    public synchronized static ResultSet getReporteFacturas(String filtro) {
        String sql
                = "SELECT f.idFactura, f.idCliente, (c.nombres||' '||c.apellidos) AS nombreFull, "
                + "        f.fecha, d.idLinea, p.idProducto, p.descripcion, "
                + "        precio,   d.cantidad, precio * d.cantidad AS Valor "
                + "FROM tabla_facturas f "
                + "INNER JOIN tabla_clientes c ON f.idCliente = c.idCliente "
                + "INNER JOIN detalleFactura d ON f.idFactura = d.idFactura "
                + "INNER JOIN tabla_productos p ON p.idproducto = d.idproducto "
                + filtro;
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
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
    public synchronized static ResultSet getFacturasNombreClientes(int idFactura) {
        final String sql
                = "SELECT r.IDCLIENTE, nombreCliente "
                + "FROM TABLA_FACTURAS r "
                + "WHERE r.IDFACTURA = ?"
                + "order by 1";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
}
