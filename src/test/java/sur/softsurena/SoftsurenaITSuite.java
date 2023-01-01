/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package sur.softsurena;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author jhironsel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({sur.softsurena.seguridadPatron.SeguridadPatronITSuite.class, sur.softsurena.jfilechooser.JfilechooserITSuite.class, sur.softsurena.graficas.GraficasITSuite.class, sur.softsurena.slidePanel.SlidePanelITSuite.class, sur.softsurena.entidades.EntidadesITSuite.class, sur.softsurena.enums.EnumsITSuite.class, sur.softsurena.formularios.FormulariosITSuite.class, sur.softsurena.utilidades.UtilidadesITSuite.class, sur.softsurena.conexion.ConexionITSuite.class})
public class SoftsurenaITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
