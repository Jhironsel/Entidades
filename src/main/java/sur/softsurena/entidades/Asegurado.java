package sur.softsurena.entidades;

import sur.softsurena.abstracta.Persona;
import java.util.Objects;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Asegurado extends Persona {

    private final int id_ars;
    private final String no_nss;
    private final Boolean estado;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id_ars;
        hash = 83 * hash + Objects.hashCode(this.no_nss);
        hash = 83 * hash + Objects.hashCode(this.estado);
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
        final Asegurado other = (Asegurado) obj;
        return Objects.equals(this.no_nss, other.no_nss);
    }

    @Override
    public String toString() {
        return no_nss;
    }
}
