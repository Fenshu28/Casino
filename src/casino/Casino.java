package casino;

import Hilos.HiloComprobar;
import Hilos.HiloNumero;
import controller.ImagenController;
import java.awt.Image;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Casino extends javax.swing.JFrame {

    private int monedas = 100;

    private final HashMap<Integer, Image> imagenes;
    private final int wImg = 100;
    private final int hImg = 150;

    public HiloNumero h1;
    public HiloNumero h2;
    public HiloNumero h3;
    public HiloComprobar hComp;

    public Casino() {

        initComponents();
        imagenes = new HashMap<>();
        fillImagen();
        init();
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public JLabel getLbMoneda() {
        return lbMoneda;
    }

    private void fillImagen() {
        try {
            for (int i = 0; i < 10; i++) {
                imagenes.put(i,
                        ImagenController.loadImagen(
                                getClass().getResource("/resource/" + i + ".png").getFile(),
                                false, wImg, hImg));
            }

        } catch (Exception ex) {
            Logger.getLogger(Casino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() {
        try {
            imgUno.setIcon(new ImageIcon(imagenes.get(7)));
            imgDos.setIcon(new ImageIcon(imagenes.get(7)));
            imgTres.setIcon(new ImageIcon(imagenes.get(7)));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean comprobar() {
        return (h1.getResult() == h2.getResult()) && (h2.getResult() == h3.getResult());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgDos = new javax.swing.JLabel();
        imgUno = new javax.swing.JLabel();
        imgTres = new javax.swing.JLabel();
        btnGirar = new javax.swing.JButton();
        lbMoneda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maquina traga moneda");
        setResizable(false);

        btnGirar.setText("Girar");
        btnGirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGirarActionPerformed(evt);
            }
        });

        lbMoneda.setText("Monedas: 100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(imgUno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imgDos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(imgTres, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGirar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbMoneda, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMoneda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgDos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgUno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgTres, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGirar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGirarActionPerformed
        if (getMonedas() >= 10) {
            h3 = new HiloNumero(imagenes, imgTres);
            h2 = new HiloNumero(imagenes, imgDos);
            h1 = new HiloNumero(imagenes, imgUno);
            hComp = new HiloComprobar(this);

            h1.start();
            h2.start();
            h3.start();
            hComp.start();
        } else {
            JOptionPane.showMessageDialog(null, "No tienes monedas disponibles");
        }

    }//GEN-LAST:event_btnGirarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Casino().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGirar;
    private javax.swing.JLabel imgDos;
    private javax.swing.JLabel imgTres;
    private javax.swing.JLabel imgUno;
    private javax.swing.JLabel lbMoneda;
    // End of variables declaration//GEN-END:variables
}
