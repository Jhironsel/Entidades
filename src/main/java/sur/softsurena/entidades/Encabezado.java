package sur.softsurena.entidades;

import lombok.Data;

@Data
public class Encabezado {
    private final int id;
    private final String nombreEmpresa;
    private final String direccionEmpresa;
    private final String telefonoEmpresa;
    private final String mensajeEmpresa;
}
