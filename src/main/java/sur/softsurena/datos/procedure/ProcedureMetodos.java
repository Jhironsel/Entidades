package sur.softsurena.datos.procedure;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Deudas;
import sur.softsurena.entidades.Estudiantes;
import sur.softsurena.entidades.Inscripcion;
import sur.softsurena.entidades.Usuario;

public class ProcedureMetodos {

    private static CallableStatement cs;
    private static String sql;
    
    public synchronized static Boolean pagoDeuda(int idDeuda, int idTurno,
            BigDecimal monto) {
        
        try {
            cs = getCnn().prepareCall(
                    "EXECUTE PROCEDURE INSER_PAGO_DEUDAS_EXT (?, ?, ?)");
            
            cs.setInt(1, idDeuda);
            cs.setInt(2, idTurno);
            cs.setBigDecimal(3, monto);
            
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar Logger
        }
        return null;
    }
    
    /**
     * Metodo utilizado para settear las fechas del sistema.
     * 
     * @param fecha Fecha que tendra la licencia del sistema. 
     * 
     * @param idMaquina Identificador del equipo unico, UUID.
     * 
     * @param clave1 Secreto.
     * 
     * @param clave2 Secreto.
     * 
     * @return Devuelve un valor booleano que indica si tuvo exito el proceso de 
     * registro. 
     */
    public synchronized static boolean setLicencia(Date fecha, 
            String idMaquina, String clave1,String clave2) {
        try {
            sql = "EXECUTE PROCEDURE SYSTEM_SET_LICENCIA (?, ?, ?, ?)";
            
            cs = getCnn().prepareCall(sql);
            
            cs.setDate(1, fecha);
            cs.setString(2, idMaquina);
            cs.setString(3, clave1);
            cs.setString(4, clave2);
            
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar logger
            return false;
        }
    }
    
    /**
     * Este metodo debe ser analizado y documentado, ya que el modo de guardar 
     * la imagen no es el correcto. 
     * @param ruta
     * @return 
     */
    public synchronized static boolean setLogo(String ruta) {
        try {
            sql = "EXECUTE PROCEDURE CALL SYSTEM_SET_LOGO (?)";
            
            cs = getCnn().prepareCall(sql);
            
            cs.setString(1, ruta);
            
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar logger
            return false;
        }
    }

    public synchronized static boolean insertDeudas(Deudas miDeuda){
        try {
            sql = "EXECUTE PROCEDURE INSER_DEUDAS (?, ?, ?)";
            
            cs = getCnn().prepareCall(sql);
            
            cs.setString(1, miDeuda.getIdCliente());
            cs.setString(3, miDeuda.getConcepto());
            cs.setBigDecimal(4, miDeuda.getMonto());
            
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar logger
            return false;
        }
    }
    

    public synchronized static boolean habilitarTurno(String idUsuario) {
        sql = "EXECUTE PROCEDURE Admin_Habilitar_Turno (?)";
        try {
            cs = getCnn().prepareCall(sql);
            
            cs.setString(1, idUsuario);
            
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    public synchronized static boolean cerrarTurno(String idUsuario) {
        try {
            cs = getCnn().prepareCall(
                    "EXECUTE PROCEDURE Admin_CerrarTurno (?)");
            
            cs.setString(1, idUsuario);
            
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }
    
    public synchronized static boolean gasto(int idTurno, String descripcion, double monto) {
        try {
            sql = "EXECUTE PROCEDURE Cajero_gasto (?, ?, ?)";
            
            cs = getCnn().prepareCall(sql);
            
            cs.setInt(1, idTurno);
            cs.setString(2, descripcion.replace("'", ""));
            cs.setDouble(3, monto);
            
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }
    
    
    public String insertarEstudiante(Estudiantes e) {
        try {
            /*Creado el 12 de abril 2022*/
            sql = "EXECUTE PROCEDURE SP_INSERT_ESTUDIANTE ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            cs = getCnn().prepareCall(sql);
            
            cs.setString(1, e.getCedula());
            cs.setString(2, e.getMatricula());
            cs.setInt(3, e.getIdPadre());
            cs.setInt(4, e.getIdMadre());
            cs.setString(5, e.getPNombre());
            cs.setString(6, e.getSNombre());
            cs.setString(7, e.getApellidos());
            cs.setString(8, ""+e.getSexo());
            cs.setString(9, e.getDireccion());
            cs.setDate(10, e.getFecha_Nacimiento());
            cs.setBoolean(11, e.getEstado());
            
            int cant = cs.executeUpdate();
            
            return "Estudiante Agregado Correctamente { "+cant+" }";
        } catch (SQLException ex) {
            
            return "Error al agregar Estudiante";
        }
    }

    public void pPagoMensualidad(String idUsuario, String pago,
            String matricula, String fechaPago) {
        try {
            sql = "EXECUTE PROCEDURE P_PAGO_MENSUALIDAD(" + idUsuario + ","
                    + pago + "," + matricula + ",'" + fechaPago + "')";
            cs = getCnn().prepareCall(sql);
            cs.execute(sql);
        } catch (SQLException ex) {
            //Instalar logger
        }
    }

    public String inscribirEstudiante(Inscripcion i) {
        /**/
        try {
            sql = "";
            
            cs = getCnn().prepareCall(sql);
            
            
            
            cs.executeUpdate();
            return "Alumno Inscripto...";
        } catch (SQLException ex) {
            
            return ex.getMessage();
        }
    }
    
    //    Procedimiento comienzo
    public synchronized static boolean pagoCumplido(int idFactura) {
        sql = "EXECUTE PROCEDURE Cajero_PagoCumplido (?)";
        
        try {
            cs = getCnn().prepareCall(sql);
            
            cs.setInt(1, idFactura);
            
            return cs.execute();
        } catch (SQLException ex) {
            //Instalar Logger
        }
        return false;
    }

    
    
    /**
     * Metodo utilizado para modificar los usuarios del sistema con el rol 
     * doctor, el cual permite agregar al registro su Exequatur y Especialidad.
     * 
     * Metodo actualizado el 19 05 2022.
     * 
     * @param u Un objeto de la case Usuario.
     * @return Devuelve un mensaje que indica si la actualizacion fue exitosa.
     */
    public String modificarUsuario(Usuario u) {
        try {
            sql = "EXECUTE PROCEDURE SP_UPDATE_USUARIOS (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            cs = getCnn().prepareCall(sql);
            
            cs.setString(1, u.getUserName());
            cs.setString(2, u.getClave());
            cs.setString(3, u.getPNombre());
            cs.setString(4, u.getSNombre());
            cs.setString(5, u.getApellidos());            
            cs.setString(6, u.getRol());
            cs.setString(7, u.getCod_Exequatur());
            cs.setString(8, u.getEspecialidad());
            cs.setBoolean(9, u.getEstado());
            cs.setBoolean(10, u.getAdministrador());
            
            int r = cs.executeUpdate();
            
            return "Usuario Modificado Correctamente { "+r+" }";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al Modificar Usuario...";
        }
    }

    

    

    
    
    
    

    

    

    

    

    

    

    
}
