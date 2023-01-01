package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class D_guia_vigilancia {

    private static final Logger LOG = Logger.getLogger(D_guia_vigilancia.class.getName());
    
    private final int id;
    private final int id_paciente;
    private final Timestamp fecha;

    public synchronized static String agregarGuiaVigilancia(int idGVD, int idPaciente) {
        final String sql = 
                " UPDATE OR INSERT INTO V_DETALLE_GUIA_VIGIL (ID_GVD,  IDPACIENTE) "
                + "VALUES (?, ?)";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)){
            
            ps.setInt(1, idGVD);
            ps.setInt(2, idPaciente);

            ps.executeUpdate();

            return "Guia de Desarrollo agregada correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar Guia de Vigilancia...";
        }
    }
    
    public synchronized static ResultSet getGuiaDesarrollo(int idPaciente, boolean cero) {
        //Esto debe de unirsele otras tablas que traega como resultado
        //que desarrollo a tenido el niño en el tiempo desde el nacimiento
        final String sql = "SELECT B.ID_GVD, B.EDAD, B.CARACT_DESARR_EVALUAR, "
                + "          COALESCE((SELECT " + (cero ? " a.SI_NO " : " a.FECHA ")
                + "                    FROM V_DETALLE_GUIA_VIGIL A "
                + "                    WHERE A.IDPACIENTE = ? and "
                + "                    A.ID_GVD = B.ID_GVD), "
                + (cero ? "false" : "' '") + " ) as RESULTADO "
                + "FROM V_GUIA_VIGILANCIA_DESARROLLO B "
                + "WHERE b.EDAD " + (cero ? "" : "!") + "= 0";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public String toString() {
        return "D_guia_vigilancia{" + 
                " id=" + id + 
                ", id_paciente=" + id_paciente + 
                ", fecha=" + fecha + 
                '}';
    }
    
    
}
