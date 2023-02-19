package sur.softsurena.entidades;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Resultados <T>{
    private final int id;
    private final String mensaje;
    private final Integer cantidad;
    private final Object objecto;
    private final Object[] objectos;
    private final List<T> lista;
    
    @Override
    public String toString() {
        return mensaje;
    }
    
    
}
