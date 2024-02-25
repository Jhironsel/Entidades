package sur.softsurena.entidades;

import sur.softsurena.abstracta.Persona;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Temporal extends Persona {

    private final int id;
    private final String nombreTemporal;
    private final HeaderFactura headerFactura;
    private final List<D_Factura> detalleFactura;
}
