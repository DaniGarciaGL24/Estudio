/* Processor.java */
/**
 ** Hecho por: Juan Perez
 ** Carnet: 14007891
 ** Seccion: A
**/

package scheduler;

import scheduler.processing.SimpleProcess;
import scheduler.scheduling.policies.Policy;

/**
 * Clase que simula un procesador atendiendo procesos.
 */
public class Processor implements Runnable {
    private Policy policy;
    private int processedCount = 0;
    private long totalTimeProcessed = 0;

    public Processor(Policy policy) {
        this.policy = policy;
    }

    @Override
    public void run() {
        while (true) {
            SimpleProcess process = policy.next();
            if (process != null) {
                try {
                    System.out.println("Processing: " + process);
                    long startTime = System.currentTimeMillis();
                    Thread.sleep((long) process.getServiceTime() * 1000); // Simulación del tiempo de servicio
                    long endTime = System.currentTimeMillis();
                    System.out.println("Completed: " + process);
                    policy.remove();
                    processedCount++;
                    totalTimeProcessed += (endTime - startTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getProcessedCount() {
        return processedCount;
    }

    public long getTotalTimeProcessed() {
        return totalTimeProcessed;
    }
}
