package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Tandas {

    /**
     * Las tandas en el sistema de Ballet permite definir al sistema cuales 
     * dias de la semana y horas se han asignado para dar clase de ballet, 
     * tambien definen el dia que se inicia la docencia de ballet. 
     * 
     * Query que permite ingresar un registro a la base de datos.
     */
    public static String INSERT_TANDA
            = "INSERT INTO V_TANDAS (ANNO_INICIAL, ANNO_FINAL, HORA_INICIO, "
            + "HORA_FINAL,LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, "
            + "SABADOS, DOMINGOS, CANTIDAD_ESTUDIANTES, EDAD_MINIMA, "
            + "EDAD_MAXIMA, CON_EDAD,ESTADO) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
