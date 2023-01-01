package sur.softsurena.entidades;

import RSMaterialComponent.RSComboBox;
import java.sql.Date;
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
import static sur.softsurena.entidades.Codigo_Postal.getCodigoPostal;

@SuperBuilder
@Getter
public class Direcciones {

    private static final Logger LOG = Logger.getLogger(Direcciones.class.getName());
    private final int id;
    private final int id_persona;
    private final char accion;
    private final Provincias provincia;
    private final Municipios municipio;
    private final Distritos_municipales distrito_municipal;
    private final Codigo_Postal codigo_postal;
    private final String direccion;
    private final Date fecha;

    public final static String[] TITULOS_DIRECCION = {"Provincia", "Municipio",
        "Distrito M.", "Calle y No. Casa", "Fecha"};

    /**
     * Permite agregar la direccion de una persona al sistema.
     */
    public static String INSERT
            = "INSERT INTO V_DIRECCIONES (ID_PERSONA, ID_PROVINCIA, ID_MUNICIPIO, "
            + "     ID_DISTRITO_MUNICIPAL, ID_CODIGO_POSTAL, DIRECCION) "
            + "VALUES (?, ?, ?, ?, 0, ?);";

    public static String UPDATE
            = "UPDATE V_DIRECCIONES a  "
            + "SET  "
            + "     a.ID_PROVINCIA = ?,  "
            + "     a.ID_MUNICIPIO = ?,  "
            + "     a.ID_DISTRITO_MUNICIPAL = ?,  "
            + "     a.DIRECCION = ?  "
            + "WHERE "
            + "     a.ID = ?";

    /**
     * Metodo que llena a los comboBox de los codigos postales del pais.
     *
     * @param id_provincia
     * @param jcbCodigoPostal
     */
    public static void llenarCodigoPostal(int id_provincia,
            RSComboBox jcbCodigoPostal) {

        //rdm = ResulSet Distrito Municipal
        ResultSet rcp = getCodigoPostal(id_provincia);

        jcbCodigoPostal.removeAllItems();

        Codigo_Postal cp;

        try {
            while (rcp.next()) {
                cp = Codigo_Postal.builder().
                        id(rcp.getInt("ID")).
                        localidad(rcp.getString("LOCALIDAD")).
                        codigo_postal(rcp.getInt("CODIGO_POSTAL")).build();

                jcbCodigoPostal.addItem(cp);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static boolean agregarDirecciones(int id, List<Direcciones> direcciones) {
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
            for (Direcciones d : direcciones) {
                ps.setInt(1, id);
                ps.setInt(2, d.getProvincia().getId());
                ps.setInt(3, d.getMunicipio().getId());
                ps.setInt(4, d.getDistrito_municipal().getId());
                ps.setString(5, d.getDireccion());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    public static boolean modificarDirecciones(int id, List<Direcciones> direcciones) {

        try (PreparedStatement ps = getCnn().prepareStatement(Direcciones.UPDATE)) {

            getCnn().setAutoCommit(false);

            for (Direcciones d : direcciones) {
                ps.setInt(1, d.getProvincia().getId());
                ps.setInt(2, d.getMunicipio().getId());
                ps.setInt(3, d.getDistrito_municipal().getId());
                ps.setString(4, d.getDireccion());
                ps.setInt(5, id);
                ps.addBatch();
            }

            ps.executeBatch();

            getCnn().commit();

            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            try {
                getCnn().rollback();
            } catch (SQLException ex1) {
                LOG.log(Level.SEVERE, ex1.getMessage(), ex1);

            }
        }
        return false;
    }

    /**
     * Obtenemos el historia de direcciones del cliente, lo cual permite tener
     * mejor control de la direcciones de los clientes.
     *
     * @param id_persona identificador del cliente del sistema, la cual ayuda
     * obtener los registros de un usuario en expecifico.
     *
     * Consulta utilizada para obtener la direccion de una persona en particular.
     * 
     * @return Retorna un conjunto de datos del tipo resultSet.
     */
    public synchronized static List<Direcciones> getDireccionByID(int id_persona) {
        List<Direcciones> direcciones = new ArrayList<>();

        final String SELECT_BY_ID
                = "SELECT r.ID, r.ID_PERSONA, r.ID_PROVINCIA, r.PROVINCIA, r.ID_MUNICIPIO, "
                + "     r.MUNICIPIO, r.ID_DISTRITO_MUNICIPAL, r.DISTRITO_MUNICIPAL, "
                + "     r.ID_CODIGO_POSTAL, r.CODIGO_POSTAL, r.DIRECCION, r.FECHA "
                + "FROM GET_DIRECCION_BY_ID r "
                + "WHERE r.ID_PERSONA = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id_persona);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {

                    Provincias p = Provincias.builder().
                            id(rs.getInt("ID_PROVINCIA")).
                            nombre(rs.getString("PROVINCIA")).build();

                    Municipios m = Municipios.builder().
                            id(rs.getInt("ID_MUNICIPIO")).
                            nombre(rs.getString("MUNICIPIO")).build();

                    Distritos_municipales d = Distritos_municipales.builder().
                            id(rs.getInt("ID_DISTRITO_MUNICIPAL")).
                            nombre(rs.getString("DISTRITO_MUNICIPAL")).build();

                    Direcciones dd = Direcciones.builder().
                            id(rs.getInt("ID")).
                            provincia(p).
                            municipio(m).
                            distrito_municipal(d).
                            direccion(rs.getString("DIRECCION")).
                            fecha(rs.getDate("FECHA")).build();

                    direcciones.add(dd);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return direcciones;
    }

    @Override
    public String toString() {
        return direccion;
    }
}
