package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author jhironsel
 */
@Getter
@SuperBuilder
public class R_Padre_Entidad<T> {
    private final Integer id;
    private final Padre padreMadre;
    private final T entindad;
}
