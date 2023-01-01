package sur.softsurena.entidades;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.utilidades.Utilidades;

@SuperBuilder
@Getter
public class Metricas {

    private static final Logger LOG = Logger.getLogger(Metricas.class.getName());
    
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
    
    /**
     * 
     */
    private static final String INSERT
            = "INSERT INTO V_METRICAS (IDCONSULTA, PESOKG, ESTATURAMETRO, ESCEFALO, "
            + "     ENF_DETECT, HALLAZGOS_POS, ID_DIAG, TX, COMPLEMENTO,"
            + "     IMAGEN_TEXTO) " +
              "     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * 
     * @param m 
     */
    public synchronized static void agregarMetricas(Metricas m) {
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
            
            ps.setInt(1, m.getIdConsulta());
            ps.setBigDecimal(2, m.getPesoKG());
            ps.setBigDecimal(3, m.getEstaturaM());
            ps.setBigDecimal(4, m.getEscefalo());
            ps.setString(5, m.getEnf_detect());
            ps.setString(6, m.getHallazgosPositivo());
            ps.setString(7, m.getIdDiagnostico());
            ps.setString(8, m.getTx());
            ps.setString(9, m.getComplemento());
            ps.setString(10, Utilidades.imagenEncode64(m.getImagenPath()));

            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @Override
    public String toString() {
        return "Metricas{" + "id=" + id + ", idConsulta=" + idConsulta + ", fecha=" + fecha + '}';
    }
}
