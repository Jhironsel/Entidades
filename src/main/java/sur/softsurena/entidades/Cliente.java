package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.abstracta.Persona;

@Getter
@SuperBuilder
public class Cliente extends Persona {
    private final BigDecimal totalFacturado;
    private final BigDecimal totalDeuda;
    private final Integer cantidadFacturado;
    private final Date fechaUltimaCompra;
    private final String correo;
    private final BigDecimal saldo;
    private final String masculino;
    private final String femenino;
    
    /**
     * Retorna el nombre del cliente completo.
     *
     * @return Retorna un String con la información del nombre del cliente.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}