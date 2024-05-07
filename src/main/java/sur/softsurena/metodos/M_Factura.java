package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Factura;
import sur.softsurena.entidades.HeaderFactura;
import static sur.softsurena.metodos.M_D_Factura.agregarDetalleFactura;
import sur.softsurena.utilidades.Resultado;
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

        List<Factura> facturasList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    facturasList.add(
                            Factura
                                    .builder()
                                    .id(rs.getInt("ID"))
                                    .build());
                }
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    "Error al consultar la vista V_M_FACTURAS del sistema.",
                    ex
            );
        }
        return facturasList;
    }

    /**
     * Metodo para agregar las facturas al temporar del sistema.
     *
     * Este metodo fue actualizado el dia 24 abril del 2022. Este metodo fue
     * actualizado el 19 05 2022: Se agrego un parametro al Insert que le
     * faltaba.
     *
     * @param factura Un objecto de Factura que recibe la funcion.
     * @return Retorna un valor booleando que indica si la factura fue inserta
     * true y false si hubo un error.
     */
    public synchronized static Resultado agregarFacturaNombre(Factura factura) {
        final String sql
                = """
                  SELECT ID
                  FROM SP_I_M_FACTURA (?, ?, ?, ?, ?, ?, ?, ?, ?);
                  """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, factura.getHeaderFactura().getId_persona());
            ps.setInt(2, factura.getHeaderFactura().getIdContactoTel());
            ps.setInt(3, factura.getHeaderFactura().getIdContactoDireccion());
            ps.setInt(4, factura.getHeaderFactura().getIdContactoEmail());
            ps.setInt(5, factura.getHeaderFactura().getIdTurno());
            ps.setBigDecimal(6, factura.getHeaderFactura().getTotal());
            ps.setBigDecimal(7, factura.getHeaderFactura().getEfectivo());
            ps.setString(8, String.valueOf(
                    factura.getHeaderFactura().getEstadoFactura()
            ));
            ps.setString(9, factura.getHeaderFactura().getNombreTemporal());

            ResultSet rs = ps.executeQuery();

            rs.next();

            int idFactura = rs.getInt("ID");

            Resultado resultado = agregarDetalleFactura(
                    idFactura,
                    factura.getDetalleFactura()
            );

            if (!resultado.getEstado()) {
                throw new SQLException("Error en el detalle de la factrura.");
            }

            return Resultado
                    .builder()
                    .id(idFactura)
                    .mensaje(FACTURA_INGRESADA_CORRECTAMENTE_AL_SISTEM)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_INSERTAR_FACTURA_AL_SISTEMA,
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR_FACTURA_AL_SISTEMA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }

    }
    public static final String FACTURA_INGRESADA_CORRECTAMENTE_AL_SISTEM
            = "Factura ingresada correctamente al sistema.";
    public static final String ERROR_AL_INSERTAR_FACTURA_AL_SISTEMA
            = "Error al insertar factura al sistema.";

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
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, f.getHeaderFactura().getId_persona());
            ps.setBigDecimal(2, f.getHeaderFactura().getEfectivo());
            ps.setBigDecimal(3, f.getHeaderFactura().getTotal());
            ps.setString(4, "" + f.getHeaderFactura().getEstadoFactura());
            ps.setInt(5, f.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * TODO CREAR VISTA. Devolver una lista.
     * 
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
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ex.getMessage(),
                    ex
            );
            return null;
        }
    }

    /**
     * TODO CREAR VISTA. Devolver una lista.
     *
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
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ex.getMessage(),
                    ex
            );
            return null;
        }
    }

    /**
     * Para obtener las facturas temporales del sistema.
     *
     * @return
     */
    public synchronized static List<Factura> getTemporales() {
        final String sql
                = """
                  SELECT 
                    ID, 
                    ID_CLIENTE, 
                    ID_CONTACTOS_TEL, 
                    ID_CONTACTOS_DIRECCIONES, 
                    ID_CONTACTOS_EMAIL, 
                    ID_TURNO, 
                    FECHA_HORA, 
                    TOTAL, 
                    EFECTIVO, 
                    ESTADO_FACTURA, 
                    NOMBRE_TEMP, 
                    USER_NAME, 
                    PNOMBRE, 
                    SNOMBRE, 
                    APELLIDOS
                  FROM GET_M_FACTURAS
                  WHERE ESTADO_FACTURA = 't';
                  """;

        List<Factura> facturaList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    facturaList.add(
                            Factura
                                    .builder()
                                    .id(rs.getInt("ID"))
                                    .headerFactura(
                                            HeaderFactura
                                                    .builder()
                                                    .id_persona(rs.getInt("ID_CLIENTE"))
                                                    .idContactoTel(rs.getInt("ID_CONTACTOS_TEL"))
                                                    .idContactoDireccion(rs.getInt("ID_CONTACTOS_DIRECCIONES"))
                                                    .idContactoEmail(rs.getInt("ID_CONTACTOS_EMAIL"))
                                                    .idTurno(rs.getInt("ID_TURNO"))
                                                    .fechaHora(rs.getTimestamp("FECHA_HORA"))
                                                    .total(rs.getBigDecimal("TOTAL"))
                                                    .efectivo(rs.getBigDecimal("EFECTIVO"))
                                                    .estadoFactura(rs.getString("ESTADO_FACTURA").charAt(0))
                                                    .nombreTemporal(rs.getString("NOMBRE_TEMP"))
                                                    .userName(rs.getString("USER_NAME"))
                                                    .pnombre(rs.getString("PNOMBRE"))
                                                    .snombre(rs.getString("SNOMBRE"))
                                                    .apellidos(rs.getString("APELLIDOS"))
                                                    .build()
                                    )
                                    .build()
                    );
                }
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    "Error al consultar la vista GET_TEMPORALES del sistema.",
                    ex
            );
        }
        return facturaList;
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
    public synchronized static Resultado borrarFactura(int id) {
        final String sql
                = "EXECUTE PROCEDURE SP_D_M_FACTURA (?);";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.execute();

            return Resultado
                    .builder()
                    .mensaje(FACTURA__BORRADA__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA
            = "!Ocurrio un error al intentar borrar la Factura...!!!";
    public static final String FACTURA__BORRADA__CORRECTAMENTE
            = "Factura Borrada Correctamente.";
}
