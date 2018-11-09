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
public class DiskScheduler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        ArrayList<Integer> main = new ArrayList<>();
        main.add(1);
        main.add(2);
        main.add(3);
        System.out.println("main: " + main.size());
//        ArrayList<Integer> copia = new ArrayList<Integer>();
//        copia = main;
        ArrayList<Integer> newList = new ArrayList<>(main);
        newList.remove(0);
        System.out.println("copia: " + newList.size());
        System.out.println("main: " + main.size());
        
        Interface interfaz = new Interface();
        interfaz.setVisible(true);
    }
    
}
