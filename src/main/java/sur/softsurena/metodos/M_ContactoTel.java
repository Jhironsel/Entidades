package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.ContactoTel;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_ContactoTel {

    /**
     * Metodo para agregar numeros telefonicos de las personas del sistema.
     *
     * @param contacto
     * @return
     */
    public static Resultado agregarContactosTel(ContactoTel contacto) {
        final String sql
                = "SELECT O_ID FROM SP_I_CONTACTO_TEL(?,?,?,?)";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, contacto.getId_persona());
            ps.setString(2, contacto.getTelefono());
            ps.setString(3, contacto.getTipo());
            ps.setBoolean(4, contacto.getPor_defecto());

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Resultado
                    .builder()
                    .id(rs.getInt("O_ID"))
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .mensaje(CONTACTO_TELEFONICO_AGREGADO_CORRECTAMENT)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_EJECUTAR_EL_SP_I_CONTACTO_TEL_EN
                            .concat(" Id Persona: ")
                            .concat(contacto.getId_persona().toString())
                            .concat("\n")
                            .concat(sql),
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .mensaje(ERROR_AL_EJECUTAR_EL_SP_I_CONTACTO_TEL_EN)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_EJECUTAR_EL_SP_I_CONTACTO_TEL_EN
            = "Error al ejecutar el SP_I_CONTACTO_TEL en el sistema.";
    public static final String CONTACTO_TELEFONICO_AGREGADO_CORRECTAMENT
            = "Contacto telefonico agregado correctamente.";

    /**
     * Metodo para agregar numeros telefonicos de las personas del sistema.
     *
     * @param contacto
     * @return
     */
    public static Resultado modificarContactoTel(ContactoTel contacto) {
        final String sql
                = """
                  EXECUTE PROCEDURE SP_U_CONTACTO_TEL(
                  ?, --ID 
                  ?, --TELEFONO
                  ?, --TIPO
                  ?, --ESTADO
                  ? --POR_DEFECTO
                  );
                  """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, contacto.getId());
            ps.setString(2, contacto.getTelefono());
            ps.setString(3, contacto.getTipo());
            ps.setBoolean(4, contacto.getEstado());
            ps.setBoolean(5, contacto.getPor_defecto());
            ps.executeUpdate();
            return Resultado
                    .builder()
                    .mensaje(CONTACTO_TELEFONICO_ACTUALIZADO_CORRECTAM)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_EJECUTAR_EL_SP_U_CONTACTO_TEL_DE,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_EJECUTAR_EL_SP_U_CONTACTO_TEL_DE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_EJECUTAR_EL_SP_U_CONTACTO_TEL_DE
            = "Error al ejecutar el SP_U_CONTACTO_TEL del sistema.";
    public static final String CONTACTO_TELEFONICO_ACTUALIZADO_CORRECTAM
            = "Contacto telefonico actualizado correctamente.";

    public static Resultado eliminarContactoTel(int id) {
        final String sql
                = """
                EXECUTE PROCEDURE SP_D_CONTACTO_TEL (?);
                """;
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return Resultado
                    .builder()
                    .mensaje(CONTACTO_TELEFONICO_ELIMINADO_CORRECTAMEN)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_ELIMINAR_CONTACTO_TELEFONICO_EN_,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_ELIMINAR_CONTACTO_TELEFONICO_EN_)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String CONTACTO_TELEFONICO_ELIMINADO_CORRECTAMEN
            = "Contacto telefonico eliminado correctamente.";
    public static final String ERROR_AL_ELIMINAR_CONTACTO_TELEFONICO_EN_
            = "Error al eliminar contacto telefonico en el SP_D_CONTACTO_TEL del sistema.";

    /**
     * Metodo utilizado para obtener los numeros telefonicos de un contacto en
     * particular por su identificador.
     *
     * @param id_persona
     * @return
     */
    public synchronized static List<ContactoTel> getTelefonoByID(Integer id_persona) {
        final String sql
                = "SELECT ID, TELEFONO, FECHA, TIPO, ESTADO, POR_DEFECTO "
                + "FROM V_CONTACTOS_TEL "
                + (Objects.isNull(id_persona) ? "ROWS (10) " : "WHERE ID_PERSONA = ?;");
        List<ContactoTel> contactosTelList = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            if (!Objects.isNull(id_persona)) {
                ps.setInt(1, id_persona);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contactosTelList.add(
                        ContactoTel
                                .builder()
                                .id(rs.getInt("ID"))
                                .telefono(rs.getString("TELEFONO"))
                                .tipo(rs.getString("TIPO"))
                                .fecha(rs.getDate("FECHA"))
                                .estado(rs.getBoolean("ESTADO"))
                                .por_defecto(rs.getBoolean("POR_DEFECTO"))
                                .build()
                );
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_CONSULTAR_LA_VISTA_V_CONTACTOS_T,
                    ex
            );
        }
        return contactosTelList;
    }
    public static final String ERROR_AL_CONSULTAR_LA_VISTA_V_CONTACTOS_T
            = "Error al consultar la vista V_CONTACTOS_TEL del sistema.";

    public static String generarTelMovil() {
        StringBuilder telefonoMovil = new StringBuilder();
        String codigoArea[] = {"809", "829", "849"};
        int indexArea = (int) (Math.random() * 3);

        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);
        int num5 = (int) (Math.random() * 10);
        int num6 = (int) (Math.random() * 10);
        int num7 = (int) (Math.random() * 10);

        telefonoMovil.
                append("+1(").
                append(codigoArea[indexArea]).
                append(") ").
                append(num1).
                append(num2).
                append(num3).
                append("-").
                append(num4).
                append(num5).
                append(num6).
                append(num7);
        return telefonoMovil.toString();
    }

    public static boolean telefono(String tel) {
        Pattern pat = Pattern.compile("[+][1][(][8][0|2|4][9][)]\\s\\d{3}-\\d{4}");
        Matcher mat = pat.matcher(tel.trim());
        return mat.matches();
    }
}
