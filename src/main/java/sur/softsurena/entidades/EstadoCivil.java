package sur.softsurena.entidades;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class EstadoCivil {

    private final char abreviatura;
    private final String estadoCivil;

    public static List<EstadoCivil> getEstadoCivilList() {
        List<EstadoCivil> estadoCivilList = new ArrayList<>();
        estadoCivilList.add(
                EstadoCivil.
                        builder().
                        abreviatura('X').
                        estadoCivil("Seleccione Estado Civil").
                        build()
        );
        estadoCivilList.add(
                EstadoCivil.
                        builder().
                        abreviatura('S').
                        estadoCivil("Soltero/a").
                        build()
        );
        estadoCivilList.add(
                EstadoCivil.
                        builder().
                        abreviatura('C').
                        estadoCivil("Casado/a").
                        build()
        );
        estadoCivilList.add(
                EstadoCivil.
                        builder().
                        abreviatura('D').
                        estadoCivil("Divorciado/a").
                        build()
        );
        estadoCivilList.add(
                EstadoCivil.
                        builder().
                        abreviatura('V').
                        estadoCivil("Viudo/a").
                        build());
        estadoCivilList.add(
                EstadoCivil.
                        builder().
                        abreviatura('U').
                        estadoCivil("Unión Libre").
                        build()
        );

        return estadoCivilList;
    }

    @Override
    public String toString() {
        return estadoCivil;
    }

}
