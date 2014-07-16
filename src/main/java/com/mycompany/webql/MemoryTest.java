/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webql;

import com.mycompany.webql.MemoryWarningSystem.Listener;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Armen Arzumanyan
 */
public class MemoryTest {

    public static void main(String[] args) {
        boolean check = true;
        MemoryWarningSystem system = new MemoryWarningSystem();
        MemoryWarningSystem.setPercentageUsageThreshold(0.8d);


        system.addListener(new Listener() {
            @Override
            public void memoryUsageLow(long usedMemory, long maxMemory) {
                long MB = 1048576L;
                System.out.println("low:MEMORY " + usedMemory / MB + " MB " + " / " + maxMemory / MB + " MB ");
                System.gc();
                System.out.println("low:after1 " + usedMemory / MB + " MB " + " / " + maxMemory / MB + " MB ");
                try {
                    Thread.sleep(1000);
                    System.gc();
                    System.out.println("low:after " + usedMemory + " / " + maxMemory);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MemoryTest.class.getName()).log(Level.SEVERE, null, ex);
                }




            }
        });


//    MemoryWarningSystem.setPercentageUsageThreshold(0.6);
//
//    MemoryWarningSystem mws = new MemoryWarningSystem();
//    mws.addListener(new MemoryWarningSystem.Listener() {
//      @Override
//      public void memoryUsageLow(long usedMemory, long maxMemory) {
//        System.out.println("Memory usage low!!!");
//        double percentageUsed = ((double) usedMemory) / maxMemory;
//        System.out.println("percentageUsed = " + percentageUsed);
//        MemoryWarningSystem.setPercentageUsageThreshold(0.8D);
//      }
//    });

        try {
            Collection<Double> numbers = new LinkedList<Double>();
            int x = 25000000;

            while (check) {
                --x;
                //  System.out.println("Add memory");
                numbers.add(Math.random());

                if (x == 1) {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
            check = false;
        }
    }
}
