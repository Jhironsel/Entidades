package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class ContactosTel {

    private static final Logger LOG = Logger.getLogger(ContactosTel.class.getName());

    private final Integer id;
    private final int id_persona;
    //La accion podrá ser i Insertar, a actualizar o b borrar
    private final char accion;
    private final String telefono;
    private final String tipo;
    private final Date fecha;

    /**
     * Metodo para agregar numeros telefonicos de las personas del sistema.
     *
     * @param id
     * @param contactos
     * @return
     */
    public static boolean agregarContactosTel(int id, List<ContactosTel> contactos) {
        final String INSERT
                = "INSERT INTO V_CONTACTOS_TEL (ID_PERSONA, TELEFONO, TIPO) VALUES(?,?,?);";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
            for (ContactosTel c : contactos) {
                ps.setInt(1, id);
                ps.setString(2, c.getTelefono());
                ps.setString(3, c.getTipo());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    /**
     * Metodo para agregar numeros telefonicos de las personas del sistema.
     *
     * @param id
     * @param contactos
     * @return
     */
    public static boolean modificarContactosTel(int id, List<ContactosTel> contactos) {
        final String UPDATE
                = "UPDATE V_CONTACTOS_TEL a "
                + "SET "
                + "     a.TELEFONO = ?, "
                + "     a.TIPO = ? "
                + "WHERE "
                + "     a.ID = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {
            for (ContactosTel c : contactos) {
                ps.setString(1, c.getTelefono());
                ps.setString(2, c.getTipo());
                ps.setInt(3, id);
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
    }

    /**
     * Metodo utilizado para obtener los numeros telefonicos de un contacto en
     * particular por su identificador.
     *
     * @param id
     * @return
     */
    public synchronized static List<ContactosTel> getTelefonoByID(int id) {
        final String SELECT_BY_ID
                = "SELECT ID, TELEFONO, TIPO, FECHA "
                + "FROM V_CONTACTOS_TEL "
                + "WHERE "
                + "     ID_PERSONA = ?";

        List<ContactosTel> contactosTelList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    contactosTelList.add(
                            ContactosTel.
                                    builder().
                                    id(rs.getInt("ID")).
                                    telefono(rs.getString("TELEFONO")).
                                    tipo(rs.getString("TIPO")).
                                    fecha(rs.getDate("FECHA")).
                                    build()
                    );
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return contactosTelList;
    }

    @Override
    public String toString() {
        return telefono;
    }
}
