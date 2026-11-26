/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.proyectodb;

import java.util.Calendar;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author jesusvldz23
 */
public class jdlCotizacion extends javax.swing.JDialog {
    
    private com.toedter.calendar.JDateChooser jdcFecha;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(jdlCotizacion.class.getName());

    /**
     * Creates new form jdlCotizacion
     */
    public jdlCotizacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.jdcFecha = new com.toedter.calendar.JDateChooser();
        this.jdcFecha.setDateFormatString("dd/MM/yyyy");
        this.pnlFecha.setLayout(new java.awt.BorderLayout());
        this.pnlFecha.add(this.jdcFecha, java.awt.BorderLayout.CENTER);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.limpiar();
        this.deshabilitar();
    }

    // Pega TODO esto debajo del constructor, ANTES del bloque gris "Generated Code".

    // ================== metodos de utileria ==================

    // 13. limpiar: restablece los campos del formulario
    public void limpiar() {
        this.jdcFecha.setDate(Calendar.getInstance().getTime()); // fecha de hoy
        this.txtDescripcion.setText("");
        this.txtNumCotizacion.setText("");
        this.txtPrecio.setText("");
        this.rdb12.setSelected(true);
        this.spnPorcentaje.setValue(0);
        this.lblPagoInicial.setText("$ 0.0");
        this.lblPagoMensual.setText("$ 0.0");
        this.lblTotalFin.setText("$ 0.0");
        this.txtNumCotizacion.requestFocus();
    }

    // 14. habilitar: activa los componentes para capturar informacion
    public void habilitar() {
        this.txtNumCotizacion.setEnabled(true);
        this.txtDescripcion.setEnabled(true);
        this.txtPrecio.setEnabled(true);
        this.spnPorcentaje.setEnabled(true);
        this.jdcFecha.setEnabled(true);
        this.rdb12.setEnabled(true);
        this.rdb24.setEnabled(true);
        this.rdb36.setEnabled(true);
        this.rdb48.setEnabled(true);
        this.btnGuardar.setEnabled(true);
    }

    // 15. deshabilitar: bloquea los componentes de captura
    public void deshabilitar() {
        this.txtNumCotizacion.setEnabled(false);
        this.txtDescripcion.setEnabled(false);
        this.txtPrecio.setEnabled(false);
        this.spnPorcentaje.setEnabled(false);
        this.jdcFecha.setEnabled(false);
        this.rdb12.setEnabled(false);
        this.rdb24.setEnabled(false);
        this.rdb36.setEnabled(false);
        this.rdb48.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnDeshabilitar.setEnabled(false);
        this.btnHabilitar.setEnabled(false);
    }

    // 16. validar: comprueba que los campos obligatorios tengan informacion
    public boolean validar() {
        boolean exito = true;
        if (this.txtNumCotizacion.getText().isEmpty()
                || this.txtDescripcion.getText().isEmpty()
                || this.txtPrecio.getText().isEmpty()) {
            exito = false;
        }
        return exito;
    }

    // 17. llenarObjeto: crea un objeto Cotizacion con los datos capturados
    public Cotizacion llenarObjeto() {
        int plazos = 0;
        Cotizacion con = new Cotizacion();
        con.setNumCotizacion(this.txtNumCotizacion.getText());
        con.setDescripcion(this.txtDescripcion.getText());
        con.setPrecio(Float.parseFloat(this.txtPrecio.getText()));
        con.setPorcentaje(Float.parseFloat(this.spnPorcentaje.getValue().toString()));

        // fecha (debe ser java.sql.Date)
        java.util.Date fecha = this.jdcFecha.getDate();
        if (fecha == null) {
            fecha = Calendar.getInstance().getTime();
        }
        con.setFecha(new java.sql.Date(fecha.getTime()));

        if (this.rdb12.isSelected()) plazos = 12;
        if (this.rdb24.isSelected()) plazos = 24;
        if (this.rdb36.isSelected()) plazos = 36;
        if (this.rdb48.isSelected()) plazos = 48;
        con.setPlazos(plazos);
        return con;
    }

    // 18. mostrarDatos: presenta la informacion de una cotizacion en la interfaz
    public void mostrarDatos(Cotizacion con) {
        this.txtNumCotizacion.setText(con.getNumCotizacion());
        this.txtDescripcion.setText(con.getDescripcion());
        this.txtPrecio.setText(String.valueOf(con.getPrecio()));
        this.spnPorcentaje.setValue(Math.round(con.getPorcentaje()));
        switch (con.getPlazos()) {
            case 12: this.rdb12.setSelected(true); break;
            case 24: this.rdb24.setSelected(true); break;
            case 36: this.rdb36.setSelected(true); break;
            case 48: this.rdb48.setSelected(true); break;
        }
        this.jdcFecha.setDate(con.getFecha());
        this.lblPagoInicial.setText(String.format(Locale.US, "$ %.2f", con.calcularPagoInicial()));
        this.lblTotalFin.setText(String.format(Locale.US, "$ %.2f", con.calcularTotalFin()));
        this.lblPagoMensual.setText(String.format(Locale.US, "$ %.2f", con.calcularPagoMensual()));
    }

    // 19. mostrarMensaje: muestra mensajes informativos al usuario
    void mostrarMensaje(String msg, String titulo) {
        JOptionPane.showMessageDialog(this, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnCerrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnDeshabilitar = new javax.swing.JButton();
        btnHabilitar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlFecha = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cmbStatus = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPagoMensual = new javax.swing.JLabel();
        lblPagoInicial = new javax.swing.JLabel();
        lblTotalFin = new javax.swing.JLabel();
        rdb48 = new javax.swing.JRadioButton();
        rdb12 = new javax.swing.JRadioButton();
        rdb24 = new javax.swing.JRadioButton();
        rdb36 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        spnPorcentaje = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtNumCotizacion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cotizacion");
        getContentPane().setLayout(null);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);
        getContentPane().add(btnCerrar);
        btnCerrar.setBounds(270, 470, 72, 23);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(270, 10, 72, 23);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(490, 70, 110, 23);

        btnGuardar.setText("Guardar");
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(490, 110, 110, 23);

        btnDeshabilitar.setText("Deshabilitar");
        getContentPane().add(btnDeshabilitar);
        btnDeshabilitar.setBounds(490, 150, 110, 20);

        btnHabilitar.setText("Habilitar");
        getContentPane().add(btnHabilitar);
        btnHabilitar.setBounds(490, 180, 110, 20);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(50, 470, 73, 23);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(160, 470, 90, 23);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setText("Plazos");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 130, 42, 50);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel2.setText("Num. Cotizacion ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 10, 110, 20);

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel3.setText("Descripcion ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 50, 100, 20);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("Porcentaje");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 90, 80, 20);

        pnlFecha.add(jPanel2);

        getContentPane().add(pnlFecha);
        pnlFecha.setBounds(430, 10, 170, 30);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activas", "No activas" }));
        cmbStatus.addActionListener(this::cmbStatusActionPerformed);
        getContentPane().add(cmbStatus);
        cmbStatus.setBounds(410, 300, 100, 30);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel6.setText("Pago Inicial");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 5, -1, -1));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setText("     Total a Financiar     ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 5, -1, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel5.setText("     Pago Mensual");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 5, -1, -1));

        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        lblPagoMensual.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblPagoMensual.setText("$ 0.0");
        jPanel1.add(lblPagoMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        lblPagoInicial.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblPagoInicial.setText("$ 0.0");
        jPanel1.add(lblPagoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        lblTotalFin.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblTotalFin.setText("$ 0.0");
        jPanel1.add(lblTotalFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 190, 450, 100);

        buttonGroup1.add(rdb48);
        rdb48.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        rdb48.setText("48 meses");
        getContentPane().add(rdb48);
        rdb48.setBounds(310, 150, 74, 19);

        buttonGroup1.add(rdb12);
        rdb12.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        rdb12.setSelected(true);
        rdb12.setText("12 meses");
        rdb12.addActionListener(this::rdb12ActionPerformed);
        getContentPane().add(rdb12);
        rdb12.setBounds(70, 150, 74, 19);

        buttonGroup1.add(rdb24);
        rdb24.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        rdb24.setText("24 meses");
        getContentPane().add(rdb24);
        rdb24.setBounds(150, 150, 74, 19);

        buttonGroup1.add(rdb36);
        rdb36.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        rdb36.setText("36 meses");
        getContentPane().add(rdb36);
        rdb36.setBounds(230, 150, 74, 20);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel9.setText("Precio");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 120, 39, 20);

        spnPorcentaje.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        getContentPane().add(spnPorcentaje);
        spnPorcentaje.setBounds(130, 87, 70, 23);

        tbDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbDatos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(22, 340, 480, 110);

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel10.setText("Mis Cotizaciones");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(300, 300, 120, 30);
        getContentPane().add(txtPrecio);
        txtPrecio.setBounds(130, 120, 64, 23);

        txtDescripcion.addActionListener(this::txtDescripcionActionPerformed);
        getContentPane().add(txtDescripcion);
        txtDescripcion.setBounds(130, 50, 170, 30);
        getContentPane().add(txtNumCotizacion);
        txtNumCotizacion.setBounds(140, 10, 120, 30);

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel11.setText("Fecha");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(380, 10, 37, 17);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void rdb12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdb12ActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    
        this.limpiar();  
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiar();
        this.deshabilitar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
      this.habilitar();
      this.txtNumCotizacion.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
      if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Deseas salir",
        "Cotizacion", JOptionPane.YES_NO_OPTION)) {
      this.dispose();
}
    }//GEN-LAST:event_btnCerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                jdlCotizacion dialog = new jdlCotizacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnDeshabilitar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHabilitar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagoInicial;
    private javax.swing.JLabel lblPagoMensual;
    private javax.swing.JLabel lblTotalFin;
    private javax.swing.JPanel pnlFecha;
    private javax.swing.JRadioButton rdb12;
    private javax.swing.JRadioButton rdb24;
    private javax.swing.JRadioButton rdb36;
    private javax.swing.JRadioButton rdb48;
    private javax.swing.JSpinner spnPorcentaje;
    private javax.swing.JTable tbDatos;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNumCotizacion;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
