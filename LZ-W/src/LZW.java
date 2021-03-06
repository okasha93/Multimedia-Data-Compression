
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdallah Okasha
 */
public class LZW extends javax.swing.JFrame {

    /**
     * Creates new form LZW
     */
    String data = "";
    BufferedReader br = null;
    Vector<String> Ordir = new Vector<String>();
    Vector<Integer> comp = new Vector<Integer>();

    public LZW() {
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

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton1.setText("Compress");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("De-compress");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("LZ-W");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(jRadioButton2)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addGap(49, 49, 49)
                .addComponent(jButton1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        jRadioButton2.setSelected(true);
        jRadioButton1.setSelected(false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jRadioButton1.isSelected()) {
            //read Data from file
            try {
                String sCurrentLine;
                br = new BufferedReader(new FileReader("D:\\FCI\\3th-Level\\Java\\LZ-W\\input.txt"));

                while ((sCurrentLine = br.readLine()) != null) {
                    data = data + sCurrentLine;
                }

            } catch (IOException e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                }
            }
            System.out.println("Data : "+data);
            //End of reading data
            for (int i = 0; i < 128; i++) {
                Ordir.add((char) i + "");
            }
            //Compression begin .. 
            String sub = "",subb = "";
            for (int i = 0; i < data.length(); i++) {
                sub = sub + data.charAt(i);
                boolean found = false;
                for (int j = 0; j < Ordir.size(); j++) {
                    if (sub.equals(Ordir.get(j))) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    Ordir.add(sub);
                    subb = "";
                    for (int j = 0; j < sub.length() - 1; j++) {
                        subb = subb + sub.charAt(j);
                    }
                    for (int k = 0; k < Ordir.size(); k++) {
                        if (subb.equals(Ordir.get(k))) {
                            comp.add(k);
                            break;
                        }
                    }
                    if (i > 0) {
                        i--;
                    }
                    sub = "";
                }
            }
            //System.out.println("\n"+sub+" "+subb);
            for (int i=0;i<Ordir.size();i++){
                if (sub.equals(Ordir.get(i))){comp.add(i);break;}
            }
            //==
            System.out.println("Compression : ");
            for (int i = 0; i < comp.size(); i++) {
                System.out.print(comp.get(i) + " ");
            }
            System.out.println("\nDictionary : ");
            for (int i = 128; i < Ordir.size(); i++) {
                System.out.print(Ordir.get(i) + " ");
            }

        } else if (jRadioButton2.isSelected()) {
            String decomp="";
            for (int i=0;i<comp.size();i++){
                decomp=decomp+Ordir.get(comp.get(i));}
            System.out.println("\nDe-compress : "+decomp);
            int maxDecomp=-1;
            for (int i=0;i<comp.size();i++){
                if (comp.get(i)>maxDecomp)maxDecomp=comp.get(i);
            }
            int x=1,cnt=0;
            while (x<maxDecomp){
                x*=2;
                cnt++;
            }
            System.out.println("Original Size : "+(7*data.length() + " bits"));
            System.out.println("Compressed Size : "+(cnt*comp.size())+" bits");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LZW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LZW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LZW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LZW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LZW().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
//xyyzxxzyyxzz
//abaababbaabaabaaaababbbbbbbb
//Okasha