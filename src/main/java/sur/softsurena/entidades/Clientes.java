package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Clientes extends Personas {
    
    /**
     * Consulta SQL utilizada para agregar clientes al sistema.
     *
     * Nota: Este Query fue actualizado el dia 16 de agosto 2022.
     *
     */
    public final static String INSERT //Se utiliza
            = "SELECT V_ID "
            + "FROM SP_INSERT_CLIENTE_SB(?, ?, ?, ?, ?, ?, ?, ?, ?);";//9

    /**
     * Consulta de SQL utilizada para actualizar los clientes del sistema.
     * 
     * Nota: Este Query fue actualizado el dia 17 de agosto 2022.
     */
    public final static String UPDATE //Se Utiliza
            = "EXECUTE PROCEDURE SP_UPDATE_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";//10 ?
    
    /**
     * Para eliminar un cliente, no debe de tener registros en el sistema.
     *
     * Nota: 
     */
    public static String DELETE //No se Utiliza
            = "EXECUTE PROCEDURE SP_DELETE_CLIENTE_SB (?, ?);";
    
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
    public static String GET_CLIENTES_SB
            = "SELECT r.ID, r.CEDULA, r.PERSONA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.SEXO, " 
            + "     r.FECHA_NACIMIENTO, r.ESTADO_CIVIL, r.FECHA_INGRESO, r.ESTADO " 
            + "FROM GET_CLIENTES_SB r";

    /**
     * Consulta utilizada para presentar los datos en la tabla del formulario
     * clientes.
     */
    public static String GET_CLIENTES_SB_BY_ID
            = "SELECT r.ID, r.CEDULA, r.PERSONA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.SEXO, " 
            + "     r.FECHA_NACIMIENTO, r.ESTADO_CIVIL, r.FECHA_INGRESO, r.ESTADO " 
            + "FROM GET_CLIENTES_SB r "
            + "WHERE r.ID = ?";
    
    /**
     * Retorna el nombre del cliente completo. 
     * @return Retorna un String con la información del nombre del cliente.
     */
    @Override
    public String toString() {
        if(super.getPNombre() == null){
            return super.getGenerales().getCedula();
        }
        return super.toString();
    }
    
    
}
