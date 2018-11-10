/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskscheduler;

/**
 *
 * @author FranM
 */
public class Requirements implements Comparable {
    String process;
    Integer track;

    public Requirements(String process, Integer track) {
        this.process = process;
        this.track = track;
    }

    public String getProcess() {
        return process;
    }

    public Integer getTrack() {
        return track;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "Requirements{" + "process=" + process + ", track=" + track + '}';
    }

    @Override
    public int compareTo(Object o) {
        int track = ((Requirements)o).getTrack();
        return this.getTrack()-track;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
