/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskscheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

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
    
    public ArrayList<Requirements> algorithmPRI(ArrayList<Requirements> listPRI){
        ArrayList<Process> processes = new ArrayList<>(Controller.getInstance().getProcesses());
        ArrayList<Requirements> requirements = new ArrayList<>();
        Process priorityProcess = null;
        
        for(int i = 0; i <= processes.size(); i++){
            priorityProcess = Controller.getInstance().getHighestPriorityProcess(processes);
            
            for(int j = 0; j < listPRI.size(); j++){
                if(listPRI.get(j).getProcess().equals(priorityProcess.getName())){
                    requirements.add(listPRI.get(j));                    
                    listPRI.remove(j);
                    j--;
                }            
            }
            processes.remove(priorityProcess);
            if(processes.size() != 0)
                i--;
        } 
        return requirements;
    }
    
    public ArrayList<Requirements> algorithmRSS(ArrayList<Requirements> listRSS){
        ArrayList<Process> processes = new ArrayList<>(Controller.getInstance().getProcesses());
        ArrayList<Requirements> requirements = new ArrayList<>();
        
        for(int i = 0; i <= processes.size(); i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, processes.size());
            
            for(int j = 0; j < listRSS.size(); j++){
                if(listRSS.get(j).getProcess().equals(processes.get(randomNum).getName())){
                    requirements.add(listRSS.get(j));                    
                    listRSS.remove(j);
                    j--;
                }            
            }
            processes.remove(randomNum);
            if(processes.size() != 0)
                i--;
        } 
        return requirements;
    }
    
    public void algorithmPRI(){
    
    }
    
    // ---------- Based on the requested ----------
    
    
    public ArrayList<String> getSequenceByFIFO2(ArrayList<String> requirements){
        ArrayList<String> res = new ArrayList<String>();
        for(int i = 0; i < requirements.size(); i++){
            String nextTrack = getNextByFIFO2(requirements);
            res.add(nextTrack);
            requirements.remove(nextTrack);
        }
        return res;
    }
    
    public String getNextByFIFO2(ArrayList<String> requirements){
        return requirements.get(0);
    }
    
    public ArrayList<String> getSequenceBySSTF(ArrayList<String> requirements, Integer initialPos){
        Collections.sort(requirements);
        ArrayList<String> res = new ArrayList<String>();
        Integer currentPos = initialPos;
        for(int i = 0; i < requirements.size(); i++){
            String nextTrack = getNextBySSTF(requirements, currentPos);
            currentPos = Integer.parseInt(nextTrack);
            res.add(nextTrack);
            requirements.remove(nextTrack);
        }
        return res;
    }
    
    private String getNextBySSTF(ArrayList<String> requirements, Integer currentPosition){
        Collections.sort(requirements);
        Integer closest = currentPosition+10000; // badass code
        
        for(String track: requirements){
            if(Math.abs(currentPosition-Integer.parseInt(track)) < Math.abs(currentPosition - closest))
                closest = Integer.parseInt(track);
        }
        return String.valueOf(closest);   
    }
    
    public ArrayList<String> getSequenceBySCAN(ArrayList<String> requirements, Integer initialPos){
        Collections.sort(requirements);
        ArrayList<String> res = new ArrayList<String>();
        Integer currentPos = initialPos;
        for(int i = 0; i < requirements.size(); i++){
            String nextTrack;
            if (isThereANextValue(requirements, currentPos, true)){
                nextTrack = getNextBySCAN(requirements, currentPos, true);
            }else{
                nextTrack = getNextBySCAN(requirements, currentPos, false);
            }
            currentPos = Integer.parseInt(nextTrack);
            res.add(nextTrack);
            requirements.remove(nextTrack);
        }
        return res;
    }
    
    private String getNextBySCAN(ArrayList<String> requirements, Integer currentPos, boolean upward){
        Collections.sort(requirements);
        Integer nextVal = 0;
        if(upward){
            for(String track: requirements){
                if(Integer.parseInt(track) >= currentPos){
                    nextVal = Integer.parseInt(track);
                    break;
                }
            }
        }else{
            Collections.reverse(requirements);
            for(String track: requirements){
                if(Integer.parseInt(track) <= currentPos){
                    nextVal = Integer.parseInt(track);
                    break;
                }
            }
        }
        
        return String.valueOf(nextVal);
    }
    
    private boolean isThereANextValue(ArrayList<String> requirements, Integer currentPos, boolean upward){
        boolean res = false;
        for(String track: requirements){
            if (upward){
                if(Integer.valueOf(track) >= currentPos)
                    res = true;
            }else{
                if(Integer.valueOf(track) <= currentPos)
                    res = true;
            }
        }
        return res;
    }
    
    public ArrayList<String> getSequenceByC_SCAN(ArrayList<String> requirements, Integer initialPos){
        Collections.sort(requirements);
        ArrayList<String> res = new ArrayList<String>();
        Integer currentPos = initialPos;
        
        for(int i = 0; i < requirements.size(); i++){
            String nextTrack = getNextByC_SCAN(requirements, currentPos);
            currentPos = Integer.parseInt(nextTrack);
            res.add(nextTrack);
            requirements.remove(nextTrack);
        }
        return res;
    }
    
    private String getNextByC_SCAN(ArrayList<String> requirements, Integer currentPosition){
        Collections.sort(requirements);
        Integer next = currentPosition+10000;
        if (!isThereANextValue(requirements, currentPosition, true)){
            next = Integer.parseInt(requirements.get(0));
        }else{
            for(String track: requirements){
                if(Integer.parseInt(track) >= currentPosition){
                    next = Integer.parseInt(track);
                    break;
                }
            }
        }
        return String.valueOf(next);
    }
    
    public ArrayList<String> getSequenceByN_STEP_SCAN(ArrayList<String> requirements, Integer initialPos, Integer batchSize){
        Collections.sort(requirements);
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<String> batch = new ArrayList<String>();
        Integer currentPos = initialPos;
        Integer realIndex= 0;
                
        for(int i = 0; i < requirements.size(); i+= batchSize){
            batch.clear();
            for (int j = 0; j< batchSize; j++){
                //realIndex = i*batchSize + j;
                
                batch.add(requirements.get(0));//realIndex))
                requirements.remove(0);
            }
            res.addAll(getSequenceBySCAN(batch, currentPos));
            currentPos = Integer.parseInt(res.get(res.size()-1));
        }
        
        return res;
    }
    
    public ArrayList<String> getSequenceByFSCAN(ArrayList<String> requirements, Integer initialPos){
        return null;
    }
    
    private String getNextByFSCAN(){
        return null;
    }
    
    
}
