package sur.softsurena.utilidades;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.util.logging.Level;
import static sur.softsurena.utilidades.Utilidades.LOG;

public final class ScreenSplash {

    private final SplashScreen splash;
    public static boolean debuger;
    
    /*Variable tipo array para leer variables del property System solamente*/
    final String[] texto = {
        "Separador de achivos: " + System.getProperty("file.separator"),
        "Ruta de Java Class: " + System.getProperty("java.class.path"),
        "Ruta de Java Home: " + System.getProperty("java.home"),
        "Java Vendedor: " + System.getProperty("java.vendor"),
        "Java Vendedor URL: " + System.getProperty("java.vendor.url"),
        "Version de Java: " + System.getProperty("java.version"),
        "Separador de Linea: " + System.getProperty("line.separator"),
        "Arquitetura del sistema: " + System.getProperty("os.arch"),
        "Nombre del Sistema Operativo: " + System.getProperty("os.name"),
        "Version del OS: " + System.getProperty("os.version"),
        "Separador de ruta: " + System.getProperty("path.separator"),
        "Directorio del usuario: " + System.getProperty("user.dir"),
        "Usuario home actual: " + System.getProperty("user.home"),
        "Nombre de la Cuenta: " + System.getProperty("user.name")};

    public ScreenSplash() {
        splash = SplashScreen.getSplashScreen();
    }

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(() -> {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
//                    try {
//                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    } catch (ClassNotFoundException
//                            | InstantiationException
//                            | IllegalAccessException
//                            | UnsupportedLookAndFeelException ex) {
//                        LOG.log(Level.SEVERE, ex.getMessage(), ex);
//                    }
//                    break;
//                }
//            }
//
//        });
//        
//        for (int i = 0; i < args.length; i++) {
//            if(args[i].equals("--debuger")){
//                try{
//                    debuger = Boolean.valueOf(args[i+1]);
//                }catch(Exception ex){
//                    System.err.println("Error en paso de parametros valor "+args[i+1]);
//                    LOG.log(Level.SEVERE, ex.getMessage(), ex);
//                }                
//            }
//            
//            if(args[i].equals("--v") || args[i].equals("--version")){
//                LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            }
//        }
//        
//        ScreenSplash miSplash = new ScreenSplash();
//        miSplash.animar();        
//    }

    public void animar() {
        if (splash != null) {
            Graphics2D g = splash.createGraphics();
            for (int i = 1; i < texto.length; i++) {
                //se pinta texto del array
                g.setColor(new Color(4, 52, 101));//color de fondo
                g.fillRect(0, 400, 820, 40);//para tapar texto anterior
                g.setColor(Color.white);//color de texto 
                g.setFont(new java.awt.Font("Ubuntu", 0, 24));
                g.drawString(texto[i - 1] + "...", 25, 420);
                g.setColor(Color.green);//color de barra de progeso
                g.fillRect(20, 360, (i * 607 / texto.length), 20);//la barra de progreso
                //se pinta una linea segmentada encima de la barra verde
                float dash1[] = {8.0f};
                BasicStroke dashed = new BasicStroke(9.0f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_MITER, 3.0f, dash1, 6.0f);
                g.setStroke(dashed);
                g.setColor(Color.GREEN);//color de barra de progeso
                g.setColor(new Color(4, 52, 101));
                g.drawLine(20, 370, 590, 370);
                
                splash.update();
                dormir();
            }
            splash.close();
        }
//        frmLogin miLogin = new frmLogin();
//        miLogin.setVisible(true);
//        miLogin.setLocationRelativeTo(null);
    }
    
    public void animar2() {
        if (splash != null) {
            Graphics2D g = splash.createGraphics();
            for (int i = 1; i < texto.length; i++) {
                //se pinta texto del array
                g.setColor(new Color(4, 52, 101));//color de fondo
                g.fillRect(0, 400, 820, 40);//para tapar texto anterior
                g.setColor(Color.white);//color de texto 
                g.setFont(new java.awt.Font("Ubuntu", 0, 24));
                g.drawString(texto[i - 1] + "...", 25, 420);
                g.setColor(Color.green);//color de barra de progeso
                g.fillRect(20, 360, (i * 607 / texto.length), 20);//la barra de progreso
                //se pinta una linea segmentada encima de la barra verde
                float dash1[] = {8.0f};
                BasicStroke dashed = new BasicStroke(9.0f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_MITER, 3.0f, dash1, 6.0f);
                g.setStroke(dashed);
                g.setColor(Color.GREEN);//color de barra de progeso
                g.setColor(new Color(4, 52, 101));
                g.drawLine(20, 370, 590, 370);
                
                splash.update();
                dormir();
            }
            splash.close();
        }
    }

    private void dormir() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            if(debuger){
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}
