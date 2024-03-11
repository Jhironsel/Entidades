package sur.softsurena.interfaces;

import java.util.List;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.utilidades.Resultado;

public interface Operaciones {
    abstract Object getEntity(FiltroBusqueda filtro);
    abstract List<Object> getEntities(FiltroBusqueda filtro);
    abstract Resultado setEntity(Object object);
    abstract Resultado putEntity(Object object);
    abstract Resultado delEntity(int id);
    
}
