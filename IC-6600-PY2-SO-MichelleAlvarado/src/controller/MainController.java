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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import logic.OSManagement.CPU;
import logic.computer.Computer;
import rojerusan.RSPanelsSlider;
import logic.ProcessesManagement.Process;
import logic.custom.OSSettings.OSConfig;
import logic.custom.OSSettings.OSConfigReader;

/**
 *
 * @author Michelle Alvarado
 */
public class MainController implements ActionListener {

    private MiniPC view;

    public MainController() {
    }

    public void showView() {
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
        MemoryTableController.getInstance().initView(viewP);
        ConfigurableProcessesController.getInstance().initView(viewP);
        ProcessesTableController.getInstance().initView(viewP);
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
    
    public void cleanSystem(){
        OSConfigReader.getInstance().cleanOSConfigReader();
        OSConfig.getInstance().cleanOSConfig();
        Computer.getInstance().cleanComputer();
        MemoryTableController.getInstance().cleanMemoryTableController();
        ConfigurableProcessesController.getInstance().cleanConfigurableProcessesController();
        ProcessesTableController.getInstance().cleanProcessesTableController();
        MemoryTableController.getInstance().initView(view);
        ConfigurableProcessesController.getInstance().initView(view);
        ProcessesTableController.getInstance().initView(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "openFiles": {
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
                this.cleanSystem();
                this.prepareProcesses(fileChooser.getSelectedFiles());
            case JFileChooser.CANCEL_OPTION:
                break;
            default:
                break;
        }
    }

    public void prepareProcesses(File[] files) throws IOException {
        Computer.getInstance().getOS().getKernel().getProcessesManager().loadProcesses(files);
        ArrayList<Process> newProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getNewProcesses();
        Computer.getInstance().getOS().getKernel().getProgramsLoader().allocateProcessesInMemory(newProcesses);
        this.setButtonsDisabled();
        if (Computer.getInstance().getOS().getKernel().getProcessesManager().getConfigurableProcessesCount() > 1) {
            ConfigurableProcessesController.getInstance();
            ConfigurableProcessesController.getInstance().setConfigurableProcessesTable();
            this.view.arrivalTimePanel.setVisible(true);
        } if (Computer.getInstance().getOS().getKernel().getProcessesManager().getConfigurableProcessesCount() >= 1) {
            MemoryTableController.getInstance().setMainMemoryTable();
            MemoryTableController.getInstance().setSecondaryMemoryTable();
        } if (Computer.getInstance().getOS().getKernel().getProcessesManager().getConfigurableProcessesCount() == 1){
            Computer.getInstance().getOS().getKernel().getProcessesManager().setReadyProcessesArrivalTime(ConfigurableProcessesController.getInstance().getCurrentProcessesArrivalTime());
            ProcessesTableController.getInstance().setProcessesTable();
        }
        else{
            this.setButtonsEnabled();
            this.generateSystemMessages();
        }
    }
    
    public void generateSystemMessages(){
        if(Computer.getInstance().getOS().getKernel().getProcessesManager().getLoadedProcesses().size() > 0 && 
                Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentReadyProcesses().isEmpty() && 
                Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentWaitingProcesses().isEmpty()){
            JOptionPane.showMessageDialog(this.view, "Los procesos cargados tienen errores. \nPor favor revise y modifique las instrucciones, \nluego proceda a cargar de nuevo los archivos. ");
        }
        if(Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentReadyProcesses().isEmpty() && 
                Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentWaitingProcesses().size() > 0){
            JOptionPane.showMessageDialog(this.view, "El tamaño definido para la memoria principal no es lo suficientemente \ngrande para almacenar los procesos que fueron cargados.");
        }
    }

    public void setButtonsEnabled() {
        this.view.openFilesButton.setEnabled(true);
        this.view.startButton.setEnabled(false);
        this.view.configButton.setEnabled(true);
    }
    
    public void setButtonsDisabled() {
        this.view.openFilesButton.setEnabled(false);
        this.view.startButton.setEnabled(true);
        this.view.configButton.setEnabled(false);
    }

    public void startButtonActionPerformed() {
        this.view.openFilesButton.setEnabled(false);
        this.view.configButton.setEnabled(false);
    }

    public void configButtonActionPerformed() {
        this.view.panelsSliderContainer.setPanelSlider(MiniPC.WIDTH, this.view.configContainer, RSPanelsSlider.DIRECT.RIGHT);
    }

    public void backToCPUButtonActionPerformed() {
        this.view.panelsSliderContainer.setPanelSlider(MiniPC.WIDTH, this.view.miniCPUContainer, RSPanelsSlider.DIRECT.RIGHT);
    }
}
