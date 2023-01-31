package sur.softsurena.hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class hiloIp extends Thread implements hiloMetodos {

    private boolean iniciar = false;

    @Override
    public void run() {
        while (iniciar) {
            
            Process p;
            BufferedReader stdInput;
            try {
                p = Runtime.getRuntime().exec("wget http://ipinfo.io/ip -qO -");

            } catch (IOException ex) {
                //Instalar Logger
                JOptionPane.showMessageDialog(null, "No esta conectado a ninguna RED");
                return;
            }
            stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            try {
                String resp = stdInput.readLine();
                if (resp == null) {
                    JOptionPane.showMessageDialog(null, "Revise su conexion a Internet");
                    return;
                }
                JOptionPane.showMessageDialog(null, resp);

            } catch (IOException ex) {
                //Instalar Logger
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
