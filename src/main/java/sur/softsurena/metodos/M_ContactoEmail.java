package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.ContactoEmail;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_ContactoEmail {

    /**
     * Metodo encargado de agregar o modificar los correo en el sistema.
     *
     * @param contacto Objeto que define la estructura basica de un correo para
     * registros en el sistema.
     * @return
     */
    public static synchronized Resultado agregarContactosEmail(ContactoEmail contacto) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                """
                SELECT O_ID
                FROM SP_I_CONTACTO_EMAIL (?,?,?);
                """,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, contacto.getId_persona());
            ps.setString(2, contacto.getEmail());
            ps.setBoolean(3, contacto.getPor_defecto());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Resultado
                            .builder()
                            .id(rs.getInt(1))
                            .mensaje(CORREO_AGREGADO_O_MODIFICADO_CORRECTAMENT)
                            .icono(JOptionPane.INFORMATION_MESSAGE)
                            .estado(Boolean.TRUE)
                            .build();
                }
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_AGREGAR_O_MODIFICAR_CORREO,
                    ex
            );
        }
        return Resultado
                .builder()
                .id(-1)
                .mensaje(ERROR_AL_AGREGAR_O_MODIFICAR_CORREO)
                .icono(JOptionPane.ERROR_MESSAGE)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String ERROR_AL_AGREGAR_O_MODIFICAR_CORREO
            = "Error al agregar o modificar correo.";
    public static final String CORREO_AGREGADO_O_MODIFICADO_CORRECTAMENT
            = "Correo agregado o modificado correctamente.";

    /**
     * Modifica el contacto de correo de una persona.
     *
     * @param contacto
     * @return
     */
    public static Resultado modificarContactosEmail(ContactoEmail contacto) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                """
                EXECUTE PROCEDURE SP_U_CONTACTO_EMAIL (?,?,?,?);
                """,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, contacto.getId());
            ps.setString(2, contacto.getEmail());
            ps.setBoolean(3, contacto.getEstado());
            ps.setBoolean(4, contacto.getPor_defecto());

            ps.execute();

            return Resultado
                    .builder()
                    .mensaje(EL_CONTACTO_DE_CORREO_FUE_ACTUALIZADO)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ex.getMessage(),
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_EJECUTAR_EL___DEL_SISTEMA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_EJECUTAR_EL___DEL_SISTEMA
            = "Error al ejecutar el procedimiento SP_U_CONTACTO_EMAIL del sistema.";

    public static final String EL_CONTACTO_DE_CORREO_FUE_ACTUALIZADO
            = "El contacto de correo fue actualizado.";

    /**
     * Permite obtener un listado de correo de un clienten o persona a consultar
     * por su id.
     *
     * @param id_persona
     * @return
     */
    public synchronized static List<ContactoEmail> getCorreoByID(
            Integer id_persona
    ) {
        final String sql
                = "SELECT ID, EMAIL, FECHA, ESTADO, POR_DEFECTO "
                + "FROM V_CONTACTOS_EMAIL "
                + (Objects.isNull(id_persona) ? "" : "WHERE ID_PERSONA = ? ")
                + "ORDER BY 2, 1 "
                + (Objects.isNull(id_persona) ? " ROWS (20) " : "");

        List<ContactoEmail> contactosEmailList = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            if (!Objects.isNull(id_persona)) {
                ps.setInt(1, id_persona);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contactosEmailList.add(
                        ContactoEmail
                                .builder()
                                .id(rs.getInt("ID"))
                                .email(rs.getString("EMAIL"))
                                .fecha(rs.getDate("FECHA"))
                                .estado(rs.getBoolean("ESTADO"))
                                .por_defecto(rs.getBoolean("POR_DEFECTO"))
                                .build()
                );
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_CONSULTAR_LA_VISTA_DE_V_CONTACTO,
                    ex
            );
        }
        return contactosEmailList;
    }
    public static final String ERROR_AL_CONSULTAR_LA_VISTA_DE_V_CONTACTO
            = "Error al consultar la vista de V_CONTACTOS_EMAIL de las personas.";

    /**
     * Metodo que genera correo aleatorio para cuestiones de pruebas.
     *
     * @return
     */
    public static String generarCorreo() {
        StringBuilder telefonoMovil = new StringBuilder();
        String correoDominio[] = {
            "@hotmail.com",
            "@gmail.com",
            "@outlook.com",
            "@yahoo.com",
            "@TeJodiste.net"
        };

        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);

        telefonoMovil
                .append("correo_prueba_")
                .append(num1)
                .append(num2)
                .append(num3)
                .append(num4)
                .append(correoDominio[(int) (Math.random() * correoDominio.length)]
                );

        return telefonoMovil.toString();
    }

    /**
     * Metodo que permite verificar si un correo es valido o no.
     *
     * @param correo correo que se intenta validar.
     * @return
     */
    public static boolean correo(String correo) {
        Pattern ptr = Pattern.compile(
                "[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-za-z0-9-]+\\.)+[A-za-z]{2,4}");
        return ptr.matcher(correo).matches();
    }

    public static Resultado borrarContactoEmail(int idEmail) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                """
                EXECUTE PROCEDURE SP_D_CONTACTO_EMAIL (?);
                """,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            ps.setInt(1, idEmail);

            ps.execute();

            return Resultado
                    .builder()
                    .mensaje(CONTACTO_BORRADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_BORRAR_EL_CONTACTO_DE_CORREO_DEL,
                    ex
            );
        }

        return Resultado
                .builder()
                .mensaje(ERROR_AL_BORRAR_EL_CONTACTO_DE_CORREO_DEL)
                .icono(JOptionPane.ERROR_MESSAGE)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String ERROR_AL_BORRAR_EL_CONTACTO_DE_CORREO_DEL
            = "Error al borrar el contacto de correo del sistema.";
    public static final String CONTACTO_BORRADO_CORRECTAMENTE
            = "Contacto borrado correctamente.";
}
