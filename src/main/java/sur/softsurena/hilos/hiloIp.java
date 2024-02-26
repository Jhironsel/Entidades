package sur.softsurena.hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class hiloIp extends Thread implements hiloMetodos {

    private Boolean iniciar = false;

    @Override
    public void run() {
        while (iniciar) {
            
            Process p;
            BufferedReader stdInput;
            try {
                p = Runtime.getRuntime().exec("wget http://ipinfo.io/ip -qO -");

            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(
                        null, 
                        "No esta conectado a ninguna RED",
                        "",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            try {
                String resp = stdInput.readLine();
                if (resp == null) {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Revise su conexion a Internet",
                            "",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                JOptionPane.showMessageDialog(
                        null, 
                        resp,
                        "",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
            detener();
        }
    }

    @Override
    public void detener() {
        iniciar = false;
    }

    @Override
    public void iniciar() {
        iniciar = true;
    }

    @Override
    public void correr() {
        
    }

}
