/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskscheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author aleandro
 */
public class CSVHandler {
    
    public void createCSV(ArrayList<String> requirements, Integer initialPosition, String algorithmName) throws IOException{
        FileWriter writer = new FileWriter(System.getProperty("user.dir")+ "/" +algorithmName+ ".csv"); 
        writer.write("0,"+String.valueOf(initialPosition)+"\n");
        for(int i =1 ; i <= requirements.size(); i++){
            writer.write(String.valueOf(i) + ","+requirements.get(i)+"\n");
        }
        
        writer.close();
   }
    
}
