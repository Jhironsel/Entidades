package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Antecedente;
import sur.softsurena.entidades.Asegurado;
import sur.softsurena.entidades.Consulta;
import sur.softsurena.entidades.Generales;
import sur.softsurena.entidades.Paciente;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.metodos.M_Antecedente.ANTECEDENTE_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Antecedente.ANTECEDENTE_MODIFICADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Antecedente.BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Antecedente.agregarAntecedente;
import static sur.softsurena.metodos.M_Antecedente.getAntecedentes;
import static sur.softsurena.metodos.M_Antecedente.modificarAntecedente;
import static sur.softsurena.metodos.M_ContactoTel.generarTelMovil;
import static sur.softsurena.metodos.M_Generales.generarCedula;
import static sur.softsurena.metodos.M_Paciente.agregarPaciente;
import static sur.softsurena.utilidades.Utilidades.javaDateToSqlDate;
import static sur.softsurena.utilidades.Utilidades.stringToDate;

public class M_AntecedentesNGTest {

    public M_AntecedentesNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        Conexion.getInstance(
                "sysdba",
                "1",
                "BaseDeDatos.db",
                "localhost",
                "3050"
        );
        assertTrue("Error al conectarse...", Conexion.verificar().getEstado());
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        Conexion.getCnn().close();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testBorrarAntecedente() {

    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testAgregarAntecedente() {

        Resultado rPaciente = agregarPaciente(Paciente
                        .builder()
                        .idPadre(0)
                        .idMadre(0)
                        .asegurado(Asegurado
                                        .builder()
                                        .id_ars(0)
                                        .no_nss(generarTelMovil())
                                        .build()
                        )
                        .generales(
                                Generales
                                        .builder()
                                        .cedula(generarCedula())
                                        .id_tipo_sangre(0)
                                        .build()
                        )
                        .pnombre("Prueba Sistema")
                        .snombre("Prueba Sistema")
                        .apellidos("Prueba Sistema")
                        .sexo('M')
                        .fecha_nacimiento(
                                javaDateToSqlDate(
                                        stringToDate("08.06.2012", "dd.MM.yyyy")
                                )
                        )
                        .estado(Boolean.TRUE)
                        .build()
        );

        int id_consulta = M_Consulta.agregarConsulta(
                Consulta
                        .builder()
                        .id_persona(rPaciente.getId())
                        .id_control_consulta(0)
                        .turno(0)
                        .build()
        ).getId();

        //Se agrega un registro
        Resultado result = agregarAntecedente(
                id_consulta,
                "Prueba de antecendetes"
        );

        assertEquals(
                result.getMensaje(),
                ANTECEDENTE_AGREGADO_CORRECTAMENTE,
                "No puede ser agregado el registro."
        );

        //Consultado el registro
        List<Antecedente> lista = getAntecedentes(
                result.getId()
        );

        assertNotNull(
                lista,
                "La tabla de antecedentes NO contiene informacion."
        );

        //Se actualiza el registro
        String resultado = modificarAntecedente(
                result.getId(),
                "Actualizado"
        );
        assertEquals(
                resultado,
                ANTECEDENTE_MODIFICADO_CORRECTAMENTE,
                "El registro de antecedente no puede ser actualizado."
        );

        //Se borra el antecedente
        String borrado = M_Antecedente.borrarAntecedente(result.getId());
        assertEquals(
                borrado,
                BORRADO_CORRECTAMENTE,
                "El registro no pudo ser eliminado del sistema."
        );

    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testModificarAntecedente() {

    }

    @Test(
            enabled = true,
            description = "",
            priority = 0
    )
    public void testGetAntecedentes() {
        int idPadre = 0;
        List<Antecedente> result = M_Antecedente.getAntecedentes(idPadre);
        assertTrue(result.isEmpty(), "La tabla de antecedentes contiene informacion.");
    }
}
