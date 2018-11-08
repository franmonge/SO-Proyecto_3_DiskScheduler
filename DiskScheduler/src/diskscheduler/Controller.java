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

    public Controller() {
        processes = new ArrayList<>();
    }
    
    public static Controller getInstance(){
        if(instance == null)
            instance = new Controller();
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
    
    public void setConfiguration(Integer totalTracks, Integer initialPosition){
        config = new Configuration(totalTracks, initialPosition);
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
