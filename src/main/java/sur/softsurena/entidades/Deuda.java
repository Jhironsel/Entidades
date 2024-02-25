
package sur.softsurena.entidades;

import sur.softsurena.abstracta.Persona;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Deuda extends Persona{
    private final Integer id_deuda;
    private final Integer id_cliente;
    private final Integer id_factura;
    private final String concepto;
    private final BigDecimal monto;
    private final Date fecha;
    private final Time hora;
    private final Character estadoDeuda;
    private final String estadoDeudaDesc;

    @Override
    public String toString() {
        return super.toString();
    }
}