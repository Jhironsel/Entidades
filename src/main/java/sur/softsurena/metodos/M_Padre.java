package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Padres;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Padre {

    /**
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
     *
     * @param p
     * @return
     */
    public static Resultado agregarPadreMadre(Padres p) {
        Resultado r;
        final String sql
                = "SELECT p.O_ID FROM SP_INSERT_PADRES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) p;";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, p.getAsegurado().getId_ars());
            ps.setString(2, p.getAsegurado().getNo_nss());
            ps.setInt(3, p.getGenerales().getId_tipo_sangre());
            ps.setString(4, p.getGenerales().getCedula());
            ps.setString(5, p.getPnombre());
            ps.setString(6, p.getSnombre());
            ps.setString(7, p.getApellidos());
            ps.setString(8, "" + p.getSexo());
            ps.setDate(9, p.getFecha_nacimiento());
            ps.setBoolean(10, p.getEstado());
            ps.setString(11, "" + p.getGenerales().getEstado_civil());

            ResultSet rs = ps.executeQuery();

            rs.next();

            int id = rs.getInt(1);

            r = Resultado
                    .builder()
                    .id(id)
                    .mensaje("Padre Agregado Exitosamente!")
                    .cantidad(-1)
                    .build();

            return r;
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ex.getMessage(), 
                    ex
            );
            r = Resultado
                    .builder()
                    .id(-1)
                    .mensaje("Error al agregar padre al sistema")
                    .cantidad(-1)
                    .build();
            return r;
        }

    }

    /**
     *
     * @param p
     * @return
     */
    public synchronized static String modificarPadre(Padres p) {
        final String sql = "EXECUTE PROCEDURE SP_UPDATE_PADRES (?, ?, ?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, p.getId_persona());
            ps.setInt(2, p.getAsegurado().getId_ars());
            ps.setString(3, p.getAsegurado().getNo_nss());
            ps.setInt(4, p.getGenerales().getId_tipo_sangre());
            ps.setString(5, p.getGenerales().getCedula());
            ps.setString(6, p.getPnombre());
            ps.setString(7, p.getSnombre());
            ps.setString(8, p.getApellidos());
            ps.setString(9, "" + p.getSexo());
            ps.setDate(10, p.getFecha_nacimiento());
            ps.setBoolean(11, p.getEstado());
            ps.setString(12, "" + p.getGenerales().getEstado_civil());

            ps.executeUpdate();
            return "Padre modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar padre...";
        }
    }

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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
     * 
     * @param estado
     * @return
     */
    public synchronized static int numeroPadres(boolean estado) {
        final String sql = "SELECT CANTIDAD "
                + "FROM V_RECCOUNT "
                + "WHERE ESTADO IS ? AND IDPADRE != 0;";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setBoolean(1, estado);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ex.getMessage(), 
                    ex
            );
        }
        return 1;
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
