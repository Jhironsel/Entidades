package sur.softsurena.formularios;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JDialog;

public class frmImpresoras extends JDialog {

    private final String seleccionar = "Seleccionar una impresora";
    
    public frmImpresoras(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Centra la ventana en la pantalla
        this.setLocationRelativeTo(this);
        listarImpresoras();
        seleccionarImpresoraPredeterminada();
    }

    private void seleccionarImpresoraPredeterminada() {
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        if (service != null) {
            String printServiceName = service.getName();
            comboImpresoras.setSelectedItem(printServiceName);
            lblImpresora.setText(printServiceName);
        } else {
            comboImpresoras.removeAllItems();
            comboImpresoras.setSelectedItem(seleccionar);
        }
    }

    private void listarImpresoras() {
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);
        comboImpresoras.removeAllItems();
        comboImpresoras.addItem(seleccionar);
        for (PrintService p : ps) {
            comboImpresoras.addItem(p.getName());
        }
    }

    private void estableceImpresoraPredeterminada(String printerName) {
        Process p = null;
        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
            try {
                p = Runtime.getRuntime().exec("lpoptions -d " + printerName);
            } catch (IOException ex) {
                //Instalar Logger
                return;
            }
        } else {
            String ruta="";
            if (System.getProperty("os.name").equals("Windows 7")) {
                ruta = "C:\\Windows\\System32\\";
            }
            
            String cmdLine = String.format(ruta+
                    "RUNDLL32 PRINTUI.DLL,PrintUIEntry /y /n \"%s\"", printerName);
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmdLine);
            builder.redirectErrorStream(true);
            try {
                p = builder.start();
            } catch (IOException e) {
                //Instalar Logger
            }
        }

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = new String();
        while (true) {
            try {
                line = r.readLine();
            } catch (IOException e) {
                //Instalar Logger
            }
            if (line == null) {
                break;
            }
        }
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboImpresoras = new javax.swing.JComboBox<>();
        btnEstablecer = new javax.swing.JButton();
        lblImpresora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Impresoras del sistema");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("IMPRESORAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(6, 6, 6))
        );

        comboImpresoras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnEstablecer.setText("ESTABLECER");
        btnEstablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstablecerActionPerformed(evt);
            }
        });

        lblImpresora.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblImpresora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImpresora.setText("Impresora");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboImpresoras, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblImpresora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEstablecer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(comboImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lblImpresora)
                .addGap(7, 7, 7)
                .addComponent(btnEstablecer, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEstablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstablecerActionPerformed
        estableceImpresoraPredeterminada((String) comboImpresoras.getSelectedItem());
        seleccionarImpresoraPredeterminada();
    }//GEN-LAST:event_btnEstablecerActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEstablecer;
    private javax.swing.JComboBox<String> comboImpresoras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblImpresora;
    // End of variables declaration//GEN-END:variables
}
