package sur.softsurena.entidades;

import java.io.File;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Categorias implements Comparable {

    private final Integer id;
    private final String descripcion;
    private final File pathImage;
    private final String image_texto;
    private final Date fecha_creacion;
    private final Boolean estado;
    private final String idUsuario;

    public final static String INSERT
            = "SELECT p.V_ID "
            + "FROM SP_INSERT_CATEGORIAS (?, ?, ?) p;";

    public final static String UPDATE
            = "UPDATE V_CATEGORIAS a "
            + "SET "
            + "     a.DESCRIPCION = ?, "
            + "     a.IMAGEN_TEXTO = ?, "
            + "     a.ESTADO = ? "
            + "WHERE "
            + "     a.ID = ?";

    public final static String DELETE
            = "DELETE FROM V_CATEGORIAS a "
            + "WHERE "
            + "     a.ID = ?";
    
    public final static String SELECT
            = "SELECT ID, DESCRIPCION FROM V_CATEGORIAS r";

    public final static String SELECT_CATEGORIA
            = "SELECT r.ID, r.DESCRIPCION, r.IMAGEN_TEXTO, r.ESTADO,"
            + "FROM V_CATEGORIAS r "
            + "ORDER BY 1";

    public final static String SELECT_CATEGORIA_ESTADO
            = "SELECT r.ID, r.DESCRIPCION, r.IMAGEN_TEXTO, r.ESTADO,"
            + "FROM V_CATEGORIAS r "
            + "WHERE r.ESTADO IS ?"
            + "ORDER BY 1";

    public final static String SELECT_ALL_CATEGORIA
            = "SELECT r.ID, r.DESCRIPCION, r.IMAGEN_TEXTO, r.FECHA_CREACION, "
            + "     r.ESTADO, r.IDUSUARIO "
            + "FROM V_CATEGORIAS r";

    public final static String SELECT_CATEGORIA_DESCRIPCION
            = "SELECT (1) "
            + "FROM V_CATEGORIAS WHERE descripcion like ?";

    public final static String SELECT_CATEGORIA_ACTIVAS
            = "SELECT r.ID, r.DESCRIPCION, r.IMAGEN_TEXTO "
            + "FROM GET_CATEGORIA_ACTIVAS r";

    @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public int compareTo(Object o) {
        Categorias c = (Categorias) o;

        return this.id.compareTo(c.id);
    }

}
