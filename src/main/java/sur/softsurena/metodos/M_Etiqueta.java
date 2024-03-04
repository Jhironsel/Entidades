package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Etiqueta;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Etiqueta {
    
    public static synchronized List<Etiqueta> getEtiquetasUsuario(String usuario) {
        List<Etiqueta> etiquetaList = new ArrayList<>();
        Etiqueta etiqueta;
        final String sql 
                = "SELECT LLAVE, VALOR "
                + "FROM VS_USUARIOS_TAGS "
                + "WHERE USUARIO STARTING WITH ?";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, usuario.strip().toUpperCase());
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    etiqueta = Etiqueta.builder().
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
