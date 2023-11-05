package sur.softsurena.entidades;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sur.softsurena.conexion.Conexion.getCnn;

public class BaseDeDatos {

    private static final Logger LOG = Logger.getLogger(BaseDeDatos.class.getName());

    /**
     * Metodo que nos devuelve la ruta donde se encuentra la base de datos.
     *
     * @return Devuelve un string de la ubicacion logica de la base de datos.
     */
    public synchronized static String pathBaseDeDatos() {
        final String PATH_BASE_DATOS
                = "SELECT MON$DATABASE_NAME FROM MON$DATABASE";

        try (PreparedStatement ps1 = getCnn().prepareStatement(
                PATH_BASE_DATOS,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs1 = ps1.executeQuery()) {
                rs1.next();
                return rs1.getString(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public synchronized static ResultSet metaBaseDatos() {

        final String METADATOS
                = "SELECT TRIM(CURRENT_CONNECTION), TRIM(CURRENT_DATE), "
                + "CURRENT_TIME, TRIM(CURRENT_TIMESTAMP), "
                + "TRIM(CURRENT_TRANSACTION) "
                + "FROM RDB$DATABASE";

        try (PreparedStatement ps1 = getCnn().prepareStatement(
                METADATOS,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            return ps1.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Este metodo devuelve un entero que indica el periodo de duracion de la
     * licencia concedida.
     *
     * @return
     */
    public synchronized static int periodoMaquina() {
        final String PERIODO
                = "SELECT DIAS_RESTANTES FROM V_E_S_SYS WHERE ID = 1";

        try (PreparedStatement ps = getCnn().prepareStatement(PERIODO)) {

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return -1;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     * Metodo que verifica la identificación del equipo en el sistema, tomando
     * su numero unico de registro.
     *
     * @param idMaquina identificador del equipo.
     *
     * @return Devuelve true si el equipo se encuentra resgistrado, y false si
     * no existe registro en la base de datos.
     */
    public synchronized static Boolean existeIdMaquina(String idMaquina) {

        final String sql
                = "SELECT (1) FROM V_FCH_LC a WHERE a.ID = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, idMaquina.strip());

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
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
            String idMaquina, String clave1, String clave2) {

        final String sql = "EXECUTE PROCEDURE SYSTEM_SET_LICENCIA (?, ?, ?, ?)";

        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
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
     * Metodo de consulta que es utilizada para obtener el numero de registros
     * de una tabla del sistema.
     *
     * @param tabla nombre de la tabla para obtener las cantidad de registros.
     *
     * @return Devuelve la cantindad de registros que existe en una tabla dada
     * en el parametro.
     *
     * //Conseguir con el conteos de las tablas....
     */
    public synchronized static int cantidadRegistros(String tabla) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                "SELECT COALESCE(cantidad, 0) as num "
                + "FROM tabla_reccount "
                + "WHERE tabla = ?;")) {
            ps.setString(1, tabla);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("num");
                } else {
                    return 0;
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return 0;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return 0;
        }
    }

}
