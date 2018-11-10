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
public class Controller {
    private static Controller instance = null;
    private Configuration config;
    private ArrayList<Process> processes;
    private Integer currentPosition;
    private Integer currentIndex;
    public static ArrayList<Requirements> PETITIONS;

    public Controller() {
        processes = new ArrayList<>();
    }
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
            PETITIONS = new ArrayList<Requirements>();
        }
        return instance;
    }
    
    public void resetSystem(){
        config = null;
        processes.clear();
    }
    
    public Process getProcess(String processID){
        for(Process proceso: processes){
            if (processID.equals(proceso.getName()))
                return proceso;
        }
        return null;
    }
    
    public Process getOlderProcess(ArrayList<Process> processes){
        Process olderProcess = null;
        if(processes.size()>0){
            olderProcess = processes.get(0);
            for(Process process: processes){
                if(process.getTimestamp() < olderProcess.getTimestamp())
                    olderProcess = process;
            }
        }        
        return olderProcess;
    }
    
    public Process getNewestProcess(ArrayList<Process> processes){
        Process newestProcess = null;
        if(processes.size()>0){
            newestProcess = processes.get(0);
            for(Process process: processes){
                if(process.getTimestamp() > newestProcess.getTimestamp())
                    newestProcess = process;
            }
        }        
        return newestProcess;
    }
    
    public Process getHighestPriorityProcess(ArrayList<Process> processes){
        Process priorityProcess = null;
        if(processes.size()>0){
            priorityProcess = processes.get(0);
            for(Process process: processes){
                if(process.getPriority() > priorityProcess.getPriority())
                    priorityProcess = process;
            }
        }        
        return priorityProcess;
    }
        
    public void setConfiguration(Integer totalTracks, Integer initialPosition){
        config = new Configuration(totalTracks, initialPosition);
        this.currentPosition = initialPosition; // so we can move and know where we are
        this.currentIndex = 0;
    }
    
    public Configuration getConfiguration(){
        return config;
    }
    
    public void addProcess(Process process){
        processes.add(process);
    }
    
    public ArrayList<Process> getProcesses(){
        return processes;
    }
}
