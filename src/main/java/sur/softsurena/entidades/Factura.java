package sur.softsurena.entidades;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Factura {
    
    public final static String INSERT_FACTURA = 
            "INSERT INTO V_FACTURAS (id_Cliente, id_Turno, efectivo, cambio, "
                + "estado_factura, nombreTemp) "
            + "values (?, ?, ?, ?, ?, ?);";
    
    public static String DELETE
            ="DELETE FROM V_FACTURAS where id = ?";
    
    private final Integer id;
    private final HeaderFactura headerFactura;
    private final List<DetalleFactura> detalleFactura;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
