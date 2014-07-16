package com.mycompany.webql;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryNotificationInfo;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.util.ArrayList;
import java.util.Collection;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;

/**
 *
 * @author Armen Arzumanyan
 */
public class MemoryWarningSystem {

    public interface Listener {

        void memoryUsageLow(long usedMemory, long maxMemory);
    }
    private final Collection<Listener> listeners = new ArrayList<Listener>();
    private static final MemoryPoolMXBean tenuredGenPool = findTenuredGenPool();

    public MemoryWarningSystem() {
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        NotificationEmitter emitter = (NotificationEmitter) mbean;
        emitter.addNotificationListener(new NotificationListener() {
            @Override
            public void handleNotification(Notification n, Object hb) {
              //  System.out.println("n.getType() " + n.getType());
                if (n.getType().equals(
                        MemoryNotificationInfo.MEMORY_THRESHOLD_EXCEEDED)) {
                    long maxMemory = tenuredGenPool.getUsage().getMax();
                    long usedMemory = tenuredGenPool.getUsage().getUsed();
                    for (Listener listener : listeners) {
                       // System.out.println("Used memory " + usedMemory);
                       // System.out.println("maxMemory " + maxMemory);
                        listener.memoryUsageLow(usedMemory, maxMemory);
                    }
                }
            }
        }, null, null);
    }

    public boolean addListener(Listener listener) {
      //  System.out.println("Added listener");
        return listeners.add(listener);
    }

    public boolean removeListener(Listener listener) {
        return listeners.remove(listener);
    }

    public static void setPercentageUsageThreshold(double percentage) {
        if (percentage <= 0.0 || percentage > 1.0) {
            throw new IllegalArgumentException("Percentage not in range");
        }
        long maxMemory = tenuredGenPool.getUsage().getMax();
        long warningThreshold = (long) (maxMemory * percentage);
       // System.out.println("maxMemory | " + maxMemory);
        //System.out.println("warningThreshold | " + warningThreshold);

        tenuredGenPool.setUsageThreshold(warningThreshold);
    }

    /**
     * Tenured Space Pool can be determined by it being of type HEAP and by it
     * being possible to set the usage threshold.
     */
    private static MemoryPoolMXBean findTenuredGenPool() {
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            // I don't know whether this approach is better, or whether
            // we should rather check for the pool name "Tenured Gen"?
           // System.out.println("pool.getType() | " + pool.getType());
            if (pool.getType() == MemoryType.HEAP
                    && pool.isUsageThresholdSupported()) {


                return pool;
            }
        }
        throw new IllegalStateException("Could not find tenured space");
    }
}
