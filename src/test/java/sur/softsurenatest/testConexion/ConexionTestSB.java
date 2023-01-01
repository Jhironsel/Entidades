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
import sur.softsurena.entidades.Categorias;
import static sur.softsurena.entidades.Categorias.agregarCategoria;
import static sur.softsurena.entidades.Categorias.borrarCategoria;
import sur.softsurena.entidades.Productos;
import static sur.softsurena.entidades.Productos.agregarProducto;
import static sur.softsurena.entidades.Productos.borrarProductoPorCodigo;
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

        Conexion.getInstance(
                "SYSDBA", 
                clave, 
                "None",
                "/home/jhironsel/docker/firebird4/data/BaseDatosSoftSurena4.FDB", 
                "localhost",
                ":3050");

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
//        Assert.assertEquals(3050, puerto);
    }

    //Metodos Insert
    @Test
    public void agregarProducto2() {

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return;
        }

        Productos p = Productos.builder().
                idCategoria(1).
                codigo("1234").
                descripcion("Producto de prueba").
                pathImagen(chooser.getSelectedFile()).
                nota("Este producto ha sido construido para la prueba JUNIT").
                estado(Boolean.TRUE).
                build();

        Resultados r = agregarProducto(p);

        idProducto = r.getId();

        Assert.assertEquals(
                "Producto agregado correctamente.",
                r.getMensaje());
    }

    @Test
    public void agregarCategoriaTest() {
        Categorias c = Categorias.builder().
                descripcion("Categoria de prueba").
                pathImage(chooser.getSelectedFile()).
                estado(Boolean.TRUE).build();

        Resultados r = agregarCategoria(c);
        idCategoria = r.getId();

        Assert.assertEquals(
                "Categoria agregada con exito.",
                r.getMensaje());
    }

    //Metodos Delete
    @Test
    public void borrarProducto() {
        String resultado = borrarProductoPorCodigo("1234");

        Assert.assertEquals("Producto Borrado Correctamente.", resultado);
    }

    @Test
    public void borrarCategoriaTest() {
        String resultado = borrarCategoria(idCategoria);

        Assert.assertEquals("Categoria Borrado Correctamente.", resultado);
    }

}
