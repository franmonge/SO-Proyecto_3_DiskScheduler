/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskscheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.knowm.xchart.CSVImporter;
import org.knowm.xchart.CSVImporter.DataOrientation;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 *
 * @author aleandro
 */
public class CSVHandler {
    
    public CSVHandler(){
        new File(System.getProperty("user.dir")+"/CSVS").mkdir();
    }
    
    public void createCSV(ArrayList<Requirements> requirements, Integer initialPosition, String algorithmName) throws IOException{
        FileWriter writer = new FileWriter(System.getProperty("user.dir")+ "/CSVS/" +algorithmName+ ".csv"); 
        //writer.write("0,"+String.valueOf(initialPosition)+"\n");
        System.out.println("Requirements size: " + String.valueOf(requirements.size()));
        
        for(int i = 0; i < requirements.size(); i++){
            if(i == 0)
                writer.write(String.valueOf(i));
            else
                writer.write(","+String.valueOf(i));
        } writer.write("\n");
        
        for(int i =0 ; i < requirements.size(); i++){
            System.out.println("LALALALA");
            if(i == 0)
                writer.write(String.valueOf(requirements.get(i).getTrack()));
            else
                writer.write(","+String.valueOf(requirements.get(i).getTrack()));
        }
        
        writer.close();
   }
    
   public void generatePlot()throws Exception{
       //System.out.println("vamo'a generar el plot twist :v");
       //XYChart chart = CSVImporter.getChartFromCSVDir(System.getProperty("user.dir")+"/CSVS/", DataOrientation.Rows, 600, 400);
       //System.out.println("se cayo? no? si?");
       //new SwingWrapper(chart).displayChart();
       
       XYChart chart = CSVImporter.getChartFromCSVDir(System.getProperty("user.dir")+ "/CSVS/", DataOrientation.Rows, 600, 400);
       new SwingWrapper(chart).displayChart();
   }
    
}
