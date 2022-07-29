package sur.softsurenatest.testConexion;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.datos.insert.InsertMetodos;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.entidades.Producto;
import sur.softsurena.utilidades.Utilidades;

public class ConexionTestSB {

    private static String clave;
    private static int puerto;
    private static JFileChooser chooser;
    private static FileNameExtensionFilter filter;

    public ConexionTestSB() {

    }

    @BeforeClass
    public static void setUpClass() {
        clave = JOptionPane.showInputDialog("Ingrese Contraseña: ");
        puerto = Conexion.getInstance("None", "SYSDBA", clave).getPort();
        chooser = new JFileChooser();
        filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
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
    public void agregarProducto() {

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return;
        }

        Producto p = Producto.builder().
                idCategoria(1).
                codigo("1234").
                descripcion("Producto de prueba").
                pathImagen(chooser.getSelectedFile()).
                nota("Este producto ha sido construido para la prueba JUNIT").
                estado(Boolean.TRUE).
                build();

        String resultado = InsertMetodos.agregarProducto(p);

        Assert.assertEquals(
                "Producto agregado correctamente.",
                resultado);
    }

    @Test
    public void agregarCategoria() {
        Categoria c = Categoria.builder().
                descripcion("Categoria de prueba").
                pathImage(chooser.getSelectedFile()).
                estado(Boolean.TRUE).build();
        
        String resultado = InsertMetodos.agregarCategoria(c);
        
        Assert.assertEquals(
                "Categoria agregada con exito.", 
                resultado);
    }

}
