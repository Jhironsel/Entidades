package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;
import static sur.softsurena.utilidades.Utilidades.LOGGER;

@SuperBuilder
@Getter
public class Turnos {

    private final int id;
    private final Almacen almacen;
    private final Facturas factura;
    private final String turno_usuario;
    private final Timestamp fecha_hora_inicio;
    private final Timestamp fecha_hora_final;
    private final Boolean estado;
    private final BigDecimal monto_facturado;
    private final BigDecimal monto_devuelto;
    private final BigDecimal monto_efectivo;
    private final BigDecimal monto_credito;
    private final String rol;
    private final String user_name;

    /**
     * Metodo actualizado el 26 de abril 2022. Este metodo esta combinado con el
     * metodo usuarioTurnoActivo.
     *
     * @param userName Id del usuario a identificar.
     * @return Valor que identifica el turno activo del usuario consultado.
     */
    public synchronized static Turnos turnosActivoByUsuario(String userName) {
        final String sql
                = "SELECT ID, ID_ALMACEN, NOMBRE_ALMACEN, ID_FACTURA "
                + "FROM GET_TURNOS "
                + "WHERE ESTADO AND TRIM(TURNO_USUARIO) STARTING WITH ?;";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, userName.toUpperCase().strip());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Turnos.
                            builder().
                            id(rs.getInt("ID")).
                            almacen(
                                    Almacen.
                                            builder().
                                            id(rs.getInt("ID_ALMACEN")).
                                            nombre(rs.getString("NOMBRE_ALMACEN")).
                                            build()
                            ).
                            factura(Facturas.builder().id(rs.getInt("ID_FACTURA")).build()).
                            build();
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return Turnos.
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
    public static List<Turnos> getTurnosActivos() {
        final String sql
                = "SELECT ID, TURNO_USUARIO, FECHA_HORA_INICIO "
                + "FROM GET_TURNOS "
                + "WHERE ESTADO;";
        List<Turnos> turnosList = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    turnosList.add(Turnos.builder().
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
    public static List<Turnos> getTurnosByUserName(String userName) {
        final String sql
                = "SELECT ID, TURNO_USUARIO, FECHA_HORA_INICIO, FECHA_HORA_FINAL, "
                + "     ESTADO, MONTO_FACTURADO, MONTO_DEVUELTO, MONTO_EFECTIVO,"
                + "     MONTO_CREDITO "
                + "FROM GET_TURNOS "
                + "WHERE ESTADO IS FALSE AND TURNO_USUARIO STARTING WITH ? ";
        List<Turnos> turnosList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, userName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    turnosList.add(Turnos.builder().
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
