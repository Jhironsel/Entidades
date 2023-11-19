package sur.softsurena.hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class hiloRespardar extends Thread implements hiloMetodos {

    private boolean respardar = false;
    private String usuarioMaster, claveMaster, RGBAK, BDR, BD, log;

    private JPasswordField pf;

    @Override
    public void run() {
        
        if (respardar) {
            
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
                        JOptionPane.showMessageDialog(
                                null,
                                "Usuario no cuenta con los permisos necesarios para realizar el backup.",
                                "",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    
                } while (stdInput.ready());

            } catch (IOException ex) {
                //Instalar Logger
            }
            detener();
        }//Para realizar el BackUp de la base de datos... Trabajando en segundo plano
    }


    @Override
    public void iniciar() {
        respardar = true;
    }
    
    @Override
    public void detener() {
        respardar = false;
    }

    @Override
    public void correr() {
        
    }
    

}
