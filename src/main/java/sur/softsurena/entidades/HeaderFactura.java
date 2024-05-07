package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.abstracta.Persona;

@Getter
@SuperBuilder
public class HeaderFactura extends Persona{
    private final Integer id;
    private final Integer idContactoTel;
    private final Integer idContactoEmail;
    private final Integer idContactoDireccion;
    private final Integer idTurno;
    private final Timestamp fechaHora;
    private final BigDecimal total;
    private final BigDecimal efectivo;
    private final Character estadoFactura;
    private final String nombreTemporal;
    private final String userName;
}
