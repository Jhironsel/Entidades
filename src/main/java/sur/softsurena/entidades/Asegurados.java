package sur.softsurena.entidades;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Asegurados extends Personas{
    private final Integer id;
    @NonNull private final int id_ars;
    @NonNull private final String no_nss;
}
