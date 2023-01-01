package sur.softsurena.entidades;

import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jhironsel
 */
public class AnimationClassIT {
    
    public AnimationClassIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    /**
     * Test of jLabelMove method, of class AnimationClass.
     */
    @Test
    public void testJLabelMove() {
        System.out.println("jLabelMove");
        String where = "";
        int start = 0;
        int stop = 0;
        int delay = 0;
        int increment = 0;
        JLabel jLabel = null;
        AnimationClass instance = new AnimationClass();
        instance.jLabelMove(where, start, stop, delay, increment, jLabel);
    }

    /**
     * Test of jPanelMove method, of class AnimationClass.
     */
    @Test
    public void testJPanelMove() {
        System.out.println("jPanelMove");
        String where = "";
        int start = 0;
        int stop = 0;
        int delay = 0;
        int increment = 0;
        JPanel jPanel = null;
        AnimationClass instance = new AnimationClass();
        instance.jPanelMove(where, start, stop, delay, increment, jPanel);
    }
    
}
