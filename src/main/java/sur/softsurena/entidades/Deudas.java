package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Deudas extends Personas{
    private final int id_deuda;
    private final int id_factura;
    private final String concepto;
    private final BigDecimal monto;
    private final Date fecha;
    private final Time hora;
    private final char estado;
}
