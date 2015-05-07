
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ram
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame1
     */
    public GUI() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        JPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        importPuzzle = new javax.swing.JButton();
        solvePuzzle = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        generatePuzzle = new javax.swing.JButton();
        n2 = new javax.swing.JRadioButton();
        n3 = new javax.swing.JRadioButton();
        n4 = new javax.swing.JRadioButton();
        n5 = new javax.swing.JRadioButton();
        Size = new javax.swing.JLabel();
        Diff = new javax.swing.JLabel();
        easy = new javax.swing.JRadioButton();
        medium = new javax.swing.JRadioButton();
        hard = new javax.swing.JRadioButton();
        n6 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        Classic = new javax.swing.JRadioButton();
        Samurai = new javax.swing.JRadioButton();
        gen_label = new javax.swing.JLabel();
        Options = new javax.swing.JLabel();
        import_label = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea1 = new javax.swing.JTextArea();
        Exit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JPanel.setName("JPanel"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        importPuzzle.setText("Import");
        importPuzzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importPuzzleActionPerformed(evt);
            }
        });

        solvePuzzle.setText("Solve");
        solvePuzzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvePuzzleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(importPuzzle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(solvePuzzle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(importPuzzle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(solvePuzzle)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        generatePuzzle.setText("Generate");
        generatePuzzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePuzzleActionPerformed(evt);
            }
        });

        buttonGroup2.add(n2);
        n2.setText("4 x 4");

        buttonGroup2.add(n3);
        n3.setText("9 x 9");

        buttonGroup2.add(n4);
        n4.setText("16 x 16");

        buttonGroup2.add(n5);
        n5.setText("25 x 25");

        Size.setText("Size");

        Diff.setText("Difficulty");

        buttonGroup3.add(easy);
        easy.setText("Easy");

        buttonGroup3.add(medium);
        medium.setText("Medium");

        buttonGroup3.add(hard);
        hard.setText("Hard");

        n6.setText("36 x 36");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(generatePuzzle)
                                .addContainerGap())
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(n6)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(n3)
                                                                        .addComponent(n5)
                                                                        .addComponent(n4)
                                                                        .addComponent(Size))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(n2)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(medium)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(easy)
                                                                .addComponent(Diff))
                                                        .addComponent(hard))
                                                .addGap(66, 66, 66))))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Size)
                                        .addComponent(Diff))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(n2)
                                        .addComponent(easy))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(n3)
                                        .addComponent(medium))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(n4)
                                        .addComponent(hard))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(n5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(n6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generatePuzzle)
                                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(Classic);
        Classic.setText("Classic");

        buttonGroup1.add(Samurai);
        Samurai.setText("Samurai");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Samurai)
                                        .addComponent(Classic))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Classic)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Samurai)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gen_label.setText("Generate Puzzle");

        Options.setText("Options");

        import_label.setText("Import Puzzle");

        javax.swing.GroupLayout JPanelLayout = new javax.swing.GroupLayout(JPanel);
        JPanel.setLayout(JPanelLayout);
        JPanelLayout.setHorizontalGroup(
                JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(JPanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(import_label)
                                        .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(Options))
                                .addGap(18, 18, 18)
                                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(gen_label))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanelLayout.setVerticalGroup(
                JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(JPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Options)
                                        .addComponent(gen_label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(JPanelLayout.createSequentialGroup()
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(23, 23, 23)
                                                .addComponent(import_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20))
        );

        textArea1.setColumns(20);
        textArea1.setRows(5);
        jScrollPane1.setViewportView(textArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 43, Short.MAX_VALUE))
        );

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        textArea2.setColumns(20);
        textArea2.setRows(5);
        jScrollPane2.setViewportView(textArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Exit)))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void importPuzzleActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                // What to do with the file, e.g. display it in a TextArea
                textArea1.read( new FileReader( file.getAbsolutePath() ), null );
                isImport = true;
            } catch (IOException ex) {
                System.out.println("problem accessing file"+file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }

    private void solvePuzzleActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int type = 0;

        // selecting type
        if(Classic.isSelected()){
            type = 1;
        }
        else if(Samurai.isSelected()){
            type = 2;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please select a type before solving!");
        }

        // check if import == true
        if (isImport == false){
            JOptionPane.showMessageDialog(rootPane, "Please import a sudoku file before solving!");
        }

        if(type > 0 && isImport == true){
            // KYLE
            MatrixGenerator mg = new MatrixGenerator();
            String finalBoard = mg.createFromFile(file);
            textArea2.setText(finalBoard);
        }
    }

    private void generatePuzzleActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int n = 0;
        int d = -1;

        // selecting size
        if(n2.isSelected()){
            n = 2;
        }
        else if(n3.isSelected()){
            n = 3;
        }
        else if(n4.isSelected()){
            n = 4;
        }
        else if(n5.isSelected()){
            n = 5;
        }
        else if(n6.isSelected()){
            n = 6;
        }

        // selecting difficulty
        if(easy.isSelected()){
            d = 0;
        }
        else if(medium.isSelected()){
            d = 1;
        }
        else if(hard.isSelected()){
            d = 2;
        }

        // check for checks
        if(d > -1 && n > 0){
            // KYLE DO YOUR JOB
            MatrixGenerator mg = new MatrixGenerator();
            Tuple<String, String> boards = mg.createNew(n, d);
            textArea1.setText(boards.x);
            textArea2.setText(boards.y);
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please select both size and difficulty before generating!");
        }
    }

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JRadioButton Classic;
    private javax.swing.JLabel Diff;
    private javax.swing.JButton Exit;
    private javax.swing.JPanel JPanel;
    private javax.swing.JLabel Options;
    private javax.swing.JRadioButton Samurai;
    private javax.swing.JLabel Size;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton easy;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel gen_label;
    private javax.swing.JButton generatePuzzle;
    private javax.swing.JRadioButton hard;
    private javax.swing.JButton importPuzzle;
    private javax.swing.JLabel import_label;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton medium;
    private javax.swing.JRadioButton n2;
    private javax.swing.JRadioButton n3;
    private javax.swing.JRadioButton n4;
    private javax.swing.JRadioButton n5;
    private javax.swing.JRadioButton n6;
    private javax.swing.JButton solvePuzzle;
    private javax.swing.JTextArea textArea1;
    private javax.swing.JTextArea textArea2;
    // End of variables declaration
    private boolean isImport = false;
    private File file;

}
