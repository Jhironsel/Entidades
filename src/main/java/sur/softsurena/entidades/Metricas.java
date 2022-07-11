package sur.softsurena.entidades;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.swing.ImageIcon;

public class Metricas {
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

    public Metricas(int id, int idConsulta, Timestamp fecha, BigDecimal pesoKG, 
            BigDecimal estaturaM, BigDecimal escefalo, String enf_detect, 
            String hallazgosPositivo, String idDiagnostico, String tx, 
            String complemento, ImageIcon imagen, File imagePath, String usuario) {
        this.id = id;
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.pesoKG = pesoKG;
        this.estaturaM = estaturaM;
        this.escefalo = escefalo;
        this.enf_detect = enf_detect;
        this.hallazgosPositivo = hallazgosPositivo;
        this.idDiagnostico = idDiagnostico;
        this.tx = tx;
        this.complemento = complemento;
        this.imagen = imagen;
        this.imagenPath = imagePath;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public BigDecimal getPesoKG() {
        return pesoKG;
    }

    public BigDecimal getEstaturaM() {
        return estaturaM;
    }

    public BigDecimal getEscefalo() {
        return escefalo;
    }

    public String getEnf_detect() {
        return enf_detect;
    }

    public String getHallazgosPositivo() {
        return hallazgosPositivo;
    }

    public String getIdDiagnostico() {
        return idDiagnostico;
    }

    public String getTx() {
        return tx;
    }
    
    public String getComplemento() {
        return complemento;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public File getImagenPath() {
        return imagenPath;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Metricas{" + "id=" + id + ", idConsulta=" + idConsulta + ", fecha=" + fecha + '}';
    }
    
    
    
}
