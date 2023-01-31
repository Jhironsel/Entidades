package sur.softsurena.utilidades;

import java.awt.GridBagConstraints;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import sur.softsurena.metodos.Imagenes;

public class ButtonJTabbedPane extends JPanel {

    private JLabel L;
    private JButton B;
    private int size = 10;

    public ButtonJTabbedPane(String title, JTabbedPane jtp) {
        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        L = new JLabel(title + " ");
        B = new JButton();
        B.setPreferredSize(new java.awt.Dimension(10, 10));
        Imagenes i = new Imagenes();
        B.setIcon((Icon) i.getIcono("Cerrar 24 x 25.png"));
        //Listener para cierre de tabs con acceso estatico al `JTabbedPane`
        //el elemento jtp debe ser un objeto statico de la clase que lo implementa. 
        B.addActionListener(e -> jtp.removeTabAt(jtp.indexOfTab(title)));
        add(L, gbc);
        gbc.gridx++;
        gbc.weightx = 0;
        add(B, gbc);
    }
    //https://es.stackoverflow.com/questions/255769/boton-x-en-pesta%C3%B1as-de-un-jtabbedpane
}
