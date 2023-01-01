package sur.softsurena.entidades;

import java.sql.CallableStatement;
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
public class Paciente extends Personas {

    private static final Logger LOG = Logger.getLogger(Paciente.class.getName());

    private final int idPadre;
    private final int idMadre;

    /**
     *
     * @param p
     * @return
     */
    public synchronized static String modificarPaciente(Paciente p) {
        final String UPDATE
                = "EXECUTE PROCEDURE SP_UPDATE_PACIENTE (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (CallableStatement ps = getCnn().prepareCall(
                UPDATE,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setInt(1, p.getId_persona());
            ps.setInt(2, p.getIdPadre());
            ps.setInt(3, p.getIdMadre());
            ps.setString(4, p.getGenerales().getCedula());
            ps.setString(5, p.getPNombre());
            ps.setString(6, p.getSNombre());
            ps.setString(7, p.getApellidos());
            ps.setString(8, "" + p.getSexo());
            ps.setDate(9, p.getFecha_nacimiento());
            ps.setInt(10, p.getGenerales().getId_tipo_sangre());
            ps.setInt(11, p.getAsegurado().getId_ars());
            ps.setString(12, p.getAsegurado().getNo_nss());
            ps.setBoolean(13, p.getEstado());
            ps.setBoolean(14, p.getAsegurado().getEstado());

            ps.executeUpdate();

            return "Paciente modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar cliente...";
        }
    }

    

    /**
     * Metodo que permite agregar un paciente al sisteme. Primer metodo
     * testeado.
     *
     * nota: Metodo actualizado 22 Junio del 2022.
     *
     * @Test agregarPaciente(), metodo de la prueba del funcionamiento.
     *
     * @param p objecto de la clase Paciente, con los campos requerido para
     * agregar un pacient.
     *
     * @return Retorna un mensaje que indica si el registro fue completado o no.
     *
     */
    public synchronized static String agregarPaciente(Paciente p) {
        /*Proceso de agregar paciente revizado y actualizado el 22 abril 2022*/
        final String INSERT
                = "SELECT p.V_ID "
                + "FROM SP_INSERT_PACIENTE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) p;";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {

            ps.setInt(1, p.getIdPadre());
            ps.setInt(2, p.getIdMadre());
            ps.setString(3, p.getGenerales().getCedula().trim());
            ps.setString(4, p.getPNombre().trim());
            ps.setString(5, p.getSNombre().trim());
            ps.setString(6, p.getApellidos().trim());
            ps.setString(7, "" + p.getSexo());
            ps.setDate(8, p.getFecha_nacimiento());
            ps.setInt(9, p.getGenerales().getId_tipo_sangre());
            ps.setInt(10, p.getAsegurado().getId_ars());
            ps.setString(11, p.getAsegurado().getNo_nss().trim());
            ps.setBoolean(12, p.getEstado());

            ps.executeQuery();
            /*Me quedo con la duda que si un paciente necesita numero de contacto
            Ya que los padres si deberian de tenerlo*/

            return "Paciente agregado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar paciente...";
        }
    }

    /**
     * Metodos que permiten borrar registros de la base de datos.
     */
    public synchronized static String borrarPaciente(String cedula) {

        final String sql = "UPDATE V_PACIENTES "
                + "SET "
                + "    ESTADO = FALSE "
                + "WHERE "
                + "    CEDULA = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, cedula);

            ps.executeUpdate();
            return "Paciente borrado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar paciente...";
        }
    }

    /**
     * 
     * @param idPaciente
     * @return 
     */
    public synchronized static String getSexoPaciente(int idPaciente) {
        final String GET_SEXO_BY_ID
                = "SELECT sexo "
                + "FROM V_PACIENTES "
                + "WHERE idPaciente = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_SEXO_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return "N/A";
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "N/A";
        }

    }

    /**
     * Verificamos si existe la cedula del paciente antes de realizar un
     * registro a la base de datos.
     *
     * @param cedula Es el identificador unico de cada persona cuando nace.
     * @return boolean si es verdadero el documento existe false puede
     * realizarse el registro a la base de datos.
     */
    public synchronized static boolean existePaciente(String cedula) {
        final String sql = "SELECT (1) FROM V_PACIENTES WHERE cedula = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
    
    /**
     *
     * @param idPaciente
     * @return
     */
    public synchronized static ResultSet getPacienteActivoID(int idPaciente) {
        final String GET_PACIENTES
            = "SELECT r.ID, r.ID_MADRE, r.ID_PADRE, r.ID_ARS, r.NONSS, r.ID_TIPO_SANGRE, "
            + "     r.CEDULA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.SEXO, r.FECHA_NACIMIENTO, "
            + "     r.FECHA_INGRESO, r.FECHA_HORA_ULTIMO_UPDATE, r.ESTADO, r.ID_USUARIO "
            + "FROM GET_PACIENTES r "
            + "WHERE IDPACIENTE = ?";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(GET_PACIENTES,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getDatosNacimiento(int id) {
        final String sql = "SELECT FECHANACIMIENTO, PESONACIMIENTOKG, ALTURA, MC,"
                + " CESAREA, TIEMPOGESTACION, PC "
                + "FROM V_DATOSNACIMIENTO "
                + "WHERE idPaciente = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, id);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    public synchronized static int getIdPaciente(String cedula) {
        final String sql = "SELECT IDPACIENTE FROM V_PACIENTES WHERE CEDULA LIKE ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IDPACIENTE");
                } else {
                    return 0;
                }
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return -1;
    }

    public synchronized static ResultSet getPacienteActivo(boolean estado) {
        final String sql = "SELECT IDPACIENTE, IDMADRE, CEDULAMADRE, nombreMadre, IDPADRE, "
                + "CEDULAPADRE, nombrePadre, CEDULAPACIENTE, NOMBRES, APELLIDOS, SEXO, "
                + "IDTIPOSANGRE, IDARS, COALESCE(NONSS, '') as NONSS, ESTADO "
                + "FROM GET_PACIENTES "
                + "WHERE Estado IS ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setBoolean(1, estado);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo(String filtro, String fecha,
            int idControlConsulta) {
        final String sql = "SELECT a.IDPACIENTE, a.CEDULAPACIENTE, a.NOMBRES, a.APELLIDOS, a.SEXO, "
                + "a.IDARS, COALESCE(a.NONSS, '') as NONSS "
                + "FROM GET_PACIENTES a "
                + "WHERE IDPACIENTE not in (SELECT IDPACIENTE"
                + "                         FROM V_CONSULTAS C "
                + "                         WHERE C.FECHA = ? and "
                + "                               C.IDCONTROLCONSULTA = ?) and "
                + "a.Estado " + filtro;

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo2(String filtro, String fecha,
            int idControlConsulta) {
        final String sql = "SELECT p.IDPACIENTE, p.CEDULAPACIENTE, p.NOMBRES, "
                + "p.APELLIDOS, p.SEXO, p.IDARS, p.NONSS, p.COVER, p.ESTADO,"
                + " c.IDCONSULTA,c.TURNO, c.FECHA "
                + "FROM GET_PACIENTES p "
                + "RIGHT JOIN V_CONSULTAS c ON c.IDPACIENTE = p.IDPACIENTE "
                + "left join V_CONSULTAS_APROVADAS A on A.IDCONSULTA = C.IDCONSULTA "
                + "WHERE A.IDCONSULTA is null and "
                + "      c.FECHA = ? and  c.IDCONTROLCONSULTA = ? "
                + filtro + " order by turno";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo3(String filtro, String fecha,
            int idControlConsulta) {
        final String sql = "SELECT C.IDCONSULTA, C.TURNO, P.CEDULAPACIENTE, "
                + "P.NOMBRES, P.APELLIDOS, A.COD_AUTORIZACION, A.COSTO, "
                + "A.DESCUENTO, A.TOTALCOSTO "
                + "FROM GET_PACIENTES P "
                + "right join V_CONSULTAS C on C.IDPACIENTE = P.IDPACIENTE "
                + "right join V_CONSULTAS_APROVADAS A on A.IDCONSULTA = C.IDCONSULTA "
                + "WHERE C.FECHA = ? and "
                + "C.IDCONTROLCONSULTA = ? "
                + " order by TURNO";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    
    @Override
    public String toString() {
        return super.toString();
    }
}
