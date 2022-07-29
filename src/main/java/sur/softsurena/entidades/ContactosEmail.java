package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ContactosEmail {

    public final static String SELECT
            = "SELECT a.ID, a.IDPERSONA, a.EMAIL "
            + "FROM V_CONTACTS_EMAIL a ;";

    public final static String SELECT_BY_ID
            = "SELECT a.ID, a.IDPERSONA, a.EMAIL "
            + "FROM V_CONTACTS_EMAIL a "
            + "WHERE "
            + "   a.IDPERSONA = ?";

    public final static String INSERT
            = "INSERT INTO V_CONTACTS_EMAIL (IDPERSONA, EMAIL) "
            + "VALUES ('IDPERSONA', 'EMAIL');";

    public final static String UPDATE
            = "UPDATE V_CONTACTS_EMAIL a "
            + "SET "
            + "   a.ID = 'ID*', "
            + "   a.IDPERSONA = 'IDPERSONA', "
            + "   a.EMAIL = 'EMAIL' "
            + "WHERE "
            + "     a.ID = ?";

    public final static String DELETE 
            = "DELETE FROM V_CONTACTS_EMAIL a WHERE a.ID = ?";

    private final Integer id;
    private final int id_persona;
    private final String email;

    @Override
    public String toString() {
        return "Contactos{"
                + "id=" + id
                + ", id_persona=" + id_persona
                + ", email=" + email
                + '}';
    }

}
