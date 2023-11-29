package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Etiquetas {

    private static final Logger LOG = Logger.getLogger(Etiquetas.class.getName());
    private final String propiedad;
    private final String valor;

    @Override
    public String toString() {
        return valor;
    }

    public synchronized static List<Etiquetas> getEtiquetasUsuario(String usuario) {
        List<Etiquetas> etiquetaList = new ArrayList<>();
        Etiquetas etiqueta;
        final String sql = "SELECT LLAVE, VALOR "
                + "FROM VS_USUARIOS_TAGS "
                + "WHERE USUARIO STATING WITH ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, usuario.strip().toUpperCase());

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    etiqueta = Etiquetas.builder().
                            propiedad(rs.getString("LLAVE")).
                            valor(rs.getString("VALOR")).
                            build();
                    etiquetaList.add(etiqueta);
                }
            }
            return etiquetaList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}
