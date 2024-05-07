package sur.softsurena.utilidades;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Resultado <T>{
    private final int id;
    private final String mensaje;
    private final Integer cantidad;
    private final Float cantidadDecimal;
    private final Object objecto;
    private final Object[] objectos;
    private final List<T> lista;
    private final Boolean estado;
    private final String excepcion;
    private final Integer icono;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.mensaje);
        hash = 97 * hash + Objects.hashCode(this.cantidad);
        hash = 97 * hash + Objects.hashCode(this.cantidadDecimal);
        hash = 97 * hash + Objects.hashCode(this.objecto);
        hash = 97 * hash + Arrays.deepHashCode(this.objectos);
        hash = 97 * hash + Objects.hashCode(this.lista);
        hash = 97 * hash + Objects.hashCode(this.estado);
        hash = 97 * hash + Objects.hashCode(this.excepcion);
        hash = 97 * hash + Objects.hashCode(this.icono);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Resultado<?> other = (Resultado<?>) obj;
        
        if (!Objects.equals(this.mensaje, other.mensaje)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return Objects.equals(this.icono, other.icono);
    }
    
    @Override
    public String toString() {
        return mensaje;
    }
}
