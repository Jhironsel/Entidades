package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Estudiante;
import sur.softsurena.entidades.Inscripcion;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Estudiante{

    /**
     * Metodo que permite agregar un estudiante al sistema de ballet, el cual
     * ejecuta un procedimiento almacenado en la base de datos.
     *
     * Procedimiento almacenado que permite registro de estudiantes al sistema.
     *
     * @param estudiante Objecto de la clase estudiante que capsula los
     * atributos de un estudiantes.
     *
     * @return Retorna un mensaje que indica si el estudiantes ha sido
     * registrado si o no.
     */
    public synchronized static Resultado agregarEntidad(
            Estudiante estudiante
    ) {
        final String sql
                = """
                  EXECUTE PROCEDURE SP_I_ESTUDIANTE (?, ?);
                  """;
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setInt(1, estudiante.getId_persona());
            cs.setString(2, estudiante.getMatricula());

            cs.execute();

            return Resultado
                    .builder()
                    .mensaje(ESTUDIANTE__AGREGADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_AGREGAR__ESTUDIANTE,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_AGREGAR__ESTUDIANTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }

    public static final String ERROR_AL_AGREGAR__ESTUDIANTE
            = "Error al agregar Estudiante.";
    public static final String ESTUDIANTE__AGREGADO__CORRECTAMENTE
            = "Estudiante Agregado Correctamente.";

    /**
     * TODO CREAR VISTA.
     * TODO Devolver una lista.
     *
     * @param matricula
     * @return
     */
    public synchronized static ResultSet getEstudiante(
            String matricula
    ) {
        final String sql
                = """
                    SELECT 
                        e.MATRICULA, e.CEDULA_PADREMADRE, e.NOMBRES, e.APELLIDOS, 
                        e.FECHANACIMIENTO, e.FECHAINGRESO, e.ESTADO, e.PERIODO_ACTUAL, 
                        (
                            SELECT 
                                TRIM(
                                    case lunes when 1 then 'Lunes ' else trim('') end || 
                                    case martes when 1 then 'Martes ' else trim('') end || 
                                    case miercoles when 1 then 'Miercoles ' else trim('') end || 
                                    case jueves when 1 then 'Jueves ' else trim('') end || 
                                    case viernes when 1 then 'Viernes ' else trim('') end || 
                                    case sabados when 1 then 'Sabados ' else trim('') end || 
                                    case domingos when 1 then 'Domingos ' else trim('') end
                                ) ||' De '|| substring(HORA_INICIO FROM 1 for 8) ||' '||' Hasta '||substring(HORA_FINAL FROM 1 for 8) 
                            FROM Tandas t  
                            WHERE t.ID_Tanda like e.ID_tanda) as dias, 
                            (
                                SELECT p.NOMBRES||' '||p.APELLIDOS 
                                FROM PADREMADRES p 
                                WHERE p.DOCUMENTO like e.CEDULA_PADREMADRE
                            ) as NombrePadre, ID_tanda 
                  FROM estudiantes e 
                  WHERE e.MATRICULA = ?
                  """;

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setString(1, matricula);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ex.getMessage(),
                    ex
            );
            return null;
        }
    }

    /**
     *
     * @param estudiante
     * @return
     */
    public synchronized static Resultado modificarEstudiante(
            Estudiante estudiante
    ) {
        final String sql
                = "EXECUTE PROCEDURE SP_UPDATE_ESTUDIANTE(?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, estudiante.getId_persona());
            ps.setInt(5, estudiante.getJcb_parentesco());
            ps.setString(6, estudiante.getPnombre());
            ps.setString(7, estudiante.getSnombre());
            ps.setString(8, estudiante.getApellidos());
            ps.setString(9, "" + estudiante.getSexo());
            ps.setDate(10, estudiante.getFecha_nacimiento());
            ps.setBoolean(11, estudiante.getEstado());

            ps.executeUpdate();
            return Resultado
                    .builder()
                    .mensaje(ESTUDIANTE__MODIFICADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            return Resultado
                    .builder()
                    .mensaje(ESTUDIANTE_NO_PUDO_SER__MODIFICADO__CONCTAT)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }

    public static final String ESTUDIANTE_NO_PUDO_SER__MODIFICADO__CONCTAT
            = "Estudiante no pudo ser Modificado, Conctate SoftSureña...!!!";
    public static final String ESTUDIANTE__MODIFICADO__CORRECTAMENTE
            = "Estudiante Modificado Correctamente...!!!";

    //--------------------------------------------------------------------------
    /**
     * 
     * @param inscripcion
     * @return 
     */
    public synchronized static String inscribirEstudiante(
            Inscripcion inscripcion
    ) {

        final String sql = "";

        try (CallableStatement cs = getCnn().prepareCall(sql)) {

            cs.executeUpdate();
            return ALUMNO__INSCRIPTO;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return ERROR_AL_INSCRIBIR_ESTUDIANTE;
        }
    }
    public static final String ERROR_AL_INSCRIBIR_ESTUDIANTE = "Error al inscribir estudiante.";
    public static final String ALUMNO__INSCRIPTO = "Alumno Inscripto...";

    //--------------------------------------------------------------------------
    /**
     * TODO CREAR CLASE.
     *
     * @param idUsuario
     * @param pago
     * @param matricula
     * @param fechaPago
     */
    public synchronized static void pPagoMensualidad(String idUsuario, String pago, String matricula,
            String fechaPago) {

        final String sql = "EXECUTE PROCEDURE P_PAGO_MENSUALIDAD(" + idUsuario + ","
                + pago + "," + matricula + ",'" + fechaPago + "')";

        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.execute(sql);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    
    //--------------------------------------------------------------------------
    /**
     * TODO Devolver una lista.
     *
     * @param matricula
     * @param periodo
     * @return
     */
    public synchronized static ResultSet getMensualidad(
            String matricula, String periodo
    ) {
        final String sql
                = "SELECT m.consecutivo, m.fecha_pago, m.Estado, m.monto, m.pagado, m.total,"
                + " m.fecha_pagado, m.fecha_abono, m.periodo "
                + "FROM Mensualidad m "
                + "WHERE matricula = " + matricula + " and PERIODO like '" + periodo + "'";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Metodo utilizado para verificar si un estudiante esta registrado en la BD
     * con la matricula proporcionada.
     *
     * @param matricula Matricula del estudiante.
     *
     * @return Devuelve un valor booleando indicando si el estudiante con la
     * matricula proporcionada está registrado en el sistema.
     */
    public synchronized static boolean existeEstudiante(String matricula) {
        final String sql = "SELECT (1) FROM PERSONAS_ESTUDIANTES_ATR WHERE MATRICULA LIKE '%?'";

        try (PreparedStatement ps = getCnn().prepareStatement(sql);) {
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Metodo utilizado para verificar que un estudiante con el estado activo se
     * encuentra en el sistema.
     *
     * @param matricula Matricula del estudiante.
     *
     * @return Devuelve un valor booleando indicando si el estudiante con la
     * matricula proporcionada está registrado en el sistema y que su estado sea
     * activo.
     */
    public synchronized static boolean estadoEstudiante(String matricula) {
        final String sql = "SELECT ID, MATRICULA, ESTADO "
                + "FROM PERSONAS_ESTUDIANTES_ATR "
                + "WHERE MATRICULA LIKE '%?' AND ESTADO";

        try (PreparedStatement ps = getCnn().prepareStatement(sql);) {
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    //--------------------------------------------------------------------------
    /**
     * 
     * @param objecto
     * @return 
     */
    public Resultado modificarEntidad(Estudiante objecto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //--------------------------------------------------------------------------
    /**
     * 
     * @return 
     */
    public List<Estudiante> getListEntidad() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //--------------------------------------------------------------------------
    /**
     * 
     * @param id
     * @return 
     */
    public Estudiante getEntidad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //--------------------------------------------------------------------------
    /**
     * 
     * @param id
     * @return 
     */
    public Resultado eliminarEntidad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //--------------------------------------------------------------------------
    /**
     * 
     * @param id
     * @return 
     */
    public Resultado borrarEntidad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
