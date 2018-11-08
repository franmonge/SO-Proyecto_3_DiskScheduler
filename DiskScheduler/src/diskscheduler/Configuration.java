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
public class Configuration {
    Integer totalTracks;
    Integer initialPosition;

    public Configuration() {
    }

    public Configuration(Integer totalTracks, Integer initialPosition) {
        this.totalTracks = totalTracks;
        this.initialPosition = initialPosition;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    public Integer getInitialPosition() {
        return initialPosition;
    }

    public void setTotalTracks(Integer totalTracks) {
        this.totalTracks = totalTracks;
    }

    public void setInitialPosition(Integer initialPosition) {
        this.initialPosition = initialPosition;
    }

    @Override
    public String toString() {
        return "Configuration{" + "totalTracks=" + totalTracks + ", initialPosition=" + initialPosition + '}';
    }
    
    
}
