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
    public String toString() {
        return mensaje;
    }
}
