package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Perfiles {

    public final static String CAMPOS
            = " r.ID, r.USERNAME, r.ROL, r.CLIENTE_SELECT, r.CLIENTE_INSERT, \n"
            + " r.CLIENTE_UPDATE, r.CLIENTE_DELETE, r.CLIENTE_MODIFICAR_CEDULA, \n"
            + " r.PRODUCTO_SELECT, r.PRODUCTO_INSERT, r.PRODUCTO_UPDATE, r.PRODUCTO_DELETE, \n"
            + " r.USUARIO_SELECT, r.USUARIO_INSERT, r.USUARIO_UPDATE, r.USUARIO_DELETE, \n"
            + " r.USUARIO_CAMBIO_CLAVE, r.FACTURA_SELECT, r.FACTURA_INSERT, \n"
            + " r.FACTURA_UPDATE, r.FACTURA_DELETE, r.TURNO_SELECT, r.TURNO_INSERT, \n"
            + " r.TURNO_UPDATE, r.TURNO_DELETE, r.DEUDAS_SELECT, r.DEUDAS_INSERT, \n"
            + " r.DEUDAS_UPDATE, r.DEUDAS_DELETE, r.REPORTES_SELECT, r.INVENTARIOS_SELECT \n";

    public final static int tamano = Perfiles.CAMPOS.split(" r.").length - 1;

    public final static String PARAMETROS
            = "?,".repeat(tamano).substring(0, tamano*2-1);;


    public final static String SELECT
            = "SELECT" + CAMPOS
            + "FROM V_PERFILES r ";

    public final static String SELECT_USERNAME_ROL
            = SELECT
            + "WHERE TRIM(r.USERNAME) LIKE TRIM(?) AND TRIM(r.ROL) LIKE TRIM(?)";

    public final static String SELECT_USERNAME
            = SELECT
            + "WHERE TRIM(r.USERNAME) LIKE TRIM(?);";

    public final static String SELECT_ID
            = SELECT
            + "WHERE r.ID = ?;";

    public final static String INSERT
            = "INSERT INTO V_PERFILES (" + CAMPOS.replaceAll("r.", "") + ") "
            + "VALUES (" + PARAMETROS + ");";

    /**
     * Query que permite actualizar un perfil
     */
    public final static String UPDATE
            = "UPDATE V_PERFILES a \n"
            + "SET \n"
            + " a.CLIENTE_SELECT = ?, \n"
            + " a.CLIENTE_INSERT = ?, \n"
            + " a.CLIENTE_UPDATE = ?, \n"
            + " a.CLIENTE_DELETE = ?, \n"
            + " a.PRODUCTO_SELECT = ?, \n"
            + " a.PRODUCTO_INSERT = ?, \n"
            + " a.PRODUCTO_UPDATE = ?, \n"
            + " a.PRODUCTO_DELETE = ?, \n"
            + " a.USUARIO_SELECT = ?, \n"
            + " a.USUARIO_INSERT = ?, \n"
            + " a.USUARIO_UPDATE = ?, \n"
            + " a.USUARIO_DELETE = ?, \n"
            + " a.CAMBIO_CLAVE = ?, \n"
            + " a.FACTURA_SELECT = ?, \n"
            + " a.FACTURA_INSERT = ?, \n"
            + " a.FACTURA_UPDATE = ?, \n"
            + " a.FACTURA_DELETE = ?, \n"
            + " a.REPORTES_SELECT = ?, \n"
            + " a.INVENTARIOS_SELECT = ?, \n"
            + " a.TURNO_SELECT = ?, \n"
            + " a.TURNO_INSERT = ?, \n"
            + " a.TURNO_UPDATE = ?, \n"
            + " a.TURNO_DELETE = ?, \n"
            + " a.DEUDAS_SELECT = ?, \n"
            + " a.DEUDAS_INSERT = ?, \n"
            + " a.DEUDAS_UPDATE = ?, \n"
            + " a.DEUDAS_DELETE = ? \n"
            + "WHERE \n"
            + "     a.ID = ?";

    public final static String DELETE = "DELETE FROM V_PERFILES a WHERE a.ID = ?";

    private final Integer id;
    private final String userName;
    private final String rol;

    //Cliente
    private final Boolean CLIENTE_SELECT;
    private final Boolean CLIENTE_INSERT;
    private final Boolean CLIENTE_UPDATE;
    private final Boolean CLIENTE_DELETE;
    private final Boolean CLIENTE_MODIFICAR_CEDULA;

    //Producto
    private final Boolean PRODUCTO_SELECT;
    private final Boolean PRODUCTO_INSERT;
    private final Boolean PRODUCTO_UPDATE;
    private final Boolean PRODUCTO_DELETE;

    //Usuario
    private final Boolean USUARIO_SELECT;
    private final Boolean USUARIO_INSERT;
    private final Boolean USUARIO_UPDATE;
    private final Boolean USUARIO_DELETE;
    private final Boolean USUARIO_CAMBIO_CLAVE;

    //Factura
    private final Boolean FACTURA_SELECT;
    private final Boolean FACTURA_INSERT;
    private final Boolean FACTURA_UPDATE;
    private final Boolean FACTURA_DELETE;

    //Turno
    private final Boolean TURNO_SELECT;
    private final Boolean TURNO_INSERT;
    private final Boolean TURNO_UPDATE;
    private final Boolean TURNO_DELETE;

    //Deudas
    private final Boolean DEUDAS_SELECT;
    private final Boolean DEUDAS_INSERT;
    private final Boolean DEUDAS_UPDATE;
    private final Boolean DEUDAS_DELETE;

    //Reportes
    private final Boolean REPORTES_SELECT;

    //Investarios
    private final Boolean INVENTARIOS_SELECT;
}
