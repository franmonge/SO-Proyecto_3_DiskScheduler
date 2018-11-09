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
    public ArrayList<Requirements> algorithmFIFO(ArrayList<Requirements> listFIFO){
        ArrayList<Process> processes = new ArrayList<>(Controller.getInstance().getProcesses());
        ArrayList<Requirements> requirements = new ArrayList<>();
        Process olderProcess = null;
        
        for(int i = 0; i <= processes.size(); i++){
            olderProcess = Controller.getInstance().getOlderProcess(processes);
            for(int j = 0; j < listFIFO.size(); j++){
                if(listFIFO.get(j).getProcess().equals(olderProcess.getName())){
                    requirements.add(listFIFO.get(j));
                    listFIFO.remove(j);
                    j--;
                }            
            }
            processes.remove(olderProcess);
            if(processes.size() != 0)
                i--;
        }
        return requirements;
    }
    
    public ArrayList<Requirements> algorithmLIFO(ArrayList<Requirements> listLIFO){
        ArrayList<Process> processes = new ArrayList<>(Controller.getInstance().getProcesses());
        ArrayList<Requirements> requirements = new ArrayList<>();
        Process newestProcess = null;
        
        for(int i = 0; i <= processes.size(); i++){
            newestProcess = Controller.getInstance().getNewestProcess(processes);
            
            for(int j = 0; j < listLIFO.size(); j++){
                if(listLIFO.get(j).getProcess().equals(newestProcess.getName())){
                    requirements.add(listLIFO.get(j));                    
                    listLIFO.remove(j);
                    j--;
                }            
            }
            processes.remove(newestProcess);
            if(processes.size() != 0)
                i--;
        } 
        return requirements;
    }
    
    public void algorithmRSS(){
    
    }
    
    public void algorithmPRI(){
    
    }
}
