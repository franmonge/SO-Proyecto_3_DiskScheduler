/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskscheduler;

import java.util.ArrayList;

/**
 *
 * @author FranM
 */
public class Algorithms {
    public void algorithmFIFO(ArrayList<Requirements> listFIFO){
        ArrayList<Process> processes = Controller.getInstance().getProcesses();
        Process olderProcess = null;
        
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
    
    public void algorithmLIFO(ArrayList<Requirements> listLIFO){
        ArrayList<Process> processes = Controller.getInstance().getProcesses();
        Process newestProcess = null;
        
        for(int i = 0; i <= processes.size(); i++){
            newestProcess = Controller.getInstance().getNewestProcess(processes);
            System.out.println("newest: " + newestProcess.getName());
            System.out.println("listFifo size: " + listLIFO.size());
            System.out.println(listLIFO.toString());
            
            for(int j = 0; j < listLIFO.size(); j++){
                //System.out.println("entra for de listFIFO");
                if(listLIFO.get(j).getProcess().equals(newestProcess.getName())){
                    System.out.println(listLIFO.get(j).toString());                    
                    listLIFO.remove(j);
                    j--;
                }            
            }
            
            processes.remove(newestProcess);
            if(processes.size() != 0)
                i--;
            //System.out.println("size del i:" + i);
            //System.out.println("process size: " + processes.size());
        } 
    }
    
    public void algorithmRSS(){
    
    }
    
    public void algorithmPRI(){
    
    }
}
