package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.EntradaProducto;
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
    public static boolean agregarProductoEntrada(EntradaProducto eProducto) {
        final String sql
                = "EXECUTE PROCEDURE SP_INSERT_ENTRADA_PRODUCTOS (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql, 
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
     *
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
