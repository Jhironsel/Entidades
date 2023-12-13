package sur.softsurena.entidades;

import java.util.List;

public interface Operaciones {
    Object getEntity(FiltroBusqueda filtro);
    List<Object> getEntities(FiltroBusqueda filtro);
    Resultados setEntity(Object object);
    Resultados putEntity(Object object);
    Resultados delEntity(int id);
    
}
