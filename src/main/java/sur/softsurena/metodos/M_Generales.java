package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import lombok.Cleanup;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Generales;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Generales {

    public static String generarCedula() {
        int primerDigito = (int) (Math.random() * 4) + 1;
        int segundoDigito = (int) (Math.random() * 5);
        int tercerDigito = (int) (Math.random() * 10);
        int cuartoDigito = (int) (Math.random() * 10);
        int quintoDigito = (int) (Math.random() * 10);
        int sextoDigito = (int) (Math.random() * 10);
        int septimoDigito = (int) (Math.random() * 10);
        int octavoDigito = (int) (Math.random() * 10);
        int novenoDigito = (int) (Math.random() * 10);

        int[] multiplicadores = {2, 1, 2, 1, 2, 1, 2, 1, 2};

        int suma = 0;

        for (int i = 0; i < multiplicadores.length; i++) {
            int digito = i == 0 ? primerDigito : i == 1 ? segundoDigito : i == 2 ? tercerDigito
                    : i == 3 ? cuartoDigito : i == 4 ? quintoDigito : i == 5 ? sextoDigito
                                            : i == 6 ? septimoDigito : i == 7 ? octavoDigito : novenoDigito;
            int producto = digito * multiplicadores[i];
            suma += producto > 9 ? producto - 9 : producto;
        }

        int ultimoDigito = (10 - (suma % 10)) % 10;

        String cedula = String.format(
                "%d%d%d-0%d%d%d%d%d%d-%d",
                primerDigito,
                segundoDigito,
                tercerDigito,
                cuartoDigito,
                quintoDigito,
                sextoDigito,
                septimoDigito,
                octavoDigito,
                novenoDigito,
                ultimoDigito
        );
        
        if(cedula(cedula)){
            return cedula;
        }else{
            return generarCedula();
        }

    }


    public static boolean cedula(String cedula) {
        // Eliminar guiones y espacios en blanco
        cedula = cedula.replaceAll("[\\s-]+", "");

        final int[] MULTIPLOS = {2, 1, 2, 1, 2, 1, 2, 1, 2};

        // Verificar longitud
        if (cedula.length() != 11) {
            return false;
        }

        // Calcular suma de productos
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.digit(cedula.charAt(i), 10);
            int producto = digito * MULTIPLOS[i];
            suma += producto > 9 ? producto - 9 : producto;
        }

        // Verificar dígito verificador
        int digitoVerificador = Character.digit(cedula.charAt(10), 10);
        int resto = (10 - (suma % 10)) % 10;

        return digitoVerificador == resto;
    }

    /**
     * Metodo que nos permite tener los atributos de una general por medio de id
     * de una persona.
     *
     * @param id
     * @return
     */
    public static Generales getEntidad(Integer id) {
        final String sql = """
                           SELECT 
                                CEDULA, 
                                ID_TIPO_SANGRE, 
                                ESTADO_CIVIL
                           FROM V_GENERALES
                           WHERE ID_PERSONA = ?
                           """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, id);

            @Cleanup
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Generales
                        .builder()
                        .cedula(rs.getString("CEDULA"))
                        .id_tipo_sangre(rs.getInt("ID_TIPO_SANGRE"))
                        .estado_civil(rs.getString("ESTADO_CIVIL").charAt(0))
                        .build();
            }

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    "<MENSAJE>",
                    ex
            );
        }
        return Generales
                .builder()
                .cedula("000-0000000-0")
                .id_tipo_sangre(0)
                .estado_civil('X')
                .build();
    }

    public static Generales getEntidadByCedula(String cedula) {
        final String sql = """
                           SELECT 
                                ID_PERSONA, 
                                ID_TIPO_SANGRE, 
                                ESTADO_CIVIL
                           FROM V_GENERALES
                           WHERE CEDULA STARTING WITH ?;
                           """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, cedula);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Generales
                        .builder()
                        .id_persona(rs.getInt("ID_PERSONA"))
                        .id_tipo_sangre(rs.getInt("ID_TIPO_SANGRE"))
                        .estado_civil(rs.getString("ESTADO_CIVIL").charAt(0))
                        .build();
            }

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    "Error al consultar la vista de V_GENERALES del sistema.",
                    ex
            );
        }
        return Generales
                .builder()
                .id_persona(-1)
                .id_tipo_sangre(0)
                .estado_civil('X')
                .build();
    }

    //--------------------------------------------------------------------------
    /**
     *
     * @param general
     * @return
     */
    public static Resultado agregarEntidad(Generales general) {
        final String sql = """
                           EXECUTE PROCEDURE SP_I_GENERAL (?,?,?,?);
                           """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            ps.setInt(1, general.getId_persona());
            ps.setString(2, general.getCedula());
            ps.setInt(3, general.getId_tipo_sangre());
            ps.setString(4, general.getEstado_civil().toString());

            ps.execute();

            return Resultado
                    .builder()
                    .mensaje(GENERAL_INSERTADA_CORRECTAMENTE_EN_EL_SIS)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_INSERTAR_GENERALES_EN_EL_SISTEMA,
                    ex
            );
        }
        return Resultado
                .builder()
                .mensaje(ERROR_AL_INSERTAR_GENERALES_EN_EL_SISTEMA)
                .icono(JOptionPane.ERROR_MESSAGE)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String ERROR_AL_INSERTAR_GENERALES_EN_EL_SISTEMA
            = "Error al insertar generales en el sistema.";
    public static final String GENERAL_INSERTADA_CORRECTAMENTE_EN_EL_SIS
            = "General insertada correctamente en el sistema.";

    //--------------------------------------------------------------------------
    /**
     * Metodo que permite la actulizaciones de las generales de una persona en
     * el sistema.
     *
     * @param general
     *
     * @return
     */
    public static Resultado modificarEntidad(Generales general) {
        final String sql = """
                           EXECUTE PROCEDURE SP_U_GENERAL(?,?,?,?);
                           """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, general.getId_persona());
            ps.setString(2, general.getCedula());
            ps.setInt(3, general.getId_tipo_sangre());
            ps.setString(4, general.getEstado_civil().toString());

            ps.execute();

            return Resultado
                    .builder()
                    .mensaje(GENERALES_ACTUALIZADA_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_ACTUALIZAR_LAS__GENERALES_EN_EL_S,
                    ex
            );
        }
        return Resultado
                .builder()
                .mensaje(ERROR_AL_ACTUALIZAR_LAS__GENERALES_EN_EL_S)
                .icono(JOptionPane.ERROR_MESSAGE)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String ERROR_AL_ACTUALIZAR_LAS__GENERALES_EN_EL_S
            = "Error al actualizar las Generales en el sistema.";
    public static final String GENERALES_ACTUALIZADA_CORRECTAMENTE
            = "Generales actualizada correctamente.";

    public static Resultado borrarEntidad(Integer id) {
        final String sql = """
                           EXECUTE PROCEDURE SP_D_GENERAL(?);
                           """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, id);

            ps.execute();

            return Resultado
                    .builder()
                    .mensaje(GENERALES_BORRADA_CORRECTAMENTE_DEL_SISTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_BORRAR_LAS_GENERALES_EN_EL_SISTE,
                    ex
            );
        }
        return Resultado
                .builder()
                .mensaje(ERROR_AL_BORRAR_LAS_GENERALES_EN_EL_SISTE)
                .icono(JOptionPane.ERROR_MESSAGE)
                .estado(Boolean.FALSE)
                .build();
    }
    public static final String ERROR_AL_BORRAR_LAS_GENERALES_EN_EL_SISTE
            = "Error al borrar las generales en el sistema.";
    public static final String GENERALES_BORRADA_CORRECTAMENTE_DEL_SISTE
            = "Generales borrada correctamente del sistema.";

    public static Resultado eliminarEntidad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
