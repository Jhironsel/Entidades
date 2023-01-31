package sur.softsurena.formularios;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import rojeru_san.RSMTextFull;
import sur.softsurena.entidades.Servidor;

public class frmParametros extends javax.swing.JFrame {

    private final Properties propiedades;
    private File f = null;

    public frmParametros() {
        initComponents();
        propiedades = new Properties();

        try {
            f = new File(getClass().getResource(
                    "/sur/softsurena/properties/propiedades.properties").toURI());
            propiedades.load(new FileReader(f));

        } catch (URISyntaxException ex) {
            Logger.getLogger(frmParametros.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmParametros.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            Logger.getLogger(frmParametros.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        cargarParamentos("todo");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtPuerto = new javax.swing.JTextField();
        txtHost = new javax.swing.JTextField();
        chbPuerto = new javax.swing.JCheckBox();
        rbtnProtocoloIPV4 = new javax.swing.JRadioButton();
        rbtnNombreServidor = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnAceptar = new RSMaterialComponent.RSButtonMaterialIconOne();
        txtPathBaseDatos = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnBuscarDB = new RSMaterialComponent.RSButtonMaterialIconOne();
        txtValor1 = new rojeru_san.RSMTextFull();
        txtValor2 = new rojeru_san.RSMTextFull();
        txtValor3 = new rojeru_san.RSMTextFull();
        txtValor4 = new rojeru_san.RSMTextFull();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Parametros del Sistema");
        setAlwaysOnTop(true);
        setSize(new java.awt.Dimension(439, 210));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setLayout(new java.awt.GridLayout(1, 4, 20, 0));

        txtPuerto.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        txtPuerto.setToolTipText("");
        txtPuerto.setName("txtPuerto"); // NOI18N
        txtPuerto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPuertoKeyReleased(evt);
            }
        });

        txtHost.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        txtHost.setEnabled(false);
        txtHost.setName("txtHost"); // NOI18N

        chbPuerto.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        chbPuerto.setText("Puerto: ");
        chbPuerto.setName("chbPuerto"); // NOI18N
        chbPuerto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chbPuertoMouseClicked(evt);
            }
        });
        chbPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbPuertoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnProtocoloIPV4);
        rbtnProtocoloIPV4.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        rbtnProtocoloIPV4.setText("Protocolo IPV4");
        rbtnProtocoloIPV4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnProtocoloIPV4MouseClicked(evt);
            }
        });
        rbtnProtocoloIPV4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnProtocoloIPV4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnNombreServidor);
        rbtnNombreServidor.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        rbtnNombreServidor.setText("URI Servidor");
        rbtnNombreServidor.setName("rbtnNombreServidor"); // NOI18N
        rbtnNombreServidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnNombreServidorMouseClicked(evt);
            }
        });
        rbtnNombreServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNombreServidorActionPerformed(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(216, 40));
        jPanel1.setMinimumSize(new java.awt.Dimension(216, 40));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        btnCancelar.setBackground(new java.awt.Color(204, 0, 51));
        btnCancelar.setText("Cancelar");
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelar.setMaximumSize(new java.awt.Dimension(216, 40));
        btnCancelar.setMinimumSize(new java.awt.Dimension(216, 40));
        btnCancelar.setPreferredSize(new java.awt.Dimension(216, 40));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);

        btnAceptar.setText("Aceptar");
        btnAceptar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK);
        btnAceptar.setMaximumSize(new java.awt.Dimension(216, 40));
        btnAceptar.setMinimumSize(new java.awt.Dimension(216, 40));
        btnAceptar.setName("btnAceptar"); // NOI18N
        btnAceptar.setPreferredSize(new java.awt.Dimension(216, 40));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAceptar);

        txtPathBaseDatos.setForeground(new java.awt.Color(187, 187, 187));
        txtPathBaseDatos.setToolTipText("Ingrese la ruta de la base de datos para poderse conectar al servidor.");
        txtPathBaseDatos.setColorIcon(new java.awt.Color(187, 187, 187));
        txtPathBaseDatos.setColorMaterial(new java.awt.Color(187, 187, 187));
        txtPathBaseDatos.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DATA_USAGE);
        txtPathBaseDatos.setName("txtPathBaseDatos"); // NOI18N
        txtPathBaseDatos.setPhColor(new java.awt.Color(187, 187, 187));
        txtPathBaseDatos.setPlaceholder("Ruta de la base de datos");

        btnBuscarDB.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btnBuscarDB.setMaximumSize(new java.awt.Dimension(216, 40));
        btnBuscarDB.setMinimumSize(new java.awt.Dimension(216, 40));
        btnBuscarDB.setPreferredSize(new java.awt.Dimension(216, 40));
        btnBuscarDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDBActionPerformed(evt);
            }
        });

        txtValor1.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        txtValor1.setPlaceholder("0 - 255");
        txtValor1.setSoloNumeros(true);
        txtValor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValor1KeyReleased(evt);
            }
        });

        txtValor2.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        txtValor2.setPlaceholder("0 - 255");
        txtValor2.setSoloNumeros(true);
        txtValor2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValor2KeyReleased(evt);
            }
        });

        txtValor3.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        txtValor3.setPlaceholder("0 - 255");
        txtValor3.setSoloNumeros(true);
        txtValor3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValor3KeyReleased(evt);
            }
        });

        txtValor4.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        txtValor4.setPlaceholder("0 - 255");
        txtValor4.setSoloNumeros(true);
        txtValor4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValor4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtnNombreServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbPuerto))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(rbtnProtocoloIPV4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValor2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValor3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValor4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtPathBaseDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarDB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbtnNombreServidor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chbPuerto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnProtocoloIPV4)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPathBaseDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarDB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtHost, txtPuerto});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBuscarDB, txtPathBaseDatos});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnNombreServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNombreServidorActionPerformed
        valoresEstados(false);
        txtValor1.setText("");
        txtValor2.setText("");
        txtValor3.setText("");
        txtValor4.setText("");
    }//GEN-LAST:event_rbtnNombreServidorActionPerformed

    private void rbtnProtocoloIPV4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnProtocoloIPV4ActionPerformed
        valoresEstados(true);
        txtHost.setText("");
    }//GEN-LAST:event_rbtnProtocoloIPV4ActionPerformed

    private void txtPuertoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuertoKeyReleased
        char caracter = evt.getKeyChar();
        if (caracter < '0' || (caracter > '9')) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_txtPuertoKeyReleased

    private void rbtnNombreServidorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnNombreServidorMouseClicked
        cargarParamentos("nombre");
    }//GEN-LAST:event_rbtnNombreServidorMouseClicked

    private void rbtnProtocoloIPV4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnProtocoloIPV4MouseClicked
        cargarParamentos("ipv4");
    }//GEN-LAST:event_rbtnProtocoloIPV4MouseClicked

    private void chbPuertoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chbPuertoMouseClicked
//        cargarParamentos("puerto");
    }//GEN-LAST:event_chbPuertoMouseClicked

    private void chbPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbPuertoActionPerformed
        cargarParamentos("puerto");
    }//GEN-LAST:event_chbPuertoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (rbtnProtocoloIPV4.isSelected()) {
            int valor;
            try {
                valor = Integer.parseInt(txtValor1.getText());
            } catch (NumberFormatException e) {
                valor = -1;
            }

            if (valor < 0 || valor > 255) {
                JOptionPane.showMessageDialog(rootPane, "Valor incorrecto en el Ambito 1");
                txtValor1.setText("");
                txtValor1.requestFocusInWindow();
                return;
            }

            try {
                valor = Integer.parseInt(txtValor2.getText());
            } catch (NumberFormatException e) {
                valor = -1;
            }
            if (valor < 0 || valor > 255) {
                JOptionPane.showMessageDialog(rootPane, "Valor incorrecto en el Ambito 2");
                txtValor2.setText("");
                txtValor2.requestFocusInWindow();
                return;
            }

            try {
                valor = Integer.parseInt(txtValor3.getText());
            } catch (NumberFormatException e) {
                valor = -1;
            }
            if (valor < 0 || valor > 255) {
                JOptionPane.showMessageDialog(rootPane, "Valor incorrecto en el Ambito 3");
                txtValor3.setText("");
                txtValor3.requestFocusInWindow();
                return;
            }

            try {
                valor = Integer.parseInt(txtValor4.getText());
            } catch (NumberFormatException e) {
                valor = -1;
            }
            if (valor < 0 || valor > 255) {
                JOptionPane.showMessageDialog(rootPane, "Valor incorrecto en el Ambito 4");
                txtValor4.setText("");
                txtValor4.requestFocusInWindow();
                return;
            }
        }

        if (rbtnNombreServidor.isSelected()) {
            if (txtHost.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nombre del servidor vacio");
                txtHost.requestFocusInWindow();
                return;
            }
        }

        if (chbPuerto.isSelected()) {
            int valor;
            try {
                valor = Integer.parseInt(txtPuerto.getText());
            } catch (NumberFormatException e) {
                valor = -1;
            }
            if (valor < 0 || valor > 65535) {
                JOptionPane.showMessageDialog(rootPane, "Este Puerto no es valido");
                txtPuerto.setText("");
                txtPuerto.requestFocusInWindow();
                return;
            }
        }

        escribirParametros();
        dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnBuscarDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDBActionPerformed
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Base de datos Firebird", "FDB", "fdb");
        jfc.setFileFilter(filter);

        int returnVal = jfc.showOpenDialog(null);

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return;
        }

        txtPathBaseDatos.setText(jfc.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_btnBuscarDBActionPerformed

    private void txtValor1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor1KeyReleased
        validarNumeros(txtValor1);
    }//GEN-LAST:event_txtValor1KeyReleased

    private void txtValor2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor2KeyReleased
        validarNumeros(txtValor2);
    }//GEN-LAST:event_txtValor2KeyReleased

    private void txtValor3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor3KeyReleased
        validarNumeros(txtValor3);
    }//GEN-LAST:event_txtValor3KeyReleased

    private void txtValor4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor4KeyReleased
        validarNumeros(txtValor4);
    }//GEN-LAST:event_txtValor4KeyReleased
    
    private void validarNumeros(RSMTextFull campo){
        if (!campo.getText().isBlank() || !campo.getText().isEmpty()) {
            int numero = Integer.parseInt(campo.getText());
            if (numero < 0 || numero > 255 || campo.getText().isEmpty()) {
                campo.setBordeColorFocus(Color.RED);
                campo.setBordeColorNoFocus(Color.RED);
            } else {
                campo.setBordeColorFocus(new Color(0,112,192));
                campo.setBordeColorNoFocus(new Color(0,112,192));
            }
        }
    }
    
    private void valoresEstados(boolean estado) {
        txtValor1.setEnabled(estado);
        txtValor2.setEnabled(estado);
        txtValor3.setEnabled(estado);
        txtValor4.setEnabled(estado);

        txtHost.setEnabled(!estado);

        if (estado) {
            txtValor1.requestFocusInWindow();
        } else {
            txtHost.requestFocusInWindow();
        }
    }

    public Servidor cargarParamentos(String zona) {
        Servidor s = Servidor.builder().
                conServidor(Boolean.valueOf(propiedades.getProperty("NombreActivo", "false"))).
                uriServidor(propiedades.getProperty("Nombre_del_Servidor", "")).
                conPuerto(Boolean.valueOf(propiedades.getProperty("Con_Puerto", "false"))).
                puerto(propiedades.getProperty("Puerto_del_Servidor", "3050")).
                conIpServidor(Boolean.valueOf(propiedades.getProperty("ProtocoloActivo", "false"))).
                ipServidor1(propiedades.getProperty("Ip_Servidor1", "127")).
                ipServidor2(propiedades.getProperty("Ip_Servidor2", "0")).
                ipServidor3(propiedades.getProperty("Ip_Servidor3", "0")).
                ipServidor4(propiedades.getProperty("Ip_Servidor4", "1")).
                pathBaseDatos(propiedades.getProperty("PathBaseDatos", "/"))
                .build();
        
        if (zona.equals("todo") || zona.equals("nombre")) {
            if (s.getConServidor()) {
                txtHost.setText(s.getUriServidor());
                rbtnNombreServidor.doClick();
            }
        }
        
        if (zona.equals("todo") || zona.equals("puerto")) {
            if (s.getConPuerto()) {
                if (zona.equals("todo")) {
                    txtPuerto.setText(s.getPuerto());
                    chbPuerto.doClick();
                }

                if (zona.equals("puerto")) {
                    if (chbPuerto.isSelected()) {
                        txtPuerto.setEnabled(true);
                        if (s.getConPuerto()) {
                            txtPuerto.setText(s.getPuerto());
                        }
                        txtPuerto.requestFocusInWindow();
                    } else {
                        txtPuerto.setEnabled(false);
                        txtPuerto.setText("");
                    }
                }
            } else {
                if (chbPuerto.isSelected()) {
                    txtPuerto.setEnabled(true);
                    txtPuerto.requestFocusInWindow();
                } else {
                    txtPuerto.setEnabled(false);
                    txtPuerto.setText("");
                }
            }
        }

        if (zona.equals("todo") || zona.equals("ipv4")) {
            if (s.getConIpServidor()) {
                txtValor1.setText(s.getIpServidor1());
                txtValor2.setText(s.getIpServidor2());
                txtValor3.setText(s.getIpServidor3());
                txtValor4.setText(s.getIpServidor4());
                rbtnProtocoloIPV4.doClick();
            }
        }


        if (zona.equals("todo") || zona.equals("PathBaseDatos")) {
            txtPathBaseDatos.setText(s.getPathBaseDatos());
        }
        
        return s;
    }

    private void escribirParametros() {
        propiedades.setProperty("Ip_Servidor1", txtValor1.getText());
        propiedades.setProperty("Ip_Servidor2", txtValor2.getText());
        propiedades.setProperty("Ip_Servidor3", txtValor3.getText());
        propiedades.setProperty("Ip_Servidor4", txtValor4.getText());
        propiedades.setProperty("ProtocoloActivo", "" + rbtnProtocoloIPV4.isSelected());
        propiedades.setProperty("NombreActivo", "" + rbtnNombreServidor.isSelected());
        propiedades.setProperty("Nombre_del_Servidor", txtHost.getText());
        propiedades.setProperty("Puerto_del_Servidor", txtPuerto.getText());
        propiedades.setProperty("Con_Puerto", "" + chbPuerto.isSelected());
        propiedades.setProperty("PathBaseDatos", txtPathBaseDatos.getText());

        try {

            propiedades.store(
                    new FileWriter(f),
                    "Parametros del Servidor");
        } catch (IOException ex) {
            Logger.getLogger(frmParametros.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconOne btnAceptar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnBuscarDB;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbPuerto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton rbtnNombreServidor;
    private javax.swing.JRadioButton rbtnProtocoloIPV4;
    private javax.swing.JTextField txtHost;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtPathBaseDatos;
    private javax.swing.JTextField txtPuerto;
    private rojeru_san.RSMTextFull txtValor1;
    private rojeru_san.RSMTextFull txtValor2;
    private rojeru_san.RSMTextFull txtValor3;
    private rojeru_san.RSMTextFull txtValor4;
    // End of variables declaration//GEN-END:variables
}
