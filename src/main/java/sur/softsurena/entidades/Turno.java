package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Turno {

    private final int id;
    private final Almacen almacen;
    private final Factura factura;
    private final String turno_usuario;
    private final Timestamp fecha_hora_inicio;
    private final Timestamp fecha_hora_final;
    private final Boolean estado;
    private final BigDecimal monto_facturado;
    private final BigDecimal monto_devuelto;
    private final BigDecimal monto_efectivo;
    private final BigDecimal monto_credito;
    private final String rol;
    private final String user_name;
}
