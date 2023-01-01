package sur.softsurena.entidades;

import java.sql.Timestamp;
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
public class AnalisisIT {
    
    public AnalisisIT() {
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
     * Test of builder method, of class Analisis.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Analisis.AnalisisBuilder expResult = null;
        Analisis.AnalisisBuilder result = Analisis.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Analisis.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Analisis instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_doctor method, of class Analisis.
     */
    @Test
    public void testGetId_doctor() {
        System.out.println("getId_doctor");
        Analisis instance = null;
        int expResult = 0;
        int result = instance.getId_doctor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_paciente method, of class Analisis.
     */
    @Test
    public void testGetId_paciente() {
        System.out.println("getId_paciente");
        Analisis instance = null;
        int expResult = 0;
        int result = instance.getId_paciente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_hora_creada method, of class Analisis.
     */
    @Test
    public void testGetFecha_hora_creada() {
        System.out.println("getFecha_hora_creada");
        Analisis instance = null;
        Timestamp expResult = null;
        Timestamp result = instance.getFecha_hora_creada();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_hora_vista method, of class Analisis.
     */
    @Test
    public void testGetFecha_hora_vista() {
        System.out.println("getFecha_hora_vista");
        Analisis instance = null;
        Timestamp expResult = null;
        Timestamp result = instance.getFecha_hora_vista();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_BHCG method, of class Analisis.
     */
    @Test
    public void testGetT_BHCG() {
        System.out.println("getT_BHCG");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_BHCG();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_EMB_ORINA method, of class Analisis.
     */
    @Test
    public void testGetT_EMB_ORINA() {
        System.out.println("getT_EMB_ORINA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_EMB_ORINA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_EMB_SANGRE method, of class Analisis.
     */
    @Test
    public void testGetT_EMB_SANGRE() {
        System.out.println("getT_EMB_SANGRE");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_EMB_SANGRE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ANT_AUSTRALIANO_BBSAG method, of class Analisis.
     */
    @Test
    public void testGetT_ANT_AUSTRALIANO_BBSAG() {
        System.out.println("getT_ANT_AUSTRALIANO_BBSAG");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ANT_AUSTRALIANO_BBSAG();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CLAMIDIA_IGA method, of class Analisis.
     */
    @Test
    public void testGetT_CLAMIDIA_IGA() {
        System.out.println("getT_CLAMIDIA_IGA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CLAMIDIA_IGA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_FTA_ABS method, of class Analisis.
     */
    @Test
    public void testGetT_FTA_ABS() {
        System.out.println("getT_FTA_ABS");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_FTA_ABS();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_HIV method, of class Analisis.
     */
    @Test
    public void testGetT_HIV() {
        System.out.println("getT_HIV");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_HIV();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_VDRL method, of class Analisis.
     */
    @Test
    public void testGetT_VDRL() {
        System.out.println("getT_VDRL");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_VDRL();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ACIDO_URICO method, of class Analisis.
     */
    @Test
    public void testGetT_ACIDO_URICO() {
        System.out.println("getT_ACIDO_URICO");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ACIDO_URICO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ANT_FEBRILES method, of class Analisis.
     */
    @Test
    public void testGetT_ANT_FEBRILES() {
        System.out.println("getT_ANT_FEBRILES");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ANT_FEBRILES();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ASO_LATEX method, of class Analisis.
     */
    @Test
    public void testGetT_ASO_LATEX() {
        System.out.println("getT_ASO_LATEX");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ASO_LATEX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_BILIRRUBINA method, of class Analisis.
     */
    @Test
    public void testGetT_BILIRRUBINA() {
        System.out.println("getT_BILIRRUBINA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_BILIRRUBINA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_COLESTEROL_TOTAL method, of class Analisis.
     */
    @Test
    public void testGetT_COLESTEROL_TOTAL() {
        System.out.println("getT_COLESTEROL_TOTAL");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_COLESTEROL_TOTAL();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_COLESTEROL_HDL method, of class Analisis.
     */
    @Test
    public void testGetT_COLESTEROL_HDL() {
        System.out.println("getT_COLESTEROL_HDL");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_COLESTEROL_HDL();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_COLESTEROL_HDL_LDL method, of class Analisis.
     */
    @Test
    public void testGetT_COLESTEROL_HDL_LDL() {
        System.out.println("getT_COLESTEROL_HDL_LDL");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_COLESTEROL_HDL_LDL();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_COPROLOGICO method, of class Analisis.
     */
    @Test
    public void testGetT_COPROLOGICO() {
        System.out.println("getT_COPROLOGICO");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_COPROLOGICO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CREATININA_SUERO method, of class Analisis.
     */
    @Test
    public void testGetT_CREATININA_SUERO() {
        System.out.println("getT_CREATININA_SUERO");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CREATININA_SUERO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CULTIVO_FARINGE method, of class Analisis.
     */
    @Test
    public void testGetT_CULTIVO_FARINGE() {
        System.out.println("getT_CULTIVO_FARINGE");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CULTIVO_FARINGE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CULTIVO_HECES method, of class Analisis.
     */
    @Test
    public void testGetT_CULTIVO_HECES() {
        System.out.println("getT_CULTIVO_HECES");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CULTIVO_HECES();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CULTIVO_HERIDA_AEROBICO method, of class Analisis.
     */
    @Test
    public void testGetT_CULTIVO_HERIDA_AEROBICO() {
        System.out.println("getT_CULTIVO_HERIDA_AEROBICO");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CULTIVO_HERIDA_AEROBICO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CULTIVO_OIDO method, of class Analisis.
     */
    @Test
    public void testGetT_CULTIVO_OIDO() {
        System.out.println("getT_CULTIVO_OIDO");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CULTIVO_OIDO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CULTIVO_SANGRE method, of class Analisis.
     */
    @Test
    public void testGetT_CULTIVO_SANGRE() {
        System.out.println("getT_CULTIVO_SANGRE");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CULTIVO_SANGRE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CULTIVO_SEMEN method, of class Analisis.
     */
    @Test
    public void testGetT_CULTIVO_SEMEN() {
        System.out.println("getT_CULTIVO_SEMEN");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CULTIVO_SEMEN();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CULTIVO_URETRA method, of class Analisis.
     */
    @Test
    public void testGetT_CULTIVO_URETRA() {
        System.out.println("getT_CULTIVO_URETRA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CULTIVO_URETRA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_CULTIVO_VAGINA method, of class Analisis.
     */
    @Test
    public void testGetT_CULTIVO_VAGINA() {
        System.out.println("getT_CULTIVO_VAGINA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_CULTIVO_VAGINA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ELECTRO_HEMOGLOBINA method, of class Analisis.
     */
    @Test
    public void testGetT_ELECTRO_HEMOGLOBINA() {
        System.out.println("getT_ELECTRO_HEMOGLOBINA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ELECTRO_HEMOGLOBINA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ERITROSEDIMENTACION method, of class Analisis.
     */
    @Test
    public void testGetT_ERITROSEDIMENTACION() {
        System.out.println("getT_ERITROSEDIMENTACION");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ERITROSEDIMENTACION();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ESPERMATOGRAMA method, of class Analisis.
     */
    @Test
    public void testGetT_ESPERMATOGRAMA() {
        System.out.println("getT_ESPERMATOGRAMA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ESPERMATOGRAMA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ESTRADIOL method, of class Analisis.
     */
    @Test
    public void testGetT_ESTRADIOL() {
        System.out.println("getT_ESTRADIOL");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ESTRADIOL();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ESTROGENO_TOTALES method, of class Analisis.
     */
    @Test
    public void testGetT_ESTROGENO_TOTALES() {
        System.out.println("getT_ESTROGENO_TOTALES");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ESTROGENO_TOTALES();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_FACTOR_REUMATOIDE method, of class Analisis.
     */
    @Test
    public void testGetT_FACTOR_REUMATOIDE() {
        System.out.println("getT_FACTOR_REUMATOIDE");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_FACTOR_REUMATOIDE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_FALCEMIA method, of class Analisis.
     */
    @Test
    public void testGetT_FALCEMIA() {
        System.out.println("getT_FALCEMIA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_FALCEMIA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_FOSFATASA_ALCALINA method, of class Analisis.
     */
    @Test
    public void testGetT_FOSFATASA_ALCALINA() {
        System.out.println("getT_FOSFATASA_ALCALINA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_FOSFATASA_ALCALINA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_FSH method, of class Analisis.
     */
    @Test
    public void testGetT_FSH() {
        System.out.println("getT_FSH");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_FSH();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_GLICEMIA method, of class Analisis.
     */
    @Test
    public void testGetT_GLICEMIA() {
        System.out.println("getT_GLICEMIA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_GLICEMIA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_HEMOGLO_GLUCOSILADA method, of class Analisis.
     */
    @Test
    public void testGetT_HEMOGLO_GLUCOSILADA() {
        System.out.println("getT_HEMOGLO_GLUCOSILADA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_HEMOGLO_GLUCOSILADA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_HEMOGRAMA method, of class Analisis.
     */
    @Test
    public void testGetT_HEMOGRAMA() {
        System.out.println("getT_HEMOGRAMA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_HEMOGRAMA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_HEPATITIS_A method, of class Analisis.
     */
    @Test
    public void testGetT_HEPATITIS_A() {
        System.out.println("getT_HEPATITIS_A");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_HEPATITIS_A();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_HEPATITIS_C method, of class Analisis.
     */
    @Test
    public void testGetT_HEPATITIS_C() {
        System.out.println("getT_HEPATITIS_C");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_HEPATITIS_C();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_KOH method, of class Analisis.
     */
    @Test
    public void testGetT_KOH() {
        System.out.println("getT_KOH");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_KOH();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_LH method, of class Analisis.
     */
    @Test
    public void testGetT_LH() {
        System.out.println("getT_LH");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_LH();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_ORINA method, of class Analisis.
     */
    @Test
    public void testGetT_ORINA() {
        System.out.println("getT_ORINA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_ORINA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_PCR method, of class Analisis.
     */
    @Test
    public void testGetT_PCR() {
        System.out.println("getT_PCR");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_PCR();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_PROGESTERONA method, of class Analisis.
     */
    @Test
    public void testGetT_PROGESTERONA() {
        System.out.println("getT_PROGESTERONA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_PROGESTERONA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_PROLACTINA method, of class Analisis.
     */
    @Test
    public void testGetT_PROLACTINA() {
        System.out.println("getT_PROLACTINA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_PROLACTINA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_PROTEINAS_TOTALES method, of class Analisis.
     */
    @Test
    public void testGetT_PROTEINAS_TOTALES() {
        System.out.println("getT_PROTEINAS_TOTALES");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_PROTEINAS_TOTALES();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_PSA_TOTAL method, of class Analisis.
     */
    @Test
    public void testGetT_PSA_TOTAL() {
        System.out.println("getT_PSA_TOTAL");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_PSA_TOTAL();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_PSA_LIBRE method, of class Analisis.
     */
    @Test
    public void testGetT_PSA_LIBRE() {
        System.out.println("getT_PSA_LIBRE");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_PSA_LIBRE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_SANG_OCULT_HECES method, of class Analisis.
     */
    @Test
    public void testGetT_SANG_OCULT_HECES() {
        System.out.println("getT_SANG_OCULT_HECES");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_SANG_OCULT_HECES();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_SGOT_TGO method, of class Analisis.
     */
    @Test
    public void testGetT_SGOT_TGO() {
        System.out.println("getT_SGOT_TGO");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_SGOT_TGO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_SGOT_TGP method, of class Analisis.
     */
    @Test
    public void testGetT_SGOT_TGP() {
        System.out.println("getT_SGOT_TGP");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_SGOT_TGP();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_T3 method, of class Analisis.
     */
    @Test
    public void testGetT_T3() {
        System.out.println("getT_T3");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_T3();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_T4 method, of class Analisis.
     */
    @Test
    public void testGetT_T4() {
        System.out.println("getT_T4");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_T4();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_T4_LIBRE method, of class Analisis.
     */
    @Test
    public void testGetT_T4_LIBRE() {
        System.out.println("getT_T4_LIBRE");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_T4_LIBRE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_COOMBS_DIRECTO method, of class Analisis.
     */
    @Test
    public void testGetT_COOMBS_DIRECTO() {
        System.out.println("getT_COOMBS_DIRECTO");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_COOMBS_DIRECTO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_COOMBS_INDIRECTO method, of class Analisis.
     */
    @Test
    public void testGetT_COOMBS_INDIRECTO() {
        System.out.println("getT_COOMBS_INDIRECTO");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_COOMBS_INDIRECTO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_TESTOSTERONA method, of class Analisis.
     */
    @Test
    public void testGetT_TESTOSTERONA() {
        System.out.println("getT_TESTOSTERONA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_TESTOSTERONA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_TIPIFICACION_SANGUINEA method, of class Analisis.
     */
    @Test
    public void testGetT_TIPIFICACION_SANGUINEA() {
        System.out.println("getT_TIPIFICACION_SANGUINEA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_TIPIFICACION_SANGUINEA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_TOXOPLAS_IGG method, of class Analisis.
     */
    @Test
    public void testGetT_TOXOPLAS_IGG() {
        System.out.println("getT_TOXOPLAS_IGG");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_TOXOPLAS_IGG();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_TOXOPLAS_IGM method, of class Analisis.
     */
    @Test
    public void testGetT_TOXOPLAS_IGM() {
        System.out.println("getT_TOXOPLAS_IGM");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_TOXOPLAS_IGM();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_TRIGLICERIDOS method, of class Analisis.
     */
    @Test
    public void testGetT_TRIGLICERIDOS() {
        System.out.println("getT_TRIGLICERIDOS");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_TRIGLICERIDOS();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_TSH method, of class Analisis.
     */
    @Test
    public void testGetT_TSH() {
        System.out.println("getT_TSH");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_TSH();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_TUBERCULINA method, of class Analisis.
     */
    @Test
    public void testGetT_TUBERCULINA() {
        System.out.println("getT_TUBERCULINA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_TUBERCULINA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getT_UREA method, of class Analisis.
     */
    @Test
    public void testGetT_UREA() {
        System.out.println("getT_UREA");
        Analisis instance = null;
        Boolean expResult = null;
        Boolean result = instance.getT_UREA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOTROS method, of class Analisis.
     */
    @Test
    public void testGetOTROS() {
        System.out.println("getOTROS");
        Analisis instance = null;
        String expResult = "";
        String result = instance.getOTROS();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUSUARIO method, of class Analisis.
     */
    @Test
    public void testGetUSUARIO() {
        System.out.println("getUSUARIO");
        Analisis instance = null;
        String expResult = "";
        String result = instance.getUSUARIO();
        assertEquals(expResult, result);
    }

    /**
     * Test of getROL method, of class Analisis.
     */
    @Test
    public void testGetROL() {
        System.out.println("getROL");
        Analisis instance = null;
        String expResult = "";
        String result = instance.getROL();
        assertEquals(expResult, result);
    }
    
}
