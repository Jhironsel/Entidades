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
    public final static String INSERT
            = "SELECT p.V_ID FROM SP_INSERT_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?) p;";
    
    
    
    
    /**
     * Consulta de SQL utilizada para actualizar los clientes del sistema.
     *
     * Nota: Este Query fue actualizado el dia 17 de agosto 2022.
     */
    public final static String UPDATE //Se Utiliza
            = "EXECUTE PROCEDURE SP_UPDATE_CLIENTE_SB(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    
    
    
    /**
     * Para eliminar un cliente, no debe de tener registros en el sistema.
     *
     * Nota:
     */
    public static String DELETE //No se Utiliza
            = "EXECUTE PROCEDURE SP_DELETE_CLIENTE_SB (?, ?);";
    
    
    

    /**
     * Consulta utilizada para obtener los clientes del sistema ya sean que 
     * esten activo o inactivo. 
     * 
     * Uso:
     *      1) Se llena una tabla de cliente en el formulario 
     * frmDetalleFacturaClientes.
     * 
     */
    public static String GET_CLIENTES = 
            "SELECT ID, CEDULA, PNOMBRE, SNOMBRE, APELLIDOS FROM GET_CLIENTES";
    
    
    
    
    
    /**
     *
     */
    public static String GET_CLIENTES_ESTADO_SB
            = "SELECT r.ID, r.CEDULA, r.ESTADO FROM GET_CLIENTES_ESTADO_SB r";
    
    
    
    

    /**
     * Este query es utilizado para validar las cedulas en el sistema. 
     * Permitiendo comprobar si existe o no cedulas de los clientes nada mas.
     * 
     * La vista GET_CLIENTES_GENERALES solo consulta los clientes en la vistas 
     * de _CLIENTES unidas a la vista V_GENERALES.
     * 
     */
    public static String GET_ID_CLIENTE_BY_CEDULA
            = "SELECT COALESCE(ID, -1) "
            + "FROM GET_CLIENTES_GENERALES "
            + "WHERE TRIM(CEDULA) LIKE TRIM(?);";

    
    
    
    
    /**
     * Consulta utilizada para presentar los datos en la tabla del formulario
     * clientes.
     */
    public static String GET_CLIENTES_SB
            = "SELECT r.ID, r.CEDULA, r.PERSONA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.SEXO, "
            + "     r.FECHA_NACIMIENTO, r.ESTADO_CIVIL, r.FECHA_INGRESO, r.ESTADO "
            + "FROM GET_CLIENTES_SB r";
    
    
    
    
    
    /**
     * Consulta corta con solo 4 campos de la vista de GET_CLIENTES_SB
     */
    public static String GET_CLIENTES_SB_COMBO
            = "SELECT r.ID, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS FROM GET_CLIENTES_SB r";
    
    
    
    

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
     *
     * @return Retorna un String con la información del nombre del cliente.
     */
    @Override
    public String toString() {
        if (super.getPNombre() == null) {
            return super.getGenerales().getCedula();
        }
        return super.toString();
    }

}
