package sur.softsurena.entidades;

import java.sql.SQLException;
import java.util.Date;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.utilidades.Utilidades;

/**
 *
 * @author jhironsel
 */
public class PacienteIT {

    public PacienteIT() {

    }

    @BeforeClass
    public static void setUpClass() {
        final String user = "jhironsel";
        final String clave = "1";
        final String pathBaseDatos = "BaseDeDatos.db";
        final String dominio = "localhost";
        final String puerto = "3050";
        Conexion.getInstance(user, clave, pathBaseDatos, dominio, puerto)
                .verificar();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        Conexion.getCnn().close();
        Conexion.setCnn(null);
    }

    

    /**
     * Test of agregarPaciente method, of class Paciente.
     */
    @Test
    public void testAgregarPaciente() {
        System.out.println("agregarPaciente");

        //Prueba de Insersion de paciente.
        
        Asegurados asegurado = Asegurados.builder().
                id_ars(0).
                no_nss("").build();

        Generales generales = Generales.builder().
                cedula("012-0000000-9").id_tipo_sangre(0).build();

        Resultados result = Paciente.agregarPaciente(
                Paciente
                        .builder()
                        .idPadre(0)
                        .idMadre(0)
                        .asegurado(asegurado)
                        .generales(generales)
                        .pnombre("Ciliosther")
                        .snombre("")
                        .apellidos("Diaz Liriano")
                        .sexo('F')
                        .estado(Boolean.TRUE)
                        .fecha_nacimiento(Utilidades.javaDateToSqlDate(new Date()))
                        .build()
        );

        assertEquals(Paciente.PACIENTE_AGREGADO_CORRECTAMENTE, result.toString());

        //Prueba de Actualizacion de paciente.
        asegurado = Asegurados.builder().
                id_ars(0).
                no_nss("").
                estado(Boolean.FALSE).build();

        generales = Generales.builder().
                cedula("012-0000001-9").id_tipo_sangre(0).build();

        int idPaciente = result.getId(); 
        
        Paciente pUpdate = Paciente.builder().id_persona(idPaciente).
                idPadre(0).idMadre(0).asegurado(asegurado).generales(generales).
                pnombre("Michael").snombre("").apellidos("Orozco").
                sexo('M').
                estado(Boolean.TRUE).
                fecha_nacimiento(Utilidades.javaDateToSqlDate(new Date())).build();

        result = Paciente.modificarPaciente(pUpdate);

        assertEquals(Paciente.PACIENTE_MODIFICADO_CORRECTAMENTE, result.toString());
        
        //Borrar el cliente. 
        result = Paciente.borrarPaciente(idPaciente);
        assertEquals(Paciente.PACIENTE_BORRADO_CORRECTAMENTE, result.toString());
    }
}
