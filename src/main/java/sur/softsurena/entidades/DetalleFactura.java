package sur.softsurena.entidades;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class DetalleFactura {

    public static String INSERT_DETALLE_FACTURA
            = "INSERT INTO V_D_FACTURAS (ID_FACTURA, ID_LINEA, ID_PRODUCTO, "
            + "     PRECIO, CANTIDAD) "
            + "VALUES (?, ?, ?, ?, ?);";

    public static String GET_DETALLE_FACTURA
            = "SELECT r.ID_FACTURA, r.ID_LINEA, r.ID_PRODUCTO, r.DESCRIPCION, r.PRECIO, "
            + "     r.CANTIDAD, r.TOTAL "
            + "FROM GET_D_FACTURAS r"
            + "WHERE ID_FACTURA = ? "
            + "ORDER BY 1 2;";

    private final int idLinea;
    private final int idProducto;
    private final String descripcion;
    private final BigDecimal precio;
    private final BigDecimal cantidad;
    private final BigDecimal total;

    @Override
    public String toString() {
        return "DetalleFactura{"
                + "idLinea=" + idLinea
                + ", idProducto=" + idProducto
                + ", descripcion=" + descripcion
                + ", precio=" + precio
                + ", cantidad=" + cantidad
                + ", total=" + total
                + '}';
    }
}
