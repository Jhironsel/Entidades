package sur.softsurena.metodos;

import java.util.ArrayList;
import java.util.List;
import sur.softsurena.entidades.TipoPersona;

/**
 * En esta clase se define y mantiene los tipos de personas que existen en los
 * sistema de softsureña. 
 * 
 * @author jhironsel
 */
public class M_TipoPersona {
    public static List<TipoPersona> getTipoPersonaList() {
        List<TipoPersona> tipoPersonaList = new ArrayList<>();

        tipoPersonaList.add(TipoPersona.builder().
                abreviatura('X').persona("Tipo de persona").build());
        tipoPersonaList.add(TipoPersona.builder().
                abreviatura('F').persona("FÍSICA").build());
        tipoPersonaList.add(TipoPersona.builder().
                abreviatura('J').persona("JURÍDICA").build());
        
        return tipoPersonaList;
    }
}
