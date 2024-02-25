package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Dato_Nacimiento {

    private final int idPaciente;
    private final String fecha;
    private final String pesoNacimiento;
    private final String altura;
    private final String PC;
    private final boolean cesarea;
    private final String tiempoGestacion;
}
