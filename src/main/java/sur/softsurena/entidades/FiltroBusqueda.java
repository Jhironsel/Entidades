package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FiltroBusqueda {
    private final Integer id;
    
    private final String criterioBusqueda;
    
    private final Boolean estado;
    
    private final Boolean filas;
    private final Integer nPaginaNro;
    private final Integer nCantidadFilas;
    
    private final Boolean conImagen;
}
