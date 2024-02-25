package sur.softsurena.graficas;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import static sur.softsurena.metodos.M_Dato_Nacimiento.getPCefalico;
import static sur.softsurena.metodos.M_Paciente.getSexoPaciente;

public class PCefalicoChicoChica {

    private final String sexo;
    private Float SD3Neg;
    private Float SD2Neg;
    private Float SD1Neg;
    private Float SD0;
    private Float SD1;
    private Float SD2;
    private Float SD3;
    private final int tamanoFigura;
    private final int idPaciente;

    public PCefalicoChicoChica(int idPaciente, int tamanoFigura) {
        sexo = getSexoPaciente(idPaciente);
        this.tamanoFigura = tamanoFigura;
        this.idPaciente = idPaciente;
    }

    private XYSeriesCollection createDataset() {
        XYSeriesCollection localXYSeriesCollection = new XYSeriesCollection();
        try {
            BufferedReader localBufferedReader = new BufferedReader(
                    new InputStreamReader(XYSeriesCollection.class.
                            getClassLoader().getResourceAsStream(
                                    "datos/PCefalico.txt")));
            String str = localBufferedReader.readLine();
            XYSeries localXYSeries1 = new XYSeries("SD3neg", true, false);
            XYSeries localXYSeries2 = new XYSeries("SD2neg", true, false);
            XYSeries localXYSeries3 = new XYSeries("SD1neg", true, false);
            XYSeries localXYSeries4 = new XYSeries("SD0", true, false);
            XYSeries localXYSeries5 = new XYSeries("SD1", true, false);
            XYSeries localXYSeries6 = new XYSeries("SD2", true, false);
            XYSeries localXYSeries7 = new XYSeries("SD3", true, false);
            XYSeries localXYSeries8 = new XYSeries("DATOS", true, false);

            for (str = localBufferedReader.readLine();
                    str != null; str = localBufferedReader.readLine()) {
                int f1 = Integer.parseInt(str.substring(0, 4).trim());//Para el Mes
                if (Integer.parseInt(str.substring(8, 11).trim()) == (sexo.equals("m") ? 1 : 2) & f1 <= 36) {//Determina Sexo
                    localXYSeries1.add(f1, Float.parseFloat(str.substring(52, 59).trim()));//SD3Neg
                    localXYSeries2.add(f1, Float.parseFloat(str.substring(63, 69).trim()));//SD2Neg
                    localXYSeries3.add(f1, Float.parseFloat(str.substring(73, 79).trim()));//SD1Neg
                    localXYSeries4.add(f1, Float.parseFloat(str.substring(83, 89).trim()));//SD0
                    localXYSeries5.add(f1, Float.parseFloat(str.substring(93, 99).trim()));//SD1
                    localXYSeries6.add(f1, Float.parseFloat(str.substring(103, 109).trim()));//SD2
                    localXYSeries7.add(f1, Float.parseFloat(str.substring(113, 117).trim()));//SD3
                    if (f1 == 36) {
                        SD3Neg = Float.valueOf(str.substring(52, 59).trim());
                        SD2Neg = Float.valueOf(str.substring(63, 69).trim());
                        SD1Neg = Float.valueOf(str.substring(73, 79).trim());
                        SD0 = Float.valueOf(str.substring(83, 89).trim());
                        SD1 = Float.valueOf(str.substring(93, 99).trim());
                        SD2 = Float.valueOf(str.substring(103, 109).trim());
                        SD3 = Float.valueOf(str.substring(113, 117).trim());
                    }
                }
            }

            ResultSet rs = getPCefalico(idPaciente);
            try {
                while (rs.next()) {
                    localXYSeries8.add(rs.getFloat(3), rs.getFloat(4));
                }
            } catch (SQLException ex) {
                //Instalar Logger
            }

            localXYSeriesCollection.addSeries(localXYSeries1);
            localXYSeriesCollection.addSeries(localXYSeries2);
            localXYSeriesCollection.addSeries(localXYSeries3);
            localXYSeriesCollection.addSeries(localXYSeries4);
            localXYSeriesCollection.addSeries(localXYSeries5);
            localXYSeriesCollection.addSeries(localXYSeries6);
            localXYSeriesCollection.addSeries(localXYSeries7);
            localXYSeriesCollection.addSeries(localXYSeries8);

        } catch (FileNotFoundException e) {
            //Instalar Logger
        } catch (IOException e) {
            //Instalar Logger
        }

        return localXYSeriesCollection;
    }

    public JFreeChart createChart(XYDataset paramXYDataset) {
        JFreeChart localJFreeChart
                = ChartFactory.createXYLineChart("",
                        "Meses", "Perímetro Cefálico (cm)", paramXYDataset,
                        PlotOrientation.VERTICAL, true, true, false);

        TextTitle localTextTitle1 = new TextTitle(
                "Perímetro Cefálico",
                new Font("SansSerif", 1, 14));

        TextTitle localTextTitle2 = new TextTitle(
                "Nacimiento hasta 3 años, Puntuación Z",
                new Font("SansSerif", 0, 11));

        localJFreeChart.setTitle(localTextTitle1);
        localJFreeChart.addSubtitle(localTextTitle2);

        XYPlot localXYPlot = (XYPlot) localJFreeChart.getPlot();

        localXYPlot.setDomainCrosshairVisible(true);
        localXYPlot.setDomainCrosshairLockedOnData(true);
        
        localXYPlot.setRangeCrosshairVisible(true);
        localXYPlot.setRangeCrosshairLockedOnData(true);
        
        localXYPlot.setDomainZeroBaselineVisible(true);
        localXYPlot.setRangeZeroBaselineVisible(true);

        localXYPlot.setDomainPannable(true);
        localXYPlot.setRangePannable(true);

        NumberAxis localNumberAxis1 = (NumberAxis) localXYPlot.getDomainAxis();
        localNumberAxis1.setUpperMargin(0.03D);

        NumberAxis localNumberAxis2 = (NumberAxis) localXYPlot.getRangeAxis();
        localNumberAxis2.setAutoRangeIncludesZero(false);

        Font localFont = new Font("Ubuntu", 1, 20);

        XYTextAnnotation localXYTextAnnotation = new XYTextAnnotation("-3", 36, SD3Neg);
        localXYTextAnnotation.setFont(localFont);
        localXYTextAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        localXYPlot.addAnnotation(localXYTextAnnotation);
        localXYTextAnnotation = new XYTextAnnotation("-2", 36, SD2Neg);
        localXYTextAnnotation.setFont(localFont);
        localXYTextAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        localXYPlot.addAnnotation(localXYTextAnnotation);
        localXYTextAnnotation = new XYTextAnnotation("-1", 36, SD1Neg);
        localXYTextAnnotation.setFont(localFont);
        localXYTextAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        localXYPlot.addAnnotation(localXYTextAnnotation);
        localXYTextAnnotation = new XYTextAnnotation("0", 36, SD0);
        localXYTextAnnotation.setFont(localFont);
        localXYTextAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        localXYPlot.addAnnotation(localXYTextAnnotation);
        localXYTextAnnotation = new XYTextAnnotation("1", 36, SD1);
        localXYTextAnnotation.setFont(localFont);
        localXYTextAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        localXYPlot.addAnnotation(localXYTextAnnotation);
        localXYTextAnnotation = new XYTextAnnotation("2", 36, SD2);
        localXYTextAnnotation.setFont(localFont);
        localXYTextAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        localXYPlot.addAnnotation(localXYTextAnnotation);
        localXYTextAnnotation = new XYTextAnnotation("3", 36, SD3);
        localXYTextAnnotation.setFont(localFont);
        localXYTextAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        localXYPlot.addAnnotation(localXYTextAnnotation);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesPaint(1, Color.ORANGE);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesPaint(3, Color.GREEN);
        renderer.setSeriesShapesVisible(3, false);
        renderer.setSeriesPaint(4, Color.YELLOW);
        renderer.setSeriesShapesVisible(4, false);
        renderer.setSeriesPaint(5, Color.ORANGE);
        renderer.setSeriesShapesVisible(5, false);
        renderer.setSeriesPaint(6, Color.RED);
        renderer.setSeriesShapesVisible(6, false);
        //renderer.setSeriesShape(7, new Rectangle(0, 0, tamanoFigura, tamanoFigura));
        renderer.setSeriesLinesVisible(7, false);

        localXYPlot.setRenderer(renderer);

        if (sexo.equals("m")) {
            System.setProperty("myColor", "0X4286f4");
        } else {
            System.setProperty("myColor", "0XEAB1B1");
        }
        localXYPlot.setBackgroundPaint(Color.getColor("myColor"));

        return localJFreeChart;
    }

    public JPanel createDemoPanel() {
        JFreeChart localJFreeChart = createChart(createDataset());
        localJFreeChart.setBackgroundPaint(Color.WHITE);
        ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
        localChartPanel.setMouseWheelEnabled(true);//Zoom Deshabilitado
        return localChartPanel;
    }
}
