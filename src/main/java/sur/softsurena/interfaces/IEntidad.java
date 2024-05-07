package sur.softsurena.interfaces;

import java.util.List;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 * 
 * @param <T>
 */
public interface IEntidad<T> {
    List<T> getListEntidad();
    T getEntidad(Integer id);
    Resultado agregarEntidad(T objecto);
    Resultado modificarEntidad(T objecto);
    Resultado borrarEntidad(Integer id);
    public static Resultado eliminarEntidad(Integer id){
        return Resultado.builder().build();
    }
}
