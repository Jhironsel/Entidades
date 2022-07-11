package sur.softsurena.entidades;

//import control.frmMedicamentos;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class JComboExp extends BasicComboBoxUI{

    public static JButton miBoton;

    public JComboExp() {
        
    }
    
    @Override
    protected JButton createArrowButton() {
        miBoton = new JButton("...");
        miBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if(!miBoton.isVisible()){
                    evt.consume();
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!miBoton.isVisible()){
//                    frmMedicamentos.cbProveedores.setEnabled(true);
                }
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(!miBoton.isVisible()){
//                    frmMedicamentos.cbProveedores.setEnabled(false);
                }
            }
        });
        
        return miBoton;
    }    
}
