package sur.softsurena.entidades;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Metrica {

    private final int id;
    private final int idConsulta;
    private final Timestamp fecha;
    private final BigDecimal pesoKG;
    private final BigDecimal estaturaM;
    private final BigDecimal escefalo;
    private final String enf_detect;
    private final String hallazgosPositivo;
    private final String idDiagnostico;
    private final String tx;
    private final String complemento;
    private final ImageIcon imagen;
    private final File imagenPath;
    private final String usuario;

    @Override
    public String toString() {
        return "Metricas{" + "id=" + id + ", idConsulta=" + idConsulta + ", fecha=" + fecha + '}';
    }
}
