package sur.softsurena.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@Getter
@SuperBuilder
public class Estudiantes extends Personas {

    private static final Logger LOG = Logger.getLogger(Estudiantes.class.getName());

    private final String matricula;
    private final Integer idPadre;
    private final Integer idMadre;
    private final Integer idTutor;
    private final Integer jcb_parentesco;

    /**
     * Metodo que permite agregar un estudiante al sistema de ballet, el cual
     * ejecuta un procedimiento almacenado en la base de datos.
     *
     * Procedimiento almacenado que permite registro de estudiantes al sistema.
     *
     * @param e Objecto de la clase estudiante que capsula los atributos de un
     * estudiantes.
     *
     * @return Retorna un mensaje que indica si el estudiantes ha sido
     * registrado si o no.
     */
    public Resultados agregarEstudiante(Estudiantes e) {

        final String INSERT_ESTUDIANTE
                = "EXECUTE PROCEDURE SP_INSERT_ESTUDIANTE (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?);";
        Resultados r;
        try (CallableStatement cs = getCnn().prepareCall(INSERT_ESTUDIANTE)) {
            cs.setInt(1, e.getAsegurado().getId_ars());
            cs.setString(2, e.getAsegurado().getNo_nss());
            cs.setInt(3, e.getGenerales().getId_tipo_sangre());
            cs.setString(4, e.getGenerales().getCedula());
            cs.setString(5, e.getPNombre());
            cs.setString(6, e.getSNombre());
            cs.setString(7, e.getApellidos());
            cs.setString(8, "" + e.getSexo());
            cs.setDate(9, e.getGenerales().getFecha_nacimiento());
            cs.setBoolean(10, e.getEstado());
            cs.setString(11, e.getMatricula());
            cs.setInt(12, e.getIdPadre());
            cs.setInt(13, e.getIdMadre());

            int cant = cs.executeUpdate();

            r = Resultados.builder().
                    id(-1).
                    mensaje("Estudiante Agregado Correctamente.").
                    cantidad(cant).build();

            return r;
        } catch (SQLException ex) {
            r = Resultados.builder().
                    id(-1).
                    mensaje("Error al agregar Estudiante.").
                    cantidad(-1).build();
            return r;
        }
    }

    public ResultSet getEstudiante(String matricula) {
        final String sql
                = " SELECT e.MATRICULA, e.CEDULA_PADREMADRE, e.NOMBRES, e.APELLIDOS, "
                + "       e.FECHANACIMIENTO, e.FECHAINGRESO, e.ESTADO, e.PERIODO_ACTUAL, "
                + "       (SELECt trim(case lunes when 1 then 'Lunes ' else trim('') end || "
                + "                    case martes when 1 then 'Martes ' else trim('') end || "
                + "                    case miercoles when 1 then 'Miercoles ' else trim('') end || "
                + "                    case jueves when 1 then 'Jueves ' else trim('') end || "
                + "                    case viernes when 1 then 'Viernes ' else trim('') end || "
                + "                    case sabados when 1 then 'Sabados ' else trim('') end || "
                + "                    case domingos when 1 then 'Domingos ' else trim('') end) ||' De '|| substring(HORA_INICIO FROM 1 for 8) ||' '||' Hasta '||substring(HORA_FINAL FROM 1 for 8) "
                + "        FROM Tandas t  "
                + "        WHERE t.ID_Tanda like e.ID_tanda) as dias, "
                + "        (SELECT p.NOMBRES||' '||p.APELLIDOS "
                + "             FROM PADREMADRES p "
                + "             WHERE p.DOCUMENTO like e.CEDULA_PADREMADRE) as NombrePadre, ID_tanda "
                + " FROM estudiantes e "
                + " WHERE e.MATRICULA = '" + matricula + "'";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public String modificarEstudiante(Estudiantes e) {
        /*Metodo para modificar los estudiante del sistema de ballet
        Actualizado el 23 de abril del 2022.
         */
        final String sql = "EXECUTE PROCEDURE SP_UPDATE_ESTUDIANTE (?, ?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, e.getId_persona());
            ps.setInt(2, e.getIdPadre());
            ps.setInt(3, e.getIdMadre());
            ps.setInt(4, e.getIdTutor());
            ps.setInt(5, e.getJcb_parentesco());
            ps.setString(6, e.getPNombre());
            ps.setString(7, e.getSNombre());
            ps.setString(8, e.getApellidos());
            ps.setString(9, "" + e.getSexo());
            ps.setDate(10, e.getFecha_nacimiento());
            ps.setBoolean(11, e.getEstado());

            ps.executeUpdate();
            return "Estudiante Modificado Correctamente...!!!";
        } catch (SQLException ex) {
            return "Estudiante no pudo ser Modificado, Conctate SoftSureña...!!!";
        }
    }

    public String inscribirEstudiante(Inscripcion i) {
        /**/
        final String sql = "";
        try (CallableStatement cs = getCnn().prepareCall(sql)) {

            cs.executeUpdate();
            return "Alumno Inscripto...";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return ex.getMessage();
        }
    }

    public void pPagoMensualidad(String idUsuario, String pago,
            String matricula, String fechaPago) {
        
        final String sql = "EXECUTE PROCEDURE P_PAGO_MENSUALIDAD(" + idUsuario + ","
            +pago + "," + matricula + ",'" + fechaPago + "')";
        
        try (CallableStatement cs = getCnn().prepareCall(sql)){
            
            cs.execute(sql);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    public ResultSet getMensualidad(String matricula, String periodo) {
        final String sql = 
                "SELECT m.consecutivo, m.fecha_pago, m.Estado, m.monto, m.pagado, m.total,"
                + " m.fecha_pagado, m.fecha_abono, m.periodo "
                + "FROM Mensualidad m "
                + "WHERE matricula = " + matricula + " and PERIODO like '" + periodo + "'";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     *
     * @param buscar
     * @return
     */
    public boolean existeEstudiante(String buscar) {
        final String sql = "SELECT (1) FROM estudiantes WHERE matricula like '"
                + buscar + "' or nombres like '" + buscar + "' or apellidos like '"
                + buscar + "'";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            return rs.next();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     *
     * @param matricula
     * @return
     */
    public boolean estadoEstudiante(String matricula) {
        final String sql = "SELECT (1) FROM estudiantes e WHERE e.matricula = " + matricula + " and e.estado = 1";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            return rs.next();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
