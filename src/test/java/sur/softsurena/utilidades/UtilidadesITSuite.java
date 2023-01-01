package sur.softsurena.utilidades;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import utilidades.AnimationClassIT;
import utilidades.CustomJPanelIT;
import utilidades.CustomTextAreaIT;
import utilidades.DesktopConFondoIT;

/**
 *
 * @author jhironsel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    CustomTextAreaIT.class, 
    AnimationClassIT.class, 
    ButtonJTabbedPaneIT.class, 
    CustomJPanelIT.class, 
    UtilidadesIT.class, 
    ScreenSplashIT.class, 
    ArchivoIT.class, DesktopConFondoIT.class})
public class UtilidadesITSuite {

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
