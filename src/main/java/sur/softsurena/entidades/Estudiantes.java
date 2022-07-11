package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Estudiantes extends Persona {
    
    private final String matricula;
    private final Integer idPadre;
    private final Integer idMadre;
    private final Integer idTutor;
    private final Integer jcb_parentesco;
    
    /**
     * ESTUDIANTES
     * 
     * Este metodo constructor tiene todos los campos necesario para crear un 
     * ejemplar de un estudiante.
     * 
     * Fecha creación : 17/06/2022
     * 
     * @param id Identificador del estudiante en la tabla Persona.
     * @param id_Ars Identificador de la Aseguradora de Riegos de la Salud.
     * @param noNSS Numero Nacional Seguridad Social.
     * @param id_Tipo_Sangra Identificador del tipo de sangre del estudiantes.
     * @param cedula Campo unico, cedula de identidad y electoral del estudiante.
     * @param pNombre Primer nombre del estudiante
     * @param sNombre Segundo nombre del estudiante
     * @param apellidos Apellidos del estudiante.
     * @param sexo Sexo del estudiante, valores posible M o F
     * @param fechaNacimiento Fecha en la que estudiante fue declarado
     * @param fechaIngreso Fecha de ingreso del estudiante, es creada en la BD.
     * @param estado Estado del estudiante en el sistema, True o False.
     * @param matricula Campo unico del estudiante.
     * @param idPadre Identificador del padre en la tabla de persona.
     * @param idMadre Identificador del madre en la tabla de persona.
     * @param idTutor Identificador del tutor en la tabla de persona.
     * @param jcb_parentesco Identificador de se almacena en la 
     */
                        //Campos de la clase persona
//    public Estudiantes(Integer id, Integer id_Ars, String noNSS, 
//            Integer id_Tipo_Sangra, String cedula, String pNombre, 
//            String sNombre, String apellidos, char sexo, Date fechaNacimiento, 
//            Date fechaIngreso, boolean estado,
//            //Campos de la clase estudiantes
//            String matricula, Integer idPadre, Integer idMadre, 
//            Integer idTutor, Integer jcb_parentesco
//            ) {
//        
//        super(id, id_Ars, noNSS, id_Tipo_Sangra, cedula, pNombre, sNombre, 
//                apellidos, sexo, fechaNacimiento, fechaIngreso,  estado);
//        
//        this.matricula = matricula;
//        this.idPadre = idPadre;
//        this.idMadre = idMadre;
//        this.idTutor = idTutor;
//        this.jcb_parentesco = jcb_parentesco;
//    }
//
//    public String getMatricula() {
//        return matricula;
//    }
//
//    public Integer getIdPadre() {
//        return idPadre;
//    }
//
//    public Integer getIdMadre() {
//        return idMadre;
//    }
//
//    public Integer getIdTutor() {
//        return idTutor;
//    }
//    
//    public Integer getJcb_parentesco() {
//        return jcb_parentesco;
//    }

    @Override
    public String toString() {
        return super.getNombres();
    }
    
}
