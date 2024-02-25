package sur.softsurena.entidades;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Factura {

    private final Integer id;
    private final HeaderFactura headerFactura;
    private final List<D_Factura> detalleFactura;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
