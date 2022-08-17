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
import sur.softsurena.datos.delete.DeleteMetodos;
import sur.softsurena.datos.insert.InsertMetodos;
import sur.softsurena.entidades.Categorias;
import sur.softsurena.entidades.Producto;
import sur.softsurena.entidades.Resultados;

public class ConexionTestSB {

    private static String clave;
    private static int puerto;
    private static JFileChooser chooser;
    private static FileNameExtensionFilter filter;
    private int idCategoria = -1;
    private int idProducto = -1;

    public ConexionTestSB() {

    }

    @BeforeClass
    public static void setUpClass() {

        clave = JOptionPane.showInputDialog("Ingrese Contraseña: ");

        chooser = new JFileChooser();
        filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);

        puerto = Conexion.getInstance("SYSDBA", clave, "None",
                "/firebird/data/BaseDeDatosSoftSurena.fdb", "localhost",
                ":3050").getPort();

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

    //Metodos Insert
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

        Resultados r = InsertMetodos.agregarProducto(p);

        idProducto = r.getId();

        Assert.assertEquals(
                "Producto agregado correctamente.",
                r.getMensaje());
    }

    @Test
    public void agregarCategoria() {
        Categorias c = Categorias.builder().
                descripcion("Categoria de prueba").
                pathImage(chooser.getSelectedFile()).
                estado(Boolean.TRUE).build();

        Resultados r = InsertMetodos.agregarCategoria(c);
        idCategoria = r.getId();

        Assert.assertEquals(
                "Categoria agregada con exito.",
                r.getMensaje());
    }

    //Metodos Delete
    @Test
    public void borrarProducto() {
        String resultado = DeleteMetodos.borrarProductoPorCodigo("1234");

        Assert.assertEquals("Producto Borrado Correctamente.", resultado);
    }

    @Test
    public void borrarCategoria() {
        String resultado = DeleteMetodos.borrarCategoria(idCategoria);

        Assert.assertEquals("Categoria Borrado Correctamente.", resultado);
    }

}
