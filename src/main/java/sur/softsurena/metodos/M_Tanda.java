package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Tanda;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Tanda {

    /**
     * Las tandas en el sistema de Ballet permite definir al sistema cuales dias
     * de la semana y horas se han asignado para dar clase de ballet, tambien
     * definen el dia que se inicia la docencia de ballet.
     *
     * Query que permite ingresar un registro a la base de datos.
     *
     * @param tanda 
    * @return
     */
    public static synchronized Resultado agregarTanda(Tanda tanda) {
        final String sql = """
                  SELECT O_ID
                  FROM SP_I_TANDA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);
                  """;
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setDate(1, tanda.getAnno_inicial());
            ps.setDate(2, tanda.getAnno_final());
            ps.setTime(3, tanda.getHora_inicial());
            ps.setTime(4, tanda.getHora_final());
            ps.setBoolean(5, tanda.getLunes());
            ps.setBoolean(6, tanda.getMartes());
            ps.setBoolean(7, tanda.getMiercoles());
            ps.setBoolean(8, tanda.getJueves());
            ps.setBoolean(9, tanda.getViernes());
            ps.setBoolean(10, tanda.getSabados());
            ps.setBoolean(11, tanda.getDomingos());
            ps.setInt(12, tanda.getCantidad_estudiantes());
            ps.setBoolean(13, tanda.getCon_edad());
            ps.setInt(14, tanda.getEdad_minima());
            ps.setInt(15, tanda.getEdad_maxima());
            ps.setBoolean(16, tanda.getEstado());

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Resultado
                    .builder()
                    .id(rs.getInt(1))
                    .mensaje(TANDA__AGREGADA__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    EXISTE__PROBLEMAS_AL_AGREGAR__TANDA__CONTACT,
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(EXISTE__PROBLEMAS_AL_AGREGAR__TANDA__CONTACT)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String EXISTE__PROBLEMAS_AL_AGREGAR__TANDA__CONTACT
            = "Existe Problemas al agregar Tanda, Contactar SoftSureña :( ...! ";
    public static final String TANDA__AGREGADA__CORRECTAMENTE
            = "Tanda Agregada Correctamente";

    
    /**
     * Campo que permite actualizar all el contenido de una tanda en el sistema.
     *
     * @param tanda 
    * @return
     */
    public static synchronized Resultado modificarTanda(Tanda tanda) {
        final String sql
                = """
                  EXECUTE PROCEDURE SP_U_TANDA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);
                  """;
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, tanda.getId_tanda());
            ps.setDate(2, tanda.getAnno_inicial());
            ps.setDate(3, tanda.getAnno_final());
            ps.setTime(4, tanda.getHora_inicial());
            ps.setTime(5, tanda.getHora_final());
            ps.setBoolean(6, tanda.getLunes());
            ps.setBoolean(7, tanda.getMartes());
            ps.setBoolean(8, tanda.getMiercoles());
            ps.setBoolean(9, tanda.getJueves());
            ps.setBoolean(10, tanda.getViernes());
            ps.setBoolean(11, tanda.getSabados());
            ps.setBoolean(12, tanda.getDomingos());
            ps.setInt(13, tanda.getCantidad_estudiantes());
            ps.setBoolean(14, tanda.getCon_edad());
            ps.setInt(15, tanda.getEdad_minima());
            ps.setInt(16, tanda.getEdad_maxima());
            ps.setBoolean(17, tanda.getEstado());

            ps.executeUpdate();
            return Resultado
                    .builder()
                    .mensaje(TANDA__MODIFICADO__CORRECTAMENTE)
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
                    .mensaje(TANDA_NO_PUDO_SER__MODIFICADO__CONCTATE__SOF)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String TANDA_NO_PUDO_SER__MODIFICADO__CONCTATE__SOF 
            = "Tanda no pudo ser Modificado, Conctate SoftSureña...!!!";
    public static final String TANDA__MODIFICADO__CORRECTAMENTE 
            = "Tanda Modificado Correctamente...!!!";
    
    /**
     * Consulta que permite conocer las tandas disponibles
     *
     * @param edadMin
     * @param edadMax
     * @return
     */
    public static synchronized List<Tanda> getTandas(Integer edadMin, 
            Integer edadMax) {
        final String sql
                = """
                    SELECT t.id, trim( 
                            case lunes when 1 then 'Lunes ' else trim('') end || 
                            case martes when 1 then 'Martes ' else trim('') end || 
                            case miercoles when 1 then 'Miercoles ' else trim('') end || 
                            case jueves when 1 then 'Jueves ' else trim('') end || 
                            case viernes when 1 then 'Viernes ' else trim('') end || 
                            case sabados when 1 then 'Sabados ' else trim('') end|| 
                            case domingos when 1 then 'Domingos ' else trim('') end) || ' De ' ||
                            subString(t.Hora_Inicio FROM 1 for 8) ||' Hasta '|| 
                            subString(t.Hora_Final FROM 1 for 8) AS HORARIO
                    FROM V_TANDAS t 
                    WHERE t.edad_minima <= ? and t.edad_maxima >= ?;
                  """;
        List<Tanda> tandaList = new ArrayList<>();
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, edadMin);
            ps.setInt(2, edadMax);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                tandaList.add(
                        Tanda
                                .builder()
                                .id_tanda(rs.getInt("ID")) //ID Tanda
                                .horario(rs.getString("HORARIO")) //HORARIO
                                .build()
                );
            }
            return tandaList;
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    "Error a consultar la vista V_TANDAS del sistema.", 
                    ex
            );
            return tandaList;
        }
    }

    

    /**
     *
     * @param id_Tanda
     * @return
     */
    public static synchronized ResultSet getTandas(Integer id_Tanda) {
        final String sql
                = "SELECT "
                + "     cantidad_estudiantes, "
                + "     edad_minima, "
                + "     edad_maxima, "
                + "     con_edad "
                + "FROM V_Tandas "
                + "WHERE id_tanda = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, id_Tanda);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public static synchronized ResultSet getHorario() {
        final String sql
                = "SELECT * "
                + "FROM V_TANDAS "
                + "ORDER BY 1";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
