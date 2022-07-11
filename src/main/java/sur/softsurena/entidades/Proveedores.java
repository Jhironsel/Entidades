package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Proveedores extends Persona{

    private String codigoProveedor;
        
//    public Proveedores(Integer id, int id_Provincia, int id_Municipio, 
//            int id_Distrito_Municipal, char persona, String cedula, 
//            String pNombre, String sNombre, String apellidos, char sexo, 
//            String direccion, boolean estado,
//            String codigoProveedor) {
//        super(id, id_Provincia, id_Municipio, id_Distrito_Municipal, persona, 
//                cedula, pNombre, sNombre, apellidos, sexo, direccion, estado);
//        
//        this.codigoProveedor = codigoProveedor;
//    }
//
//    public String getCodigoProveedor() {
//        return codigoProveedor;
//    }
//
//    public void setCodigoProveedor(String codigoProveedor) {
//        this.codigoProveedor = codigoProveedor;
//    }

    @Override
    public String toString() {
        return super.getNombres() +" "+super.getApellidos();
    }
    
    
    
}
