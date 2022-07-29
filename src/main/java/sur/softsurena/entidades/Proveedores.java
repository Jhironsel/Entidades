package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Proveedores extends Persona {

    /**
     * Este campo permite hacer una consulta y una inserción de un registro
     * de proveedores al sistema.
     * 
     * Los campos necesarios son:
     * id_provincia,
     * id_Municipio,
     * persona,
     * cedula,
     * pnombre,
     * snombre,
     * apellidos,
     * sexo,
     * direccion,
     * estado,
     * codigo_proveedor
     * 
     * 
     */
    public static String INSERT_PROVEEDOR
            = "SELECT p.V_ID "
            + "FROM SP_INSERT_PROVEEDOR (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) p;";

    private String codigoProveedor;

    @Override
    public String toString() {
        return super.getNombres() + " " + super.getApellidos();
    }

}
