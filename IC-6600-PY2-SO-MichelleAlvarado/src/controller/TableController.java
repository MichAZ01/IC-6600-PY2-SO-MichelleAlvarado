/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JTable;

/**
 *
 * @author Michelle Alvarado
 */
public class TableController {
     public TableController(){
         
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
}
