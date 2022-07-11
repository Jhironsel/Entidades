package sur.softsurena.entidades;

import java.io.File;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Producto{
    
    public static final String  INSERT_PRODUCTO = "INSERT INTO V_PRODUCTOS (IDCATEGORIA, CODIGO, "
            + " DESCRIPCION, IMAGEN_TEXTO, NOTA, ESTADO) VALUES (?, ?, ?, ?, ?, ? )";
    
    private final Integer id;
    private final int idCategoria;
    private final String desc_categoria;
    private final String imagen_categoria;
    private final String codigo;
    private final String descripcion;
    private final File pathImagen;
    private final String ImagenTexto;
    private final String nota;
    private final Date FechaCreacion;
    private final Boolean estado;
    private final String idUsuario;
    private final String rol;

    @Override
    public String toString() {
        return descripcion;
    }
}
