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
    private final int id_persona;
    private final int id_provincia;
    private final int id_municipio;
    private final int id_distrito_municipal;
    private final int id_codigo_postal;
    private final String direccion;
    private final Date fecha;

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
            + "VALUES (?, ?, ?, ?, ?, ?);";

    public static String UPDATE
            = "UPDATE V_DIRECCIONES a \n"
            + "SET \n"
            + "     a.ID_PROVINCIA = ?, \n"
            + "     a.ID_MUNICIPIO = ?, \n"
            + "     a.ID_DISTRITO_MUNICIPAL = ?, \n"
            + "     a.ID_CODIGO_POSTAL = ?, \n"
            + "     a.DIRECCION = ? \n"
            + "WHERE \n"
            + "     a.ID = ?";

    /**
     * Metodo que llena a los comboBox de las provincias del pais.
     *
     * @param jcbProvincias
     */
    public static void llenarProvincias(RSComboBox jcbProvincias) {

        //ResulSet de las provincias
        ResultSet rp = SelectMetodos.getProvincias();

        Provincias p;

        jcbProvincias.removeAllItems();
        try {
            while (rp.next()) {
                p = Provincias.builder().
                        id(rp.getInt("ID")).
                        nombre(rp.getString("NOMBRE")).build();
                jcbProvincias.addItem(p);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    /**
     * Metodo que llena a los comboBox de los municipios del pais.
     *
     * @param id_provincia
     * @param jcbMunicipios
     */
    public static void llenarMunicipios(int id_provincia,
            RSComboBox jcbMunicipios) {

        //ResulSet de Municipio
        ResultSet rm = SelectMetodos.getMunicipio(id_provincia);

        jcbMunicipios.removeAllItems();

        Municipios m;

//        m = Municipios.builder().
//                id(0).
//                nombre("Ingrese Municipio").build();
//        jcbMunicipios.addItem(m);

        try {
            while (rm.next()) {
                m = Municipios.builder().
                        id(rm.getInt("id")).
                        nombre(rm.getString("nombre")).build();
                jcbMunicipios.addItem(m);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    /**
     * Metodo que llena a los comboBox de los distritos municipal del pais.
     *
     * @param id_municipio
     * @param jcbDistritoMunicipal
     */
    public static void llenarDistritoMunicipal(int id_municipio,
            RSComboBox jcbDistritoMunicipal) {

        //rdm = ResulSet Distrito Municipal
        ResultSet rdm = SelectMetodos.getDistritosMunicipales(id_municipio);

        jcbDistritoMunicipal.removeAllItems();

        Distritos_municipales dm;

//        dm = Distritos_municipales.builder().
//                id(0).
//                nombre("Inserte Distritos").build();
//
//        jcbDistritoMunicipal.addItem(dm);

        try {
            while (rdm.next()) {
                dm = Distritos_municipales.builder().
                        id(rdm.getInt("id")).
                        nombre(rdm.getString("nombre")).build();

                jcbDistritoMunicipal.addItem(dm);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

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
}
