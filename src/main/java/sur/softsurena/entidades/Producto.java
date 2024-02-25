package sur.softsurena.entidades;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Producto{
    
    private final Integer id;
    private final Categoria categoria;
    private final String codigo;
    private final String descripcion;
    private final BigDecimal existencia;
    private final File pathImagen;
    private final String imagenProducto;
    private final String nota;
    private final Date fechaCreacion;
    private final Boolean estado;
    private final String userName;
    private final String rol;
    
    @Override
    public String toString() {
        return descripcion;
    }
}
