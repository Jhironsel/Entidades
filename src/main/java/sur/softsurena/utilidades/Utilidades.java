package sur.softsurena.utilidades;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.apache.commons.codec.binary.Base64;
import sur.softsurena.entidades.Celda_CheckBox;
import sur.softsurena.entidades.Render_CheckBox;
import sur.softsurena.metodos.Imagenes;

public class Utilidades {
//    public static final Logger LOGGER = Logger.getLogger(Utilidades.class.getName());
//    static {
//        try {
//            boolean append = true;
//            FileHandler fh = new FileHandler(System.getProperty("user.dir") +"/TestLog.log", append);
//            //fh.setFormatter(new XMLFormatter());
//            fh.setFormatter(new SimpleFormatter());
//            LOGGER.addHandler(fh);
//        } catch (IOException e) {
//            System.out.print(e.getLocalizedMessage());
//        }
//    }

    /**
     * No se está utilizando...
     *
     * Metodo utilizado para definir las teclas de acceso rapido en el
     * formulario principal... Nota: Aprender un poco mas de esto...
     *
     * @param button
     * @param actionName
     * @param key
     */
    public static void clickOnKey(final AbstractButton button, String actionName,
            int key) {
        button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(key, 0), actionName);
        button.getActionMap().put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.doClick();
            }
        });
    }
    /**
     * Metodo que nos permite seleccionar todo el contenido de un JTextField.
     * Dicho metodo debe ser declarado despues del metodo init del constructor
     * de la clase que valla a utilizarse.
     * 
     * @param txt Es el componente de tipo JTextField que debe enviarse para 
     * agregarle el evento focus Adapter.
     * @param inicio indica de donde inicia la seleccion del campo. 
     */
    public static void selectTextoAll(JTextField txt, int inicio){
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        txt.setSelectionStart(inicio);
                        txt.setSelectionEnd(txt.getText().length());
                    }
                });
            }
        });
    }

    /**
     * No se está utilizando
     *
     * @param filePath
     * @param print
     */
    public static void extractPrintImage(String filePath, JasperPrint print) {
        try {
            OutputStream ouputStream = new FileOutputStream(filePath);

            DefaultJasperReportsContext.getInstance();

            JasperPrintManager printManager
                    = JasperPrintManager.getInstance(
                            DefaultJasperReportsContext.getInstance());

            BufferedImage rendered_image = (BufferedImage) printManager.printToImage(print, 0, 1.6f);
            ImageIO.write(rendered_image, "png", ouputStream);

        } catch (IOException | JRException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    /**
     * Se utiliza, pero debe ser cambiado por bigDecimal....
     *
     * @param longValue
     * @return
     */
    public static double controlDouble(Object longValue) {
        double valueTwo = -99; // whatever to state invalid!
        if (longValue instanceof Number) {
            valueTwo = ((Number) longValue).doubleValue();
        }
        return valueTwo;
    }

    /**
     * Metodo utilizado para copiar un archivo de un lugar a otro.
     *
     * @param source
     * @param name
     */
    public static void copyFileUsingFileChannels(String source, String name) {
        try {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream("imagenes/" + name);

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                //out.flush();
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    /**
     * No se está utilizando...
     *
     * @param cadena
     * @return
     */
    public static boolean isNumerc(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * No se está utilizando...
     *
     * @param cadena
     * @return
     */
    public static boolean isNumercFloat(String cadena) {
        try {
            Float.parseFloat(cadena.replace("$", "").replace(" ", ""));
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Utilizado....
     *
     *
     *
     * @param date
     * @return
     */
    public static java.sql.Date javaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * Metodo utilizado para devolver un String con la fecha que se le ha pasado
     * del tipo java.util.Date, la cual se devolverá con el tipo que se indique
     * en los parametros. Actualizado 17/05/2022.
     *
     * @param fecha Un objeto de tipo Date de la java.util.Date
     * @param tipo Un String indicando el tipo de formato de la fecha que se
     * desea recibir de la función. EJ.: "dd-MM-yyyy", "MM-dd-yyyy",
     * "dd/MM/yyyy" "dd.MM.yyyy", osea cual guste.
     * @return
     */
    public static String formatDate(Date fecha, String tipo) {
        if (fecha == null) {
            fecha = new Date();
        }
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(tipo);
        return formatoDelTexto.format(fecha);
    }

    /**
     *
     * @param Obj
     * @return
     */
    public static double objectToDouble(Object Obj) {
        String Str = Obj.toString();
        String aux = Str.replace("R", "").replace("D", "").replace("$", "").trim();
        return Double.parseDouble(aux);
    }

    /**
     *
     * @param Obj
     * @return
     */
    public static int objectToInt(Object Obj) {
        String Str = Obj.toString();
        return Integer.parseInt(Str);
    }

    /**
     *
     * @param price
     * @return
     */
    public static String priceWithDecimal(double price) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(price);
    }

    /**
     *
     */
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Muestra el toolTip de cualquier componente.
     *
     * @param component
     */
    public static void showTooltip(Component component) {
        final ToolTipManager ttm = ToolTipManager.sharedInstance();
        final int oldDelay = ttm.getInitialDelay();
        ttm.setInitialDelay(0);
        ttm.mouseMoved(new MouseEvent(component, 0, 0, 0,
                0, 0, // X-Y of the mouse for the tool tip
                0, false));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ttm.setInitialDelay(oldDelay);
            }

        });

    }

    /**
     * Convierte una imagen a Base64, esto con el objetivo de almacenar la
     * imagen o archivo a una forma standar.
     *
     * @param file Contiene la ruta del archivo que se va almacenar a la base de
     * datos.
     *
     * @return Devuelve un string que permite almacenar en un campo blob de la
     * base datos.
     */
    public synchronized static String imagenEncode64(File file) {
        try {
            if (file == null) {
                return "";
            }

            FileInputStream imageInFile = new FileInputStream(file);

            byte imageData[] = new byte[(int) file.length()];

            imageInFile.read(imageData);

            // Converting Image byte array into Base64 String
            return Base64.encodeBase64URLSafeString(imageData);

        } catch (FileNotFoundException ex) {
            //Instalar Logger
        } catch (IOException ex) {
            //Instalar Logger
        }

        return "Foto NO Insertada";
    }

    /**
     * Metodo utilizado para decodificar una cadena de string en base64 aun
     * formato de imagen PNG.
     *
     * @param imagen64
     * @return
     */
    public synchronized static ImageIcon imagenDecode64(String imagen64) {
        byte[] data = Base64.decodeBase64(imagen64);

        if (data == null) {
            Imagenes img = new Imagenes();
            return img.getIcono("Sin_imagen 64 x 64.png");
        }

        BufferedImage img = null;

        try {
            img = ImageIO.read(new ByteArrayInputStream(data));
        } catch (IOException ex) {
            //Instalar Logger
        }

        return new ImageIcon(img);

    }

    /**
     *
     * @param obj
     * @return
     */
    public static Date objectToDate(Object obj) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date aux = null;
        try {
            aux = formatoDelTexto.parse(obj.toString());
        } catch (ParseException ex) {
            System.out.print(ex.getLocalizedMessage());
        }
        return aux;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static Date objectToTime(Object obj) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("hh:mm:ss");
        Date aux = null;
        try {
            aux = formatoDelTexto.parse(obj.toString());
        } catch (Exception ex) {
        }
        return aux;
    }

    public static char Persona(int index) {
        if (index == 1) {
            return 'F';
        }
        if (index == 2) {
            return 'J';
        }
        return 'X';
    }

    /**
     *
     * @param cadena
     * @return
     */
    public static String eliminarComas(String cadena) {
        String cadena1 = cadena.replace(",", "");

        try {
            return cadena1;
        } catch (NumberFormatException nfe) {
            return "0";
        }
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static Date stringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date aux = null;
        try {
            aux = formatoDelTexto.parse(fecha);
        } catch (Exception ex) {
        }
        return aux;
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static Date stringToDate2(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date aux = null;
        try {
            aux = formatoDelTexto.parse(fecha);
        } catch (Exception ex) {
        }
        return aux;
    }

    /**
     *
     * @param numero
     * @param digitos
     * @return
     */
    public static float Redondear(double numero, int digitos) {
        float cifras = (float) Math.pow(10, digitos);

        return (float) (Math.rint(numero * cifras) / cifras);
    }

    /**
     *
     * @param numero
     * @param digitos
     * @return
     */
    public static String Redondear2(String numero, int digitos) {
        float cifras = (float) Math.pow(10, digitos);
        float Numero = Float.parseFloat(numero.replace("$", "").replace(",", ""));
        return "" + (Math.rint(Numero * cifras) / cifras);
    }

    /**
     *
     * @param price
     * @return
     */
    public static String priceWithDecimal(Double price) {
        DecimalFormat formatter = new DecimalFormat("#.00");
        return formatter.format(price);
    }

    public static String priceWithoutDecimal(Double price) {
        DecimalFormat formatter = new DecimalFormat("#.00");
        return formatter.format(price);
    }

    public static String priceToString(Float price) {
        String toShow = priceWithoutDecimal((double) price);
        if (toShow.indexOf(".") > 0) {
            return priceWithDecimal((double) price);
        } else {
            return priceWithoutDecimal((double) price);
        }
    }

    public static void repararColumnaTable(JTable table) {
        //Se obtiene el modelo de la columna
        TableColumnModel columnModel = table.getColumnModel();

        //Se obtiene el total de las columnas
        for (int column = 0; column < table.getColumnCount(); column++) {

            //Establecemos un valor minimo para el ancho de la columna
            int width = 150; //Min Width

            //Obtenemos el numero de filas de la tabla
            for (int row = 0; row < table.getRowCount(); row++) {

                //Obtenemos el renderizador de la tabla
                TableCellRenderer renderer = table.getCellRenderer(row, column);

                //Creamos un objeto para preparar el renderer
                Component comp = table.prepareRenderer(renderer, row, column);

                //Establecemos el width segun el valor maximo del ancho de la columna
                width = Math.max(comp.getPreferredSize().width + 1, width);

            }

            //Se establece una condicion para no sobrepasar el valor de 300
            //Esto es Opcional
            if (width > 300) {
                width = 300;
            }

            //Se establece el ancho de la columna
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    /**
     * Metodo utilizado para convertir las columnas de una Jtable con un
     * checkBox dentro.
     *
     * @param tblTabla Tabla que contiene columnas que necesitan tener un
     * checkBox dentro.
     *
     * @param indexColumna El indice de la columna que necesita tener un
     * checkBox dentro.
     */
    public static void columnasCheckBox(JTable tblTabla, int[] indexColumna) {
        for (int i = 0; i < indexColumna.length; i++) {
            tblTabla.getColumnModel().getColumn(indexColumna[i]).setCellEditor(new Celda_CheckBox());
            tblTabla.getColumnModel().getColumn(indexColumna[i]).setCellRenderer(new Render_CheckBox());
        }

    }

}
