package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Tanda {
    private final Integer id_tanda;
    private final Date anno_inicial;
    private final Date anno_final;
    private final Time hora_inicial;
    private final Time hora_final;
    private final Boolean lunes;
    private final Boolean martes;
    private final Boolean miercoles;
    private final Boolean jueves;
    private final Boolean viernes;
    private final Boolean sabados;
    private final Boolean domingos;
    private final int cantidad_estudiantes;
    private final int edad_minima;
    private final int edad_maxima;
    private final Boolean con_edad;
    private final Boolean estado;

    @Override
    public String toString() {
        return "Tandas{"
                + "id_tanda=" + id_tanda
                + ", anno_inicial=" + anno_inicial
                + ", anno_final=" + anno_final
                + ", hora_inicial=" + hora_inicial
                + ", hora_final=" + hora_final
                + ", lunes=" + lunes
                + ", martes=" + martes
                + ", miercoles=" + miercoles
                + ", jueves=" + jueves
                + ", viernes=" + viernes
                + ", sabados=" + sabados
                + ", domingos=" + domingos
                + ", cantidad_estudiantes=" + cantidad_estudiantes
                + ", edad_minima=" + edad_minima
                + ", edad_maxima=" + edad_maxima
                + ", con_edad=" + con_edad
                + '}';
    }

}
