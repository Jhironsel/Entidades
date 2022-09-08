package sur.softsurena.entidades;

import java.io.File;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class E_S_SYS {

    private final int ID_E_S_SYS;
    private final Date FCHI;
    private final Date FCHA;
    private final Date FCHV;
    private final String IDMAC;
    private final File fileLogo;
    private final String LOGO;

    public static String INSERT_LOGO
            = "UPDATE OR INSERT INTO V_E_S_SYS(ID_E_S_SYS, LOGO) "
            + "VALUES(1,?) "
            + "MATCHING(ID_E_S_SYS);";

    public static String SELECT_LOGO
            = "SELECT r.LOGO \n"
            + "FROM V_E_S_SYS r \n"
            + "WHERE r.ID_E_S_SYS = 1; ";
}
