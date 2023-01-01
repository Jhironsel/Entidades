/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package sur.softsurena.slidePanel;

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
@Suite.SuiteClasses({sur.softsurena.slidePanel.BookFormIT.class, sur.softsurena.slidePanel.TitlePanelIT.class, sur.softsurena.slidePanel.SlideAnimatorIT.class, sur.softsurena.slidePanel.StateListenerIT.class, sur.softsurena.slidePanel.TestSlidingPanelIT.class, sur.softsurena.slidePanel.SlidingPanelIT.class, sur.softsurena.slidePanel.SlideContainerIT.class, sur.softsurena.slidePanel.SlidePaneFactoryIT.class})
public class SlidePanelITSuite {

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
