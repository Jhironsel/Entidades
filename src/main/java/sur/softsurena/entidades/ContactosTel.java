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
public class ContactosTel {

    private static final Logger LOG = Logger.getLogger(ContactosTel.class.getName());
    
    /**
     * Es una variable compartida con el formulario de frmClientes, la cual 
     * define lo encabezado de columnas de las tablas de telefonos. 
     * 
     */
    public static final String[] TITULOS_TELEFONO = {"Numero", "Tipo", "Fecha"};
    
    private final Integer id;
    private final int id_persona;
    //La accion podrá ser i Insertar, a actualizar o b borrar
    private final char accion;
    private final String telefono;
    private final String tipo;
    private final Date fecha;
    
    
    

    public final static String DELETE
            = "DELETE FROM V_CONTACTS_TEL a "
            + "WHERE "
            + "     a.ID = ? ";
    
    
    /**
     * Metodo para agregar numeros telefonicos de las personas del sistema.
     *
     * @param id
     * @param contactos
     * @return
     */
    public static boolean agregarContactosTel(int id, List<ContactosTel> contactos) {
        final String INSERT
            = "INSERT INTO V_CONTACTS_TEL (ID_PERSONA, TELEFONO, TIPO) VALUES(?,?,?);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
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
            = "UPDATE V_CONTACTS_TEL a "
            + "SET "
            + "     a.TELEFONO = ?, "
            + "     a.TIPO = ? "
            + "WHERE "
            + "     a.ID = ?";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {
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
     * 
     * @param id
     * @return 
     */
    public synchronized static DefaultTableModel getTelefonoByID(int id) {
        final String SELECT_BY_ID
            = "SELECT a.ID, a.TELEFONO, a.TIPO, a.FECHA "
            + "FROM V_CONTACTOS_TEL a  "
            + "WHERE "
            + "     a.ID_PERSONA = ?";
        
        Object registroTel[] = new Object[TITULOS_TELEFONO.length];

        DefaultTableModel dtmTelefono = new DefaultTableModel(null, TITULOS_TELEFONO);

        try ( PreparedStatement ps = getCnn().prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ContactosTel t = ContactosTel.builder().
                            id(rs.getInt("ID")).
                            telefono(rs.getString("TELEFONO")).
                            tipo(rs.getString("TIPO")).
                            fecha(rs.getDate("FECHA")).build();

                    registroTel[0] = t;
                    registroTel[1] = t.getTipo();
                    registroTel[2] = t.getFecha();

                    dtmTelefono.addRow(registroTel);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return dtmTelefono;
    }
    
    @Override
    public String toString() {
        return telefono;
    }
}
