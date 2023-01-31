package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class ContactosEmail {

    private static final Logger LOG = Logger.getLogger(ContactosEmail.class.getName());
    
    /**
     * Es una variable utilizada en el formulario de frmClientes, la cual define
     * las columnas de la tabla de dicho modulo. 
     */
    public static final String[] TITULOS_CORREO = {"Correo", "Fecha"};
    
    private final Integer id;
    private final int id_persona;
    //La accion podrá ser i Insertar, a actualizar o b borrar
    private final char accion;
    private final String email;
    private final Date fecha;
    
    /**
     *
     * @param id
     * @param contactos
     * @return
     */
    public static boolean agregarContactosEmail(int id, List<ContactosEmail> contactos) {
        final String INSERT
            = "INSERT INTO V_CONTACTS_EMAIL (ID_PERSONA, EMAIL) "
            + "VALUES (?, ?);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
            for (ContactosEmail c : contactos) {
                ps.setInt(1, id);
                ps.setString(2, c.getEmail());

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
     *
     * @param id
     * @param contactos
     * @return
     */
    public static boolean modificarContactosEmail(int id, List<ContactosEmail> contactos) {
        final String UPDATE
            = "UPDATE V_CONTACTS_EMAIL a "
            + "SET "
            + "   a.EMAIL = ? "
            + "WHERE "
            + "     a.ID = ?";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {

            for (ContactosEmail c : contactos) {
                ps.setString(1, c.getEmail());
                ps.setInt(2, id);

                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
    
    
    public synchronized static DefaultTableModel getCorreoByID(int id) {
        final String SELECT_BY_ID
            = "SELECT a.ID, a.EMAIL, a.FECHA "
            + "FROM V_CONTACTS_EMAIL a "
            + "WHERE "
            + "   a.ID_PERSONA = ?; ";
        
        Object registroCorreo[] = new Object[TITULOS_CORREO.length];

        DefaultTableModel dtmCorreo = new DefaultTableModel(null, TITULOS_CORREO);

        try ( PreparedStatement ps = getCnn().prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);

            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ContactosEmail c = ContactosEmail.builder().
                            id(rs.getInt("ID")).
                            email(rs.getString("EMAIL")).
                            fecha(rs.getDate("FECHA")).build();

                    registroCorreo[0] = c;
                    registroCorreo[1] = c.getFecha();

                    dtmCorreo.addRow(registroCorreo);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return dtmCorreo;
    }
    
    @Override
    public String toString() {
        return email;
    }

}
