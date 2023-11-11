package sur.softsurena.entidades;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * Esta clase contiene todos los metodos necesario para crear los reportes. 
 */
public class ShapesApp {

    public void fill() throws JRException {
        long start = System.currentTimeMillis();
        JasperFillManager.fillReportToFile("build/reports/ShapesReport.jasper", 
                null, (JRDataSource) null);
        System.err.println("Filling time : " + (System.currentTimeMillis() - start));
    }

    public void print() throws JRException {
        long start = System.currentTimeMillis();
        JasperPrintManager.printReport("build/reports/ShapesReport.jrprint", true);
        System.err.println("Printing time : " + (System.currentTimeMillis() - start));
    }

    public static void pdf(String origen, String destino) {
        long start = System.currentTimeMillis();
        
        try {
            JasperExportManager.exportReportToPdfFile(origen, destino);
        } catch (JRException ex) {
            Logger.getLogger(ShapesApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
    }

    public void rtf() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/ShapesReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".rtf");

        JRRtfExporter exporter = new JRRtfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile));

        exporter.exportReport();

        System.err.println("RTF creation time : " + (System.currentTimeMillis() - start));
    }

    public void xml() throws JRException {
        long start = System.currentTimeMillis();
        JasperExportManager.exportReportToXmlFile("build/reports/ShapesReport.jrprint", false);
        System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
    }

    public void xmlEmbed() throws JRException {
        long start = System.currentTimeMillis();
        JasperExportManager.exportReportToXmlFile("build/reports/ShapesReport.jrprint", true);
        System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
    }

    public void html() throws JRException {
        long start = System.currentTimeMillis();
        JasperExportManager.exportReportToHtmlFile("build/reports/ShapesReport.jrprint");
        System.err.println("HTML creation time : " + (System.currentTimeMillis() - start));
    }

    public void xls() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/ShapesReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xls");

        JRXlsExporter exporter = new JRXlsExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(false);
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
    }

    public void csv() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/ShapesReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".csv");

        JRCsvExporter exporter = new JRCsvExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile));

        exporter.exportReport();

        System.err.println("CSV creation time : " + (System.currentTimeMillis() - start));
    }

    public void odt() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/ShapesReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".odt");

        JROdtExporter exporter = new JROdtExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

        exporter.exportReport();

        System.err.println("ODT creation time : " + (System.currentTimeMillis() - start));
    }

    public void ods() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/ShapesReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".ods");

        JROdsExporter exporter = new JROdsExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

        exporter.exportReport();

        System.err.println("ODS creation time : " + (System.currentTimeMillis() - start));
    }

    public void docx() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/ShapesReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".docx");

        JRDocxExporter exporter = new JRDocxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

        exporter.exportReport();

        System.err.println("DOCX creation time : " + (System.currentTimeMillis() - start));
    }

    public void xlsx() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/ShapesReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xlsx");

        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(false);
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        System.err.println("XLSX creation time : " + (System.currentTimeMillis() - start));
    }

    public void pptx() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/ShapesReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".pptx");

        JRPptxExporter exporter = new JRPptxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

        exporter.exportReport();

        System.err.println("PPTX creation time : " + (System.currentTimeMillis() - start));
    }

}
