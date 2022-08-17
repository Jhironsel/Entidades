package sur.softsurena.entidades;

import RSMaterialComponent.RSComboBox;
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

    public static void llenarProvincias(RSComboBox jcbProvincias) {

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

    public static void llenarMunicipios(int id_provincia,
            RSComboBox jcbMunicipios) {
        ResultSet rm = SelectMetodos.getMunicipio(id_provincia);

        jcbMunicipios.removeAllItems();
        
        Municipios m = Municipios.builder().
                        id(0).
                        nombre("Ingrese Municipio").build();
                jcbMunicipios.addItem(m);;

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

    public static void llenarDistritoMunicipal(int id_municipio,
            RSComboBox jcbDistritoMunicipal) {
        ResultSet rdm = SelectMetodos.getDistritosMunicipales(id_municipio);
        
        jcbDistritoMunicipal.removeAllItems();

        Distritos_municipales dm = Distritos_municipales.builder().
                id(0).
                nombre("Inserte Distrito").build();
        jcbDistritoMunicipal.addItem(dm);;


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
}
