package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class ContactosEmail {

    private static final Logger LOG = Logger.getLogger(ContactosEmail.class.getName());

    private final Integer id;
    private final Integer id_persona;
    //La accion podrá ser i Insertar, a actualizar o b borrar
    private final Character accion;
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
                = "INSERT INTO V_CONTACTOS_EMAIL (ID_PERSONA, EMAIL) "
                + "VALUES (?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
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
                = "UPDATE V_CONTACTOS_EMAIL a "
                + "SET "
                + "   a.EMAIL = ? "
                + "WHERE "
                + "     a.ID = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {

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

    public synchronized static List<ContactosEmail> getCorreoByID(int id) {
        final String SELECT_BY_ID
                = "SELECT ID, EMAIL, FECHA "
                + "FROM V_CONTACTOS_EMAIL  "
                + "WHERE ID_PERSONA = ?;";
        List<ContactosEmail> contactosEmailList = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    contactosEmailList.add(
                            ContactosEmail.
                                    builder().
                                    id(rs.getInt("ID")).
                                    email(rs.getString("EMAIL")).
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
        return contactosEmailList;
    }
    
    public static String generarCorreo(){
        StringBuilder telefonoMovil = new StringBuilder();
        String correoDominio[] = {"@hotmail.com", "@gmail.com", "@outlook.com", "@yahoo.com"};
        int indexArea = (int) (Math.random() * 4);
        
        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);
        
        telefonoMovil.
                append("correo_prueba_").
                append(num1).
                append(num2).
                append(num3).
                append(num4).
                append(correoDominio[indexArea]);
        
        
        return telefonoMovil.toString();
    }
    
    public static boolean correo(String correo) {
        Pattern ptr = Pattern.compile(
                "[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-za-z0-9-]+\\.)+[A-za-z]{2,4}");
        return ptr.matcher(correo).matches();
    }

    @Override
    public String toString() {
        return email;
    }

}
