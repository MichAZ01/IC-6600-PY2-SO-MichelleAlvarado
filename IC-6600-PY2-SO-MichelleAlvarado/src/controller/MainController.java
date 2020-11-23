/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.MiniPC;
import GUI.MyCustomFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import logic.OSManagement.CPU;
import logic.computer.Computer;
import rojerusan.RSPanelsSlider;

/**
 *
 * @author Michelle Alvarado
 */
public class MainController implements ActionListener {
    private MiniPC view;
    
    public MainController(){
        
    }
    
    public void showView(){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiniPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiniPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiniPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiniPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        this.view = new MiniPC();
        MiniPC viewP = this.view;
        this.view.openFilesButton.addActionListener(this);
        this.view.startButton.addActionListener(this);
        this.view.configButton.addActionListener(this);
        this.view.backToCPUButton.addActionListener(this);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                viewP.setVisible(true);
                viewP.setResizable(false);
                viewP.setLocationRelativeTo(null);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "openFiles":
        {
            try {
                this.OpenFolderButtonActionPerformed(view);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case "startExecution":
                this.startButtonActionPerformed();
                break;
            case "openConfig":
                this.configButtonActionPerformed();
                break;
            case "backToMiniCPU":
                this.backToCPUButtonActionPerformed();
                break;
            default:
                break;
        }
    }
    
    public void OpenFolderButtonActionPerformed(javax.swing.JFrame view) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        MyCustomFilter assemblerFilter = (MyCustomFilter) new MyCustomFilter(".asm", "Archivo de código Ensamblador");
        fileChooser.addChoosableFileFilter(assemblerFilter);
        int result = fileChooser.showOpenDialog(view);
        switch (result) {
            case JFileChooser.APPROVE_OPTION:
                this.prepareProcesses(fileChooser.getSelectedFiles());
            case JFileChooser.CANCEL_OPTION:
                break;
            default:
                break;
        }
    }
    
    public void prepareProcesses(File[] files) throws IOException{
        Computer.getInstance().getOS().getKernel().getProcessesManager().loadProcesses(files);
        this.setButtonsEnabled();
        if(Computer.getInstance().getOS().getKernel().getProcessesManager().getConfigurableProcessesCount() > 1){
            ConfigurableProcessesController configurableProcessesController = new ConfigurableProcessesController(this.view);
            configurableProcessesController.setConfigurableProcessesTable();
            this.view.arrivalTimePanel.setVisible(true);
            
        }
    }
    
    public void setButtonsEnabled(){
        this.view.openFilesButton.setEnabled(!this.view.openFilesButton.isEnabled());
        this.view.startButton.setEnabled(!this.view.startButton.isEnabled());
        this.view.configButton.setEnabled(!this.view.configButton.isEnabled());
    }
    
    public void startButtonActionPerformed(){
        this.view.openFilesButton.setEnabled(false);
        this.view.configButton.setEnabled(false);
    }
    
    public void configButtonActionPerformed(){
        this.view.panelsSliderContainer.setPanelSlider(MiniPC.WIDTH, this.view.configContainer, RSPanelsSlider.DIRECT.RIGHT);
    }
    
    public void backToCPUButtonActionPerformed(){
        this.view.panelsSliderContainer.setPanelSlider(MiniPC.WIDTH, this.view.miniCPUContainer, RSPanelsSlider.DIRECT.RIGHT);
    }
}
