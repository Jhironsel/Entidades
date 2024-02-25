package sur.softsurena.metodos;

import java.util.ArrayList;
import java.util.List;
import sur.softsurena.entidades.Sexo;

/**
 * Clase que controla o muestra los sexo de las personas.
 * @author jhironsel
 */
public class M_Sexo {
    public static List<Sexo> getSexoList(){
        List<Sexo> sexoList = new ArrayList<>();
        sexoList.add( Sexo.builder().abreviatura('X').sexo("Seleccione sexo").build() );
        sexoList.add( Sexo.builder().abreviatura('F').sexo("FEMENINA").build());
        sexoList.add( Sexo.builder().abreviatura('M').sexo("MASCULINO").build() );
        return sexoList;
    }
}
