package sur.softsurena.hilos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.logging.Level;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.formularios.frmPrintFacturaConReporte2;
import sur.softsurena.utilidades.Utilidades;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class hiloImpresionFactura extends Thread {

    //Variable que permite iniciar o continuar el hilo. 
    private boolean continuar = true;
    
    private final boolean preVista;
    
    private final boolean credito;
    
    private String fileReporte;
    
    private final Map parametros;
    
    private final JPanel jPanelImpresion;
    
    private final JProgressBar jprImpresion;

    /**
     * Constructor que permite iniciar las variables del reporte.
     * 
     * @param preVista Variable que permite ver el documento en vista previa.
     * 
     * @param credito Variable que permite crear otra copia del documento.
     * 
     * @param fileReporte Ubicacion del reporte. 
     * 
     * @param parametros Variable que permite recibir los parametros que se 
     * encuentra en el reporte.
     */
    public hiloImpresionFactura(Boolean preVista, Boolean credito,
            String fileReporte, Map parametros, JPanel jPanelImpresion,
            JProgressBar jprImpresion) {
        this.preVista = preVista;
        this.credito = credito;
        this.fileReporte = fileReporte;
        this.parametros = parametros;
        this.jprImpresion = jprImpresion;
        this.jPanelImpresion = jPanelImpresion;
        
        this.jPanelImpresion.setVisible(true);
    }

    /**
     * Es el metodo que inicia el hijo sincronico con la app.
     * TODO Esto es necesario llevarlo a un metodo de jasperReport.
     */
    @Override
    public void run() {
        if (continuar) {
            try {
                jprImpresion.setValue(20);
                jprImpresion.setString("20% Orden tomada");
                JasperReport masterReporte
                        = (JasperReport) JRLoader.loadObjectFromFile(
                                fileReporte);
                
                jprImpresion.setValue(27);
                jprImpresion.setString("27% Documento Cargado");
                JasperPrint jp = JasperFillManager.fillReport(masterReporte, parametros,
                        getCnn());
                
                jprImpresion.setValue(42);
                jprImpresion.setString("42% Documento Creado");
                
                JasperExportManager.exportReportToPdfFile(jp, "reportes/reporteTemp.pdf");
                jprImpresion.setValue(58);
                jprImpresion.setString("58% PDF Creado, Enviando...");         
                
                if (preVista) {
                    jprImpresion.setValue(70);
                    jprImpresion.setString("70% Extrayendo imagen");
                    Utilidades.extractPrintImage("reportes/reporteTemp.png", jp);
                    
                    jprImpresion.setValue(77);
                    jprImpresion.setString("77% Image Creada");
                    frmPrintFacturaConReporte2 miReport = new frmPrintFacturaConReporte2(null, true);
                    
                    jprImpresion.setValue(89);
                    jprImpresion.setString("89% Imagen cargada");
                    terminar();
                    
                    miReport.setCopia(credito);
                    miReport.setLocationRelativeTo(null);
                    miReport.setVisible(true);
                    
                } else {
                    jprImpresion.setValue(73);
                    jprImpresion.setString("73% Generando Documento");
                    
                    FileInputStream in = new FileInputStream("reportes/reporteTemp.pdf");
                    Doc doc = new SimpleDoc(in, DocFlavor.INPUT_STREAM.PDF, null);
                    
                    jprImpresion.setValue(80);
                    jprImpresion.setString("80% Buscando Impres. Princ.");
                    
                    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
                    service.createPrintJob().print(doc, new HashPrintRequestAttributeSet());
                    
                    jprImpresion.setValue(88);
                    jprImpresion.setString("88% Enviando....");
                    
                    terminar();
                }
            } catch (JRException | FileNotFoundException | PrintException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
            detenElHilo();
        }
    }

    /**
     * Metodo que se ejecuta al terminar de crear el reporte.
     */
    public void terminar() {
        jprImpresion.setString("Cargado con Exito!!!");
        jprImpresion.setValue(100);
        jPanelImpresion.setVisible(false);
    }

    /**
     * Permite detener el hilo. 
     */
    public void detenElHilo() {
        continuar = false;
    }

    /**
     * Permite iniciar el hilo. 
     */
    public void iniciarElHilo() {
        continuar = true;
    }
}
