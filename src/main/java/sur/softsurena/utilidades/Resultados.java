package sur.softsurena.utilidades;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Resultados <T>{
    private final int id;
    private final String mensaje;
    private final Integer cantidad;
    private final Float cantidadDecimal;
    private final Object objecto;
    private final Object[] objectos;
    private final List<T> lista;
    
    /**
     * Estado del resultado que espera respuesta. 
     */
    private final Boolean estado;
    
    /**
     * Las excepciones son aquellas que estan definicada en la base de datos.
     */
    private final String excepcion;
    
    /**
     * Los iconos pueden ser las variables estaticas de la clase JOptionPane como:
     * 
     * JOptionPane.ERROR_MESSAGE
     * JOptionPane.INFORMATION_MESSAGE
     * JOptionPane.PLAIN_MESSAGE
     * JOptionPane.WARNING_MESSAGE
     * JOptionPane.QUESTION_MESSAGE
     */
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
        final Resultados<?> other = (Resultados<?>) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.mensaje, other.mensaje)) {
            return false;
        }
        if (!Objects.equals(this.excepcion, other.excepcion)) {
            return false;
        }
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        if (!Objects.equals(this.cantidadDecimal, other.cantidadDecimal)) {
            return false;
        }
        if (!Objects.equals(this.objecto, other.objecto)) {
            return false;
        }
        if (!Arrays.deepEquals(this.objectos, other.objectos)) {
            return false;
        }
        if (!Objects.equals(this.lista, other.lista)) {
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
