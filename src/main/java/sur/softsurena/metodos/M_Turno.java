package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Almacen;
import sur.softsurena.entidades.Factura;
import sur.softsurena.utilidades.Resultados;
import sur.softsurena.entidades.Turno;
import static sur.softsurena.utilidades.Utilidades.LOGGER;

/**
 *
 * @author jhironsel
 */
public class M_Turno {
    
    /**
     * Metodo actualizado el 26 de abril 2022. Este metodo esta combinado con el
     * metodo usuarioTurnoActivo.
     *
     * @param userName Id del usuario a identificar.
     * @return Valor que identifica el turno activo del usuario consultado.
     */
    public synchronized static Turno turnosActivoByUsuario(String userName) {
        final String sql
                = "SELECT ID, ID_ALMACEN, NOMBRE_ALMACEN, ID_FACTURA "
                + "FROM GET_TURNOS "
                + "WHERE ESTADO AND TRIM(TURNO_USUARIO) STARTING WITH ?;";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, userName.toUpperCase().strip());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Turno.
                            builder().
                            id(rs.getInt("ID")).
                            almacen(
                                    Almacen.
                                            builder().
                                            id(rs.getInt("ID_ALMACEN")).
                                            nombre(rs.getString("NOMBRE_ALMACEN")).
                                            build()
                            ).
                            factura(Factura.builder().id(rs.getInt("ID_FACTURA")).build()).
                            build();
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return Turno.
                builder().
                id(-1).
                almacen(
                        Almacen.
                                builder().
                                id(-1).
                                nombre("Almacen no encontrado.").
                                build()
                ).
                build();
    }

    /**
     * Metodo utilizado para verificar si un empleado o cajero cuenta con un
     * turno habilitado por un usuario autorizado que le permita facturar en el
     * sistema.
     *
     * Metodo actualizado el 17/05/2022.
     *
     * @param userName Valor que utilizan los usuario en el sistema para iniciar
     * session.
     * @return Retorna true si el usuario cuenta con un turno habierto y false
     * si no cuenta con un turno abierto.
     */
    public synchronized static boolean usuarioTurnoActivo(String userName) {
        return (turnosActivoByUsuario(userName).getId() > 0);
    }

    /**
     *
     * @return
     */
    public static List<Turno> getTurnosActivos() {
        final String sql
                = "SELECT ID, TURNO_USUARIO, FECHA_HORA_INICIO "
                + "FROM GET_TURNOS "
                + "WHERE ESTADO;";
        List<Turno> turnosList = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    turnosList.add(Turno.builder().
                            id(rs.getInt("ID")).
                            turno_usuario(rs.getString("TURNO_USUARIO")).
                            fecha_hora_inicio(rs.getTimestamp("FECHA_HORA_INICIO")).
                            build());

                }
                return turnosList;
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param userName
     * @return
     */
    public static List<Turno> getTurnosByUserName(String userName) {
        final String sql
                = "SELECT ID, TURNO_USUARIO, FECHA_HORA_INICIO, FECHA_HORA_FINAL, "
                + "     ESTADO, MONTO_FACTURADO, MONTO_DEVUELTO, MONTO_EFECTIVO,"
                + "     MONTO_CREDITO "
                + "FROM GET_TURNOS "
                + "WHERE ESTADO IS FALSE AND TURNO_USUARIO STARTING WITH ? ";
        List<Turno> turnosList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, userName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    turnosList.add(Turno.builder().
                            id(rs.getInt("ID")).
                            fecha_hora_inicio(rs.getTimestamp("FECHA_HORA_INICIO")).
                            fecha_hora_final(rs.getTimestamp("FECHA_HORA_FINAL")).
                            monto_facturado(rs.getBigDecimal("MONTO_FACTURADO")).
                            monto_devuelto(rs.getBigDecimal("MONTO_DEVUELTO")).
                            monto_efectivo(rs.getBigDecimal("MONTO_EFECTIVO")).
                            monto_credito(rs.getBigDecimal("MONTO_CREDITO")).build());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
        return turnosList;
    }

    /**
     * Metodo que nos permite habilitar a los cajeros al modulo de facturacion.
     *
     * @param id_almacen
     * @param idUsuario
     * @return
     */
    public synchronized static Resultados habilitarTurno(int id_almacen, String idUsuario) {
        final String sql = "EXECUTE PROCEDURE ADMIN_HABILITAR_TURNO(?, ?)";
        try (CallableStatement cs = getCnn().prepareCall(sql)) {

            cs.setInt(1, id_almacen);
            cs.setString(2, idUsuario);

            return Resultados.
                    builder().
                    estado(cs.execute()).
                    mensaje("Turno habilitado correctamente.").
                    icono(JOptionPane.INFORMATION_MESSAGE).
                    build();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.
                    builder().
                    estado(Boolean.FALSE).
                    mensaje("Error al habilitar el turno").
                    icono(JOptionPane.ERROR_MESSAGE).
                    build();
        }
    }

    /**
     * Metodo que nos permite cerrar los turno de los cajeros habiertos en el
     * sistema.
     *
     * @param idTurno
     * @return
     */
    public synchronized static boolean cerrarTurno(Integer idTurno) {
        final String sql = "EXECUTE PROCEDURE ADMIN_CERRAR_TURNO(?)";
        try (CallableStatement cs = getCnn().prepareCall(sql)) {

            cs.setInt(1, idTurno);

            return cs.execute();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
