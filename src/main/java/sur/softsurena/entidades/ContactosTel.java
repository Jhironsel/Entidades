package sur.softsurena.entidades;

import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ContactosTel {
    private final Integer id;
    private final int id_persona;
    private final String telefono;
    private final String tipo;
    private final Date fecha;
    
    public final static String SELECT
            = "SELECT a.ID, a.IDPERSONA, a.TELEFONO "
            + "FROM V_CONTACTS_TEL a ";

    public final static String SELECT_BY_ID
            = "SELECT a.ID, a.IDPERSONA, a.TELEFONO "
            + "FROM CONTACTOS_TEL a  "
            + "WHERE "
            + "     a.ID = ?";

    public static String INSERT
            = "INSERT INTO V_CONTACTS_TEL (ID_PERSONA, TELEFONO, TIPO) VALUES(?,?,?);";

    public final static String UPDATE
            = "UPDATE V_CONTACTS_TEL a "
            + "SET "
            + "     a.TELEFONO = ? "
            + "WHERE "
            + "     a.ID = ?";

    public final static String DELETE
            = "DELETE FROM V_CONTACTS_TEL a "
            + "WHERE "
            + "     a.ID = ? ";

    @Override
    public String toString() {
        return telefono;
    }
}
