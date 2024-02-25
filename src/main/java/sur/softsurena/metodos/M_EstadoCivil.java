package sur.softsurena.metodos;

import java.util.ArrayList;
import java.util.List;
import sur.softsurena.entidades.EstadoCivil;

/**
 * 
 * Ejemplo:
 * getEstadoCivilList().stream().forEach(
 *      estadoCivil -> <JComboBox>.addItem(estadoCivil)
 * );
 * @author jhironsel
 */
public class M_EstadoCivil {
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
}
