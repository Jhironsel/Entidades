package sur.softsurena.utilidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FiltroBusqueda {
    private final Integer id;
    
    private final String criterioBusqueda;
    
    private final Boolean estado;
    
    private final Boolean filas;
    private final int nPaginaNro;
    private final int nCantidadFilas;
    
    private final Boolean conImagen;
}
