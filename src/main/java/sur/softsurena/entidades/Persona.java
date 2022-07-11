package sur.softsurena.entidades;

import java.sql.Date;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString()
//@AllArgsConstructor
@SuperBuilder
public class Persona {

    private final Integer id;
    private final Integer id_Ars;
    private final String noNSS;
    private final Integer id_Provincia;
    private final Integer id_Municipio;
    private final Integer id_Distrito_Municipal;
    private final Integer id_Codigo_Postal;
    private final Integer id_Tipo_Sangre;
    private final char persona;//Este campo es para saber si es F Fisica o J Juridica la persona
    private final String cedula;
    private String nombres;
    private final String pNombre;
    private final String sNombre;
    private final String apellidos;
    private final char sexo;
    private final String direccion;
    private final Date fecha_Nacimiento;
    private final Date fecha_Ingreso;
    private final Boolean estado;
    private final char estado_Civil;
//
//    /**
//     * Este constructor es utilizado para 
//     * @param id
//     * @param cedula 
//     */
//    public Persona(Integer id, String pNombre, String sNombre, String apellidos, 
//            String cedula) {
//        this.id = id;
//        this.cedula = cedula;
//        this.pNombre = pNombre;
//        this.sNombre = sNombre;
//        this.nombres = pNombre +(sNombre.isEmpty() ? "": sNombre)+ " " + apellidos;;
//        
//        //Campos no utilizados
//        this.id_Ars = 0;
//        this.noNSS = "";
//        this.id_Provincia = 0;
//        this.id_Municipio = 0;
//        this.id_Distrito_Municipal = 0;
//        this.id_Codigo_Postal = 0;
//        this.id_Tipo_Sangre = 0;
//        this.persona = 'x';
//        this.apellidos = "";
//        this.sexo = 'x';
//        this.direccion = "";
//        this.fecha_Nacimiento = null;
//        this.fecha_Ingreso = null;
//        this.estado = false;
//        this.estado_Civil = 'x';
//    }
//    
//    
//
//    /**
//     * ESTUDIANTES y PACIENTE
//     * Metodo constructor para la clase estudiantes. 
//     * Actualmente se está utilizando este metodo tanto para Estudiantes como
//     * paciente. 
//     * 
//     * Metodo creado 17 dee Junio del 2022.
//     * 
//     * @param id identificador unico del registro del estudiante.
//     * @param id_Ars identificador del tipo de Aseguradora de Salud.
//     * @param noNSS Campo Unico la Aseguradora. 
//     * @param id_Tipo_Sangre Tipo de sangre que tiene el paciente.
//     * @param cedula Campo unico, cedula de identidad y electoral del estudiante.
//     * @param pNombre Primer nombre.
//     * @param sNombre Segundo nombre. 
//     * @param apellidos Apellidos Paterno y Materno
//     * @param sexo Sexo que define al estudiante.
//     * @param fecha_Nacimiento Fecha de nacimiento del estudiante.
//     * @param fecha_Ingreso Fecha de ingreso del estudiantes.
//     * @param estado Estado del estudiante, Activo o Inactivo. 
//     */
//    public Persona(Integer id, Integer id_Ars, String noNSS, int id_Tipo_Sangre, 
//            String cedula, String pNombre, String sNombre, String apellidos, 
//            char sexo, Date fecha_Nacimiento, Date fecha_Ingreso, 
//            Boolean estado) {
//        this.id = id;
//        this.id_Ars = id_Ars;
//        this.noNSS = noNSS;
//        this.id_Tipo_Sangre = id_Tipo_Sangre;
//        this.cedula = cedula;
//        this.pNombre = pNombre;
//        this.sNombre = sNombre;
//        this.apellidos = apellidos;
//        this.sexo = sexo;
//        this.fecha_Nacimiento = fecha_Nacimiento;
//        this.fecha_Ingreso = fecha_Ingreso;
//        this.estado = estado;
//        
//        id_Provincia = 0;
//        id_Municipio = 0;
//        id_Distrito_Municipal = 0;
//        id_Codigo_Postal = 0;
//        persona = 'x';
//        nombres= pNombre +(sNombre.isEmpty() ? "": sNombre)+ " " + apellidos;
//        direccion="N/A";
//        estado_Civil = 'x';
//        
//    }
//
//    /**
//     * DOCTOR
//     * Este constructor es utilizado para la clase doctor,
//     * la cual deciende de esta clase.
//     * @param id
//     * @param cedula
//     * @param pNombre
//     * @param sNombre
//     * @param apellidos
//     * @param sexo
//     * @param fecha_Nacimiento
//     * @param fecha_Ingreso
//     * @param estado 
//     */
//    public Persona(int id, String cedula, String pNombre, String sNombre, 
//            String apellidos, char sexo, Date fecha_Nacimiento, 
//            Date fecha_Ingreso, boolean estado) {
//        this.id = id;
//        this.cedula = cedula;
//        this.nombres = pNombre + (sNombre.isBlank() ? "": " " + sNombre);
//        this.pNombre = pNombre;
//        this.sNombre = sNombre;
//        this.apellidos = apellidos;
//        this.sexo = sexo;
//        this.fecha_Ingreso = fecha_Ingreso;
//        this.estado = estado;
//        
//        this.id_Ars = 0;
//        this.noNSS = "N/A";
//        this.id_Provincia = 0;
//        this.id_Municipio = 0;
//        this.id_Distrito_Municipal = 0;
//        this.id_Codigo_Postal = 0;
//        this.direccion = "N/A";
//        this.id_Tipo_Sangre = 0;
//        this.persona = 'F';
//        this.fecha_Nacimiento = null;
//        this.estado_Civil = 'S';
//        
//    }
//
//    /**
//     * PERSONA
//     * Este constructor contiene todos los campos o atributo de la clase
//     * persona, aun no es utilizado.
//     *
//     * @param id Identificador de la persona.
//     * @param id_Ars identificador de la ars de la persona.
//     * @param noNSS Numero de la seguridad social.
//     * @param id_Provincia
//     * @param id_Municipio
//     * @param id_Distrito_Municipal
//     * @param id_Codigo_Postal
//     * @param id_Tipo_Sangre
//     * @param persona
//     * @param cedula
//     * @param pNombre
//     * @param sNombre
//     * @param nombres
//     * @param apellidos
//     * @param sexo
//     * @param direccion
//     * @param fecha_Nacimiento
//     * @param fecha_Ingreso
//     * @param estado
//     * @param estado_Civil
//     */
//    public Persona(Integer id, Integer id_Ars, String noNSS, Integer id_Provincia,
//            Integer id_Municipio, Integer id_Distrito_Municipal, Integer id_Codigo_Postal,
//            Integer id_Tipo_Sangre, char persona, String cedula, String pNombre,
//            String sNombre, String nombres, String apellidos, char sexo, 
//            String direccion, Date fecha_Nacimiento, Date fecha_Ingreso, 
//            boolean estado, char estado_Civil) {
//        this.id = id;
//        this.id_Ars = id_Ars;
//        this.noNSS = noNSS;
//        this.id_Provincia = id_Provincia;
//        this.id_Municipio = id_Municipio;
//        this.id_Distrito_Municipal = id_Distrito_Municipal;
//        this.id_Codigo_Postal = id_Codigo_Postal;
//        this.id_Tipo_Sangre = id_Tipo_Sangre;
//        this.persona = persona;
//        this.cedula = cedula;
//        
//        this.pNombre = pNombre;
//        this.sNombre = sNombre;
//        this.nombres = pNombre + (sNombre.isBlank() ? "": " " + sNombre);
//        
//        this.apellidos = apellidos;
//        this.sexo = sexo;
//        this.direccion = direccion;
//        this.fecha_Nacimiento = fecha_Nacimiento;
//        this.fecha_Ingreso = fecha_Ingreso;
//        this.estado = estado;
//        this.estado_Civil = estado_Civil;
//    }
//
//    /**
//     * SOPHY BALLET
//     * Constructor utilizado para los Sistema Comercial Sophy. Contiene los
//     * campos necesario para una persona ser registrada. Constructor de uso
//     * exclusivo para la clase cliente.
//     *
//     * @param id Identificador del cliente.
//     * @param id_Provincia
//     * @param id_Municipio
//     * @param id_Distrito_Municipal
//     * @param persona
//     * @param cedula
//     * @param nombres
//     * @param apellidos
//     * @param sexo
//     * @param direccion
//     * @param fecha_Nacimiento
//     * @param fecha_Ingreso
//     * @param estado
//     * @param estado_Civil
//     */
//    public Persona(Integer id, Integer id_Provincia, Integer id_Municipio,
//            int id_Distrito_Municipal, char persona, String cedula,
//            String pNombre, String sNombre, String apellidos, char sexo, String direccion,
//            Date fecha_Nacimiento, Date fecha_Ingreso, boolean estado,
//            char estado_Civil) {
//        this.id = id;
//        this.id_Provincia = id_Provincia;
//        this.id_Municipio = id_Municipio;
//        this.id_Distrito_Municipal = id_Distrito_Municipal;
//        this.persona = persona;
//        this.cedula = cedula;
//        
//        this.pNombre = pNombre;
//        this.sNombre = sNombre;
//        this.nombres = pNombre + (sNombre.isBlank() ? "": " " + sNombre);
//        
//        this.apellidos = apellidos;
//        this.sexo = sexo;
//        this.direccion = direccion;
//        this.fecha_Nacimiento = fecha_Nacimiento;
//        this.fecha_Ingreso = fecha_Ingreso;
//        this.estado = estado;
//        this.estado_Civil = estado_Civil;
//        
//        //Variables no utilizada
//        this.id_Ars = -1;
//        this.noNSS = null;
//        this.id_Codigo_Postal = -1;
//        this.id_Tipo_Sangre = -1;
//    }
//
//    /**
//     * USUARIOS
//     * Constructor creado para los usuarios del sistema, la cual contiene los
//     * campos necesario para representar un usuario en el sistema.
//     * 
//     * @param id
//     * @param id_Ars
//     * @param noNSS
//     * @param cedula
//     * @param pNombre
//     * @param sNombre
//     * @param apellidos
//     * @param sexo
//     * @param direccion
//     * @param fecha_Nacimiento
//     * @param fecha_Ingreso
//     * @param estado 
//     */
//    public Persona(int id, int id_Ars, String noNSS, String cedula, 
//            String pNombre, String sNombre, String apellidos, 
//            char sexo, String direccion, Date fecha_Nacimiento, 
//            Date fecha_Ingreso, boolean estado) {
//        this.id = id;
//        this.id_Ars = id_Ars;
//        this.noNSS = noNSS;
//        this.cedula = cedula;
//        
//        this.pNombre = pNombre;
//        this.sNombre = sNombre;
//        this.nombres = pNombre + (sNombre.isBlank() ? "": " " + sNombre);
//        
//        this.apellidos = apellidos;
//        this.sexo = sexo;
//        this.direccion = direccion;
//        this.fecha_Nacimiento = fecha_Nacimiento;
//        this.fecha_Ingreso = fecha_Ingreso;
//        this.estado = estado;
//        
//        //Variables no utilizada
//        this.id_Provincia = -1;
//        this.id_Municipio = -1;
//        this.id_Distrito_Municipal = -1;
//        this.id_Codigo_Postal = -1;
//        this.id_Tipo_Sangre = -1;
//        this.persona = 'p';
//        this.estado_Civil = 's';
//    }
//
//    /**
//     * PROVEEDOR
//     * 
//     * Constructor utilizado para la clase Proveedores, la cual tiene los campos 
//     * necesarios para cumplir con la herencia de la clase. 
//     * 
//     * @param id
//     * @param id_Provincia
//     * @param id_Municipio
//     * @param id_Distrito_Municipal
//     * @param persona
//     * @param cedula
//     * @param pNombre
//     * @param sNombre
//     * @param apellidos
//     * @param sexo
//     * @param direccion
//     * @param estado 
//     */
//    public Persona(Integer id, Integer id_Provincia, Integer id_Municipio, Integer id_Distrito_Municipal, char persona, String cedula, String pNombre, String sNombre, String apellidos, char sexo, String direccion, Boolean estado) {
//        this.id = id;
//        this.id_Provincia = id_Provincia;
//        this.id_Municipio = id_Municipio;
//        this.id_Distrito_Municipal = id_Distrito_Municipal;
//        this.persona = persona;
//        this.cedula = cedula;
//        this.pNombre = pNombre;
//        this.sNombre = sNombre;
//        this.apellidos = apellidos;
//        this.sexo = sexo;
//        this.direccion = direccion;
//        this.estado = estado;
//        
//        this.nombres = pNombre + (sNombre.isBlank() ? "": " " + sNombre);
//        
//        //Campos no utilizado.
//        id_Ars = 0;
//        noNSS = "";
//        id_Codigo_Postal = 0;
//        id_Tipo_Sangre = 0;
//        fecha_Ingreso=null;
//        fecha_Nacimiento=null;
//        estado_Civil ='s';
//    }
//    
//    
//    
    
    @Override
    public String toString() {
        nombres = pNombre + (
                sNombre.isBlank() || 
                sNombre.isEmpty() || 
                sNombre == null ? "": " " + sNombre) + " " + apellidos;
        return nombres;
    }
}
