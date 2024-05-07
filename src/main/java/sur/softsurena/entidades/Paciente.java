package sur.softsurena.entidades;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.abstracta.Persona;

@Getter
@SuperBuilder
public class Paciente extends Persona {

    private final BigDecimal pesoNacimiento;
    private final BigDecimal altura;
    private final BigDecimal perimetroCefalico;
    private final Boolean cesarea;
    private final Integer tiempoGestacion;
    private final BigDecimal masaCefalica;
    
    @Override
    public String toString() {
        return super.toString();
    }
}
    