package sur.softsurena.entidades;

import RSMaterialComponent.RSComboBox;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.datos.select.SelectMetodos;

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
     * Consulta utilizada para obtener la direccion de una persona en
     * particular.
     */
    public static String SELECT_BY_ID
            = "SELECT r.ID, r.ID_PERSONA, r.ID_PROVINCIA, r.PROVINCIA, r.ID_MUNICIPIO, "
            + "     r.MUNICIPIO, r.ID_DISTRITO_MUNICIPAL, r.DISTRITO_MUNICIPAL, "
            + "     r.ID_CODIGO_POSTAL, r.CODIGO_POSTAL, r.DIRECCION, r.FECHA "
            + "FROM GET_DIRECCION_BY_ID r "
            + "WHERE r.ID_PERSONA = ?";

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
        ResultSet rcp = SelectMetodos.getCodigoPostal(id_provincia);

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
    
    @Override
    public String toString() {
        return direccion;
    }
}
