package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Cliente extends Persona {

    /**
     * Consulta SQL utilizada para agregar clientes al sistema.
     *
     *
     */
    public final static String INSERT_CLIENTE
            = "SELECT V_ID "
            + "FROM SP_INSERT_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * Esta consulta es utilizada para obtener el identificador, nombres y
     * apellidos de los clientes
     */
    public final static String SELECT_CLIENTE_POR_ID_PSNOMBRE_APELLIDOS
            = "SELECT id, pNombre, sNombre, apellidos "
            + "FROM GET_CLIENTES "
            + "WHERE estado || pNombre like ?% || sNombre like ?% || id = ? "
            + "order by 1";

    /**
     * Para eliminar un cliente, no debe de tener registros en el sistema. 
     */
    public static String DELETE
            ="DELETE FROM V_CLIENTES WHERE ID = ?";
    
    /**
     * Se consulta a la base de datos que no exista un cliente con una cedula x 
     * 
     */
    public static String GET_CLIENTES
            = "SELECT (1) "
            + "FROM GET_CLIENTES "
            + "WHERE CEDULA starting ? ";
    
    /**
     * 
     */
    public static String GET_CLIENTES_ESTAD
            = "SELECT r.ID, r.CEDULA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.ESTADO "
            + "FROM GET_CLIENTES_SB r "
            + "WHERE r.ESTADO";
}
