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
import sur.softsurena.datos.insert.InsertMetodos;
import sur.softsurena.entidades.Paciente;

public class ConexionTestN2Care {

    private static String clave;
    private static int puerto;

    public ConexionTestN2Care() {

    }

    @BeforeClass
    public static void setUpClass() {
        clave = JOptionPane.showInputDialog("Ingrese Contraseña: ");
        puerto = Conexion.getInstance("None", "SYSDBA", clave).getPort();
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
        Assert.assertEquals(3050, puerto);
    }

    @Test
    public void TipoSangreTest() {

    }

    @Test
    public void agregarPaciente() {
        Paciente p = Paciente.builder().
                idPadre(0).
                idMadre(1).
                cedula("012-0089344-9").
                pNombre("Jhadiel").
                sNombre("Jhoandry").
                apellidos("Diaz Paniagua").
                sexo('m').
                id_Ars(1).
                noNSS("1234").
                id_Tipo_Sangre(3).
                fecha_Nacimiento(
                        new Date(
                                new GregorianCalendar(2017, 06, 23).
                                        getTimeInMillis())).
                estado(Boolean.TRUE).build();

        String resp = InsertMetodos.agregarPaciente(p);

        Assert.assertEquals(
                "Error al insertar un paciente.",
                "Paciente agregado correctamente", 
                resp);
    }

}
