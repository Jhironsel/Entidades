package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Asegurado;
import sur.softsurena.entidades.Generales;
import sur.softsurena.entidades.Paciente;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Paciente {

    /**
     * Metodo que permite agregar un paciente al sisteme.
     *
     * @param paciente objecto de la clase Paciente, con los campos requerido
     * para agregar un pacient.
     *
     * @return Retorna un objecto de la clase Resultados el cual indica si la
     * operacion fue exito o no.
     */
    public synchronized static Resultados agregarPaciente(Paciente paciente) {
        final String sql
                = "SELECT V_ID "
                + "FROM SP_INSERT_PACIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, paciente.getIdPadre());
            ps.setInt(2, paciente.getIdMadre());
            ps.setInt(3, paciente.getAsegurado().getId_ars());
            ps.setString(4, paciente.getAsegurado().getNo_nss().trim());
            ps.setString(5, paciente.getGenerales().getCedula().trim());
            ps.setString(6, paciente.getPnombre().trim());
            ps.setString(7, paciente.getSnombre().trim());
            ps.setString(8, paciente.getApellidos().trim());
            ps.setString(9, "" + paciente.getSexo());
            ps.setDate(10, paciente.getFecha_nacimiento());
            ps.setInt(11, paciente.getGenerales().getId_tipo_sangre());
            ps.setBoolean(12, paciente.getEstado());

            /*Me quedo con la duda que si un paciente necesita numero de contacto
            Ya que los padres si deberian de tenerlo*/
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();

                return Resultados
                        .builder()
                        .id(rs.getInt("V_ID"))
                        .mensaje(PACIENTE_AGREGADO_CORRECTAMENTE)
                        .cantidad(-1)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .build();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR_PACIENTE)
                    .cantidad(-1)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR_PACIENTE = "Error al insertar paciente.";
    public static final String PACIENTE_AGREGADO_CORRECTAMENTE = "Paciente agregado correctamente.";

    /**
     *
     * @param paciente
     * @return
     */
    public synchronized static Resultados modificarPaciente(Paciente paciente) {
        final String sql
                = "EXECUTE PROCEDURE SP_UPDATE_PACIENTE (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (CallableStatement ps = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, paciente.getId_persona());
            ps.setInt(2, paciente.getIdPadre());
            ps.setInt(3, paciente.getIdMadre());
            ps.setString(4, paciente.getGenerales().getCedula());
            ps.setString(5, paciente.getPnombre());
            ps.setString(6, paciente.getSnombre());
            ps.setString(7, paciente.getApellidos());
            ps.setString(8, "" + paciente.getSexo());
            ps.setDate(9, paciente.getFecha_nacimiento());
            ps.setInt(10, paciente.getGenerales().getId_tipo_sangre());
            ps.setInt(11, paciente.getAsegurado().getId_ars());
            ps.setString(12, paciente.getAsegurado().getNo_nss());
            ps.setBoolean(13, paciente.getEstado());
            ps.setBoolean(14, paciente.getAsegurado().getEstado());

            int cantidad = ps.executeUpdate();

            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(PACIENTE_MODIFICADO_CORRECTAMENTE)
                    .cantidad(cantidad)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_MODIFICAR_CLIENTE)
                    .cantidad(-1)
                    .build();
        }
    }
    public static final String PACIENTE_MODIFICADO_CORRECTAMENTE = "Paciente modificado correctamente";
    public static final String ERROR_AL_MODIFICAR_CLIENTE = "Error al modificar cliente...";

    /**
     * Metodos que permiten borrar registros de la base de datos.
     *
     * @param id
     * @return
     */
    public synchronized static Resultados borrarPaciente(int id) {

        final String sql = "EXECUTE PROCEDURE SP_DELETE_PACIENTE (?);";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, id);

            int cantidad = ps.executeUpdate();

            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(PACIENTE_BORRADO_CORRECTAMENTE)
                    .cantidad(cantidad)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_BORRAR_PACIENTE)
                    .cantidad(-1)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
    public static final String ERROR_AL_BORRAR_PACIENTE = "Error al borrar paciente.";
    public static final String PACIENTE_BORRADO_CORRECTAMENTE = "Paciente borrado correctamente";

    /**
     * Metodo utilizado para consultar el sexo de los pacientes en la Base de
     * Datos.
     *
     * @param idPaciente Identificador que se encuentra registrado en la tabla
     * de V_PERSONAS_PACIENTES.
     *
     * @return
     */
    public synchronized static String getSexoPaciente(int idPaciente) {
        final String GET_SEXO_BY_ID
                = "SELECT SEXO "
                + "FROM GET_PACIENTES "
                + "WHERE ID = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_SEXO_BY_ID,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            ResultSet rs = ps.executeQuery();

            rs.next();

            return rs.getString(1);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "X";
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
        final String sql 
                = "SELECT (1) "
                + "FROM V_PACIENTES "
                + "WHERE cedula = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * TODO CREAR TEST
     * @param idPaciente
     * @return
     */
    public synchronized static Paciente getPacienteActivoID(int idPaciente) {
        final String GET_PACIENTES
                = "SELECT "
                + "     ID, "
                + "     ID_ARS, "
                + "     NONSS, "
                + "     ID_TIPO_SANGRE, "
                + "     CEDULA, "
                + "     PNOMBRE, "
                + "     SNOMBRE, "
                + "     APELLIDOS, "
                + "     SEXO, "
                + "     FECHA_NACIMIENTO, "
                + "     FECHA_INGRESO, "
                + "     ESTADO "
                + "FROM GET_PACIENTES "
                + "WHERE ID = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(GET_PACIENTES,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPaciente);

            ResultSet rs = ps.executeQuery();

            return Paciente
                    .builder()
                    .id_persona(rs.getInt("ID"))
                    .asegurado(
                            Asegurado
                                    .builder()
                                    .id_ars(rs.getInt("ID_ARS"))
                                    .no_nss(rs.getString("NONSS"))
                                    .build()
                    )
                    .generales(
                            Generales
                                    .builder()
                                    .id_tipo_sangre(rs.getInt("ID_TIPO_SANGRE"))
                                    .cedula(rs.getString("CEDULA"))
                                    .build()
                    )
                    .pnombre(rs.getString("PNOMBRE"))
                    .snombre(rs.getString("SNOMBRE"))
                    .apellidos(rs.getString("APELLIDOS"))
                    .sexo(rs.getString("SEXO").charAt(0))
                    .fecha_nacimiento(rs.getDate("FECHA_NACIMIENTO"))
                    .fecha_ingreso(rs.getDate("FECHA_INGRESO"))
                    .estado(rs.getBoolean("ESTADO"))
                    .build();
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
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
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

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
