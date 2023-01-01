package sur.softsurenatest.testConexion;

import java.sql.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Asegurados;
import sur.softsurena.entidades.Generales;
import sur.softsurena.entidades.Paciente;
import static sur.softsurena.entidades.Paciente.agregarPaciente;

public class ConexionTestN2Care {

    private static String clave;
    private static boolean estaCerrado;

    public ConexionTestN2Care() {

    }

    @BeforeClass
    public static void setUpClass() {
        clave = JOptionPane.showInputDialog("Ingrese Contraseña: ");
        Conexion.getInstance(
                "SYSDBA", 
                clave, 
                "None", 
                "/home/jhironsel/docker/firebird4/data/BaseDatosSoftSurena4.FDB", 
                "localhost", 
                "3050");
//        try {
//            estaCerrado = Conexion.getCnn().isClosed();
//        } catch (SQLException ex) {
//            Logger.getLogger(ConexionTestN2Care.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void conexionTest() {
//        Assert.assertEquals(false, estaCerrado);
    }

    @Test
    public void TipoSangreTest() {

    }

    @Test
    public void agregarPaciente2() {
        Generales g = Generales.builder().
                cedula("012-0089344-9").
                id_tipo_sangre(3).build();
        Asegurados a = Asegurados.builder().
                id_ars(1).
                no_nss("1234").build();
        Paciente p = Paciente.builder().
                idPadre(0).
                idMadre(1).
                generales(g).
                pNombre("Jhadiel").
                sNombre("Jhoandry").
                apellidos("Diaz Paniagua").
                sexo('m').
                asegurado(a).
                fecha_nacimiento(
                        new Date(
                                new GregorianCalendar(2017, 06, 23).
                                        getTimeInMillis())).
                estado(Boolean.TRUE).build();

        String resp = agregarPaciente(p);

        Assert.assertEquals(
                "Error al insertar un paciente.",
                "Paciente agregado correctamente", 
                resp);
    }

}
