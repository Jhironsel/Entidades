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
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Paciente {

    /**
     * Metodo que permite agregar un paciente al sisteme.
     *
     * @param paciente objecto de la clase Paciente, con los campos requerido
     * para agregar un paciente.
     *
     * @return Retorna un objecto de la clase Resultados el cual indica si la
     * operacion fue exito o no.
     */
    public synchronized static Resultado agregarPaciente(Paciente paciente) {
        final String sql
                = """
                  SELECT V_ID
                  FROM SP_I_PACIENTE (
                        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                  );
                  """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(   1, paciente.getPnombre().strip());
            ps.setString(   2, paciente.getSnombre().strip());
            ps.setString(   3, paciente.getApellidos().strip());
            ps.setString(   4, paciente.getSexo().toString());
            ps.setDate(     5, paciente.getFecha_nacimiento());
            ps.setBoolean(  6, paciente.getEstado());
            ps.setString(   7, paciente.getGenerales().getCedula().strip());
            ps.setInt(      8, paciente.getGenerales().getId_tipo_sangre());
            ps.setInt(      9, paciente.getAsegurado().getId_ars());
            ps.setString(   10,paciente.getAsegurado().getNo_nss().strip());

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Resultado
                    .builder()
                    .id(rs.getInt("V_ID"))
                    .mensaje(PACIENTE_AGREGADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_INSERTAR_PACIENTE,
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR_PACIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR_PACIENTE
            = "Error al insertar paciente.";
    public static final String PACIENTE_AGREGADO_CORRECTAMENTE
            = "Paciente agregado correctamente.";

    /**
     * Metodo que te permite modificar los paciente del sistema.
     * @param paciente
     * @return
     */
    public synchronized static Resultado modificarPaciente(Paciente paciente) {
        final String sql
                = """
                  EXECUTE PROCEDURE SP_U_PACIENTE (
                        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                  );
                  """;
        try (CallableStatement ps = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(      1, paciente.getId_persona());
            ps.setString(   2, paciente.getPnombre());
            ps.setString(   3, paciente.getSnombre());
            ps.setString(   4, paciente.getApellidos());
            ps.setString(   5, paciente.getSexo().toString());
            ps.setDate(     6, paciente.getFecha_nacimiento());
            ps.setBoolean(  7, paciente.getEstado());
            ps.setString(   8, paciente.getGenerales().getCedula());
            ps.setInt(      9, paciente.getGenerales().getId_tipo_sangre());
            ps.setInt(      10, paciente.getAsegurado().getId_ars());
            ps.setString(   11, paciente.getAsegurado().getNo_nss());
            ps.setBoolean(  12, paciente.getAsegurado().getEstado());

            ps.executeUpdate();

            return Resultado
                    .builder()
                    .mensaje(PACIENTE_MODIFICADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_MODIFICAR_PACIENTE,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_MODIFICAR_PACIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String PACIENTE_MODIFICADO_CORRECTAMENTE = "Paciente modificado correctamente";
    public static final String ERROR_AL_MODIFICAR_PACIENTE = "Error al modificar cliente...";

    /**
     * Metodos que permiten borrar registros de la base de datos.
     *
     * @param idPaciente
     * @return
     */
    public synchronized static Resultado borrarPaciente(int idPaciente) {
        final String sql = "EXECUTE PROCEDURE SP_D_PACIENTE(?)";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idPaciente);

            ps.executeUpdate();

            return Resultado
                    .builder()
                    .mensaje(PACIENTE_BORRADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_BORRAR_PACIENTE,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_BORRAR_PACIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_BORRAR_PACIENTE
            = "Error al borrar paciente.";
    public static final String PACIENTE_BORRADO_CORRECTAMENTE
            = "Paciente borrado correctamente";

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
        final String sql
                = "SELECT SEXO "
                + "FROM GET_PACIENTES "
                + "WHERE ID = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            ResultSet rs = ps.executeQuery();

            rs.next();

            return rs.getString(1);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ERROR_AL_CONSULTAR_EL_SEXO_DE_UN_PACIENTE,
                    ex
            );
            return "X";
        }
    }
    public static final String ERROR_AL_CONSULTAR_EL_SEXO_DE_UN_PACIENTE
            = "Error al consultar el sexo de un paciente en el sistema. ";

    /**
     * Verificamos si existe la cedula del paciente antes de realizar un
     * registro a la base de datos.
     *
     * @param cedula Es el identificador unico de cada persona cuando nace.
     * @return boolean si es verdadero el documento existe false puede
     * realizarse el registro a la base de datos.
     */
    public synchronized static Resultado existePaciente(String cedula) {
        final String sql
                = """
                  SELECT ID
                  FROM GET_GENERALES_PACIENTES
                  WHERE CEDULA STARTING WITH ?;
                  """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, cedula.strip());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Resultado
                        .builder()
                        .id(rs.getInt("ID"))
                        .estado(Boolean.TRUE)
                        .build();
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_CONSULTAR_LA_CEDULA_DEL_PACIENTE,
                    ex
            );
        }
        return Resultado
                .builder()
                .id(-1)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String ERROR_AL_CONSULTAR_LA_CEDULA_DEL_PACIENTE
            = "Error al consultar la cedula del paciente.";

    /**
     * Consulta la propiedades de un paciente en el sistema.
     *
     * @param idPaciente id del paciente.
     * @return
     */
    public synchronized static Paciente getPacienteActivoID(int idPaciente) {
        final String sql
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
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPaciente);

            ResultSet rs = ps.executeQuery();

            rs.next();

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
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_CONSULTAR_LA_VISTA_GET_PACIENTES,
                    ex
            );
            return Paciente.builder().build();
        }
    }
    public static final String ERROR_AL_CONSULTAR_LA_VISTA_GET_PACIENTES
            = "Error al consultar la vista GET_PACIENTES del sistema.";

    /**
     *
     * @param estado
     * @return
     */
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

    /**
     *
     * @param filtro
     * @param fecha
     * @param idControlConsulta
     * @return
     */
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
