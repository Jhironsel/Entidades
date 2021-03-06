package sur.softsurena.entidades;

import java.io.File;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Producto {

    public static final String INSERT_PRODUCTO
            = "INSERT INTO V_PRODUCTOS (IDCATEGORIA, CODIGO, DESCRIPCION, "
            + "                         IMAGEN_TEXTO, NOTA, ESTADO) "
            + "VALUES (?, ?, ?, ?, ?, ? );";

    public static final String DELETE_PRODUCTO
            = "DELETE FROM V_PRODUCTOS WHERE id = ? ";
    
    public static final String UPDATE_PRODUCTO
            ="update V_PRODUCTOS set "
                    + "idCategoria = ?, "
                    + "codigo = ?, "
                    + "descripcion = ?, "
                    + "imagen_texto = ?, "
                    + "nota = ?, "
                    + "estado = ? "
                    + "where id = ? ";

    public static final String BUSCAR_PRODUCTO_ID_DESCRIPCION_CODIGO
            = "SELECT r.ID, r.CODIGO, r.DESCRIPCION "
            + "FROM V_PRODUCTOS r "
            + "WHERE r.ID = ? OR r.CODIGO LIKE ?% OR r.DESCRIPCION LIKE ?%";
    
    public static final String EXISTE_PRODUCTO
            = "SELECT (1) "
            + "FROM v_productos "
            + "WHERE codigo = ? or descripcion starting ?";
    
    public static final String EXISTE_CATEGORIA
            = "SELECT (1) FROM V_PRODUCTOS WHERE idCategoria = ?";
    
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
