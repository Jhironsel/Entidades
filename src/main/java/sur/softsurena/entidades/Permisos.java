package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Permisos {

    private final Boolean consultar;
    private final Boolean insertar;
    private final Boolean actualizar;
    private final Boolean borrar;
    private final String vista;
    private final String loginUser;

    @Override
    public String toString() {
        return "Permisos{" + "consultar=" + consultar + ", insertar=" + insertar + ", actualizar=" + actualizar + ", borrar=" + borrar + ", vista=" + vista + ", loginUser=" + loginUser + '}';
    }
}
