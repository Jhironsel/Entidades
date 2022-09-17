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
