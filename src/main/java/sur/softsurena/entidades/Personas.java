package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.datos.select.SelectMetodos;

@Getter
@SuperBuilder
public abstract class Personas {

    private static final Logger LOG = Logger.getLogger(Personas.class.getName());
    private final int id_persona;//1
    private final char persona;//2 Este campo es para saber si es F Fisica o J Juridica la persona
    private final String pNombre;//3
    private final String sNombre;//4
    private final String apellidos;//5
    private final char sexo;//6
    private final Date fecha_nacimiento;//7
    private final Date fecha_ingreso;//8
    private final Date fecha_hora_ultima_update;//9
    private final Boolean estado;//10
    private final String user_name;//11
    private final String rol;//12
    
    private final Asegurados asegurado;
    private final Direcciones[] direccion;
    private final Generales generales;

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

    public static void llenarSexo(JComboBox jcbSexo) {
        jcbSexo.removeAllItems();
        Sexo s = Sexo.builder().abreviatura('X').sexo("Seleccione sexo").build();
        jcbSexo.addItem(s);
        s = Sexo.builder().abreviatura('F').sexo("FEMENINA").build();
        jcbSexo.addItem(s);
        s = Sexo.builder().abreviatura('M').sexo("MASCULINO").build();
        jcbSexo.addItem(s);
    }

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

    public static void llenarTipoSangre(JComboBox jcbTipoSangre) {
        TipoSangre ts;
        ResultSet rts = SelectMetodos.getTipoSangre();
        jcbTipoSangre.removeAllItems();
        try {
            while (rts.next()) {
                ts = TipoSangre.builder().
                        id(rts.getInt("ID")).
                        descripcion(rts.getString("DESCRIPCION")).build();
                jcbTipoSangre.addItem(ts);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public String toString() {
        return pNombre + (sNombre.isBlank()
                || sNombre.isEmpty()
                || sNombre == null ? "" : " " + sNombre) + " " + apellidos;
    }
}
