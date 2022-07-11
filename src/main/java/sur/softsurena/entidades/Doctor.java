package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Doctor extends Persona{
    private final String especialidad;
    private final String execuatur;

//    public Doctor(int id, String cedula, String pNombre, String sNombre, 
//            String apellidos, char sexo, Date fechaNacimiento, Date fechaIngreso, 
//            boolean estado, String especialidad, String execuatur ) {
//        super(id, cedula, pNombre, sNombre, apellidos, sexo, fechaNacimiento, 
//                fechaIngreso, estado);
//        this.especialidad = especialidad;
//        this.execuatur = execuatur;
//    }
//
//    
//
//    public String getEspecialidad() {
//        return especialidad;
//    }
//
//    public String getExecuatur() {
//        return execuatur;
//    }
//
//    @Override
//    public String toString() {
//        return "Doctor{" + 
//                "especialidad=" + especialidad + 
//                ", execuatur=" + execuatur + 
//                '}';
//    }
    
    
}
