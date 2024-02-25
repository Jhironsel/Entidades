package sur.softsurena.entidades;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Gasto {

    private final Integer id;
    private final Integer id_turno;
    private final String descripcion;
    private final BigDecimal monto;

    @Override
    public String toString() {
        return "Gastos{"
                + "id=" + id
                + ", id_turno=" + id_turno
                + ", descripcion=" + descripcion
                + ", monto=" + monto + '}';
    }
}
