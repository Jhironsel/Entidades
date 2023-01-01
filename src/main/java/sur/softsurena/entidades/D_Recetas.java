package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class D_Recetas {

    private static final Logger LOG = Logger.getLogger(D_Recetas.class.getName());
    
    private final int id_receta;
    private final int linea;
    private final int id_Medicamento;
    private final BigDecimal cantidad;
    private final String detalleDosis;
    
    /**
     * 
     */
    private static final String INSERT 
            ="insert into V_DETALLERECETAS (IDRECETAS, LINEA, "
                    + "ID_MEDICAMENTO, CANTIDAD, D_DOSIS) "
                    + "values (?, ?, ?, ?, ?)";
    
    /**
     * 
     * @param dr 
     */
    public synchronized static void agregarRecetaDetalle(List<D_Recetas> dr) {
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
            dr.stream().forEach(x -> {
                try {
                    ps.setInt(1, x.getId_receta());
                    ps.setInt(2, x.getLinea());
                    ps.setInt(3, x.getId_Medicamento());
                    ps.setBigDecimal(4, x.getCantidad());
                    ps.setString(5, x.getDetalleDosis());
                } catch (SQLException ex) {
                    Logger.getLogger(D_Recetas.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @Override
    public String toString() {
        return "D_Recetas{" + 
                " id_receta=" + id_receta + 
                ", linea=" + linea + 
                ", id_Medicamento=" + id_Medicamento + 
                ", cantidad=" + cantidad + 
                ", detalleDosis=" + detalleDosis + 
                '}';
    }
    
}
