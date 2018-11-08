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
public class Process {
    String name;
    Integer priority;
    Integer timestamp;

    public Process(String name, Integer priority, Integer timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Process{" + "name=" + name + ", priority=" + priority + ", timestamp=" + timestamp + '}';
    }
        
}
