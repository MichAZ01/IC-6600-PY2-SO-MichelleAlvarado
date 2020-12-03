/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.ColorRenderer;
import GUI.MiniPC;
import java.util.ArrayList;
import javax.swing.JTable;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public class TableController {
    private MiniPC view;
    
     public TableController(){
         
     }
     
     public void initView(MiniPC viewp){
         this.view = viewp;
     }

    public MiniPC getView() {
        return view;
    }
     
     public void setTableModel(Object[][] data, JTable table, String[] header, boolean[] editable) {
        table.setModel(new javax.swing.table.DefaultTableModel(
                data,
                header
        ) {
            boolean[] canEdit = editable;

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }
     
     /**
     * create a new table matrix with empty fields
     *
     * @param columns
     * @param rows
     * @return data
     */
    public Object[][] CreateEmptyData(int columns, int rows) {
        Object[][] data = new Object[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = null;
            }
        }
        return data;
    }
}
