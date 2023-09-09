package sur.softsurena.hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import static sur.softsurena.entidades.BaseDeDatos.pathBaseDeDatos;

public class hiloRestaurar extends Thread implements hiloMetodos {

    private boolean retaurar = true;
    private String RGBAK, BDR, log, usuarioMaster, BD, claveMaster;


    @Override
    public void run() {
        detener();
        correr();
        iniciar();
        if (retaurar) {
            
            Process p;
            BufferedReader stdInput;
            try {
                p = Runtime.getRuntime().exec(RGBAK
                        + " -c -v -USER " + usuarioMaster + " -PASSWORD "
                        + claveMaster + " "
                        + BDR + " "
                        + BD);

                stdInput = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));

                String linea;

                do {
                    linea = stdInput.readLine();
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        //Instalar Logger
                    }

                } while (stdInput.ready());

                if (linea == null || linea.equals("null")) {
                    JOptionPane.showMessageDialog(null,
                            "Usuario no valido o no puede realizarse el "
                            + "backup...",
                            "Validacion no completada",
                            JOptionPane.ERROR_MESSAGE);
                    return;

                }
            } catch (IOException ex) {
                //Instalar Logger
                return;
            }

            JOptionPane.showMessageDialog(null, "Base de Datos restaurada",
                    "sistema de restauracion",
                    JOptionPane.INFORMATION_MESSAGE);

            detener();
        }//Para restaurar una base de datos en segundo plano....
    }

    @Override
    public void detener() {
        retaurar = false;
    }

    @Override
    public void iniciar() {
        retaurar = true;
    }

    @Override
    public void correr() {
        
        BD = pathBaseDeDatos();//Base de Datos actual.

        BDR = System.getProperty("user.dir") + 
                "/Data/respaldo/SistemaDeBebida.FBK";//Base de Datos Guardada.
        //Archivo Log
        log = System.getProperty("user.dir") + "/Data/respaldo/Respaldo.Log";

        RGBAK = System.getProperty("user.dir") + 
                "/respaldo/gbak";
        
        usuarioMaster = JOptionPane.showInputDialog(null,
                "Inserte el nombre de Usuario: ", "Usuario...",
                JOptionPane.INFORMATION_MESSAGE);
        
        if (usuarioMaster.equals("null")) {
            return;
        }
        JPasswordField pf = new JPasswordField();        

        //TODO Analizar este proceso.
        claveMaster = "" + JOptionPane.showConfirmDialog(
                null, 
                pf,
                "Inserte la clave de Usuario: ", 
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );
        
        pf.requestFocusInWindow();

        if (claveMaster.isEmpty()) {
            return;
        }

        Process p;
        BufferedReader stdInput;
//            Calendar miCan = Calendar.getInstance();
        try {//RGBAK.getAbsolutePath()+                        
            String comando = "gbak -b -nodbtriggers -l -nt -v -user '" + usuarioMaster
                    + "' -password '" + new String(pf.getPassword())
                    + "' -y " + log + " "
                    + BD + " "
                    + BDR;
//                        + BDR.getAbsolutePath().replace(".FBK", "")
//                        + miCan.get(Calendar.DATE) + "_"
//                        + (miCan.get(Calendar.MONTH) + 1) + "_"
//                        + miCan.get(Calendar.YEAR) + ".FBK";
            
            p = Runtime.getRuntime().exec(comando);
            stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String linea;
            do {
                linea = stdInput.readLine();
                if (linea != null) {
                    JOptionPane.showMessageDialog(null,
                            "Usuario no valido o no puede realizarse el "
                            + "backup...",
                            "Validacion no completada",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            } while (stdInput.ready());
        } catch (IOException ex) {
            //Instalar Logger
        }
    }
}
