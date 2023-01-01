package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Tandas {

    private static final Logger LOG = Logger.getLogger(Tandas.class.getName());

    private final Integer id_tanda;
    private final Date anno_inicial;
    private final Date anno_final;
    private final Time hora_inicial;
    private final Time hora_final;
    private final Boolean lunes;
    private final Boolean martes;
    private final Boolean miercoles;
    private final Boolean jueves;
    private final Boolean viernes;
    private final Boolean sabados;
    private final Boolean domingos;
    private final int cantidad_estudiantes;
    private final int edad_minima;
    private final int edad_maxima;
    private final Boolean con_edad;
    private final Boolean estado;

    public static String agregarTanda(Tandas t) {
        /**
         * Las tandas en el sistema de Ballet permite definir al sistema cuales
         * dias de la semana y horas se han asignado para dar clase de ballet,
         * tambien definen el dia que se inicia la docencia de ballet.
         *
         * Query que permite ingresar un registro a la base de datos.
         */
        final String INSERT_TANDA
                = "INSERT INTO V_TANDAS (ANNO_INICIAL, ANNO_FINAL, HORA_INICIO, "
                + "HORA_FINAL,LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, "
                + "SABADOS, DOMINGOS, CANTIDAD_ESTUDIANTES, EDAD_MINIMA, "
                + "EDAD_MAXIMA, CON_EDAD,ESTADO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT_TANDA)) {
            ps.setDate(1, t.getAnno_inicial());
            ps.setDate(2, t.getAnno_final());
            ps.setTime(3, t.getHora_inicial());
            ps.setTime(4, t.getHora_final());
            ps.setBoolean(5, t.getLunes());
            ps.setBoolean(6, t.getMartes());
            ps.setBoolean(7, t.getMiercoles());
            ps.setBoolean(8, t.getJueves());
            ps.setBoolean(9, t.getViernes());
            ps.setBoolean(10, t.getSabados());
            ps.setBoolean(11, t.getDomingos());
            ps.setInt(12, t.getCantidad_estudiantes());
            ps.setInt(13, t.getEdad_minima());
            ps.setInt(14, t.getEdad_maxima());
            ps.setBoolean(15, t.getCon_edad());
            ps.setBoolean(16, t.getEstado());

            ps.executeUpdate();
            return "Tanda Agregada Correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Existe Problemas al agregar Tanda, Contactar SoftSureña :( ...! \n" + ex.toString();
        }
    }

    /**
     * Consulta que permite conocer las tandas disponibles
     *
     * @param edadMin
     * @param edadMax
     * @return
     */
    public ResultSet getTandas(Integer edadMin, Integer edadMax) {
        final String sql
                = "SELECT t.id, trim( "
                + "          case lunes when 1 then 'Lunes ' else trim('') end || "
                + "          case martes when 1 then 'Martes ' else trim('') end || "
                + "          case miercoles when 1 then 'Miercoles ' else trim('') end || "
                + "          case jueves when 1 then 'Jueves ' else trim('') end ||  "
                + "          case viernes when 1 then 'Viernes ' else trim('') end || "
                + "          case sabados when 1 then 'Sabados ' else trim('') end|| "
                + "          case domingos when 1 then 'Domingos ' else trim('') end) || ' De ' || "
                + "          subString(t.Hora_Inicio FROM 1 for 8) ||' Hasta '|| "
                + "             subString(t.Hora_Final FROM 1 for 8) AS HORARIO "
                + "FROM V_TANDAS t "
                + "WHERE t.edad_minima <= ? and t.edad_maxima >= ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, edadMin);
            ps.setInt(2, edadMax);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public String modificarTanda(Tandas t) {
        /**
         * Campo que permite actualizar todo el contenido de una tanda en el
         * sistema.
         */
        final String UPDATE
                = "update V_TANDAS set "
                + "ANNO_INICIAL = ?, "
                + "ANNO_FINAL = ?, "
                + "HORA_INICIO = ?, "
                + "HORA_FINAL = ?, "
                + "LUNES = ?, "
                + "MARTES = ?, "
                + "MIERCOLES = ?, "
                + "JUEVES = ?, "
                + "VIERNES = ?, "
                + "SABADOS = ?, "
                + "DOMINGOS = ?, "
                + "CANTIDAD_ESTUDIANTES = ?, "
                + "EDAD_MINIMA = ?, "
                + "EDAD_MAXIMA = ?, "
                + "CON_EDAD = ?, "
                + "ESTADO = ? "
                + "where (ID_TANDA = ?) ";

        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {
            ps.setDate(1, t.getAnno_inicial());
            ps.setDate(2, t.getAnno_final());
            ps.setTime(3, t.getHora_inicial());
            ps.setTime(4, t.getHora_final());
            ps.setBoolean(5, t.getLunes());
            ps.setBoolean(6, t.getMartes());
            ps.setBoolean(7, t.getMiercoles());
            ps.setBoolean(8, t.getJueves());
            ps.setBoolean(9, t.getViernes());
            ps.setBoolean(10, t.getSabados());
            ps.setBoolean(11, t.getDomingos());
            ps.setInt(12, t.getCantidad_estudiantes());
            ps.setInt(13, t.getEdad_minima());
            ps.setInt(14, t.getEdad_maxima());
            ps.setBoolean(15, t.getCon_edad());
            ps.setBoolean(16, t.getEstado());
            ps.setInt(17, t.getId_tanda());

            ps.executeUpdate();
            return "Tanda Modificado Correctamente...!!!";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Tanda no pudo ser Modificado, Conctate SoftSureña...!!!" + ex.getMessage();
        }
    }
    public ResultSet getTandas(Integer id_Tanda) {
        final String sql = "SELECT t.cantidad_estudiantes, t.edad_minima, "
                + "t.edad_maxima, t.con_edad "
                + "FROM V_Tandas t "
                + "WHERE t.id_tanda = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, id_Tanda);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    public ResultSet getHorario() {
        final String sql = "SELECT * FROM V_TANDAS order by 1";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public String toString() {
        return "Tandas{"
                + "id_tanda=" + id_tanda
                + ", anno_inicial=" + anno_inicial
                + ", anno_final=" + anno_final
                + ", hora_inicial=" + hora_inicial
                + ", hora_final=" + hora_final
                + ", lunes=" + lunes
                + ", martes=" + martes
                + ", miercoles=" + miercoles
                + ", jueves=" + jueves
                + ", viernes=" + viernes
                + ", sabados=" + sabados
                + ", domingos=" + domingos
                + ", cantidad_estudiantes=" + cantidad_estudiantes
                + ", edad_minima=" + edad_minima
                + ", edad_maxima=" + edad_maxima
                + ", con_edad=" + con_edad
                + '}';
    }

}
