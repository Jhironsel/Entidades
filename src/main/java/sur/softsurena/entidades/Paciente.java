package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class Paciente extends Persona{
    
    private final int idPadre;
    private final int idMadre;
//
//    public Paciente(Integer id, int id_Ars, String noNSS, int id_Tipo_Sangre,
//            String cedula, String pNombre, String sNombre, String apellidos, 
//            char sexo, Date fecha_Nacimiento, boolean estado,
//            int idPadre, int idMadre) {
//        super(id, id_Ars, noNSS, id_Tipo_Sangre, cedula, pNombre, sNombre, 
//                apellidos, sexo, fecha_Nacimiento, null, estado);
//        this.idPadre = idPadre;
//        this.idMadre = idMadre;
//    }


    @Override
    public String toString() {
        return super.getPNombre() + 
                (super.getSNombre().isBlank() ? "": " " + super.getSNombre()) + 
                " "+super.getApellidos();
    }
}
