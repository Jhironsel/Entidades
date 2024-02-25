package sur.softsurena.entidades;

import java.io.File;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Categoria implements Comparable {
    private final Integer id_categoria;
    private final String descripcion;
    private final File pathImage;
    private final String image_texto;
    private final Date fecha_creacion;
    private final Boolean estado;
    private final String idUsuario;

    @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public int compareTo(Object o) {
        Categoria c = (Categoria) o;

        return this.id_categoria.compareTo(c.id_categoria);
    }

}
