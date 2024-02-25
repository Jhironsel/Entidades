package sur.softsurena.entidades;

import java.io.File;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class E_S_SYS {

    private final Integer ID_E_S_SYS;
    private final String nombre;
    private final String telefono;
    private final Date FCHI;
    private final Date FCHA;
    private final Date FCHV;
    private final String IDMAC;
    private final String direccion;
    private final String mensaje_footer;
    private final File fileLogo;
    private final String LOGO;    
}
