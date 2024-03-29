/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

/**
 *
 * @author Usuario
 */
public class Programa extends javax.swing.JFrame {

    /**
     * Creates new form Programa
     */
    public Programa() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botonVender = new javax.swing.JButton();
        botonIngresarProductos = new javax.swing.JButton();
        botonInventario = new javax.swing.JButton();
        botonCerrarSesion = new javax.swing.JButton();
        botonHistorial = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ferro-Ventas activo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 810, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Menu de opciones");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        botonVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vender.png"))); // NOI18N
        botonVender.setText("Vender prodcutos");
        botonVender.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonVender.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(botonVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 170, -1));

        botonIngresarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N
        botonIngresarProductos.setText("Ingresar productos");
        botonIngresarProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonIngresarProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(botonIngresarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 170, -1));

        botonInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inventario.png"))); // NOI18N
        botonInventario.setText("Inventatio y modificaciones");
        botonInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonInventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(botonInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 170, -1));

        botonCerrarSesion.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        botonCerrarSesion.setText("Cerrar sesion");
        jPanel1.add(botonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, -1, -1));

        botonHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/historial.png"))); // NOI18N
        botonHistorial.setText("Historial de ventas");
        botonHistorial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonHistorial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(botonHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Programa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonCerrarSesion;
    public javax.swing.JButton botonHistorial;
    public javax.swing.JButton botonIngresarProductos;
    public javax.swing.JButton botonInventario;
    public javax.swing.JButton botonVender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
