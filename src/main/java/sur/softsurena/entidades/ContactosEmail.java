package sur.softsurena.entidades;

import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ContactosEmail {
    
    private final Integer id;
    private final int id_persona;
    //La accion podrá ser i Insertar, a actualizar o b borrar
    private final char accion;
    private final String email;
    private final Date fecha;
    
    public static final String[] TITULOS_CORREO = {"Correo", "Fecha"};

    public final static String SELECT
            = "SELECT a.ID, a.IDPERSONA, a.EMAIL "
            + "FROM V_CONTACTS_EMAIL a ;";

    public final static String SELECT_BY_ID
            = "SELECT a.ID, a.EMAIL, a.FECHA "
            + "FROM V_CONTACTS_EMAIL a "
            + "WHERE "
            + "   a.ID_PERSONA = ?; ";

    public final static String INSERT
            = "INSERT INTO V_CONTACTS_EMAIL (ID_PERSONA, EMAIL) "
            + "VALUES (?, ?);";

    public final static String UPDATE
            = "UPDATE V_CONTACTS_EMAIL a "
            + "SET "
            + "   a.EMAIL = ? "
            + "WHERE "
            + "     a.ID = ?";

    public final static String DELETE 
            = "DELETE FROM V_CONTACTS_EMAIL a WHERE a.ID = ?";

    
    @Override
    public String toString() {
        return email;
    }

}
