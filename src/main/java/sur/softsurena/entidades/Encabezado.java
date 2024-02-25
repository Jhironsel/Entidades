package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Encabezado {
    private final int id;
    private final String nombreEmpresa;
    private final String direccionEmpresa;
    private final String telefonoEmpresa;
    private final String mensajeEmpresa;

    @Override
    public String toString() {
        return nombreEmpresa;
    }
}
