/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package sur.softsurena.graficas;

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
@Suite.SuiteClasses({sur.softsurena.graficas.PCefalicoChicoChicaIT.class, sur.softsurena.graficas.PesoParaEstaturaIT.class, sur.softsurena.graficas.LongitudAlturaParaEdadNino0a5annoIT.class, sur.softsurena.graficas.PesoParaLongitudIT.class, sur.softsurena.graficas.PesoParaEdadChicoChicaIT.class})
public class GraficasITSuite {

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
