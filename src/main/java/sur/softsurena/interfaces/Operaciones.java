package sur.softsurena.interfaces;

import java.util.List;
import sur.softsurena.entidades.FiltroBusqueda;
import sur.softsurena.entidades.Resultados;

public interface Operaciones {
    abstract Object getEntity(FiltroBusqueda filtro);
    abstract List<Object> getEntities(FiltroBusqueda filtro);
    abstract Resultados setEntity(Object object);
    abstract Resultados putEntity(Object object);
    abstract Resultados delEntity(int id);
    
}
