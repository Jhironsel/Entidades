package sur.softsurena.formularios;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class frmPrintFacturaConReporte2 extends javax.swing.JDialog {
    private boolean copia;
    private Point p;

    public boolean getCopia() {
        return copia;
    }

    public void setCopia(boolean copia) {
        this.copia = copia;
    }
    public frmPrintFacturaConReporte2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAceptar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlFactura = new javax.swing.JLabel();
        cbCopia = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Factura del Cliente...");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(360, 432));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(1, 1, 1));
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Impresora.png"))); // NOI18N
        btnAceptar.setMnemonic('a');
        btnAceptar.setText("Imprimir");
        btnAceptar.setToolTipText("se procedera a imprimir la factura en la pantalla...");
        btnAceptar.setBorder(null);
        btnAceptar.setPreferredSize(new java.awt.Dimension(123, 44));
        btnAceptar.setSelected(true);
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAceptarMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAceptarMouseEntered(evt);
            }
        });
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setDoubleBuffered(true);
        jScrollPane2.setMaximumSize(null);
        jScrollPane2.setMinimumSize(null);

        jlFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlFactura.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jlFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jScrollPane2.setViewportView(jlFactura);

        cbCopia.setSelected(true);
        cbCopia.setText("Imprimir copia");
        cbCopia.setToolTipText("Marque si desea una copia de la factura actual...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cbCopia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCopia)))
        );

        setSize(new java.awt.Dimension(370, 462));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            String miFile = System.getProperty("user.dir")+"/Reportes/reporteTemp.pdf";
            FileInputStream in = new FileInputStream(miFile);
            Doc doc = new SimpleDoc(in, DocFlavor.INPUT_STREAM.PDF, null);
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            service.createPrintJob().print(doc, null);
            if (cbCopia.isSelected()) {
                in = new FileInputStream(miFile);
                doc = new SimpleDoc(in, DocFlavor.INPUT_STREAM.PDF, null);
                service = PrintServiceLookup.lookupDefaultPrintService();
                service.createPrintJob().print(doc, null);
            }
        } catch (PrintException | FileNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed
    private void btnAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseEntered
        btnAceptar.setForeground(Color.BLUE);
    }//GEN-LAST:event_btnAceptarMouseEntered
    private void btnAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseExited
        btnAceptar.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnAceptarMouseExited
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        ImageIcon imagen = new ImageIcon(System.getProperty("user.dir")+"/Reportes/reporteTemp.png");
        //Tamaño de icono
        Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(imagen.getIconWidth(),
                imagen.getIconHeight(),
                Image.SCALE_DEFAULT));

        imagen.getImage().flush();
        jlFactura.setIcon(icon);
        jlFactura.validate();
        p = new Point(0, imagen.getIconHeight());
        btnAceptar.requestFocusInWindow();
        cbCopia.setSelected(getCopia());
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        jScrollPane2.getViewport().setViewPosition(p);
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JCheckBox cbCopia;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlFactura;
    // End of variables declaration//GEN-END:variables
}
