package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.EntradaProducto;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Entrada_Producto {
    
    /**
     * Agregar entrada de producto a la base de datos en la tabla de entrada de
     * productos.
     *
     * Actualizado el dia 13 de abril del 2022.
     *
     * @param eProducto Es un objeto de la clase EntradaProducto
     *
     * @return Devuelve un valor booleano que indica si el registro fue exitoso.
     */
    public static Resultado agregarProductoEntrada(EntradaProducto eProducto) {
        final String sql
                = "EXECUTE PROCEDURE SP_I_ENTRADA_PRODUCTO(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql, 
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, eProducto.getIdProvedor());
            ps.setString(2, eProducto.getCod_factura());
            ps.setInt(3, eProducto.getLinea());
            ps.setInt(4, eProducto.getIdProducto());
            ps.setBigDecimal(5, eProducto.getEntrada());
            ps.setDate(6, eProducto.getFechaVecimiento());
            
            ps.executeUpdate();
            
            return Resultado
                    .builder()
                    .mensaje(PRODUCTO_AGREGADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    EL_PRODUCTO_NO_FUE_AGREGADO, 
                    ex
            );
            
            return Resultado
                    .builder()
                    .mensaje(EL_PRODUCTO_NO_FUE_AGREGADO)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String PRODUCTO_AGREGADO_CORRECTAMENTE 
            = "Producto agregado correctamente.";
    public static final String EL_PRODUCTO_NO_FUE_AGREGADO 
            = "El producto NO FUE agregado.";

    /**
     * CREAR SP.
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

        final String sql
                = "INSERT INTO ENTRADAS_PRODUCTO (IDENTRADA_PRODUCTO, CONCEPTO, "
                + "IDPRODUCTO, ENTRADA, OP, IDUSUARIO, NUMERO)"
                + "VALUES (?, ?, ?, ?, '-', ?, ? );";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql, 
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * TODO CREAR VISTA.
     * @param mes
     * @param year
     * @return
     */
    public synchronized static ResultSet getEntradaProducto(int mes, int year) {
        final String sql 
                = "SELECT "
                + "     r.FECHAENTRADA, "
                + "     IIF(r.OP = '+', 'Entrada', 'Salida') as operacion, "
                + "     r.IDUSUARIO "
                + "FROM TABLA_ENTRADAS_PRUDUCTO r "
                + "WHERE EXTRACT(MONTH FROM r.FECHAENTRADA) = ? and "
                + "      EXTRACT(YEAR FROM r.FECHAENTRADA) = ? "
                + "GROUP BY r.FECHAENTRADA,  r.OP, r.IDUSUARIO";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql, 
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, mes);
            ps.setInt(2, year);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
