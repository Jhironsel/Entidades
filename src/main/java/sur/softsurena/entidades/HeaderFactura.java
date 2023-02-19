package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class HeaderFactura{
    private final int idCliente;
    private final int idTurno;
    private final BigDecimal efectivo;
    private final BigDecimal cambio;
    private final Date fecha;
    private final Time hora;
    private final char estado;
    private final String userName;
    private final Boolean credito;
}
