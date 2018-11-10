/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskscheduler;


import org.knowm.xchart.CSVImporter;
import org.knowm.xchart.CSVImporter.DataOrientation;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 *
 * @author FranM
 */
public class DiskScheduler {

     public static void main(String[] args) throws Exception {
 
        XYChart chart = CSVImporter.getChartFromCSVDir("C:/Users/FranM/OneDrive/Documentos/Git Repositorios/SO-Proyecto_3_DiskScheduler/DiskScheduler/", DataOrientation.Rows, 600, 400);

    // Show it
    new SwingWrapper(chart).displayChart();
        
        Interface interfaz = new Interface();
        interfaz.setVisible(true);
    }
    
}
