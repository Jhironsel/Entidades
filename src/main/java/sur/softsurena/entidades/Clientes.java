package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Clientes extends Personas {

    private final Direcciones direccion;
    private final Generales generales;
    
    /**
     * Consulta SQL utilizada para agregar clientes al sistema.
     *
     *
     */
    public final static String INSERT
            = "SELECT V_ID "
            + "FROM SP_INSERT_CLIENTE_SB(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";//13 ?

    /**
     * Consulta de SQL utilizada para actualizar los clientes del sistema.
     */
    public final static String UPDATE
            = "EXECUTE PROCEDURE SP_UPDATE_CLIENTE (?, ?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?);";//14 ?
    
    /**
     * Para eliminar un cliente, no debe de tener registros en el sistema.
     *
     */
    public static String DELETE
            = "DELETE FROM V_CLIENTES WHERE ID = ?";
    
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
    public static String GET_CLIENTES_ESTADO_SB
            = "SELECT r.ID, r.CEDULA, r.ESTADO "
            + "FROM GET_CLIENTES_ESTADO_SB r";

    /**
     * Consulta utilizada para presentar los datos en la tabla del formulario
     * clientes.
     */
    public static String GET_CLIENTES_SB_TABLA
            = "SELECT r.ID, r.CEDULA, r.PERSONA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, "
            + "     r.FECHA_INGRESO, r.ESTADO "
            + "FROM GET_CLIENTES_SB_TABLA r";

    /**
     * Retorna el nombre del cliente completo. 
     * @return Retorna un String con la información del nombre del cliente.
     */

    @Override
    public String toString() {
        if(super.getPNombre() == null){
            return generales.getCedula();
        }
        return super.toString();
    }
    
    
}
