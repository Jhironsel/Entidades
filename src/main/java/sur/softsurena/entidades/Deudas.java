
package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.interfaces.Operaciones;

@SuperBuilder
@Getter
public class Deudas extends Personas implements Operaciones{

    private static final Logger LOG = Logger.getLogger(Deudas.class.getName());

    private final Integer id_deuda;
    private final Integer id_cliente;
    private final Integer id_factura;
    private final String concepto;
    private final BigDecimal monto;
    private final Date fecha;
    private final Time hora;
    private final Character estadoDeuda;

    /**
     * Es el metodo utilizado para obtener la lista de las deudas registrada en
     * el sistema cuyo ESTADO debe ser Credito, Iniciada o Abonada.
     * 
     * Consulta que nos trae la informacion de los registros de las deudas que 
     * como estado estan a Credito, Iniciada o Abonada.
     * 
     * Cuya consulta está unida por las tablas de V_DEUDAS, V_PERSONAS, 
     * V_GENERALES.
     * @return 
     */
    public static synchronized List<Deudas> getDeudas() {
        List<Deudas> deudasList = new ArrayList<>();
        
        final String sql
            = "SELECT ID, ID_CLIENTE, ID_FACTURA, CONCEPTO, MONTO, FECHA, HORA,"
            + "     ESTADO, P_NOMBRE, S_NOMBRE, APELLIDOS, CEDULA "
            + "FROM GET_DEUDAS";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            try (ResultSet rs = ps.executeQuery();) {
                while(rs.next()){
                    deudasList.add(Deudas.builder().
                        id_deuda(rs.getInt("ID")).
                        id_persona(rs.getInt("ID_CLIENTE")).
                        concepto(rs.getString("CONCEPTO")).
                        monto(rs.getBigDecimal("MONTO")).
                        fecha(rs.getDate("FECHA")).
                        hora(rs.getTime("HORA")).
                        pnombre(rs.getString("P_NOMBRE")).
                        snombre(rs.getString("S_NOMBRE")).
                        apellidos(rs.getString("APELLIDOS")).
                        generales(Generales.builder().cedula(rs.getString("CEDULA")).build()).build());
                }
                return deudasList;
            } 
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * 
     * @return 
     */
    public synchronized static ResultSet getDeudaClientes() {
        final String sql = "SELECT r.IDCLIENTE, (c.NOMBRES||' '||c.APELLIDOS) as nombres "
                + "FROM TABLA_DEUDAS r "
                + "LEFT JOIN TABLA_CLIENTES c"
                + "    ON c.IDCLIENTE LIKE r.IDCLIENTE "
                + "WHERE r.ESTADO IN('i', 'a') "
                + "GROUP BY r.IDCLIENTE, c.NOMBRES, c.APELLIDOS ";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * Este procedimiento permite modificar el estado de una factura.
     * 
     * @param idDeuda
     * @param op
     * @return
     */
    public synchronized static Resultados modificarDeuda(int idDeuda, String op) {
        final String sql = "EXECUTE PROCEDURE SP_UPDATE_DEUDA_ESTADO (?, ?);";
        
        try ( CallableStatement ps = getCnn().prepareCall(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setInt(1, idDeuda);
            ps.setString(2, op);
            boolean estado = ps.execute();
            return Resultados
                    .builder()
                    .estado(estado)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .mensaje("Esperacion realizada correctamente.")
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .estado(Boolean.FALSE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .mensaje("Error a ejecutar Operacion")
                    .build();
        }
    }
    
    /**
     * 
     * @param idDeuda
     * @param idTurno
     * @param monto
     * @return 
     */
    public synchronized static Boolean pagoDeuda(int idDeuda, int idTurno,
            BigDecimal monto) {
        
        final String sql = "EXECUTE PROCEDURE INSER_PAGO_DEUDAS_EXT (?, ?, ?)";
        
        try (CallableStatement cs = getCnn().prepareCall(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)){
            cs.setInt(1, idDeuda);
            cs.setInt(2, idTurno);
            cs.setBigDecimal(3, monto);
            return cs.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
    
    /**
     * 
     * @param miDeuda
     * @return 
     */
    public synchronized static boolean insertDeudas(Deudas miDeuda) {
        final String sql = "EXECUTE PROCEDURE INSER_DEUDAS (?, ?, ?)";
        try (CallableStatement cs = getCnn().prepareCall(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)){
            cs.setInt(1, miDeuda.getId_persona());
            cs.setString(3, miDeuda.getConcepto());
            cs.setBigDecimal(4, miDeuda.getMonto());
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar logger
            return false;
        }
    }
    
    /**
     * 
     * @param estado
     * @return 
     */
    public synchronized static ResultSet getDeudaClientesEstado(String estado) {
        final String sql = "SELECT r.IDDEUDAS, IIF(r.IDCLIENTE = '0', '000-0000000-0', "
                + "r.IDCLIENTE) as IDCLIENTE, c.NOMBRES, c.APELLIDOS, "
                + "r.CONCEPTO, r.MONTO, r.FECHA, "
                + "        (IIF(r.ESTADO = 'i', 'Inicial', "
                + "         IIF(r.ESTADO = 'p', 'Pagada', "
                + "         IIF(r.ESTADO = 'a', 'Abonada', "
                + "         IIF(r.ESTADO = 'n','Nula','No Definida'))))) as ESTADO "
                + "FROM TABLA_DEUDAS r "
                + "LEFT JOIN TABLA_CLIENTES c ON c.IDCLIENTE LIKE r.IDCLIENTE "
                + estado
                + "ORDER by 1";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * 
     * @param idCliente
     * @return 
     */
    public synchronized static ResultSet getDeudaCliente(String idCliente) {
        final String sql = "SELECT r.IDDEUDAS, r.CONCEPTO, r.MONTO, r.FECHA, r.ESTADO "
                + "FROM TABLA_DEUDAS r "
                + "WHERE r.IDCLIENTE LIKE ? AND r.ESTADO NOT IN('n', 'p')";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * 
     * @param idDeuda
     * @return 
     */
    public synchronized static ResultSet getDeudaClienteExterna(String idDeuda) {
        final String sql = "SELECT r.CODIGO, r.FECHA, r.HORA, r.MONTO "
                + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                + "WHERE r.IDDEUDA = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, idDeuda);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 
     * @param idDeuda
     * @return 
     */
    //!OJO Metodo para analizarlo
    public synchronized static ResultSet getPagoDeudasExterna(int idDeuda) {
        final String sql = "SELECT r.CODIGO, r.MONTO, r.FECHA, r.HORA "
                + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                + "WHERE r.IDDEUDA = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setInt(1, idDeuda);
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
    //!OJO Metodo para analizarlo
    public synchronized static ResultSet getPagoDeudas(int idFactura) {
        final String sql = "SELECT r.IDPAGODEUDA, r.FECHA, r.HORA, r.MONTOPAGO "
                + "FROM TABLA_PAGODEUDA r "
                + "WHERE r.IDFACTURA = ?";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * 
     * @param idCliente
     * @return 
     */
    public synchronized static BigDecimal getDeudaActual(String idCliente) {
        final String sql = "SELECT r.DEUDAACTUAL "
                + "FROM TABLA_DEUDAS r "
                + "WHERE r.IDCLIENTE LIKE ?";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, idCliente);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("DEUDAACTUAL");
                } else {
                    return new BigDecimal(0);
                }
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return new BigDecimal(-1);
        }
    }
    
    public synchronized static BigDecimal sumaMontoPagoDeudaExterna(int idDeuda) {
        final String sql = "SELECT SUM(r.MONTO) "
                + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                + "WHERE r.IDDEUDA = ?";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1);
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
        return super.toString();
    }

    @Override
    public Object getEntity(FiltroBusqueda filtro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> getEntities(FiltroBusqueda filtro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resultados setEntity(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resultados putEntity(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resultados delEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}