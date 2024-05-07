package sur.softsurena.entidades;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class D_Factura {

    private final Integer idLinea;
    private final Integer idProducto;
    private final BigDecimal precio;
    private final BigDecimal cantidad;
    
    private final String descripcion;
    
    @Override
    public String toString() {
        return "DetalleFactura{"
                + "idLinea=" + idLinea
                + ", idProducto=" + idProducto
                + ", descripcion=" + descripcion
                + ", precio=" + precio
                + ", cantidad=" + cantidad
                + '}';
    }
}
