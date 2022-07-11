package sur.softsurena.seguridadPatron;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
public class UnlockJFrame extends JFrame {
    private UnlockPanel unlockPanel;
    /**Constructor de clase*/
    public UnlockJFrame(){
        initComponents();
        UnlockJFrame.this.setLocationRelativeTo(null);
    }    
    private void initComponents() {        
        setTitle("Unlock Pattern");
        setResizable(false);
        setSize(new Dimension(246,280));
        setPreferredSize(new Dimension(246,280));
        unlockPanel = new UnlockPanel(this);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(unlockPanel);
        pack();
    }    
    public static void main(String args[]){
        EventQueue.invokeLater(() -> {
            new UnlockJFrame().setVisible(true);
        });
    }    
}