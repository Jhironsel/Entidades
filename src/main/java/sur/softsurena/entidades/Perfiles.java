package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Perfiles {

    public final static String SELECT
            ="SELECT r.ID, r.USERNAME, r.ROL, r.CLIENTE_SELECT, r.CLIENTE_INSERT, " +
            "     r.CLIENTE_UPDATE, r.CLIENTE_DELETE, r.PRODUCTO_SELECT, r.PRODUCTO_INSERT, " +
            "     r.PRODUCTO_UPDATE, r.PRODUCTO_DELETE, r.USUARIO_SELECT, r.USUARIO_INSERT, " +
            "     r.USUARIO_UPDATE, r.USUARIO_DELETE, r.CAMBIO_CLAVE, r.FACTURA_SELECT, " +
            "     r.FACTURA_INSERT, r.FACTURA_UPDATE, r.FACTURA_DELETE, r.REPORTES_SELECT, " +
            "     r.INVENTARIOS_SELECT, r.TURNO_SELECT, r.TURNO_INSERT, r.TURNO_UPDATE, " +
            "     r.TURNO_DELETE, r.DEUDAS_SELECT, r.DEUDAS_INSERT, r.DEUDAS_UPDATE, " +
            "     r.DEUDAS_DELETE " +
            "FROM V_PERFILES r";
    
    public final static String SELECT_USERNAME_ROL
            ="SELECT r.ID, r.CLIENTE_SELECT, r.CLIENTE_INSERT, " +
            "     r.CLIENTE_UPDATE, r.CLIENTE_DELETE, r.PRODUCTO_SELECT, r.PRODUCTO_INSERT, " +
            "     r.PRODUCTO_UPDATE, r.PRODUCTO_DELETE, r.USUARIO_SELECT, r.USUARIO_INSERT, " +
            "     r.USUARIO_UPDATE, r.USUARIO_DELETE, r.CAMBIO_CLAVE, r.FACTURA_SELECT, " +
            "     r.FACTURA_INSERT, r.FACTURA_UPDATE, r.FACTURA_DELETE, r.REPORTES_SELECT, " +
            "     r.INVENTARIOS_SELECT, r.TURNO_SELECT, r.TURNO_INSERT, r.TURNO_UPDATE, " +
            "     r.TURNO_DELETE, r.DEUDAS_SELECT, r.DEUDAS_INSERT, r.DEUDAS_UPDATE, " +
            "     r.DEUDAS_DELETE " +
            "FROM V_PERFILES r " + 
            "WHERE TRIM(r.USERNAME) LIKE TRIM(?) AND TRIM(r.ROL) LIKE TRIM(?)";
    
    public final static String SELECT_USERNAME
            ="SELECT r.ID, r.ROL, r.CLIENTE_SELECT, r.CLIENTE_INSERT, " +
            "     r.CLIENTE_UPDATE, r.CLIENTE_DELETE, r.PRODUCTO_SELECT, r.PRODUCTO_INSERT, " +
            "     r.PRODUCTO_UPDATE, r.PRODUCTO_DELETE, r.USUARIO_SELECT, r.USUARIO_INSERT, " +
            "     r.USUARIO_UPDATE, r.USUARIO_DELETE, r.CAMBIO_CLAVE, r.FACTURA_SELECT, " +
            "     r.FACTURA_INSERT, r.FACTURA_UPDATE, r.FACTURA_DELETE, r.REPORTES_SELECT, " +
            "     r.INVENTARIOS_SELECT, r.TURNO_SELECT, r.TURNO_INSERT, r.TURNO_UPDATE, " +
            "     r.TURNO_DELETE, r.DEUDAS_SELECT, r.DEUDAS_INSERT, r.DEUDAS_UPDATE, " +
            "     r.DEUDAS_DELETE " +
            "FROM V_PERFILES r " + 
            "WHERE TRIM(r.USERNAME) LIKE TRIM(?);";
    
    public final static String SELECT_ID
            ="SELECT r.USERNAME, r.ROL, r.CLIENTE_SELECT, r.CLIENTE_INSERT, " +
            "     r.CLIENTE_UPDATE, r.CLIENTE_DELETE, r.PRODUCTO_SELECT, r.PRODUCTO_INSERT, " +
            "     r.PRODUCTO_UPDATE, r.PRODUCTO_DELETE, r.USUARIO_SELECT, r.USUARIO_INSERT, " +
            "     r.USUARIO_UPDATE, r.USUARIO_DELETE, r.CAMBIO_CLAVE, r.FACTURA_SELECT, " +
            "     r.FACTURA_INSERT, r.FACTURA_UPDATE, r.FACTURA_DELETE, r.REPORTES_SELECT, " +
            "     r.INVENTARIOS_SELECT, r.TURNO_SELECT, r.TURNO_INSERT, r.TURNO_UPDATE, " +
            "     r.TURNO_DELETE, r.DEUDAS_SELECT, r.DEUDAS_INSERT, r.DEUDAS_UPDATE, " +
            "     r.DEUDAS_DELETE " +
            "FROM V_PERFILES r " + 
            "WHERE r.ID = ?;";
    
    public final static String INSERT 
            = 
            "INSERT INTO V_PERFILES (ID, USERNAME, ROL, CLIENTE_SELECT, CLIENTE_INSERT, " +
            "     CLIENTE_UPDATE, CLIENTE_DELETE, PRODUCTO_SELECT, PRODUCTO_INSERT, " +
            "     PRODUCTO_UPDATE, PRODUCTO_DELETE, USUARIO_SELECT, USUARIO_INSERT, " +
            "     USUARIO_UPDATE, USUARIO_DELETE, CAMBIO_CLAVE, FACTURA_SELECT, " +
            "     FACTURA_INSERT, FACTURA_UPDATE, FACTURA_DELETE, REPORTES_SELECT, " +
            "     INVENTARIOS_SELECT, TURNO_SELECT, TURNO_INSERT, TURNO_UPDATE, TURNO_DELETE, " +
            "     DEUDAS_SELECT, DEUDAS_INSERT, DEUDAS_UPDATE, DEUDAS_DELETE) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    
    /**
     * Query que permite actualizar un perfil
     */
    public final static String UPDATE 
            =   "UPDATE V_PERFILES a " +
                "SET  " +
                "     a.CLIENTE_SELECT = ?, " +
                "     a.CLIENTE_INSERT = ?, " +
                "     a.CLIENTE_UPDATE = ?, " +
                "     a.CLIENTE_DELETE = ?, " +
            
                "     a.PRODUCTO_SELECT = ?, " +
                "     a.PRODUCTO_INSERT = ?, " +
                "     a.PRODUCTO_UPDATE = ?, " +
                "     a.PRODUCTO_DELETE = ?, " +
            
                "     a.USUARIO_SELECT = ?, " +
                "     a.USUARIO_INSERT = ?, " +
                "     a.USUARIO_UPDATE = ?, " +
                "     a.USUARIO_DELETE = ?, " +
            
                "     a.CAMBIO_CLAVE = ?, " +
            
                "     a.FACTURA_SELECT = ?, " +
                "     a.FACTURA_INSERT = ?, " +
                "     a.FACTURA_UPDATE = ?, " +
                "     a.FACTURA_DELETE = ?, " +
            
                "     a.REPORTES_SELECT = ?, " +
            
                "     a.INVENTARIOS_SELECT = ?, " +
            
                "     a.TURNO_SELECT = ?, " +
                "     a.TURNO_INSERT = ?, " +
                "     a.TURNO_UPDATE = ?, " +
                "     a.TURNO_DELETE = ?, " +
            
                "     a.DEUDAS_SELECT = ?, " +
                "     a.DEUDAS_INSERT = ?, " +
                "     a.DEUDAS_UPDATE = ?, " +
                "     a.DEUDAS_DELETE = ? " +
                "WHERE " +
                "     a.ID = '?'";
    
    public final static String DELETE = "DELETE FROM V_PERFILES a WHERE a.ID = ?";
    
    
    private final Integer id;
    private final String userName;
    private final String rol;
    
    //Cliente
    private final Boolean CLIENTE_SELECT;
    private final Boolean CLIENTE_INSERT;
    private final Boolean CLIENTE_UPDATE;
    private final Boolean CLIENTE_DELETE;
    
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
    
    //CAMBIO_CLAVE
    private final Boolean CAMBIO_CLAVE;
    
    //Factura
    private final Boolean FACTURA_SELECT;
    private final Boolean FACTURA_INSERT;
    private final Boolean FACTURA_UPDATE;
    private final Boolean FACTURA_DELETE;
    
    //Reportes
    private final Boolean REPORTES_SELECT;
    
    //Investarios
    private final Boolean INVENTARIOS_SELECT;
    
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
}
