package sur.softsurena.entidades;

import java.sql.Date;
import java.util.List;
import javax.swing.JComboBox;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.entidades.TiposSangres.getTipoSangre;

@Getter
@SuperBuilder
public abstract class Personas {

    private static String INSERT
            = "SELECT p.V_ID FROM SP_INSERT_PERSONA (?, ?, ?, ?, ?, ?, ?) p;";

    private final int id_persona;
    private final char persona;
    private final String pNombre;
    private final String sNombre;
    private final String apellidos;
    private final char sexo;
    private final Date fecha_nacimiento;
    private final Date fecha_ingreso;
    private final Date fecha_hora_ultima_update;
    private final Boolean estado;
    private final String user_name;
    private final String rol;

    private final Generales generales;
    private final Asegurados asegurado;
    private final List<Direcciones> direccion;
    private final List<ContactosEmail> contactosEmail;
    private final List<ContactosTel> contactosTel;

    /**
     * 
     * @param jcbPersona 
     */
    public static void llenarPersona(JComboBox jcbPersona) {

        jcbPersona.removeAllItems();

        TipoPersona tp = TipoPersona.builder().
                abreviatura('X').persona("Tipo de persona").build();
        jcbPersona.addItem(tp);

        tp = TipoPersona.builder().
                abreviatura('F').persona("FÍSICA").build();
        jcbPersona.addItem(tp);

        tp = TipoPersona.builder().
                abreviatura('J').persona("JURÍDICA").build();
        jcbPersona.addItem(tp);
    }

    /**
     * 
     * @param jcbSexo 
     */
    public static void llenarSexo(JComboBox jcbSexo) {
        jcbSexo.removeAllItems();
        Sexo s = Sexo.builder().abreviatura('X').sexo("Seleccione sexo").build();
        jcbSexo.addItem(s);
        s = Sexo.builder().abreviatura('F').sexo("FEMENINA").build();
        jcbSexo.addItem(s);
        s = Sexo.builder().abreviatura('M').sexo("MASCULINO").build();
        jcbSexo.addItem(s);
    }

    /**
     * 
     * @param jcbEstadoCivil 
     */
    public static void llenarEstadoCivil(JComboBox jcbEstadoCivil) {
        jcbEstadoCivil.removeAllItems();
        EstadoCivil ec = EstadoCivil.builder().
                abreviatura('X').
                estadoCivil("Seleccione Estado Civil").build();
        jcbEstadoCivil.addItem(ec);

        ec = EstadoCivil.builder().
                abreviatura('S').
                estadoCivil("Soltero/a").build();
        jcbEstadoCivil.addItem(ec);

        ec = EstadoCivil.builder().
                abreviatura('C').
                estadoCivil("Casado/a").build();
        jcbEstadoCivil.addItem(ec);

        ec = EstadoCivil.builder().
                abreviatura('D').
                estadoCivil("Divorciado/a").build();
        jcbEstadoCivil.addItem(ec);

        ec = EstadoCivil.builder().
                abreviatura('V').
                estadoCivil("Viudo/a").build();
        jcbEstadoCivil.addItem(ec);

        ec = EstadoCivil.builder().
                abreviatura('U').
                estadoCivil("Unión Libre").build();
        jcbEstadoCivil.addItem(ec);
    }

    /**
     * Metodo que permite llenar los comboBox sobre los tipos de sangre que se
     * utilizan en los sistema de pacientes.
     *
     * @param jcbTipoSangre
     */
    public static void llenarTipoSangre(JComboBox jcbTipoSangre) {

        List<TiposSangres> tiposSangresList = getTipoSangre();
        jcbTipoSangre.removeAllItems();

        tiposSangresList.stream().forEach(tsl -> {
            TiposSangres ts = TiposSangres.builder().
                    id(tsl.getId()).
                    descripcion(tsl.getDescripcion()).build();
            jcbTipoSangre.addItem(ts);

        });
    }

    @Override
    public String toString() {
        StringBuilder nombre = new StringBuilder();
        nombre.append(pNombre).
                append(" ").
                append((sNombre.isBlank() ? "":sNombre)).
                append(" ").
                append(apellidos);
        return nombre.toString();
    }
}
