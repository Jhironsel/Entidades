package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class EntradaProducto {

    private static final Logger LOG = Logger.getLogger(EntradaProducto.class.getName());
    
    private final Integer id;
    private final Integer idProvedor; //Identificador del proveedor
    private final String cod_factura; //Encabezado de la factura.
    private final int linea; //Linea consecutiva de la entrada de producto
    private final int idProducto; //Identificador del producto. 
    private final BigDecimal entrada;
    private final Date fechaVecimiento;
    private final Boolean estado;
    private final String idUsuairo;
    private final String rol;

    private final static String INSERT_SP
            = "EXECUTE PROCEDURE SP_INSERT_ENTRADA_PRODUCTOS (?, ?, ?, ?, ?, ?);";
    
    /**
     * Agregar entrada de producto a la base de datos en la tabla de entrada de
     * productos.
     *
     * Actualizado el dia 13 de abril del 2022.
     *
     * @param e Es un objeto de la clase EntradaProducto
     *
     * @return Devuelve un valor booleano que indica si el registro fue exitoso.
     */
    public synchronized static boolean agregarProductoEntrada(EntradaProducto e) {
        try(PreparedStatement ps = getCnn().prepareStatement(EntradaProducto.INSERT_SP)) {
            ps.setInt(1, e.getIdProvedor());
            ps.setString(2, e.getCod_factura());
            ps.setInt(3, e.getLinea());
            ps.setInt(4, e.getIdProducto());
            ps.setBigDecimal(5, e.getEntrada());
            ps.setDate(6, e.getFechaVecimiento());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
    
    private static final String INSERT
            = "INSERT INTO ENTRADAS_PRODUCTO (IDENTRADA_PRODUCTO, CONCEPTO, "
                    + "IDPRODUCTO, ENTRADA, OP, IDUSUARIO, NUMERO)"
                    + "VALUES (?, ?, ?, ?, '-', ?, ? );";
    
    /**
     * 
     * @param IDENTRADA_PRODUCTO
     * @param numero
     * @param cencepto
     * @param idProducto
     * @param entrada
     * @param idUsuario
     * @return 
     */
    public boolean agregarProductoSalida(int IDENTRADA_PRODUCTO, int numero,
            String cencepto, String idProducto, double entrada, String idUsuario) {
        
        
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
    
    /**
     * 
     * @param mes
     * @param year
     * @return 
     */
    public synchronized static ResultSet getEntradaProducto(int mes, int year) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.FECHAENTRADA, IIF(r.OP = '+', 'Entrada', 'Salida') as operacion, "
                + "r.IDUSUARIO "
                + "FROM TABLA_ENTRADAS_PRUDUCTO r "
                + "WHERE EXTRACT(MONTH FROM r.FECHAENTRADA) = ? "
                + "and EXTRACT(YEAR FROM r.FECHAENTRADA) = ? "
                + "GROUP BY r.FECHAENTRADA,  r.OP, r.IDUSUARIO")) {
            ps.setInt(1, mes);
            ps.setInt(2, year);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
