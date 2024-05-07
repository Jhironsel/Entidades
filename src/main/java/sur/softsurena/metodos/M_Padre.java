package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Padre;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Padre {

    /**
     * TODO Devolver una lista.
     * 
     * @param cedula
     * @return
     */
    public synchronized static ResultSet getPadresRecuperar(String cedula) {
        final String sql
                = "SELECT NOMBRES, APELLIDOS, SEXO, IDTIPOSANGRE, IDARS, "
                + "NONSS, TELEFONO, MOVIL, CORREO, DIRECCION, CIUDAD, "
                + "FECHANACIMIENTO "
                + "FROM V_PADRES "
                + "WHERE CEDULA = ? AND ESTADO IS FALSE;";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

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
     * Metodo que permite agregar los padres al sistema pediatrico. 
     * @param padre
     * @return
     */
    public static Resultado agregarPadreMadre(Padre padre) {
        final String sql
                = "SELECT O_ID FROM SP_I_PADRE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(5, padre.getPnombre());
            ps.setString(6, padre.getSnombre());
            ps.setString(7, padre.getApellidos());
            ps.setString(8, "" + padre.getSexo());
            ps.setDate(9, padre.getFecha_nacimiento());
            ps.setBoolean(10, padre.getEstado());

            ResultSet rs = ps.executeQuery();

            rs.next();

            int id = rs.getInt(1);

            return Resultado
                    .builder()
                    .id(id)
                    .mensaje(PADRE__AGREGADO__EXITOSAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_AGREGAR_PADRE_AL_SISTEMA, 
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_AGREGAR_PADRE_AL_SISTEMA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }

    }
    public static final String ERROR_AL_AGREGAR_PADRE_AL_SISTEMA 
            = "Error al agregar padre al sistema";
    public static final String PADRE__AGREGADO__EXITOSAMENTE 
            = "Padre Agregado Exitosamente!";

    /**
     *
     * @param p
     * @return
     */
    public synchronized static Resultado modificarPadre(Padre p) {
        final String sql 
                = "EXECUTE PROCEDURE SP_U_PADRE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setString(6, p.getPnombre());
            ps.setString(7, p.getSnombre());
            ps.setString(8, p.getApellidos());
            ps.setString(9, p.getSexo().toString());
            ps.setDate(10, p.getFecha_nacimiento());
            ps.setBoolean(11, p.getEstado());

            ps.executeUpdate();
            return Resultado
                    .builder()
                    .mensaje(PADRE_MODIFICADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_MODIFICAR_PADRE, 
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_MODIFICAR_PADRE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_MODIFICAR_PADRE 
            = "Error al modificar padre...";
    public static final String PADRE_MODIFICADO_CORRECTAMENTE
            = "Padre modificado correctamente";

    /**
     * TODO Crear SP.
     * @param cedula
     * @return
     */
    public synchronized static String borrarPadre(String cedula) {
        final String sql
                = "UPDATE V_Padres "
                + "SET "
                + "    ESTADO = FALSE "
                + "WHERE "
                + "    CEDULA = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            ps.setString(1, cedula);

            ps.executeUpdate();
            return "Borrado o inactivo correctamente";
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ex.getMessage(), 
                    ex
            );
            return "Error al borrar padre...";
        }
    }

    /**
     * TODO CREAR VISTA.
     * @param idPadre
     * @return
     */
    public static ResultSet getPadreMadres(int idPadre) {

        final String sql = "SELECT * FROM PADREMADRES WHERE documento LIKE ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPadre);

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
     * @return
     */
    public static ResultSet getPadreMadres() {
        final String sql = "SELECT * FROM PADREMADRES";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
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
     * @param cedula
     * @return
     */
    public static boolean validarPadreMadre(String cedula) {
        final String sql = "SELECT 1 FROM PERSONA WHERE documento like ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ex.getMessage(), 
                    ex
            );
            return false;
        }
    }

    /**
     *
     * @param idPadre
     * @return
     */
    public synchronized static ResultSet getPadresActivoID(int idPadre) {
        final String sql = "SELECT NOMBRES, APELLIDOS, ARS, NONSS "
                + "FROM GET_PADRES "
                + "WHERE IDPADRE = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPadre);
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
     * @param cedula
     * @param sexo
     * @return
     */
    public synchronized static ResultSet getPadresActivo(String cedula, String sexo) {
        final String sql = "SELECT IDPADRE, CEDULA, NOMBRES, APELLIDOS "
                + "FROM GET_PADRES "
                + "WHERE ESTADO AND CEDULA STARTING WITH ? AND SEXO LIKE ?;";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, cedula);
            ps.setString(2, sexo);
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
     * @param estado
     * @return
     */
    public synchronized static ResultSet getPadresActivo(boolean estado) {
        final String sql = "SELECT IDPADRE, CEDULA, NOMBRES, APELLIDOS, SEXO, "
                + "IDTIPOSANGRE, TIPOSANGRE, IDARS, ARS, NONSS, "
                + "TELEFONO, MOVIL, CORREO, DIRECCION, CIUDAD, FECHANACIMIENTO, "
                + "ESTADO "
                + "FROM GET_PADRES "
                + "WHERE ESTADO IS ? and idPadre != 0";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setBoolean(1, estado);

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

    public synchronized int getIdMadrePadre(String cedula) {
        final String sql = "SELECT IDPADRE FROM V_PADRES WHERE CEDULA LIKE ?";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, cedula);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IDPADRE");
                } else {
                    return 0;
                }
            }

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ex.getMessage(), 
                    ex
            );
        }
        return -1;
    }

    public synchronized static boolean existePadre(String cedula, boolean estado) {
        final String sql = "SELECT (1) FROM V_PADRES WHERE cedula = ? and ESTADO IS ?";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, cedula);
            ps.setBoolean(2, estado);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
