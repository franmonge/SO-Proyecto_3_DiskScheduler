/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskscheduler;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author FranM
 */
public class Algorithms {
    public void algorithmFIFO(ArrayList<Requirements> listFIFO){
        ArrayList<Process> processes = Controller.getInstance().getProcesses();
        Process olderProcess = null;
        System.out.println("process size: " + processes.size());
        
        for(int i = 0; i <= processes.size(); i++){
            olderProcess = Controller.getInstance().getOlderProcess(processes);
            System.out.println("older: " + olderProcess.getName());
            System.out.println("listFifo size: " + listFIFO.size());
            for(int j = 0; j < listFIFO.size(); j++){
                System.out.println("entra for de listFIFO");
                if(listFIFO.get(j).getProcess().equals(olderProcess.getName())){
                    System.out.println(listFIFO.get(j).toString());
                    System.out.println(listFIFO.toString());
                    listFIFO.remove(j);
                    j--;
                    System.out.println("listFifo size: " + listFIFO.size());
                }            
            }
            
            processes.remove(olderProcess);
            if(processes.size() != 0)
                i--;
            System.out.println("size del i:" + i);
            System.out.println("process size: " + processes.size());
        }     
    }
    
    public void algorithmLIFO(){
    
    }
    
    public void algorithmRSS(){
    
    }
    
    public void algorithmPRI(){
    
    }
}
