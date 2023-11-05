package sur.softsurena.entidades;

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
import static sur.softsurena.entidades.DetalleFactura.agregarDetalleFactura;

@SuperBuilder
@Getter
public class Facturas {

    private static final Logger LOG = Logger.getLogger(Facturas.class.getName());

    private final Integer id;
    private final HeaderFactura headerFactura;
    private final List<DetalleFactura> detalleFactura;
    
    /**
     * Consulta que nos permite obtener los ID de las facturas.
     * 
     * 
     * 
     * @return Devuelve un lista de los identificadores unicos de las faturas.
     */
    public synchronized static List<Facturas> getFacturas() {
        
        final String sql
            = "SELECT ID FROM V_FACTURAS ORDER BY 1";
        
        try (PreparedStatement ps = getCnn().prepareStatement(sql);) {
            List<Facturas> facturasList = new ArrayList<>();
            
            try (ResultSet rs = ps.executeQuery();) {
                while(rs.next()){
                    facturasList.add(Facturas.builder().
                        id(rs.getInt("ID")).build());
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
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
     * @param id Es el identificador del registro de la factura.
     * @return Devuelve un mensaje de la acción
     */
    public synchronized static String borrarFactura(int id) {
        
        final String DELETE
            = "DELETE FROM V_FACTURAS where id = ?";
        
        try (PreparedStatement ps = getCnn().prepareStatement(DELETE)){
            ps.setInt(1, id);
            int r = ps.executeUpdate();
            return "Factura Borrada Correctamente. {"+r+"}";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "!Ocurrio un error al intentar borrar la Factura...!!!";
        }
    }

    /**
     * Metodo para agregar las facturas al temporar del sistema.
     *
     * Este metodo fue actualizado el dia 24 abril del 2022. Este metodo fue
     * actualizado el 19 05 2022: Se agrego un parametro al Insert que le
     * faltaba.
     *
     * @param f Un objecto de Factura que recibe la funcion.
     * @return Retorna un valor booleando que indica si la factura fue inserta
     * true y false si hubo un error.
     */
    public synchronized static Integer agregarFacturaNombre(Facturas f) {
        
        final String INSERT_FACTURA
            = "INSERT INTO V_FACTURAS (id_Cliente, id_Turno, efectivo, cambio, "
            + "estado_factura) "
            + "values (?, ?, ?, ?, ?);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT_FACTURA)) {
            
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
     * @param f Objeto de la clase Factura.
     * @return retorna true si fue modificada y false si hubo un error en la
     * modificacion de la factura.
     */
    public synchronized static boolean modificarFactura(Facturas f) {
        final String UPDATE
            = "UPDATE V_FACTURAS a SET "
                + "    a.ID_CLIENTE = ?, "
                + "    a.EFECTIVO = ?, "
                + "    a.CAMBIO = ?, "
                + "    a.ESTADO = ? "
                + "WHERE "
                + "    a.ID = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {

            ps.setInt(1, f.getHeaderFactura().getIdCliente());
            ps.setBigDecimal(2, f.getHeaderFactura().getEfectivo());
            ps.setBigDecimal(3, f.getHeaderFactura().getCambio());
            ps.setString(4, ""+f.getHeaderFactura().getEstado());
            ps.setInt(5, f.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
    
    /**
     * 
     * @param filtro
     * @return 
     */
    public synchronized static ResultSet getReporteFacturas(String filtro) {
        String sql = "SELECT f.idFactura, f.idCliente, (c.nombres||' '||c.apellidos) AS nombreFull, "
                + "        f.fecha, d.idLinea, p.idProducto, p.descripcion, "
                + "        precio,   d.cantidad, precio * d.cantidad AS Valor "
                + "FROM tabla_facturas f "
                + "INNER JOIN tabla_clientes c ON f.idCliente = c.idCliente "
                + "INNER JOIN detalleFactura d ON f.idFactura = d.idFactura "
                + "INNER JOIN tabla_productos p ON p.idproducto = d.idproducto "
                + filtro;
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * 
     * @param idFactura
     * @return 
     */
    public synchronized static ResultSet getFacturasNombreClientes(int idFactura) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.IDCLIENTE, nombreCliente "
                + "FROM TABLA_FACTURAS r "
                + "WHERE r.IDFACTURA = ?"
                + "order by 1")) {
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
