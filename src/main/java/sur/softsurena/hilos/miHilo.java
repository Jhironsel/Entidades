package sur.softsurena.hilos;

public class miHilo extends Thread {

    private boolean continuar = true;
    
    @Override
    public void run() {

        while (continuar) {
            
        }

    }

    public void detenElHilo() {
        continuar = false;
    }
    public void iniciarElHilo() {
        continuar = true;
    }

}
