package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Consultas_aprobadas {

    private static final Logger LOG = Logger.getLogger(Consultas_aprobadas.class.getName());
    
    private final int id;
    private final String codAutorizacion;
    private final BigDecimal costo;
    private final BigDecimal descuento;
    private final String usuario;
    private final BigDecimal totalCosto;

    private static final String INSERT
            = "INSERT INTO V_CONSULTAS_APROBADAS (ID, COD_AUTORIZACION, COSTO, DESCUENTO) "
            + "VALUES (?, ?, ?, ?);";
    
    public synchronized static String agregarConsultaVerificada(Consultas_aprobadas ca) {
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
            ps.setInt(1, ca.getId());
            ps.setString(2, ca.getCodAutorizacion());
            ps.setBigDecimal(3, ca.getCosto());
            ps.setBigDecimal(4, ca.getDescuento());

            ps.executeUpdate();
            return "Consulta Aprobada correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar registro";
        }
    }
    
    @Override
    public String toString() {
        return "ConsultasAprobadas{"
                + "id=" + id
                + ", codAutorizacion=" + codAutorizacion
                + ", costo=" + costo
                + ", descuento=" + descuento
                + ", usuario=" + usuario
                + ", totalCosto=" + totalCosto + '}';
    }

}
