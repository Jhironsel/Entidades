package sur.softsurena.entidades;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class D_Factura {

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
