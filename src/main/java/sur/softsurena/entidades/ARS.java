package sur.softsurena.entidades;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ARS {

    public static String DELETE
            = "DELETE FROM V_ARS WHERE id = ?";

    private final Integer id;
    @NonNull private final String descripcion;
    @NonNull private final BigDecimal covertura;
    @NonNull private final Boolean estado;
    private final String userName;
    private final String rol;

    @Override
    public String toString() {
        return descripcion;
    }
}
