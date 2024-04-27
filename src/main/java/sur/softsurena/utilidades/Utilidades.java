package sur.softsurena.utilidades;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.apache.commons.codec.binary.Base64;
import sur.softsurena.metodos.Imagenes;

public class Utilidades {

    public static final Logger LOG = Logger.getLogger(Utilidades.class.getName());

    static {
        try {
            final File file = new File("Logs/Log " + new Date().toString() + ".log");

            FileHandler fh = new FileHandler(
                    file.getPath(),
                    true
            );

            fh.setLevel(Level.SEVERE);
            fh.setLevel(Level.WARNING);

            fh.setFormatter(new SimpleFormatter());

            LOG.addHandler(fh);

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

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
     * Metodo que nos permite seleccionar el contenido de un JTextField. Dicho
     * metodo debe ser declarado despues del metodo init del constructor de la
     * clase que valla a utilizarse.
     *
     * @param txt Es el componente de tipo JTextField que debe enviarse para
     * agregarle el evento focus Adapter.
     * @param inicio indica de donde inicia la seleccion del campo.
     */
    public static void selectTextoAll(JTextField txt, int inicio) {
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
     * TODO llevar esto a un metodo para jasper
     *
     * @param filePath
     * @param print
     */
    public static void extractPrintImage(String filePath, JasperPrint print) {
        try {
            OutputStream ouputStream = new FileOutputStream(filePath);

            JasperPrintManager printManager
                    = JasperPrintManager.getInstance(
                            DefaultJasperReportsContext.getInstance());

            BufferedImage rendered_image = (BufferedImage) printManager.printToImage(print, 0, 1.6f);
            ImageIO.write(rendered_image, "png", ouputStream);

        } catch (IOException x) {
            JOptionPane.showMessageDialog(
                    null,
                    x.getLocalizedMessage(),
                    "",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (JRException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getLocalizedMessage(),
                    "",
                    JOptionPane.ERROR_MESSAGE
            );
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
        try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream("imagenes/" + name);) {
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                //out.flush();
                out.write(buf, 0, len);
            }
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
    public static boolean isNumeric(String cadena) {
        try {
            Integer.valueOf(cadena);
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
    public static boolean isNumericFloat(String cadena) {
        try {
            Float.valueOf(cadena.replace("$", "").replace(" ", ""));
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Utilizado....
     *
     * Para campos que tenga javaDateToSqlDate( stringToDate("08.06.2012",
     * "dd.MM.yyyy") )
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
                ttm.setInitialDelay(oldDelay + 10000);
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
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return "Foto NO Insertada";
    }

    /**
     * Metodo utilizado para decodificar una cadena de string en base64 aun
     * formato de imagen PNG.
     *
     * @param imagen64
     * @param ancho
     * @param alto
     * @return
     */
    public synchronized static ImageIcon imagenDecode64(String imagen64, int ancho, int alto) {
        byte[] data = Base64.decodeBase64(imagen64);

        Imagenes img = new Imagenes();
        if (Objects.isNull(data) || data.length <= 1) {
            return new ImageIcon(img.getIcono("NoImageTransp 96 x 96.png")
                    .getImage()
                    .getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        }

        try {
            return new ImageIcon(new ImageIcon(
                    ImageIO.read(
                            new ByteArrayInputStream(data))).getImage()
                    .getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return new ImageIcon(img.getIcono("NoImageTransp 96 x 96.png")
                    .getImage()
                    .getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        }
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
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
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

    public static Date sqlDateToUtilDate(java.sql.Date sqlDate) {
        java.sql.Date sqlDates = sqlDate;

        java.util.Date utilDate = new java.util.Date(sqlDates.getTime());

        return utilDate;
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
     * Para campos de tipo fecha sql.
     *
     * javaDateToSqlDate( stringToDate("08.06.2012", "dd.MM.yyyy") )
     *
     * @param fecha
     * @return
     */
    public static Date stringToDate(String fecha, String formato) {
        //Posibles formato: "yyyy-MM-dd" "dd-MM-yyyy"
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
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

    /**
     * Este metodo nos permite administrar el ancho de las columnas de las
     * tablas, pudiendo asi tener el alcho de las columnas. Deberia de ejecutar
     * luego de insertar, actualizar o borrar un registro.
     *
     * @param miTabla Es el componente JTable que va a modicarse su ancho
     * automaticamente.
     */
    public static void repararColumnaTable(JTable miTabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellHeaderRenderer() {
        };
        tcr.setHorizontalAlignment(SwingConstants.LEFT);
//        tblPermisosAsignados.getColumnModel().getColumn(0).setCellRenderer(tcr);

        //Se obtiene el modelo de la columna
        TableColumnModel columnModel = miTabla.getColumnModel();

        //Se obtiene el total de las columnas
        for (int column = 0; column < miTabla.getColumnCount(); column++) {

            //Establecemos un valor minimo para el ancho de la columna
            int width = 150; //Min Width

            //Obtenemos el numero de filas de la tabla
            for (int row = 0; row < miTabla.getRowCount(); row++) {

                //Obtenemos el renderizador de la tabla
                TableCellRenderer renderer = miTabla.getCellRenderer(row, column);

                //Creamos un objeto para preparar el renderer
                Component comp = miTabla.prepareRenderer(renderer, row, column);

                //Establecemos el width segun el valor maximo del ancho de la columna
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }

            miTabla.getColumnModel().getColumn(0).setCellRenderer(tcr);

            //Se establece una condicion para no sobrepasar el valor de 300
            //Esto es Opcional
            if (width > 400) {
                width = 400;
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
            //tblTabla.getColumnModel().getColumn(indexColumna[i]).setCellEditor(new Celda_CheckBox());
            tblTabla.getColumnModel().getColumn(indexColumna[i]).setCellRenderer(new Render_CheckBox());
        }

    }

    /**
     * Por el momento no se le está dando uso a este metodo, pero es utilizado
     * para limitar los caracteres de un campos de texto, será util en otros
     * momento.
     *
     * Puede ser util para un JtextField o cualquier otro.
     *
     * @param limite
     * @param txt
     * @return
     */
    public static KeyListener limitarCaracteres(final int limite, final JFormattedTextField txt) {

        KeyListener keyListener = new KeyAdapter() {
            private int suma = 0;

            @Override
            public void keyReleased(KeyEvent e) {
                suma = suma + 1;
                if (suma == limite) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "El limte se caracteres es " + suma + "\n" + txt.getText());

                }
            }
        };

        return keyListener;
    }

    /**
     * Metodo utilizado para centralizar las ventanas del tipo JInternalFrame
     */
    public static void centralizar(javax.swing.JInternalFrame ventana) {
        Dimension d = ventana.getDesktopPane().getSize();
        ventana.setLocation((d.width - ventana.getSize().width) / 2, (d.height - ventana.getSize().height) / 2);
    }

    public static boolean validarCampo(javax.swing.JFormattedTextField campo) {
        try {
            campo.commitEdit();
        } catch (ParseException ex) {
            campo.requestFocusInWindow();
            campo.selectAll();
            return true;
        }
        return false;
    }

    public static void eliminarRegistroTabla(JTable tabla, DefaultTableModel modelo,
            List lista) {
        if (tabla.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar un registro de la tabla",
                    "",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        lista.remove(tabla.getSelectedRow());
        modelo.removeRow(tabla.getSelectedRow());
        tabla.setModel(modelo);
    }

    /**
     * Dado un monto, este metodo devuelve un Array con la lista de cambios del
     * arreglo de coins. 
     * 
     * @param amount Monto a menuduzar. 
     * 
     * @return 
     */
    public static ArrayList<Integer> coinChangeProblem(int amount) {
        // Define an array of coin denominations in descending order
        Integer[] coins = {1, 5, 10, 20, 50, 100, 200, 500, 1000, 2000};

        // Sort the coin denominations in descending order
        Arrays.sort(coins, Comparator.reverseOrder());

        ArrayList<Integer> ans = new ArrayList<>(); // List to store selected coins

        // Iterate through the coin denominations
        for (int i = 0; i < coins.length; i++) {
            // Check if the current coin denomination can be used to reduce the remaining amount
            if (coins[i] <= amount) {
                // Repeatedly subtract the coin denomination from the remaining amount
                while (coins[i] <= amount) {
                    ans.add(coins[i]); // Add the coin to the list of selected coins
                    amount -= coins[i]; // Update the remaining amount
                }
            }
        }
        return ans;
    }
}
