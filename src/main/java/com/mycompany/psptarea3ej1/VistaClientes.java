/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.psptarea3ej1;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author castr
 */
public class VistaClientes extends javax.swing.JFrame {

    boolean mensajeListo = false;
    String mensaje = "";
    String host;

    /**
     * Creates new form VistaClientes
     */
    public VistaClientes() {
        initComponents();
        jtpChat.setEditable(false);
        iniciar();

    }

    @SuppressWarnings("serial")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtpMensaje = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtpChat = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtpMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtpMensajeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtpMensajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtpMensajeKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jtpMensaje);

        jScrollPane2.setViewportView(jtpChat);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("ENVIAR");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtpMensajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtpMensajeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtpMensajeKeyTyped

    private void jtpMensajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtpMensajeKeyReleased

     }//GEN-LAST:event_jtpMensajeKeyReleased

    private void jtpMensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtpMensajeKeyPressed

    }//GEN-LAST:event_jtpMensajeKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (!mensajeListo) {
            mensaje = jtpMensaje.getText().trim();
            System.out.println(mensaje);
            jtpMensaje.setText(null);
            if (!mensaje.equals(null) && !mensaje.equals("")) {
                mensajeListo = true;
            }

        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
        if (!mensajeListo) {
            mensaje = jtpMensaje.getText().trim();
            jtpMensaje.setText(null);
            if (!mensaje.equals(null) && !mensaje.equals("")) {
                mensajeListo = true;
            }

        }
        
    }//GEN-LAST:event_jButton1MouseClicked

    public void displayMessage(String receivedMessage) {
        StyledDocument doc = jtpChat.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), receivedMessage + "\n", null);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    public boolean isMensajeListo() {
        return mensajeListo;
    }

    public void setMensajeListo(boolean mensajeListo) {
        this.mensajeListo = mensajeListo;
    }

    public void iniciar() {

        host = JOptionPane.showInputDialog("Ingrese el host:");

        if (host == null || host.trim().isEmpty()) {

            System.out.println("Se debe proporcionar un host. Saliendo...");
            System.exit(0);
        }

        this.setTitle("UDP CHAT  Server: " + host);

        initComponents();
        jtpChat.setEditable(false);
        this.setVisible(true);
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jtpChat;
    private javax.swing.JTextPane jtpMensaje;
    // End of variables declaration//GEN-END:variables
}
