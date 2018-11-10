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
    
    
    public ArrayList<Requirements> getSequenceByFIFO2(ArrayList<Requirements> requirementsFIFO2){
        System.out.println("Actual requirements size inside FIFO2: " + String.valueOf(requirementsFIFO2.size()));
        ArrayList<Requirements> requirements = new ArrayList<Requirements>();
        for(Requirements req: requirementsFIFO2){
            requirements.add(req);
        }
        ArrayList<Requirements> res = new ArrayList<Requirements>();
        for(int i = 0; i < requirementsFIFO2.size(); i++){
            Requirements nextTrack = getNextByFIFO2(requirements);
            res.add(nextTrack);
            requirements.remove(nextTrack);
            System.out.println("I'm at iteration: " + String.valueOf(i));
        }
        System.out.println("Actual requirements size about to be delivered:" + String.valueOf(requirements.size()));
        return res;
    }
    
    public Requirements getNextByFIFO2(ArrayList<Requirements> requirements){
        return requirements.get(0);
    }
    
    public ArrayList<Requirements> getSequenceBySSTF(ArrayList<Requirements> requirements, Integer initialPos){
        Collections.sort(requirements);
        ArrayList<Requirements> res = new ArrayList<Requirements>();
        Integer currentPos = initialPos;
        for(int i = 0; i < requirements.size(); i++){
            Requirements nextTrack = getNextBySSTF(requirements, currentPos);
            currentPos = nextTrack.getTrack();
            res.add(nextTrack);
            requirements.remove(nextTrack);
        }
        return res;
    }
    
    private Requirements getNextBySSTF(ArrayList<Requirements> requirements, Integer currentPosition){ //TODO
        Collections.sort(requirements);
        Requirements closest = requirements.get(0); // badass code
        
        for(Requirements req: requirements){
            if(Math.abs(currentPosition-req.getTrack()) < Math.abs(currentPosition - closest.getTrack()))
                closest = req;
        }
        return closest;   
    }
    
    public ArrayList<Requirements> getSequenceBySCAN(ArrayList<Requirements> requirements, Integer initialPos){
        Collections.sort(requirements);
        ArrayList<Requirements> res = new ArrayList<Requirements>();
        Integer currentPos = initialPos;
        for(int i = 0; i < requirements.size(); i++){
            Requirements nextTrack;
            if (isThereANextValue(requirements, currentPos, true)){
                nextTrack = getNextBySCAN(requirements, currentPos, true);
            }else{
                nextTrack = getNextBySCAN(requirements, currentPos, false);
            }
            currentPos = nextTrack.getTrack();
            res.add(nextTrack);
            requirements.remove(nextTrack);
        }
        return res;
    }
    
    private Requirements getNextBySCAN(ArrayList<Requirements> requirements, Integer currentPos, boolean upward){
        Collections.sort(requirements);
        Requirements nextTrack = null;
        if(upward){
            for(Requirements track: requirements){
                if(track.getTrack() >= currentPos){
                    nextTrack = track;
                    break;
                }
            }
        }else{
            Collections.reverse(requirements);
            for(Requirements track: requirements){
                if(track.getTrack() <= currentPos){
                    nextTrack = track;
                    break;
                }
            }
        }
        
        return nextTrack;
    }
    
    private boolean isThereANextValue(ArrayList<Requirements> requirements, Integer currentPos, boolean upward){
        boolean res = false;
        for(Requirements track: requirements){
            if (upward){
                if(track.getTrack() >= currentPos)
                    res = true;
            }else{
                if(track.getTrack() <= currentPos)
                    res = true;
            }
        }
        return res;
    }
    
    public ArrayList<Requirements> getSequenceByC_SCAN(ArrayList<Requirements> requirements, Integer initialPos){
        Collections.sort(requirements);
        ArrayList<Requirements> res = new ArrayList<Requirements>();
        Integer currentPos = initialPos;
        
        for(int i = 0; i < requirements.size(); i++){
            Requirements nextTrack = getNextByC_SCAN(requirements, currentPos);
            currentPos = nextTrack.getTrack();
            res.add(nextTrack);
            requirements.remove(nextTrack);
        }
        return res;
    }
    
    private Requirements getNextByC_SCAN(ArrayList<Requirements> requirements, Integer currentPosition){
        Collections.sort(requirements);
        Requirements next = null;
        if (!isThereANextValue(requirements, currentPosition, true)){
            next = requirements.get(0);
        }else{
            for(Requirements track: requirements){
                if(track.getTrack() >= currentPosition){
                    next = track;
                    break;
                }
            }
        }
        return next;
    }
    
    public ArrayList<Requirements> getSequenceByN_STEP_SCAN(ArrayList<Requirements> requirements, Integer initialPos, Integer batchSize){
        Collections.sort(requirements);
        ArrayList<Requirements> res = new ArrayList<Requirements>();
        ArrayList<Requirements> batch = new ArrayList<Requirements>();
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
            currentPos = res.get(res.size()-1).getTrack();
        }
        
        return res;
    }
    
    public ArrayList<Requirements> getSequenceByFSCAN(ArrayList<Requirements> requirements, Integer initialPos){
        return null;
    }
    
    private Requirements getNextByFSCAN(){
        return null;
    }
    
    
}
